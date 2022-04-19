package com.zhangwei.service;

import com.zhangwei.domain.FormData;
import com.zhangwei.repo.CreditRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageService {

    private final CreditRecordRepo creditRecordRepo;

    @Autowired
    public StorageService(CreditRecordRepo creditRecordRepo) {
        this.creditRecordRepo = creditRecordRepo;
    }

    public void storeData(FormData data) {
        System.out.println("storing data");
        System.out.println(data);
        creditRecordRepo.add(data);
    }

    public List<FormData> getAllData() {
        System.out.println("get all data");
        return creditRecordRepo.getAllData();
    }
}
