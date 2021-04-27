package com.lxd.service;

import com.lxd.pojo.Message;

import java.util.List;

/**
 * @author : Lxd
 * @date : 20:47 2021/4/14
 */
public interface MessageService {
    //查询留言列表
    List<Message> listMessage();

    //添加留言
    int saveMessage(Message message);

    //删除留言
    void deleteMessage(Long id);
}
