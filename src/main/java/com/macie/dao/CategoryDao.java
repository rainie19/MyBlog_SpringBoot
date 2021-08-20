package com.macie.dao;

import com.macie.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * @author Macie
 * @date 2020/9/28 -16:29
 */
@Mapper
public interface CategoryDao {
    /**
     *
     * @return 所有分类
     */
    public ArrayList<Category> listAllCategories();


    /**
     * 获取分类总数
     * @return
     */
    public Long countAllCategories();

    /**
     * 将分类下的所有文章移到默认分类
     * @param categoryId
     * @return
     */
    public int updateCategory2Default(Integer categoryId);
    /**
     *
     * @param categoryId
     * @return
     */
    public int deleteCategoryById(Integer categoryId);

    /**
     *插入新分类
     * @param Category
     * @return
     */
    public int insertNewCategory(Category Category);

    /**
     * 查询分类是否存在
     * @param categoryName
     * @return
     */
    public Boolean isCategoryExists(String categoryName);
}
