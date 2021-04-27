package com.lxd.service;

import com.lxd.pojo.Blog;
import com.lxd.vo.*;

import java.util.List;

/**
 * @author : Lxd
 * @date : 19:48 2021/4/7
 */
public interface BlogService {
    int save(Blog blog);

    List<BlogQuery> findAllBlog();

    int deleteBlog(Long id);

    ShowBlog getBlogById(Long id);

    int updateBlog(ShowBlog showBlog);

    List<BlogQuery> findByTitleAndTypeId(SearchBlog searchBlog);

    //查询首页最新博客
    List<FirstPageBlog> findAllFirstPageBlog();

    //查询推推荐博客
    List<RecommendBlog> findAllRecommendBlog();

    //搜索博客列表
    List<FirstPageBlog> searchBlog(String query);

    //统计博客总数
    Integer getBlogTotal();

    //统计访问总数
    Integer getBlogViewTotal();

    //统计评论总数
    Integer getBlogCommentTotal();

    //统计留言总数
    Integer getBlogMessageTotal();

    //查询博客详情
    DetailedBlog getDetailBlog(Long id);

    //根据TypeId查询博客显示在分类页面
    List<FirstPageBlog> getBlogByTypeId(Long typeId);
}
