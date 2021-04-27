package com.lxd.dao;

import com.lxd.pojo.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Lxd
 * @date : 20:18 2021/4/9
 * 照片管理持久层
 */
@Mapper
@Repository
public interface PictureDao {
    //查询照片
    List<Picture> findAllPicture();

    //根据Id查询照片
    Picture findById(Long id);

    //修改照片
    int updatePicture(Picture picture);

    //增加照片
    int insertPicture(Picture picture);

    //删除照片
    int deletePicture(Long id);
}
