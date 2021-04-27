package com.lxd.service;

import com.lxd.pojo.Picture;

import java.util.List;

/**
 * @author : Lxd
 * @date : 20:29 2021/4/9
 */
public interface PictureService {
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
