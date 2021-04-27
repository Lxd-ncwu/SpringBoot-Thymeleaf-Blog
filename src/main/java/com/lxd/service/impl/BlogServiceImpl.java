package com.lxd.service.impl;

import com.lxd.NoFoundException;
import com.lxd.dao.BlogDao;
import com.lxd.pojo.Blog;
import com.lxd.service.BlogService;
import com.lxd.until.MarkDownUtils;
import com.lxd.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author : Lxd
 * @date : 19:49 2021/4/7
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;

    @Override
    public int save(Blog blog) {
        blog.setViews(0);
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setCommentCount(0);
        return blogDao.saveBlog(blog);
    }

    @Override
    public List<BlogQuery> findAllBlog() {
        return blogDao.findAllBlog();
    }

    @Override
    public int deleteBlog(Long id) {
        return blogDao.deleteBlog(id);
    }

    @Override
    public ShowBlog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    @Override
    public int updateBlog(ShowBlog showBlog) {
        showBlog.setUpdateTime(new Date());
        return blogDao.updateBlog(showBlog);
    }

    @Override
    public List<BlogQuery> findByTitleAndTypeId(SearchBlog searchBlog) {
        return blogDao.findByTitleAndTypeId(searchBlog);
    }

    @Override
    public List<FirstPageBlog> findAllFirstPageBlog() {
        return blogDao.findAllFirstPageBlog();
    }

    @Override
    public List<RecommendBlog> findAllRecommendBlog() {
        return blogDao.findAllRecommendBlog();
    }

    @Override
    public List<FirstPageBlog> searchBlog(String query) {
        return blogDao.searchBlog(query);
    }

    @Override
    public Integer getBlogTotal() {
        return blogDao.getBlogTotal();
    }

    @Override
    public Integer getBlogViewTotal() {
        return blogDao.getBlogViewTotal();
    }

    @Override
    public Integer getBlogCommentTotal() {
        return blogDao.getBlogCommentTotal();
    }

    @Override
    public Integer getBlogMessageTotal() {
        return blogDao.getBlogMessageTotal();
    }

    @Override
    public DetailedBlog getDetailBlog(Long id) {
        DetailedBlog detailedBlog = blogDao.getDetailBlog(id);
        if (detailedBlog == null){
            throw new NoFoundException("该博客不存在");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkDownUtils.markdownToHtmlExtensions(content));
        //文章访问数自增
        blogDao.updateViews(id);
        //文章评论数量更新
        blogDao.getCommentCountById(id);
        return detailedBlog;
    }

    @Override
    public List<FirstPageBlog> getBlogByTypeId(Long typeId) {
        return blogDao.getBlogByTypeId(typeId);
    }
}
