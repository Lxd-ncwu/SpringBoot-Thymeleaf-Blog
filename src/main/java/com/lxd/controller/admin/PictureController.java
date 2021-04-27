package com.lxd.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxd.pojo.Picture;
import com.lxd.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author : Lxd
 * @date : 20:34 2021/4/9
 */
@Controller
@RequestMapping("/admin")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    //查询照片列表
    @GetMapping("/pictures")
    public String pictures(Model model , @RequestParam(defaultValue = "1", value = "pageNum")Integer pageNum){
        PageHelper.startPage(pageNum,10);
        List<Picture> allPicture = pictureService.findAllPicture();
        PageInfo<Picture> pageInfo = new PageInfo<>(allPicture);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/pictures";
    }

    //跳转新增页面
    @GetMapping("/pictures/input")
    public String input(Model model){
        model.addAttribute("picture",new Picture());
        return "admin/pictures-input";
    }

    //新增照片
    @PostMapping("/pictures")
    public String post(Picture picture,RedirectAttributes attributes){
        int i = pictureService.insertPicture(picture);
        if (i == 0){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/pictures";
    }

    //跳转修改照片
    @GetMapping("/pictures/{id}/update")
    public String update(Model model, @PathVariable("id") Long id){
        model.addAttribute("picture",pictureService.findById(id));
        return "admin/pictures-input";
    }

    //修改照片
    @PostMapping("pictures/{id}")
    public String edit(@PathVariable("id") Long id , RedirectAttributes attributes , Picture picture){
        int i = pictureService.updatePicture(picture);
        if (i==0){
            attributes.addFlashAttribute("message","修改失败");
        }else{
            attributes.addFlashAttribute("message","修改成功");
        }
        return "redirect:/admin/pictures";
    }

    //删除照片
    @GetMapping("/pictures/{id}/delete")
    public String delete(@PathVariable("id")Long id , RedirectAttributes attributes){
        int i = pictureService.deletePicture(id);
        if (i==0){
            attributes.addFlashAttribute("message","删除失败");
        }else{
            attributes.addFlashAttribute("message","删除成功");
        }
        return "redirect:/admin/pictures";
    }
}
