package com.macie.dao;

import com.macie.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * @author Macie
 * @date 2020/9/30 -16:37
 */
@Mapper
public interface TagDao {
    /**
     *
     * @return
     */
    public ArrayList<Tag> listAllTags();

    /**
     *
     * @param articleId
     * @return
     */
    public ArrayList<Tag> getTagsByArticleId(Integer articleId);

    /**
     *根据文章标题更新文章的tags
     * @param articleTitle
     * @param tagName
     * @return
     */
    //public int updateTagsByArticleTitle(String articleTitle, String tagName);

    /**
     * 获取标签总数
     * @return
     */
    public Long countAllTags();
    /**
     * 删除文章下所有tag
     * @param articleId
     * @return
     */
    public int deleteTagsByArticleId(Integer articleId);

    /**
     *插入具体文章tag
     * @param articleId
     * @param tagName
     * @return
     */
    public int insertTagByArticleId(@Param("articleId") Integer articleId, @Param("tagName") String tagName);
    /**
     * 往Database中添加新标签
     * @param tagName
     * @return
     */
    public int insertNewTag(String tagName);
    /**
     *在所有地方删除此tag
     * @param tagName
     * @return
     */
    public int deleteTag(String tagName);
}
