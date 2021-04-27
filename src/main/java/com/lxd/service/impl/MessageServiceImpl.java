package com.lxd.service.impl;

import com.lxd.dao.MessageDao;
import com.lxd.pojo.Message;
import com.lxd.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : Lxd
 * @date : 20:49 2021/4/14
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    //存放所有的留言
    private List<Message> allMessage = new ArrayList<>();

    @Override
    public List<Message> listMessage() {
        //查询父节点
        List<Message> messages = messageDao.findByParentIdNull(Long.parseLong("-1"));
        for (Message message : messages) {
            Long id = message.getId();
            String parentNickname = message.getNickname();
            List<Message> childMessages = messageDao.findByParentIdNotNull(id);
            combineChildren(childMessages,parentNickname);
            message.setReplyMessages(allMessage);
            allMessage = new ArrayList<>();
        }
        return messages;
    }

    //查询子评论
    private void combineChildren(List<Message> childMessages, String parentNickname) {
        //判断是否有一级评论
        if (childMessages.size()>0){
            //找出子评论的Id
            for (Message childMessage : childMessages) {
                String parentNickname1 = childMessage.getNickname();
                childMessage.setParentNickname(parentNickname);
                allMessage.add(childMessage);
                Long childId = childMessage.getId();
                //查询二级评论
                recursively(childId,parentNickname1);
            }
        }
    }

    //递归找出二级评论
    private void recursively(Long childId, String parentNickname1) {
        //根据一级评论id找到二级评论
        List<Message> replyMessages = messageDao.findByReplyId(childId);
        if (replyMessages.size()>0){
            for (Message replyMessage : replyMessages) {
                String parentNickname = replyMessage.getNickname();
                replyMessage.setParentNickname(parentNickname1);
                Long replyId = replyMessage.getId();
                allMessage.add(replyMessage);
                recursively(replyId,parentNickname);
            }
        }
    }

    @Override
    public int saveMessage(Message message) {
        message.setCreateTime(new Date());
        return messageDao.saveMessage(message);
    }

    @Override
    public void deleteMessage(Long id) {
        messageDao.deleteMessage(id);
    }
}
