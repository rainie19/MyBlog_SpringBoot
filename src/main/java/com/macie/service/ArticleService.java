package com.macie.service;

import com.macie.entity.Article;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Macie
 * @date 2020/10/28 -16:55
 */
@Validated
public interface ArticleService {
    /**
     *
     * @param queryType 查询的类型
     * @param queryPage 查询的页码
     * @param pageSize
     * @param queryName 查询类型的具体名字
     * @return
     */
    public ArrayList<Article>  listArticles(String queryType, @Min(value = 0) Integer queryPage, @Min(value = 0) Integer pageSize, String queryName);

    /**
     * 获取一篇文章
     * @param articleId
     * @return
     */
    public Article getArticle(@Min(value = 1) Integer articleId);

    /**
     * 获取前一篇文章
     * @param articleId
     * @return
     */
    public Article getPreviousArticle(@Min(value = 1) Integer articleId);

    /**
     * 获取后一篇文章
     * @param articleId
     * @return
     */
    public Article getNextArticle(@Min(value = 1) Integer articleId);

    /**
     * 获取某个类型下的文章总数
     * @param queryType
     * @param queryName
     * @return
     */
    public Long countArticles(String queryType, String queryName);

    /**
     * 根据id更新文章阅读次数
     * @param articleId
     */
    public void updateArticleViewCount(@Min(value = 1) Integer articleId);

    /**
     * 删除文章
     * @param articleId
     */
    public void deleteArticle(@Min(value = 1) Integer articleId);

    public Article publishArticle(@Valid Article Article, @NotNull List<@NotBlank @Length(max = 6) String> tagList, @NotBlank String publishType);
}
