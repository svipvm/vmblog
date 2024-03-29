package com.demo.service;

import com.demo.entity.Article;

import java.util.Date;
import java.util.List;

/**
 * (Article)表服务接口
 *
 * @author vmice
 * @since 2022-04-04 18:59:53
 */
public interface ArticleService {
    Article queryById(Integer id);
    List<Article> queryAllByLimit(int offset, int limit);
    Article insert(Article article);
    boolean deleteById(Integer id);
    Article insertAndReturn(Article article);
    Article queryByAccoutAndLink(Integer account, String link);
    int update(Article article);
    int deleteByAccountAndLink(Integer id, String link);

    int addVisCount(Integer id);

    List<Article> queryAllByBloggerIdAndLimit(Integer bloggerId, int offset, int limit);

    int updatePostDateById(Integer id, Date postDate);

    int updateDatesById(Integer id, Date date);

    List<Article> queryByCount(int number);

    int queryCountByBloggerId(Integer bloggerId);

    Article queryCurrentPost(Integer bloggerId);
}