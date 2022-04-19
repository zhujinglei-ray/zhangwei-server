package com.zhangwei.controller;

import com.zhangwei.domain.FormData;
import com.zhangwei.service.RequestProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit")
@CrossOrigin
public class WebsiteController {
    private final RequestProcessing requestProcessing;

    @Autowired
    public WebsiteController(RequestProcessing requestProcessing) {
        this.requestProcessing = requestProcessing;
    }

    @RequestMapping(value = "/score", method = RequestMethod.POST)
    public String process(@RequestBody FormData formData) {
        System.out.println(formData);
        var res = requestProcessing.processOnlineRequest(formData);
        return res;
    }
}
