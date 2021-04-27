package com.lxd.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxd.pojo.Type;
import com.lxd.service.BlogService;
import com.lxd.service.TypeService;
import com.lxd.vo.FirstPageBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author : Lxd
 * @date : 15:10 2021/4/13
 * 分类页面
 */
@Controller
public class ShowTypeBlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id , @RequestParam(defaultValue = "1" , value = "pageNum") Integer pageNum , Model model){
        List<Type> types = typeService.getAllTypesAndBlog();

        //id为-1表示从首页进入的分类展示页面
        if (id == -1){
            id = types.get(0).getId();
        }
        List<FirstPageBlog> blogs = blogService.getBlogByTypeId(id);
        model.addAttribute("types",types);
        PageHelper.startPage(pageNum,10000);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTypeId",id);
        return "types";
    }
}
