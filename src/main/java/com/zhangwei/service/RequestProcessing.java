package com.zhangwei.service;

import com.zhangwei.domain.FormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class RequestProcessing {
    private final RequestPythonRestService pythonRestService;
    private final StorageService storageService;
    @Autowired
    public RequestProcessing(RequestPythonRestService pythonRestService, StorageService storageService) {
        this.pythonRestService = pythonRestService;
        this.storageService = storageService;
    }

    // 以后模型变量 改变以后 需要 把这里的 mapping 改了
    // 这里数据清理的参数 应该再多研究一下，现在这里只是作为 一个简单的预处理，作为展示使用
    public String processOnlineRequest(FormData formData, String type) {
        storageService.storeData(formData);
        var params = convertToParamsMap(formData);
        return pythonRestService.sentToPython(params, type);
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

    private Map<String, String> convertToParamsMap(FormData data) {
        Map<String, String> params = new HashMap<>();
        addBinaryVariables(data, params);
        addChildNum(data, params);
        addAgeGroup(data, params);
        addOccupationType(data, params);
        addHouseType(data, params);
        addWorkTime(data, params);
        addFamilySize(data, params);
        addFamilyType(data, params);
        addEducationType(data, params);

        return params;
    }

    // binary variable
    private void addBinaryVariables(FormData data, Map<String, String> params) {

        params.put("gender", String.valueOf(data.getGender()));
        String real_estate = data.isOwnRealEstate() ? "1" : "0";
        params.put("real_estate", real_estate);
        String work_phone = data.isOwnWorkPhone() ? "1" : "0";
        params.put("work_phone", work_phone);
    }


    //0        15908
    //1         6118
    //2More     3108
    private void addChildNum(FormData data, Map<String, String> params) {
        if (data.getChildNum() == 1) {
            params.put("child_num_1", "1");
            // child num 怎么算的 要再看一下
            params.put("child_num_2More", "0");
            return;
        } else if (data.getChildNum() > 1) {
            params.put("child_num_1", "0");
            // child num 怎么算的 要再看一下
            params.put("child_num_2More", "1");

            return;
        }

        params.put("child_num_1", "0");
        params.put("child_num_2More", "0");
    }

    //年龄分组 // 根据
    //(19.951999999999998, 24.7]    0.025066
    //(24.7, 29.4]                  0.134280
    //(29.4, 34.1]                  0.169770
    //(34.1, 38.8]                  0.140805
    //(38.8, 43.5]                  0.173072
    //(43.5, 48.2]                  0.141880
    //(48.2, 52.9]                  0.099069
    //(52.9, 57.6]                  0.076550
    //(57.6, 62.3]                  0.032585
    //(62.3, 67.0]                  0.006923


    // highest > 60
    // high 50 -60
    // lowest 18 - 25
    // low 25 - 35

    private void addAgeGroup(FormData data, Map<String, String> params) {
        //'gp_age_high': 0.0, 'gp_age_highest': 0.0, 'gp_age_low': 0.0, 'gp_age_lowest': 0.0,
        params.put("gp_age_high", "0");
        params.put("gp_age_highest", "0");
        params.put("gp_age_low", "0");
        params.put("gp_age_lowest", "0");
        int age = data.getAge();
        if (age > 18 && age <= 25) {
            params.put("gp_age_lowest", "1");
            return;
        }

        if (age <= 35) {
            params.put("gp_age_low", "1");
            return;
        }

        if (age > 50 && age <= 60) {
            params.put("gp_age_high", "1");
            return;
        }

        if (age > 60) {
            params.put("gp_age_highest", "1");
        }

    }

    //Laborwk      10496
    //officewk     10183
    //hightecwk     4455
    private void addOccupationType(FormData data, Map<String, String> params) {
        //'occupation_type_hightecwk': 0.0, 'occupation_type_officewk': 0.0,
        var officeWorkSet = getOfficeWorkSet();
        var highTechSet = getHighTecSet();
        if (officeWorkSet.contains(data.getOccupationType())) {
            params.put("occupation_type_hightecwk", "0");
            params.put("occupation_type_officewk", "1");
            return;
        }

        if (highTechSet.contains(data.getOccupationType())) {
            params.put("occupation_type_hightecwk", "1");
            params.put("occupation_type_officewk", "0");
        }

        params.put("occupation_type_hightecwk", "0");
        params.put("occupation_type_officewk", "0");
    }

    private Set<String> getOfficeWorkSet() {
        Set<String> set = new HashSet<>();
        set.add("Accountants");
        set.add("Core staff");
        set.add("HR staff");
        set.add("Medicine staff");
        set.add("Private service staff");
        set.add("Realty agents");
        set.add("Sales staff");
        set.add("Secretaries");
        return set;
    }

    private Set<String> getHighTecSet() {
        Set<String> set = new HashSet<>();
        set.add("Managers");
        set.add("High skill tech staff");
        set.add("IT staff");
        return set;
    }

    //House / apartment      22102
    //With parents            1430
    //Municipal apartment      812
    //Rented apartment         439
    //Office apartment         199
    //Co-op apartment          152
    private void addHouseType(FormData data, Map<String, String> params) {
        //'house_type_Co-op apartment', 'house_type_Municipal apartment',
        //'house_type_Office apartment', 'house_type_Rented apartment',
        //'house_type_With parents',
        params.put("house_type_Co-op apartment", "0");
        params.put("house_type_Municipal apartment", "0");
        params.put("house_type_Office apartment", "0");
        params.put("house_type_Rented apartment", "0");
        params.put("house_type_With parents", "0");

        String houseType = data.getHouseType();

        if (houseType.equals("Parents")) {
            params.put("house_type_With parents", "1");
            return;
        }

        if (houseType.equals("Municipal")) {
            params.put("house_type_Municipal apartment", "1");
            return;
        }

        if (houseType.equals("Co-op")) {
            params.put("house_type_Co-op apartment", "1");
            return;
        }

        if (houseType.equals("Office")) {
            params.put("house_type_Office apartment", "1");
            return;
        }

        if (houseType.equals("Rented")) {
            params.put("house_type_Rented apartment", "1");
        }

    }

    //lowest     18254
    //low         4987
    //medium      1378
    //high         425
    //highest       90
    //(-0.044, 4.3]    0.476566
    //(4.3, 8.6]       0.249702
    //(8.6, 12.9]      0.125567
    //(12.9, 17.2]     0.072850
    //(17.2, 21.5]     0.033938
    //(21.5, 25.8]     0.020888
    //(25.8, 30.1]     0.011459
    //(30.1, 34.4]     0.005451
    //(34.4, 38.7]     0.002308
    //(38.7, 43.0]     0.001273
    private void addWorkTime(FormData data, Map<String, String> params) {
        // 'gp_work_time_high', 'gp_work_time_highest', 'gp_work_time_low', 'gp_work_time_medium'
        params.put("gp_work_time_high", "0");
        params.put("gp_work_time_highest", "0");
        params.put("gp_work_time_low", "0");
        params.put("gp_work_time_medium", "0");
        int yearsOfWorking = data.getWorksYears();
        if (yearsOfWorking > 30) {
            params.put("gp_work_time_highest", "1");
            return;
        }

        if (yearsOfWorking > 17) {
            params.put("gp_work_time_high", "1");
            return;
        }

        if (yearsOfWorking > 8) {
            params.put("gp_work_time_medium", "1");
            return;
        }

        if (yearsOfWorking > 4) {
            params.put("gp_work_time_low", "1");
        }

    }

    //2.0     12697
    //1.0      4263
    //3.0      5216
    //4.0      2576
    //6.0        51
    //9.0         2
    //5.0       307
    //7.0        18
    //20.0        1
    //15.0        3
    private void addFamilySize(FormData data, Map<String, String> params) {
        // 'famsizegp_1', 'famsizegp_3more'
        int size = data.getFamilySize();
        if (size == 1) {
            params.put("famsizegp_1", "1");
            params.put("famsizegp_3more", "0");
            return;
        }

        if (size > 1) {
            params.put("famsizegp_1", "0");
            params.put("famsizegp_3more", "1");
        }

        params.put("famsizegp_1", "0");
        params.put("famsizegp_3more", "0");

    }


    private void addFamilyType(FormData data, Map<String, String> params) {
        //'family_type_Civil marriage': 0.0, 'family_type_Separated': 1, 'family_type_Single / not married': 0, 'family_type_Widow': 0
        params.put("family_type_Civil marriage", "0");
        params.put("family_type_Separated", "0");
        params.put("family_type_Single / not married", "0");
        params.put("family_type_Widow", "0");

        String marriageStatus = data.getMarriageStatus();

        if (marriageStatus.equals("Married")) {
            params.put("family_type_Civil marriage", "1");
            return;
        }

        if (marriageStatus.equals("Separated")) {
            params.put("family_type_Separated", "1");
            return;
        }

        if (marriageStatus.equals("Single")) {
            params.put("family_type_Single / not married", "1");
            return;
        }

        if (marriageStatus.equals("Widow")) {
            params.put("family_type_Widow", "1");
        }
    }


    //Secondary / secondary special    16808
    //Higher education                  7146
    //Incomplete higher                  993
    //Lower secondary                    187
    private void addEducationType(FormData data, Map<String, String> params) {
        //'education_type_Higher education': 0.0, 'education_type_Incomplete higher': 0.0,
        //'education_type_Lower secondary': 0.0,
        params.put("education_type_Higher education", "0");
        params.put("education_type_Incomplete higher", "0");
        params.put("education_type_Lower secondary", "0");

        String educationType = data.getEducationStatus();

        //中学 以及中学一下
        if (educationType.equals("Secondary School")) {
            params.put("education_type_Lower secondary", "1");

            return;
        }

        //大学
        if (educationType.equals("University")) {
            params.put("education_type_Higher education", "1");
            return;
        }

        // 大学辍学 或未完成
        if (educationType.equals("Incomplete University")) {
            params.put("education_type_Incomplete higher", "1");
        }
    }

}
