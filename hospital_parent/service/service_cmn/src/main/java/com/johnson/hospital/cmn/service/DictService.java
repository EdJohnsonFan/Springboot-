package com.johnson.hospital.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.johnson.hosptial.model.cmn.Dict;
import com.johnson.hosptial.model.hosp.HospitalSet;

import java.util.List;

public interface DictService extends IService<Dict> {
    List<Dict> findChlidData(Long id);
}
