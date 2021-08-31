package com.johnson.hospital.cmn.controller;

import com.johnson.hospital.cmn.service.DictService;
import com.johnson.hospital.common.result.Result;
import com.johnson.hosptial.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(value = "数据字典接口")
@RestController
@RequestMapping("/admin/cmn/dict")
@CrossOrigin
public class DickController {
    @Autowired
    private DictService dictService;

    //导入数据字典
    @PostMapping("importData")
    public Result importDick(MultipartFile file) {
        dictService.importDickData(file);
        return Result.ok();

    }

    //导出数据字典接口
    @GetMapping("exportData")
    public void exportDict(HttpServletResponse response) {
        dictService.exportDickData(response);

    }

    //根据数据id查询子数据
    @ApiOperation(value = "根据数据id查询子数据")
    @GetMapping("findChildData/{id}")
    public Result findChildData(@PathVariable Long id) {
        List<Dict> list = dictService.findChlidData(id);
        return Result.ok(list);
    }
}
