package com.lxd.service.impl;

import com.lxd.dao.PictureDao;
import com.lxd.pojo.Picture;
import com.lxd.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Lxd
 * @date : 20:29 2021/4/9
 */
@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureDao pictureDao;
    @Override
    public List<Picture> findAllPicture() {
        return pictureDao.findAllPicture();
    }

    @Override
    public Picture findById(Long id) {
        return pictureDao.findById(id);
    }

    @Override
    public int updatePicture(Picture picture) {
        return pictureDao.updatePicture(picture);
    }

    @Override
    public int insertPicture(Picture picture) {
        return pictureDao.insertPicture(picture);
    }

    @Override
    public int deletePicture(Long id) {
        return pictureDao.deletePicture(id);
    }
}
