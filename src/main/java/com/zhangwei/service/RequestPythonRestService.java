package com.zhangwei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestPythonRestService {
    @Autowired
    private RestTemplate restTemplate;
    private final static String BASEURL = "http://127.0.0.1:5000/test";

    public String sentToPython(MultiValueMap<String, String> params) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity(params, headers);

        ResponseEntity<String> responseEntity = restTemplate
                .exchange(BASEURL, HttpMethod.POST, httpEntity, String.class);
        String responseBody = responseEntity.getBody();
        System.out.println(responseBody);
        return responseBody;
    }
}
