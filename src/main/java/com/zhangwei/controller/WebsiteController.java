package com.zhangwei.controller;

import com.zhangwei.domain.FormData;
import com.zhangwei.service.RequestProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;

@RestController
@RequestMapping("/credit/score")
@CrossOrigin
public class WebsiteController {
    private final RequestProcessing requestProcessing;

    @Autowired
    public WebsiteController(RequestProcessing requestProcessing) {
        this.requestProcessing = requestProcessing;
    }

    @RequestMapping(value = "/lr", method = RequestMethod.POST)
    public double processViaLogisticRegression(@RequestBody FormData formData) {
        var res = requestProcessing.processOnlineRequest(formData, "lr");
        double r = Double.parseDouble(res);
//        DecimalFormat df = new DecimalFormat("0.00%");
//        String percentage = df.format(r);
        r = r * 100;
        return r;
    }

    @RequestMapping(value = "/dt", method = RequestMethod.POST)
    public double processViaDecisionTree(@RequestBody FormData formData) {
        var res = requestProcessing.processOnlineRequest(formData, "dt");

        double r = Double.parseDouble(res);
//        DecimalFormat df = new DecimalFormat("0.00%");
//        String percentage = df.format(r);
        r = r * 100;
        return r;
    }

    @RequestMapping(value = "/rf", method = RequestMethod.POST)
    public double processViaRandomForest(@RequestBody FormData formData) {
        var res = requestProcessing.processOnlineRequest(formData, "rf");

        double r = Double.parseDouble(res);
//        DecimalFormat df = new DecimalFormat("0.00%");
//        String percentage = df.format(r);
        r = r * 100;
        return r;
    }


    @RequestMapping(value = "/ann", method = RequestMethod.POST)
    public double processViaAnn(@RequestBody FormData formData) {
        var res = requestProcessing.processOnlineRequest(formData, "ann");
        double r = Double.parseDouble(res);
//        DecimalFormat df = new DecimalFormat("0.00%");
//        String percentage = df.format(r);
        r = r * 100;
        return r;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String testEndPoint() {
        return "test";
    }
}
