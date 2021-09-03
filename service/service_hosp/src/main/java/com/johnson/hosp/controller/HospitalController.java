package com.johnson.hosp.controller;

import com.johnson.hosp.service.HospitalService;
import com.johnson.hosp.service.HospitalSetService;
import com.johnson.hospital.common.result.Result;
import com.johnson.hosptial.model.hosp.Hospital;
import com.johnson.hosptial.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/hosp/hospital")
@CrossOrigin
public class HospitalController {
        @Autowired
        private HospitalService hospitalService;
        //医院列表(条件查询分页）
        @GetMapping("list/{page}/{limit}")
        public Result listHosp(@PathVariable Integer page,
                               @PathVariable Integer limit,
                               HospitalQueryVo hospitalQueryVo){
            Page<Hospital> pageModel=hospitalService.slectHospPage(page,limit,hospitalQueryVo);
            return Result.ok(pageModel);

        }
        //更新医院上线状态
        @ApiOperation(value = "更新医院上线状态")
        @GetMapping("updateStatus/{id}/{status}")
        public  Result updateHospStatus(@PathVariable String id,
                                        @PathVariable Integer status){
            hospitalService.updateStatus(id,status);
            return Result.ok();
        }
        //医院详情信息
        @ApiOperation(value = "医院详情信息")
        @GetMapping("showHospDetail/{id}")
        public Result showHospDetail(@PathVariable String id){
            Map<String,Object> map=hospitalService.getHospById(id);
            return Result.ok(map);
        }
}
