package com.lxd.dao;

import com.lxd.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Lxd
 * @date : 9:19 2021/4/14
 * 留言持久层
 */
@Mapper
@Repository
public interface MessageDao {
    //查询父级留言
    List<Message> findByParentIdNull(@Param("parentId") Long parentId);

    //查询一级回复
    List<Message> findByParentIdNotNull(@Param("id") Long id);

    //查询二级回复
    List<Message> findByReplyId(@Param("childId") Long childId);

    //添加留言
    int saveMessage(Message message);

    //删除留言
    void deleteMessage(Long id);
}
