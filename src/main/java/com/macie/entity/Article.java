package com.macie.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author Macie
 * @date 2020/10/8 -18:32
 */
public class Article {
    private Integer articleId;

    @NotBlank(message = "文章标题不能为空")
    @Length(min = 3, max = 30, message = "文章标题长度为3-30个字符")
    private String articleTitle;

    @NotBlank(message = "英文标题不能为空")
    @Length(min = 3, max = 40, message = "英文标题长度为3-40个字符")
    private String articleSlug;

    @NotBlank(message = "文章作者名不能为空")
    private String articleAuthor;

    private Date articleCreateTime;

    private Integer articleViewCount;

    private Integer articleCommentsCount;

    @NotBlank(message = "文章摘要不能为空")
    @Length(max = 200, message = "文章摘要不能大于 200 个字符")
    private String articleSummary;

    private String articleContentHtml;

    private String articleContentMd;

    @NotBlank(message = "请为文章选择一个分类")
    @Length(min = 1, max = 10, message = "分类长度为1-10个字符")
    private String categoryName;

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleSlug='" + articleSlug + '\'' +
                ", articleAuthor='" + articleAuthor + '\'' +
                ", articleCreateTime=" + articleCreateTime +
                ", articleViewCount=" + articleViewCount +
                ", articleCommentsCount=" + articleCommentsCount +
                ", articleSummary='" + articleSummary + '\'' +
                ", articleContentHtml='" + articleContentHtml + '\'' +
                ", categoryId=" + categoryName +
                ", articleContentMd='" + articleContentMd + '\'' +
                '}';
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }
    public String getArticleContentHtml() {
        return articleContentHtml;
    }

    public void setArticleContentHtml(String articleContentHtml) {
        this.articleContentHtml = articleContentHtml;
    }

    public String getArticleContentMd() {
        return articleContentMd;
    }

    public void setArticleContentMd(String articleContentMd) {
        this.articleContentMd = articleContentMd;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleSlug() {
        return articleSlug;
    }

    public void setArticleSlug(String articleSlug) {
        this.articleSlug = articleSlug;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public Date getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(Date articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    public Integer getArticleViewCount() {
        return articleViewCount;
    }

    public void setArticleViewCount(Integer articleViewCount) {
        this.articleViewCount = articleViewCount;
    }

    public Integer getArticleCommentsCount() {
        return articleCommentsCount;
    }

    public void setArticleCommentsCount(Integer articleCommentsCount) {
        this.articleCommentsCount = articleCommentsCount;
    }

    public String getArticleSummary() {
        return articleSummary;
    }

    public void setArticleSummary(String articleSummary) {
        this.articleSummary = articleSummary;
    }

}
