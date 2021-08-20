package com.macie.service.serviceImpl;

import com.macie.dao.ArticleDao;
import com.macie.dao.CategoryDao;
import com.macie.dao.TagDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Macie
 * @date 2020/10/23 -19:31
 */
@Service
public class AllCountService {
    @Autowired
    public ArticleDao articleDao;

    @Autowired
    public CategoryDao categoryDao;

    @Autowired
    public TagDao tagDao;

    /**
     * 获取概览文章,分类，标签数
     * @return
     */
    public Map<String, Long> getAllCount() {
        Map<String, Long> countMap = new HashMap<>();
        Long articlesTotalCount = articleDao.countAllArticles();
        Long categoriesTotalCount = categoryDao.countAllCategories();
        Long tagsTotalCount = tagDao.countAllTags();
        countMap.put("articlesTotalCount", articlesTotalCount);
        countMap.put("categoriesTotalCount", categoriesTotalCount);
        countMap.put("tagsTotalCount", tagsTotalCount);
        return countMap;
    }
}
