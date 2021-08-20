package com.macie.controller;

import com.macie.dto.JsonResponse;
import com.macie.entity.Tag;
import com.macie.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Macie
 * @date 2021/10/9 -22:31
 */
@RestController
@Transactional
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     *获取所有tags
     *
     * @return
     */
    @RequestMapping("/getAllTags")
    public JsonResponse getAllTags() {
        ArrayList<Tag> tags = tagService.retrieveAllTags();
        Map<String, Object> map = new HashMap();
        map.put("tags", tags);
        return JsonResponse.responseOK(map);
    }

    /**
     * 删除某个tag
     *
     * @param tagName
     * @return
     */
    @RequestMapping("/deleteTag")
    public JsonResponse deleteTag(@RequestParam("tagName") String tagName) {
        tagService.deleteTag(tagName);
        Map<String, Object> map = new HashMap();
        return JsonResponse.responseOK();
    }
}
