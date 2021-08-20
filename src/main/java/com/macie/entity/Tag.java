package com.macie.entity;

/**
 * @author Macie
 * @date 2020/10/8 -18:32
 */
public class Tag {
    private Integer tagConnectId;
    private Integer articleId;
    private String tagName;
    private Integer tagCount;

    public Integer getTagConnectId() {
        return tagConnectId;
    }

    public void setTagConnectId(Integer tagConnectId) {
        this.tagConnectId = tagConnectId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getTagCount() {
        return tagCount;
    }

    public void setTagCount(Integer tagCount) {
        this.tagCount = tagCount;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagConnectId=" + tagConnectId +
                ", articleId=" + articleId +
                ", tagName='" + tagName + '\'' +
                ", tagCount=" + tagCount +
                '}';
    }
}
