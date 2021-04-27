package com.lxd.service;

import com.lxd.pojo.Comment;

import java.util.List;

/**
 * @author : Lxd
 * @date : 20:53 2021/4/11
 * 评论业务层接口
 */
public interface CommentService {
    //根据id查询评论信息
    List<Comment> listCommentByBlogId(Long blogId);

    //添加回复
    int saveComment(Comment comment);

    //删除回复
    void deleteComment(Long id , Comment comment);
}
