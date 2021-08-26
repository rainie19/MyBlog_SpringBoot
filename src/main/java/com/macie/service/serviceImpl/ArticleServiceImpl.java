package com.macie.service.serviceImpl;

import com.macie.common.ResponseCode;
import com.macie.dao.ArticleDao;
import com.macie.dao.CategoryDao;
import com.macie.dao.TagDao;
import com.macie.entity.Article;
import com.macie.entity.Category;
import com.macie.exception.BusinessException;
import com.macie.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Macie
 * @date 2020/10/29 -16:57
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Autowired
    public TagDao tagDao;

    @Autowired
    public CategoryDao categoryDao;

    @Override
    public ArrayList<Article> listArticles(String queryType, Integer queryPage, Integer pageSize, String queryName) {
        ArrayList<Article> Articles = null;

        // 检索所有文章或某个tag,category下的所有文章
        if(queryPage == 0) {
            if("category".equals(queryType) && queryName != null) {
                Articles = articleDao.listArticlesByCategoryName(queryName);
            }
            else if("tag".equals(queryType) && queryName != null) {
                Articles = articleDao.listArticlesByTagName(queryName);
            }
            else{
                Articles = articleDao.listAllArticles();
            }
        }
        // 检索某一页的文章或某个tag,category下的某一页的文章
        else {
            Integer limit = pageSize;
            Integer offset = pageSize * (queryPage - 1);
            if("category".equals(queryType) && queryName != null){
                Articles = articleDao.listArticlesByCategoryNamePerPage(queryName, limit, offset);
            }
            else if("tag".equals(queryType) && queryName != null){
                Articles = articleDao.listArticlesByTagNamePerPage(queryName, limit, offset);
            }
            else if("article".equals(queryType)){
                Articles = articleDao.listArticlesPerPage(limit, offset);
            }
        }
        return Articles;
    }

    @Override
    public Article getArticle(Integer articleId) {
        return articleDao.getArticleById(articleId);

    }

    @Override
    public Article getPreviousArticle(Integer articleId) {
        return articleDao.getPreviousArticle(articleId);
    }

    @Override
    public Article getNextArticle(Integer articleId) {
        return articleDao.getNextArticle(articleId);
    }

    @Override
    public Long countArticles(String queryType, String queryName) {
        Long totalCount;
        if("category".equals(queryType) && queryName != null){
            totalCount = articleDao.countAllArticles("category", queryName);
        }
        else if("tag".equals(queryType) && queryName != null){
            totalCount = articleDao.countAllArticles("tag", queryName);
        }else {
            totalCount = articleDao.countAllArticles();
        }
        return totalCount;
    }

    @Override
    public void updateArticleViewCount(Integer articleId) {
        articleDao.updateArticleViewCount(articleId);
    }

    @Override
    public void deleteArticle(@Min(value = 1) Integer articleId) {
        if(articleDao.deleteArticle(articleId) == 0) {
            throw new BusinessException(ResponseCode.ARTICLE_NOT_EXIST);
        }
        // 删除文章后需要把文章对应的标签也删掉
        tagDao.deleteTagsByArticleId(articleId);
    }

    @Override
    public Article publishArticle(Article article, List<String> tagList,  String publishType) {
        Integer articleId = null;
        // 若是创造了新的分类，先将此分类信息插入数据库中
        String categoryName = article.getCategoryName();
        if(!categoryDao.isCategoryExists(categoryName)) {
            Category category = new Category();
            category.setCategoryName(categoryName);
            categoryDao.insertNewCategory(category);
        }
        //TODO:publishType校验为指定值,insert和update分开
        if("create".equals(publishType)) {
            articleDao.insertNewArticle(article);
            articleId = article.getArticleId();
        }
        else if("modify".equals(publishType) && article.getArticleId() != null) {
            if(articleDao.updateArticle(article) == 0) {
                throw new BusinessException(ResponseCode.ARTICLE_NOT_EXIST);
            }
            articleId = article.getArticleId();
        }
        else {
            throw new BusinessException(ResponseCode.WRONG_ARTICLE_OPERATION);
        }
        // 插入文章的tags
        tagDao.deleteTagsByArticleId(articleId);
        for (String tag : tagList) {
            tagDao.insertTagByArticleId(articleId, tag);
        }
        //返回id和slug即可
        Article responseArticle = new Article();
        responseArticle.setArticleSlug(article.getArticleSlug());
        responseArticle.setArticleId(articleId);
        return responseArticle;
    }
}
