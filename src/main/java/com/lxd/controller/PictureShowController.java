package com.lxd.controller;

import com.lxd.pojo.Picture;
import com.lxd.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author : Lxd
 * @date : 17:42 2021/4/13
 */
@Controller
public class PictureShowController {
    @Autowired
    private PictureService pictureService;
    @GetMapping("/picture")
    public String picture(Model model){
        List<Picture> pictures = pictureService.findAllPicture();
        model.addAttribute("pictures",pictures);
        return "picture";
    }
}
