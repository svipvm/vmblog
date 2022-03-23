package com.demo.service.impl;

import com.demo.dao.ArticleMapper;
import com.demo.dao.BloggerMapper;
import com.demo.entity.Article;
import com.demo.entity.Blogger;
import com.demo.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("visitorService")
public class VisitorServiceImpl implements VisitorService {
    @Autowired
    private BloggerMapper bloggerMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public boolean existBlogger(String account) {
        Blogger blogger = bloggerMapper.selectByAccount(account);
        return blogger != null;
    }

    @Override
    public Blogger getBloggerByAccount(String account) {
        return bloggerMapper.selectByAccount(account);
    }

    @Override
    public List<Article> getAllArticleByAccount(String account) {
        return articleMapper.selectByAccount(account);
    }
}
