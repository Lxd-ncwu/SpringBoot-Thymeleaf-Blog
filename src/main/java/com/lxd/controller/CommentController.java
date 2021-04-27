package com.lxd.controller;

import com.lxd.pojo.Comment;
import com.lxd.pojo.User;
import com.lxd.service.BlogService;
import com.lxd.service.CommentService;
import com.lxd.vo.DetailedBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author : Lxd
 * @date : 15:10 2021/4/12
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    //查询评论列表
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog::commentList";
    }

    //新增评论
    @PostMapping("/comments")
    public String post(Model model, Comment comment, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Long blogId = comment.getBlogId();

        //判断是管理员还是游客  设置头像
        if (user.getId() == 1) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        } else {
            comment.setAvatar(avatar);
        }

        //如果有父评论 设置父评论的Id
        if (comment.getParentComment().getId() != null) {
            comment.setParentCommentId(comment.getParentComment().getId());
        }
        commentService.saveComment(comment);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog::commentList";
    }

    //删除评论
    @GetMapping("/comments/{blogId}/{id}/delete")
    public String delete(@PathVariable Long blogId, @PathVariable Long id, RedirectAttributes attributes, Model model, Comment comment) {
        commentService.deleteComment(id, comment);
        //删除以后要重新查询博客和评论
        DetailedBlog detailedBlog = blogService.getDetailBlog(blogId);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("blog", detailedBlog);
        model.addAttribute("comments", comments);
        return "blog";
    }
}