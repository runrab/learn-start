package cn.edu.zut.gradesign;

import cn.binarywang.tools.generator.ChineseAddressGenerator;
import cn.hutool.json.JSONUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//此类负责生成随机数据并添加到数据库中
public class GenRandomDataTest {
    //email
    @Test
    public void genRandomEmail(){
        List<String> emailSuff= Arrays.asList("@qq.com","@sina.com", "@gmail.com","@yahoo.com","@msn.com","@hotmail.com",
                "@aol.com","@ask.com","@live.com","@163.com","@163.net","@yeah","@189.com");
//        String prefix= RandomStringUtils.randomAlphanumeric(10);
        String prefix=RandomStringUtils.randomNumeric(10);
        Random random = new Random();
        int n = random.nextInt(emailSuff.size());
        String email=prefix+emailSuff.get(n);
        System.out.println(email);
    }

    @Test
    public void city(){
        String city = ChineseAddressGenerator.getInstance()
                .generate();
        String cityAddress="";
        if (city.contains("省")){
            cityAddress=city.substring(city.indexOf("省"), city.indexOf("市"));
        }else {
            cityAddress=city.substring(0, city.indexOf("市"));
        }
        System.out.println(cityAddress);
    }


    @Test
    public  void gradeTestG(){
        int n=99;
        for (int i=0;i<n;i++){
            System.out.printf(i+"级");
        }
    }
    @Test
    public void test1(){
        String stuNum= RandomStringUtils.randomNumeric(8);
        Random r=new Random();
        System.out.println((1980+r.nextInt(42))+""+stuNum);
    }
}
