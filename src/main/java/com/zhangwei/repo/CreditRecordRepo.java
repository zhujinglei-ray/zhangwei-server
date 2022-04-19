package com.zhangwei.repo;

import com.zhangwei.domain.FormData;

import java.util.List;

public interface CreditRecordRepo {
    public int add(FormData data);

    public FormData getDataViaId(Long id);

    public List<FormData> getAllData();

    public int updateData(int id, FormData data);
}
