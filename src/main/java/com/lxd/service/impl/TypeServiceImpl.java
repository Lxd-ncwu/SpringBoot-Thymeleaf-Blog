package com.lxd.service.impl;

import com.lxd.dao.TypeDao;
import com.lxd.pojo.Type;
import com.lxd.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : Lxd
 * @date : 21:58 2021/4/5
 *
 */
//@Transactional注解：实现事务操作
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;
    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }
    @Transactional
    @Override
    public void deleteType(Long id) {
        typeDao.deleteType(id);
    }
    @Transactional
    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }
    @Transactional
    @Override
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }
    @Transactional
    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }
    @Transactional
    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    @Override
    public List<Type> getAllTypesAndBlog() {
        return typeDao.getAllTypesAndBlog();
    }
}
