package com.macie.controller;

import com.macie.dto.JsonResponse;
import com.macie.entity.Article;
import com.macie.entity.Tag;
import com.macie.service.ArticleService;
import com.macie.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Macie
 * @date 2021/10/9 -15:36
 */
@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    /**
     * 获取某条件下的所有文章
     *
     */
    @RequestMapping("/getArticles")
    public JsonResponse getArticles(String queryType,  @Min(value = 0) Integer queryPage,  @Min(value = 0) Integer pageSize, String queryName) {
        Map<String, Object> map = new HashMap();
        Long totalCount = articleService.countArticles(queryType, queryName);
        ArrayList<Article> articles = articleService.listArticles(queryType, queryPage, pageSize, queryName);
        Map<Integer, ArrayList<Tag>> articleTagMap = null;
        if (articles != null) {
            articleTagMap = tagService.getArticleTagMap(articles);
        }

        map.put("articles", articles);
        map.put("articleTotalCount", totalCount);
        map.put("articleIdTagsMap", articleTagMap);

        return JsonResponse.responseOK(map);
    }

    /**
     * 获取某篇文章的内容
     *
     * @param articleId
     * @return
     */
    @RequestMapping("/getArticleDetail")
    public JsonResponse getArticleDetail(@Min(value = 1) Integer articleId) {
        Article article = articleService.getArticle(articleId);
        System.out.println(System.getProperty("user.dir"));
        // 映射article_id和tags
        ArrayList<Tag> tagVoArrayList = tagService.retrieveTagsByArticleId(articleId);
        //返回上一篇文章和下一篇文章信息
        Article prevArticle = articleService.getPreviousArticle(articleId);
        Article nextArticle = articleService.getNextArticle(articleId);

        Map<String, Object> map = new HashMap();
        map.put("article", article);
        map.put("prevArticle", prevArticle);
        map.put("nextArticle", nextArticle);
        map.put("tags", tagVoArrayList);

        // response返回后增加文章阅读次数
        articleService.updateArticleViewCount(articleId);

        return JsonResponse.responseOK(map);
    }

    /**
     * 删除文章
     *
     * @param articleId
     * @return
     */
    @RequestMapping("/deleteArticle")
    public JsonResponse deleteArticle(@Min(value = 1) Integer articleId) {
        articleService.deleteArticle(articleId);
        Map<String, Object> map = new HashMap();
        return JsonResponse.responseOK();
    }

    /**
     * 发布文章
     *
     * @param article
     * @param dynamicTags
     * @param publishType
     */
    @RequestMapping("/publishArticle")
    public JsonResponse publishArticle(@Validated Article article, String dynamicTags, @NotBlank String publishType) {
        dynamicTags = StringUtils.trimAllWhitespace(dynamicTags);
        String[] tags = StringUtils.delimitedListToStringArray(dynamicTags, ",", "[\"]");
        Article responseArticle = articleService.publishArticle(article, Arrays.asList(tags), publishType);

        Map<String, Object> map = new HashMap();
        map.put("article", responseArticle);
        return JsonResponse.responseOK(map);
    }
}
