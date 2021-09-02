package com.johnson.hosp.repository;

import com.johnson.hosptial.model.hosp.Department;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface DepartmentRepository extends MongoRepository<Department,String> {
    //上传科室接口
    Department getDepartmentByHoscodeAndDepcode(String hoscode, String depcode);


}
