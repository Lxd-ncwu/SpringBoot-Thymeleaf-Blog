package com.lxd.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author : Lxd
 * @date : 10:21 2021/4/10
 * 推荐实体类
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RecommendBlog {
    private Long id;
    private String title;
    private String firstPicture;
    private Boolean recommend;      //是否推荐
}
