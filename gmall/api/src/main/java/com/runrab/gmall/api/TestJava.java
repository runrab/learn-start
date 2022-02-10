package com.runrab.gmall.api;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

public class TestJava {
    public static void main(String[] args) {
        DateTime date = DateUtil.parseDate("2020-12-17");
        System.out.println(date);
        System.out.println(date.offsetNew(DateField.HOUR,0));
        System.out.println(date.offsetNew(DateField.HOUR,1));
        System.out.println(date.offsetNew(DateField.HOUR,1));
        System.out.println(date.offsetNew(DateField.HOUR,1));
        System.out.println(date.offsetNew(DateField.HOUR,1));
    }
}
