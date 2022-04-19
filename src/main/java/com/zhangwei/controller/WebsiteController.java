package com.zhangwei.controller;

import com.zhangwei.domain.FormData;
import com.zhangwei.service.RequestProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;

@RestController
@RequestMapping("/credit")
@CrossOrigin
public class WebsiteController {
    private final RequestProcessing requestProcessing;

    @Autowired
    public WebsiteController(RequestProcessing requestProcessing) {
        this.requestProcessing = requestProcessing;
    }

    @RequestMapping(value = "/score/lr", method = RequestMethod.POST)
    public String processViaLogisticRegression(@RequestBody FormData formData) {
        var res = requestProcessing.processOnlineRequest(formData, "lr");


        double r = Double.parseDouble(res);
        DecimalFormat df = new DecimalFormat("0.00%");
        String percentage = df.format(r);

        return percentage;
    }

    @RequestMapping(value = "/score/dt", method = RequestMethod.POST)
    public String processViaDecisionTree(@RequestBody FormData formData) {
        var res = requestProcessing.processOnlineRequest(formData, "dt");

        double r = Double.parseDouble(res);
        DecimalFormat df = new DecimalFormat("0.00%");
        String percentage = df.format(r);

        return percentage;
    }

    @RequestMapping(value = "/score/rf", method = RequestMethod.POST)
    public String processViaRandomForest(@RequestBody FormData formData) {
        var res = requestProcessing.processOnlineRequest(formData, "rf");

        double r = Double.parseDouble(res);
        DecimalFormat df = new DecimalFormat("0.00%");
        String percentage = df.format(r);

        return percentage;
    }


    @RequestMapping(value = "/score/ann", method = RequestMethod.POST)
    public String processViaAnn(@RequestBody FormData formData) {
        var res = requestProcessing.processOnlineRequest(formData, "ann");


        double r = Double.parseDouble(res);
        DecimalFormat df = new DecimalFormat("0.00%");
        String percentage = df.format(r);

        return percentage;
    }


}
