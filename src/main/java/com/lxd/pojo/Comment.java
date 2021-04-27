package com.lxd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : Lxd
 * @date : 21:20 2021/4/5
 * 评论实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {
    private Long id;
    private String nickname;
    private String email;
    private String content; //评论内容
    private String avatar;  //头像
    private Date createTime;
    private Long blogId;
    private Long parentCommentId;  //父评论id
    private boolean adminComment;   //是否为管理员评论

    //回复评论
    private List<Comment> replyComments = new ArrayList<>(); //回复评论合集
    private Comment parentComment;      //父评论
    private String parentNickname;         //父评论昵称
}