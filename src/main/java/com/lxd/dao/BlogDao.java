package com.lxd.dao;

import com.lxd.pojo.Blog;
import com.lxd.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * @author : Lxd
 * @date : 19:46 2021/4/7
 * 博客管理持久层
 */
@Repository
@Mapper
public interface BlogDao {
    //保存新增博客
    int saveBlog(Blog blog);

    //查询文章管理列表
    List<BlogQuery> findAllBlog();

    //删除博客
    int deleteBlog(Long id);

    //编辑博客
    int updateBlog(ShowBlog showBlog);

    //查询修改博客的文章
    ShowBlog getBlogById(Long id);


    //搜索博客 模糊查询
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

    //文章访问更新
    int updateViews(Long id);

    //根据博客id查询出评论数量
    int getCommentCountById(Long id);

    //根据TypeId查询博客显示在分类页面
    List<FirstPageBlog> getBlogByTypeId(Long typeId);

}
