package com.demo.dao;

import com.demo.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * (Category)表数据库访问层
 *
 * @author vmice
 * @since 2022-04-04 18:59:53
 */
@Mapper
public interface CategoryDao {
    // @Results(id = "CategoryResultMap", value = {
    //         @Result(property = "id", column = "id"),
    //         @Result(property = "bloggerId", column = "blogger_id"),
    //         @Result(property = "typeName", column = "type_name")
    // })

    @Select("select * from category where id=#{id}")
    Category queryById(Integer id);

    @Select("select * from category limit #{limit}, #{offset}")
    List<Category> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    @Insert("insert into category(blogger_id, type_name) " +
            "values(#{bloggerId}, #{typeName})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insert(Category category);

    @Delete("delete from category where id=#{id}")
    int deleteById(Integer id);

    @Select("select * from category where blogger_id=#{bloggerId}")
    List<Category> queryByBloggerId(@Param("bloggerId") Integer bloggerId);

    @Select("select * from category where blogger_id=#{bloggerId} and type_name=#{typeName}")
    Category queryByIdAndType(@Param("bloggerId") Integer bloggerId, @Param("typeName") String typeName);

    @Update("update category set type_name=#{typeName} where id=#{id}")
    int updateTypeNameById(@Param("id") Integer id, @Param("typeName") String typeName);

    @Select("select * from category where blogger_id=#{bloggerId} and type_name=#{typeName}")
    Category queryByBloggerIdAndTypeName(@Param("bloggerId") Integer bloggerId, @Param("typeName") String typeName);

    @Select("select count(*) from category where blogger_id=#{bloggerId} and parent_id=#{parentId}")
    int querySonCountByIds(@Param("bloggerId") Integer bloggerId, @Param("parentId") Integer parentId);

    @Update("update category set parent_id=#{parentId} where id=#{id}")
    int updateParentByIds(@Param("id") Integer id, @Param("parentId") Integer parentId);

    @Select("select * from category where blogger_id=#{bloggerId} and parent_id=#{parentId}")
    List<Category> queryByIds(@Param("bloggerId") Integer bloggerId, @Param("parentId") Integer parentId);
}