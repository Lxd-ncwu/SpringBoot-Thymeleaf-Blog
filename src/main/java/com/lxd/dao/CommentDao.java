package com.lxd.dao;

import com.lxd.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author : Lxd
 * @date : 20:31 2021/4/11
 * 评论持久层接口
 */
@Repository
@Mapper
public interface CommentDao {
    //查询父级评论
    List<Comment> findByBlogIdParentIdNull(@Param("blogId") Long blogId, @Param("blogParentId") Long blogParentId);

    //查询一级回复
    List<Comment> findByBlogIdParentIdNotNull(@Param("blogId") Long blogId,@Param("id") Long id);

    //查询二级回复
    List<Comment> findByBlogIdAndReplyId(@Param("blogId") Long blogId , @Param("childId")Long childId);

    //添加评论
    int saveComment(Comment comment);

    //删除评论
    void deleteComment(Long id);
}
