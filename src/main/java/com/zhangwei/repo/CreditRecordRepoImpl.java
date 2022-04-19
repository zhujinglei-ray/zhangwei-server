package com.zhangwei.repo;

import com.zhangwei.domain.FormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CreditRecordRepoImpl implements CreditRecordRepo {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public CreditRecordRepoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int add(FormData data) {
        SimpleJdbcInsert simpleJdbcInsert =
                new SimpleJdbcInsert(jdbcTemplate.getJdbcTemplate())
                        .withTableName("user_record")
                        .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = createInsertMap(data);
        return simpleJdbcInsert.execute(parameters);
    }

    private Map<String, Object> createInsertMap(FormData brand) {
        return createMap(brand);
    }

    private Map<String, Object> createMap(FormData data) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("gender", data.getGender());
        dataMap.put("ownRealEstate", data.isOwnRealEstate());
        dataMap.put("ownCar", data.isOwnCar());
        dataMap.put("ownWorkPhone", data.isOwnWorkPhone());

        dataMap.put("annualIncome", data.getAnnualIncome());
        dataMap.put("childNum", data.getChildNum());
        dataMap.put("age", data.getAge());
        dataMap.put("worksYears", data.getWorksYears());
        dataMap.put("familySize", data.getFamilySize());

        dataMap.put("occupationType", data.getOccupationType());
        dataMap.put("houseType", data.getHouseType());
        dataMap.put("educationStatus", data.getEducationStatus());
        dataMap.put("marriageStatus", data.getMarriageStatus());


        return dataMap;
    }

    @Override
    public FormData getDataViaId(Long id) {
        String sql = "SELECT * FROM user_record WHERE id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource().addValue("id", id);

        return jdbcTemplate.queryForObject(sql, parameterSource, (rs, i) -> mapToData(rs));
    }

    @Override
    public List<FormData> getAllData() {
        String sql = " SELECT * FROM user_recorde";
        MapSqlParameterSource params = new MapSqlParameterSource();
        return jdbcTemplate.query(sql, params, (rs, i) -> mapToData(rs));
    }

    @Override
    public int updateData(int id, FormData data) {
        //todo
        return 0;
    }


    private FormData mapToData(ResultSet rs) throws SQLException {
        FormData formData = new FormData();
        formData.setGender(rs.getInt("gender"));
        formData.setOwnWorkPhone(rs.getBoolean("ownRealEstate"));
        formData.setOwnCar(rs.getBoolean("ownCar"));
        formData.setOwnWorkPhone(rs.getBoolean("ownWorkPhone"));

        formData.setAnnualIncome(rs.getInt("annualIncome"));
        formData.setChildNum(rs.getInt("childNum"));
        formData.setAge(rs.getInt("age"));
        formData.setWorksYears(rs.getInt("worksYears"));
        formData.setFamilySize(rs.getInt("familySize"));

        formData.setOccupationType(rs.getString("occupationType"));
        formData.setHouseType(rs.getString("houseType"));
        formData.setEducationStatus(rs.getString("educationStatus"));
        formData.setMarriageStatus(rs.getString("marriageStatus"));

        return formData;
    }
}
