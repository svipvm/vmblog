package com.demo.service.impl;

import com.demo.entity.Blogger;
import com.demo.dao.BloggerDao;
import com.demo.service.BloggerService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Blogger)表服务实现类
 *
 * @author vmice
 * @since 2022-04-04 18:59:53
 */
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService {
    @Resource
    private BloggerDao bloggerDao;

    @Override
    public Blogger queryById(Integer id) {
        return this.bloggerDao.queryById(id);
    }

    @Override
    public List<Blogger> queryAllByLimit(int offset, int limit) {
        return this.bloggerDao.queryAllByLimit(offset, limit);
    }

    @Override
    public int insert(Blogger blogger) {
        try {
            this.bloggerDao.insert(blogger);
            return 1;
        } catch (Exception e) {
//            System.out.println(e.toString());
        }
        return -1;
    }

    @Override
    public boolean deleteById(Integer id) {
        return this.bloggerDao.deleteById(id) > 0;
    }

    @Override
    public boolean checkAccountValid(String account, String password, boolean remember) {
        Blogger blogger = this.bloggerDao.queryByAccount(account);
        String real_password = password + "&" + blogger.getSaSalt();
        real_password = DigestUtils.shaHex(real_password);
        return real_password.equals(blogger.getErPassword());
    }

    @Override
    public Blogger queryByAccount(String account) {
        return this.bloggerDao.queryByAccount(account);
    }
}