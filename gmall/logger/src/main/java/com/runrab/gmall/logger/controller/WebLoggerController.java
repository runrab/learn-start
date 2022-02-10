package com.runrab.gmall.logger.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class WebLoggerController {
    @Autowired
    KafkaTemplate kafkaTemplate;

    @RequestMapping("/applog")
    //接口位置
    public void logger(@RequestBody String logger){

        //落地文件中
        log.info("info:{}",logger);
        JSONObject json = JSON.parseObject(logger);
        //日志分流处理
        if(json.getString("start")!=null && json.getString("start").length()>0) {
            kafkaTemplate.send("GMALL_STARTS",logger);
        } else {
            kafkaTemplate.send("GMALL_EVENTS",logger);
        }

    }
}
