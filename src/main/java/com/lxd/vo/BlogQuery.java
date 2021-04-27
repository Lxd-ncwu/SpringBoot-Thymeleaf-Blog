package com.lxd.vo;

import com.lxd.pojo.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author : Lxd
 * @date : 19:51 2021/4/7
 * 查询博客列表
 * 查询实体类
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BlogQuery {
    private Long id;
    private String title;
    private Date updateTime;
    private Boolean recommend;          //是否推荐
    private Boolean published;          //是否发表
    private Long typeId;
    private Type type;
}
