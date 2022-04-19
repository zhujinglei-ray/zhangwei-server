package com.zhangwei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RequestPythonRestService {
    @Autowired
    private RestTemplate restTemplate;
    private final static String BASEURL = "http://127.0.0.1:5000/predict/";

    public String sentToPython(Map<String, String> params, String type) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> httpEntity = new HttpEntity(params, headers);

        ResponseEntity<String> responseEntity = restTemplate
                .exchange(BASEURL + type, HttpMethod.POST, httpEntity, String.class);
        String responseBody = responseEntity.getBody();
        System.out.println(responseBody);
        return responseBody;
    }
}
