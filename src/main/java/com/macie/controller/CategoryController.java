package com.macie.controller;

import com.macie.dto.JsonResponse;
import com.macie.entity.Category;
import com.macie.service.serviceImpl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Macie
 * @date 2021/10/9 -22:32
 */
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取所有分类
     *
     * @return
     */
    @RequestMapping("/getAllCategories")
    public JsonResponse getAllCategories() {
        ArrayList<Category> categories = categoryService.listAllCategories();
        Map<String, Long> articlesCount = null;
        if (categories != null) {
            articlesCount = categoryService.countArticlesEachCategory(categories);
        }
        Map<String, Object> map = new HashMap();
        map.put("categories", categories);
        map.put("articlesCount", articlesCount);
        return JsonResponse.responseOK(map);
    }

    /**
     * 删除分类
     *
     * @param categoryId
     * @return
     */
    @RequestMapping("/deleteCategory")
    public JsonResponse deleteCategory(@Min(value = 1) Integer categoryId) {
        //把要删除分类下的文章都移到默认分类中
        categoryService.updateCategory2Default(categoryId);
        categoryService.deleteCategoryById(categoryId);
        Map<String, Object> map = new HashMap();
        return JsonResponse.responseOK();
    }
}
