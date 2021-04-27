package com.lxd.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxd.service.BlogService;
import com.lxd.vo.DetailedBlog;
import com.lxd.vo.FirstPageBlog;
import com.lxd.vo.RecommendBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author : Lxd
 * @date : 15:30 2021/4/10
 * 首页控制器
 */
@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;

    //分类查询博客列表
    @GetMapping("/")
    public String index(Model model , @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum , RedirectAttributes attributes){
        PageHelper.startPage(pageNum,10);
        List<FirstPageBlog> allFirstPageBlog = blogService.findAllFirstPageBlog();
        List<RecommendBlog> allRecommendBlog = blogService.findAllRecommendBlog();
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(allFirstPageBlog);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("allRecommendBlog",allRecommendBlog);
        return "index";
    }

    //搜索博客
    @PostMapping("/search")
    public String search(Model model ,@RequestParam("query")String query ,  @RequestParam(defaultValue = "1" , value = "pageNum")Integer pageNum , RedirectAttributes attributes){
        PageHelper.startPage(pageNum,1000);
        List<FirstPageBlog> firstPageBlogs = blogService.searchBlog(query);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(firstPageBlogs);
        model.addAttribute("pageInfo",pageInfo);
        return "search";
    }

    //博客信息统计
    @GetMapping("/footer")
    public String blogMessage(Model model){
        Integer blogMessageTotal = blogService.getBlogMessageTotal();
        Integer blogCommentTotal = blogService.getBlogCommentTotal();
        Integer blogTotal = blogService.getBlogTotal();
        Integer blogViewTotal = blogService.getBlogViewTotal();
        model.addAttribute("blogMessageTotal",blogMessageTotal);
        model.addAttribute("blogCommentTotal",blogCommentTotal);
        model.addAttribute("blogTotal",blogTotal);
        model.addAttribute("blogViewTotal",blogViewTotal);
        return "commons::blogMessage";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id")Long id , Model model){
        DetailedBlog detailedBlog = blogService.getDetailBlog(id);
        model.addAttribute("blog",detailedBlog);
        return "blog";
    }
}
