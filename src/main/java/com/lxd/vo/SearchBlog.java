package com.lxd.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author : Lxd
 * @date : 16:19 2021/4/8
 * 搜索博客
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SearchBlog {
    private String title;
    private Long typeId;
}
