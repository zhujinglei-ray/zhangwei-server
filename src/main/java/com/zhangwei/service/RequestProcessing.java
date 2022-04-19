package com.zhangwei.service;

import com.zhangwei.domain.FormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class RequestProcessing {
    private final RequestPythonRestService pythonRestService;

    @Autowired
    public RequestProcessing(RequestPythonRestService pythonRestService) {
        this.pythonRestService = pythonRestService;
    }

    // 以后模型变量 改变以后 需要 把这里的 mapping 改了
    // 这里数据清理的参数 应该再多研究一下，现在这里只是作为 一个简单的预处理，作为展示使用
    public String processOnlineRequest(FormData formData) {
        var params = convertToParamsMap(formData);

        return params.toString();
    }

    // 根据现有的 信息价值得出的 高价值 参数,参数列表 以后需要更新 当我们有更多数据以后
    //'gender': 0.0, 'real_estate': 1.0, 'child_num_1': 1.0, 'child_num_2More': 0.0, 'work_phone': 0.0,
    //                 'gp_age_high': 0.0, 'gp_age_highest': 0.0, 'gp_age_low': 0.0,
    //                 'gp_age_lowest': 0.0, 'gp_work_time_high': 0.0, 'gp_work_time_highest': 0.0,
    //                 'gp_work_time_low': 0.0, 'gp_work_time_medium': 0.0, 'occupation_type_hightecwk': 0.0,
    //                 'occupation_type_officewk': 0.0, 'famsizegp_1': 0.0, 'famsizegp_3more': 1.0,
    //                 'house_type_Co-op apartment': 0.0, 'house_type_Municipal apartment': 0.0,
    //                 'house_type_Office apartment': 0.0, 'house_type_Rented apartment': 0.0,
    //                 'house_type_With parents': 0.0, 'education_type_Higher education': 0.0,
    //                 'education_type_Incomplete higher': 0.0, 'education_type_Lower secondary': 0.0,
    //                 'family_type_Civil marriage': 0.0,
    //                 'family_type_Separated': 1, 'family_type_Single / not married': 0, 'family_type_Widow': 0}

    private MultiValueMap<String, String> convertToParamsMap(FormData data) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("gender", String.valueOf(data.getGender()));
        params.add("real_estate", String.valueOf(data.getRealEstate()));
        params.add("work_phone", String.valueOf(data.getRealEstate()));

        return params;
    }

    private void addChildNum(FormData data, MultiValueMap<String, String> params) {
        if (data.getChildNum() == 1) {
            params.add("child_num_1", "1");
            // child num 怎么算的 要再看一下
            params.add("child_num_2More", "0");
        } else {
            params.add("child_num_1", "0");
            // child num 怎么算的 要再看一下
            params.add("child_num_2More", "1");
        }
    }

    //年龄分组
    private void addAgeGroup(FormData data, MultiValueMap<String, String> params) {
        //'gp_age_high': 0.0, 'gp_age_highest': 0.0, 'gp_age_low': 0.0, 'gp_age_lowest': 0.0,
        params.add("gp_age_high", "0");
        params.add("gp_age_highest", "0");
        params.add("gp_age_low", "0");
        params.add("gp_age_lowest", "0");
    }


    private void addOccupationType(FormData data, MultiValueMap<String, String> params) {
        //'occupation_type_hightecwk': 0.0, 'occupation_type_officewk': 0.0,
        params.add("occupation_type_hightecwk", "0");
        params.add("occupation_type_officewk", "0");
    }

    private void addHouseType(FormData data, MultiValueMap<String, String> params) {
        //'house_type_Co-op apartment', 'house_type_Municipal apartment',
        //'house_type_Office apartment', 'house_type_Rented apartment',
        //'house_type_With parents',
        params.add("house_type_Co-op apartment", "0");
        params.add("house_type_Municipal apartment", "0");
        params.add("house_type_Office apartment", "0");
        params.add("house_type_Rented apartment", "0");
        params.add("house_type_With parents", "0");

    }

    private void addWorkTime(FormData data, MultiValueMap<String, String> params) {
        // 'gp_work_time_high', 'gp_work_time_highest', 'gp_work_time_low', 'gp_work_time_medium'
        params.add("gp_work_time_high", "0");
        params.add("gp_work_time_highest", "0");
        params.add("gp_work_time_low", "0");
        params.add("gp_work_time_medium", "0");

    }

    private void addFamilySize(FormData data, MultiValueMap<String, String> params) {
        // 'famsizegp_1', 'famsizegp_3more'
        params.add("famsizegp_1", "0");
        params.add("famsizegp_3more", "0");
    }

    private void addFamilyType(FormData data, MultiValueMap<String, String> params) {
        //'family_type_Civil marriage': 0.0, 'family_type_Separated': 1, 'family_type_Single / not married': 0, 'family_type_Widow': 0
        params.add("family_type_Civil marriage", "0");
        params.add("family_type_Separated", "0");
        params.add("family_type_Single / not married", "0");
        params.add("family_type_Widow", "0");
    }

    private void addEducationType(FormData data, MultiValueMap<String, String> params) {
        //'education_type_Higher education': 0.0, 'education_type_Incomplete higher': 0.0,
        //'education_type_Lower secondary': 0.0,
        params.add("education_type_Higher education", "0");
        params.add("education_type_Incomplete higher", "0");
        params.add("education_type_Lower secondary", "0");
    }

}
