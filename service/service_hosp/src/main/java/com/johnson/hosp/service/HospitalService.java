package com.johnson.hosp.service;

import com.johnson.hosptial.model.hosp.Hospital;
import com.johnson.hosptial.vo.hosp.HospitalQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface HospitalService {
    void save(Map<String, Object> paramMap);

    Hospital getByHoscode(String hoscode);
    //医院列表(条件查询分页）
    Page<Hospital> slectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);

    void updateStatus(String id, Integer status);

    Map<String,Object> getHospById(String id);
}
