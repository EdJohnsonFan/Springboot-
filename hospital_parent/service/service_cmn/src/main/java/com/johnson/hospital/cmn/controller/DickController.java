package com.johnson.hospital.cmn.controller;

import com.johnson.hospital.cmn.service.DictService;
import com.johnson.hospital.common.result.Result;
import com.johnson.hosptial.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "数据字典接口")
@RestController
@RequestMapping("/admin/cmn/dict")
public class DickController {
    @Autowired
    private DictService dictService;

    //根据数据id查询子数据
    @ApiOperation(value ="根据数据id查询子数据" )
    @GetMapping("findChildData/{id}")
    public Result findChildData(@PathVariable Long id){
        List<Dict> list = dictService.findChlidData(id);
        return Result.ok(list);
    }
}
