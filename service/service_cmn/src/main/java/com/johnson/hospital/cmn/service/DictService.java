package com.johnson.hospital.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.johnson.hosptial.model.cmn.Dict;
import com.johnson.hosptial.model.hosp.HospitalSet;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DictService extends IService<Dict> {
    List<Dict> findChlidData(Long id);

    void exportDickData(HttpServletResponse response);

    void importDickData(MultipartFile file);
}
