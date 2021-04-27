package com.lxd.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author : Lxd
 * @date : 10:15 2021/4/10
 * 博客首页信息实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FirstPageBlog {

    //博客信息
    private Long id;
    private String title;
    private String firstPicture;
    private Integer views;
    private Integer commentCount;
    private Date updateTime;
    private String description;
    private String createTime;

    //分类名称
    private String typeName;

    //用户名
    private String nickname;

    //头像
    private String avatar;
}
