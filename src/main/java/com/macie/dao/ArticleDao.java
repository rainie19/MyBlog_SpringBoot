package com.macie.dao;

import com.macie.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * @author Macie
 * @date 2020/9/25 -17:24
 */
@Mapper
public interface ArticleDao {
    /**
     *查询所有文章信息
     * @return 所有文章信息
     */
    public ArrayList<Article> listAllArticles();

    /**
     * 查询某一页的文章信息
     * @return
     */
    public ArrayList<Article> listArticlesPerPage(@Param("limit") Integer limit, @Param("offset") Integer offset);
    /**
     * 根据articl_id查询文章信息
     * @param articleId
     * @return 具体博客
     */
    public Article getArticleById(Integer articleId);

    /**
     * 增加一篇文章
     * @param Article
     * @return 返回增加文章的id
     */
    public int insertNewArticle(Article Article);

    /**
     * 修改文章内容
     * @param Article
     * @return
     */
    public int updateArticle(Article Article);

    /**
     * 根据id更新文章阅读次数
     * @param articleId
     * @return 返回更新结果
     */
    public int updateArticleViewCount(Integer articleId);

    /**
     *删除文章
     * @param articleId
     * @return
     */
    public int deleteArticle(Integer articleId);

    /**
     * 获取前一篇文章
     * @param articleId
     * @return
     */
    public Article getPreviousArticle(Integer articleId);

    /**
     * 查找下一篇文章
     * @param articleId
     * @return
     */
    public Article getNextArticle(Integer articleId);

    /**
     * 获取文章总数
     * @return
     */
    public Long countAllArticles();

    /**
     * 获取某个type下的所有文章数
     * @param type 分类或标签
     * @param value
     * @return
     */
    public Long countAllArticles(@Param("type") String type, @Param("value") String value);

    /**
     *获取某分类下的所有文章
     * @return 某分类下的所有文章
     */
    public ArrayList<Article> listArticlesByCategoryName(String categoryName);

    /**
     * 获取某分类下某一页的文章
     * @param categoryName
     * @param limit
     * @param offset
     * @return
     */
    public ArrayList<Article> listArticlesByCategoryNamePerPage(@Param("categoryName") String categoryName, @Param("limit") Integer limit, @Param("offset") Integer offset);

    /**
     *获取某标签下的所有文章
     * @return 某标签下的所有文章
     */
    public ArrayList<Article> listArticlesByTagName(String tagName);

    /**
     * 获取某标签下某一页的文章
     * @param tagName
     * @param limit
     * @param offset
     * @return
     */
    public ArrayList<Article> listArticlesByTagNamePerPage(@Param("tagName") String tagName, @Param("limit") Integer limit, @Param("offset") Integer offset);

}
