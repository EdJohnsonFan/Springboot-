package com.johnson.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.johnson.hosp.service.HospitalSetService;
import com.johnson.hospital.common.exception.HospitalException;
import com.johnson.hospital.common.result.Result;
import com.johnson.hospital.common.utils.MD5;
import com.johnson.hosptial.model.hosp.HospitalSet;
import com.johnson.hosptial.vo.hosp.HospitalSetQueryVo;
import com.mysql.jdbc.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Api(tags = "医院设置管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
@CrossOrigin
public class HospitalSetController {
    @Autowired
    private HospitalSetService hospitalSetService;


    //localhost:8201/admin/hosp/hospitalSet/findAll
    @ApiOperation(value = "获取所有医院设置信息")
    @GetMapping("findAll")
    public Result findALLHospitalSet() {
        //调用service的方法
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }

    //2.逻辑删除医院设置
    @ApiOperation(value = "逻辑删除医院设置信息")
    @DeleteMapping("{id}")
    public Result removeHospSet(@PathVariable Long id) {
        boolean flag = hospitalSetService.removeById(id);
        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //3.条件查询带分页
    @PostMapping("findPage/{current}/{limit}")
    public Result findPageHospSet(@PathVariable Long current, @PathVariable Long limit,
                                  @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo) {
        //创建page对象，传递当前页，记录每页数
        Page<HospitalSet> page = new Page<>(current, limit);
        //构造条件
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        String hosname = hospitalSetQueryVo.getHosname();//医院名称
        String hoscode = hospitalSetQueryVo.getHoscode();//医院编号
        if (!StringUtils.isNullOrEmpty(hosname)) {
            wrapper.like("hosname", hospitalSetQueryVo.getHosname());
        }
        if (!StringUtils.isNullOrEmpty(hoscode)) {
            wrapper.eq("hoscode", hospitalSetQueryVo.getHoscode());
        }
        //调用方法实现分页查询
        Page<HospitalSet> pageHospitalSet = hospitalSetService.page(page, wrapper);

        return Result.ok(pageHospitalSet);

    }

    //4.添加医院设置
    @PostMapping("saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet) {
        //设置状态1使用 0 不能使用
        hospitalSet.setStatus(1);
        //签名密钥
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis() + "" + random.nextInt(1000)));

        //调用service
        boolean save = hospitalSetService.save(hospitalSet);
        if (save) {
            return Result.ok();
        } else {
            return Result.fail();
        }

    }

    //5.根据id获取医院设置
    @GetMapping("getHospSet/{id}")
    public Result getHospSet(@PathVariable Long id) {
//        //模拟异常
//        try {
//            int a =1/0;
//        }catch (Exception e){
//            throw new HospitalException("失败",201);
//        }
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        return Result.ok(hospitalSet);

    }

    //6.修改医院设置
    @PostMapping("updateHospitalSet")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet) {
        boolean flag = hospitalSetService.updateById(hospitalSet);
        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //7.批量修改医院设置
    @DeleteMapping("batchRemove")
    public Result batchRemoveHospitalSet(@RequestBody List<Long> idList) {
        hospitalSetService.removeByIds(idList);
        return Result.ok();
    }

    //8.医院设置锁定和接解锁
    @PutMapping("lockHospitalSet/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Long id, @PathVariable Integer status) {
        //根据id查询医院设置信息
        HospitalSet hospitalSet;
        hospitalSet = hospitalSetService.getById(id);
        //设置状态
        hospitalSet.setStatus(status);
        //保存
        hospitalSetService.updateById(hospitalSet);
        return Result.ok();
    }


    //9.发送签名密钥
    @PutMapping("sendKey/{id}")
    public Result lockHospitalSet(@PathVariable Long id) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
        //todo
        return null;
    }

}
