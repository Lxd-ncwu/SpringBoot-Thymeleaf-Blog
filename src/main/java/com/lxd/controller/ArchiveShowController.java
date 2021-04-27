package com.lxd.controller;

import com.lxd.service.BlogService;
import com.lxd.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author : Lxd
 * @date : 17:27 2021/4/13
 * 时间轴
 */
@Controller
public class ArchiveShowController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archive(Model model){
        List<BlogQuery> allBlog = blogService.findAllBlog();
        model.addAttribute("blogs",allBlog);
        return "archives";
    }
}
