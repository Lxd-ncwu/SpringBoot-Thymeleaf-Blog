package com.lxd.service;

import com.lxd.pojo.Type;

import java.util.List;

/**
 * @author : Lxd
 * @date : 21:58 2021/4/5
 */
public interface TypeService {
    //新增分类
    int saveType(Type type);

    //删除分类
    void deleteType(Long id);

    //获取分类
    Type getType(Long id);

    //查询所有分类
    List<Type> getAllType();

    //按名称查询分类
    Type getTypeByName(String name);

    //修改分类
    int updateType(Type type);

    //查询所有Type和该分类下的博客
    List<Type> getAllTypesAndBlog();
}
