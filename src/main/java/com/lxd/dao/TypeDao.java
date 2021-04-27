package com.lxd.dao;

import com.lxd.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Lxd
 * @date : 21:36 2021/4/5
 */
@Mapper
@Repository
public interface TypeDao {
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
