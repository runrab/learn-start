package com.runrab.gmall.api.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.runrab.gmall.api.entry.ResultVO;
import com.runrab.gmall.api.entry.Rool;
import com.runrab.gmall.api.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//订单 不用
@RestController
@CrossOrigin
@RequestMapping("/rool")
public class RoolController {
    @Autowired
    RedisService redisService;
    //用于显示实时订单信息
    @GetMapping("")
    public ResultVO<Rool> trade(@RequestParam(required = false) String d) {
        ResultVO<Rool> result = new ResultVO<>();
        //2020-12-14
        //获取当前的日期
        d = Optional.ofNullable(d).orElse(DateUtil.today());
        DateTime date = DateUtil.parseDate(d);
        //获取现在的小时
        int dh = DateUtil.date().hour(true);
        String dhKey = "rool:" + DateUtil.date().setField(DateField.HOUR_OF_DAY, dh).toString("yyyy-MM-dd:HH");

        List<String[]> oisCount = new LinkedList();
        //从redis中取到数据
        Set<Object> dhOi = redisService.sGet(dhKey);
        System.out.println(dhKey);
        //获取到当前小时内有多少条订单数据，把订单数据全部放到一个List中
        dhOi.forEach(res -> {
            String str = (String) res;
            String[] strings = str.split("_");
            oisCount.add(strings);
        });
        String[][] res = new String[oisCount.size()][3];
        for (int i = 0; i < res.length; i++) {
            res[i] = oisCount.get(i);
        }
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println(res[i][j]);
            }
        }
        result.setCode(200);
        result.setMsg("success");
        System.out.println(res);
        result.setData(new Rool(res));
        System.out.println(result);
        return result;
    }
}
