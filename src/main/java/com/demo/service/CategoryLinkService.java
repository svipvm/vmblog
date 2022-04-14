package com.demo.service;

import com.demo.entity.CategoryLink;
import java.util.List;

/**
 * (CategoryLink)表服务接口
 *
 * @author vmice
 * @since 2022-04-04 18:59:53
 */
public interface CategoryLinkService {
    CategoryLink queryById(Integer id);
    List<CategoryLink> queryAllByLimit(int offset, int limit);
    CategoryLink insert(CategoryLink categoryLink);
    boolean deleteById(Integer id);

}