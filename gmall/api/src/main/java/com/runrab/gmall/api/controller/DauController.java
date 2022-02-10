package com.runrab.gmall.api.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import com.runrab.gmall.api.entry.DauCount;
import com.runrab.gmall.api.entry.ResultVO;
import com.runrab.gmall.api.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin //跨域访问
@RequestMapping("/dau")  //访问接口
public class DauController {
    @Autowired  //默认按type
    RedisService redisService;
    @GetMapping("")
    public ResultVO<DauCount> dau(@RequestParam(required = false) String d){
        ResultVO<DauCount> result = new ResultVO<>();
        //2020-11-22
        d = Optional.ofNullable(d).orElse(DateUtil.today());
        DateTime date = DateUtil.parseDate(d);
        //int dh = 5;
        int dh = DateUtil.date().hour(true);
        //获取昨日的日期
        String yesDdKey = "dau:"+date.offsetNew(DateField.DAY_OF_MONTH,-1).toString("yyyy-MM-dd");
        String dayDdKey = "dau:"+date.toString("yyyy-MM-dd");
        String yesTrade = "trade:"+date.offsetNew(DateField.DAY_OF_MONTH,-1).toString("yyyy-MM-dd");
        String dayTrade = "trade:"+date.toString("yyyy-MM-dd");
        String yesTCount = "count:"+date.offsetNew(DateField.DAY_OF_MONTH,-1).toString("yyyy-MM-dd");
        String dayTCount = "count:"+date.toString("yyyy-MM-dd");
        //获取今日到目前为止的总的日活数
        long dayTotalCount = redisService.sGetSetSize(dayDdKey);
        //获取昨日总的日活数
        long yesTotalCount = redisService.sGetSetSize(yesDdKey);
        List<Long> yesCount = new LinkedList();
        List<Long> dayCount = new LinkedList();
        String yesDhKey;
        String dayDhKey;
        for (int i = 0;i<dh+1;i++) {
            yesDhKey = "dau:"+date.offsetNew(DateField.DAY_OF_MONTH,-1).offsetNew(DateField.HOUR_OF_DAY,i).toString("yyyy-MM-dd:HH");
            dayDhKey = "dau:"+date.offsetNew(DateField.HOUR_OF_DAY,i).toString("yyyy-MM-dd:HH");
            yesCount.add(redisService.sGetSetSize(yesDhKey));
            dayCount.add(redisService.sGetSetSize(dayDhKey));
        }
        double yesTradeTotal = redisService.tradetotal(yesTrade);
        double dayTradeTotal = redisService.tradetotal(dayTrade);
        long yesTradeCount = redisService.tradecount(yesTCount);
        long dayTradeCount = redisService.tradecount(dayTCount);
        result.setCode(200);
        result.setMsg("successful!");
        result.setData(new DauCount(ArrayUtil.range(0,dh+1),yesCount,dayCount,yesTotalCount,dayTotalCount,yesTradeTotal,dayTradeTotal,yesTradeCount,dayTradeCount));
        System.out.println(result);
        return result;
    }
}
