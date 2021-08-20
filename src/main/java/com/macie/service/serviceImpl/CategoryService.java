package com.macie.service.serviceImpl;

import com.macie.dao.ArticleDao;
import com.macie.dao.CategoryDao;
import com.macie.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Macie
 * @date 2020/9/29 -16:37
 */
@Service
@Validated
public class CategoryService {
    @Autowired
    public CategoryDao categoryDao;

    @Autowired
    public ArticleDao articleDao;

    public ArrayList<Category> listAllCategories() {
        return categoryDao.listAllCategories();
    }

    public Long countAllCategories() {
        return categoryDao.countAllCategories();
    }

    public int updateCategory2Default(@Min(value = 1) Integer categoryId) {
        return categoryDao.updateCategory2Default(categoryId);
    }

    public int deleteCategoryById(@Min(value = 1) Integer categoryId) {
        return categoryDao.deleteCategoryById(categoryId);
    }

    public int insertNewCategory(@Valid Category category) {
        return categoryDao.insertNewCategory(category);
    }

    public Map<String, Long> countArticlesEachCategory(@NotNull ArrayList<@Valid Category> categories) {
        Map<String, Long> countMap = new HashMap<>();
        for (Category category : categories) {
            String categoryName = category.getCategoryName();
            Long count = articleDao.countAllArticles("category", categoryName);
            countMap.put(categoryName, count);
        }
        return countMap;
    }

}
