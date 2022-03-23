package com.demo.service;

import com.demo.entity.Article;
import com.demo.entity.Blogger;
import com.demo.entity.Tag;

import java.util.List;

public interface BloggerService {
    boolean checkBlogger(String account, String password);
    Blogger getBloggerByAccount(String account);
    List<Article> getArticleByAccount(String account);
    Article getReccentPostArticle(String account);
    boolean postArticle(String account, String title, String time, String content);
    List<Tag> getTagByAccount(String account);
    int getTagLinkCount(Tag tag);
    boolean addTag(Tag tag);
}
