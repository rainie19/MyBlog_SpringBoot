package com.macie.controller;

import com.macie.dto.JsonResponse;
import com.macie.service.serviceImpl.AllCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Macie
 * @date 2021/10/10 -0:17
 */
@RestController
@Transactional
public class AllCountController {

    @Autowired
    private AllCountService allCountService;

    /**
     * 获取文章，分类，标签总数
     * @return
     */
    @RequestMapping("/getAllCount")
    public JsonResponse getAllCount() {
        Map<String, Long> countMap = allCountService.getAllCount();
        Map<String, Object> map = new HashMap();
        map.put("AllCount", countMap);
        return JsonResponse.responseOK(map);
    }

}
