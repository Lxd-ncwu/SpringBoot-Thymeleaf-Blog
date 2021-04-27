package com.lxd.service.impl;

import com.lxd.dao.BlogDao;
import com.lxd.dao.CommentDao;
import com.lxd.pojo.Comment;
import com.lxd.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : Lxd
 * @date : 20:56 2021/4/11
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private BlogDao blogDao;
    //存放迭代找出所有子评论的合集
    private List<Comment> allComment = new ArrayList<>();

    //查询所有评论
    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        //查询父节点
        List<Comment> comments = commentDao.findByBlogIdParentIdNull(blogId, Long.parseLong("-1"));
        for (Comment comment : comments) {
            Long id = comment.getId();
            String parentNickName = comment.getNickname();
            List<Comment> childComments = commentDao.findByBlogIdParentIdNotNull(blogId, id);
            combineChildren(blogId, childComments, parentNickName);
            comment.setReplyComments(allComment);
            allComment = new ArrayList<>();
        }
        return comments;
    }

    //查询子评论
    private void combineChildren(Long blogId, List<Comment> childComments, String parentNickName) {
        //判断是否有一级评论
        if (childComments.size() > 0) {
            //循环找出子评论的id
            for (Comment childComment : childComments) {
                String parentNickname1 = childComment.getNickname();
                childComment.setParentNickname(parentNickName);
                allComment.add(childComment);
                Long childId = childComment.getId();
                //查询二级评论
                recursively(blogId,childId,parentNickname1);
            }
        }
    }

    //循环迭代找出二级评论
    private void recursively(Long blogId, Long childId, String parentNickname1) {
        //根据子一级评论的id找到子二级评论
        List<Comment> replyComments = commentDao.findByBlogIdAndReplyId(blogId, childId);

        if (replyComments.size()>0){
            for (Comment replyComment : replyComments) {
                String parentNickname = replyComment.getNickname();
                replyComment.setParentNickname(parentNickname1);
                Long replyId = replyComment.getId();
                allComment.add(replyComment);
                recursively(blogId, replyId, parentNickname);
            }
        }
    }


    //新增评论
    @Override
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        int comments = commentDao.saveComment(comment);
        //文章评论数统计
        blogDao.getCommentCountById(comment.getBlogId());
        return comments;
    }

    @Override
    public void deleteComment(Long id, Comment comment) {
        commentDao.deleteComment(id);
        blogDao.getCommentCountById(comment.getId());
    }
}
