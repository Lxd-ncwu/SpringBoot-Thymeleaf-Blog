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
 * @date : 21:19 2021/4/5
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private Long id;                           //id
    private String title;                      //标题
    private String content;                    //博客内容
    private String firstPicture;               //首图地址
    private String flag;                       //是否原创
    private Integer views;                     //浏览次数
    private Integer commentCount;              //评论次数
    private boolean appreciation;              //是否开启赞赏
    private boolean shareStatement;            //是否开启版权
    private boolean commentabled;              //是否开启评论
    private boolean published;                 //是否发布
    private boolean recommend;                 //是否推荐
    private Date createTime;                   //创建时间
    private Date updateTime;                   //修改时间
    private String description;                //描述

    private Type type;                  //博客类型
    private User user;                  //用户
    private Long typeId;                //分类id
    private Long userId;                //用户id
    private List<Comment> comments = new ArrayList<>();  //评论合集
}
