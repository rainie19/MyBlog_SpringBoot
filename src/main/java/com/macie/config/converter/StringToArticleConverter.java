package com.macie.config.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.macie.entity.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Macie
 * @date 2021/7/3 -13:42
 */
public class StringToArticleConverter implements Converter<String, Article> {
    private static final Logger log = LoggerFactory.getLogger(StringToArticleConverter.class);

    @Override
    public Article convert(String source) {
        Article article = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            article = mapper.readValue(source, Article.class);
        } catch (JsonProcessingException e) {
            log.error("Article类型转换错误", e);
            throw new IllegalArgumentException(e.getMessage());
        }
        return article;
    }
}
