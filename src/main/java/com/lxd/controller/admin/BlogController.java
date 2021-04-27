package com.lxd.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxd.pojo.Blog;
import com.lxd.pojo.Type;
import com.lxd.pojo.User;
import com.lxd.service.BlogService;
import com.lxd.service.TypeService;
import com.lxd.vo.BlogQuery;
import com.lxd.vo.SearchBlog;
import com.lxd.vo.ShowBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author : Lxd
 * @date : 19:49 2021/4/7
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    //跳转到添加博客的页面
    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("blog",new Blog());
        model.addAttribute("types",typeService.getAllType());
        return "admin/blogs-input";
    }

    //添加博客
    @PostMapping("/blogs")
    public String save(Blog blog , RedirectAttributes attributes , HttpSession session){
        //新增博客传递的blog对象应该有User
        blog.setUser((User) session.getAttribute("user"));
        //设置blog的类型
        blog.setType(typeService.getType(blog.getType().getId()));
        //设置Blog中的typeId
        blog.setTypeId(blog.getType().getId());
        //设置用户id
        blog.setUserId(blog.getUser().getId());

        int i = blogService.save(blog);
        if (i==0){
            attributes.addFlashAttribute("message","新增失败");
        }else{
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/blogs";
    }

    //分页
    @RequestMapping("/blogs")
    public String blogs(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum,10,orderBy);
        List<BlogQuery> list = blogService.findAllBlog();
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(list);
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs";
    }

    //删除
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable("id") Long id ,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/blogs";
    }

    //跳转到修改页面
    @GetMapping("/blogs/{id}/update")
    public String update(@PathVariable("id") Long id, Model model){
        ShowBlog showBlog = blogService.getBlogById(id);
        List<Type> allType = typeService.getAllType();
        model.addAttribute("blog",showBlog);
        model.addAttribute("types",allType);
        return "admin/blogs-input";
    }

    //编辑修改博客
    @PostMapping("/blogs/{id}")
    public String edit(@PathVariable("id") Long id,RedirectAttributes attributes ,  ShowBlog showBlog){
        int i = blogService.updateBlog(showBlog);
        if (i == 0){
            attributes.addFlashAttribute("message","编辑失败");
        }else{
            attributes.addFlashAttribute("message","编辑成功");
        }
        return "redirect:/admin/blogs";
    }

    //搜索
    @PostMapping("/blogs/search")
    public String search(SearchBlog searchBlog , Model model , @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){
        List<BlogQuery> listBlogSearch = blogService.findByTitleAndTypeId(searchBlog);
        PageHelper.startPage(pageNum,10);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(listBlogSearch);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs::blogList";
    }
}