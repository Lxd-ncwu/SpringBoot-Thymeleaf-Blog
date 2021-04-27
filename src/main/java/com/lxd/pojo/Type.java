package com.lxd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Lxd
 * @date : 21:20 2021/4/5
 * 博客分类实体
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Type {
    private long id;
    private String name;
    List<Blog> blogs = new ArrayList<>();


}
