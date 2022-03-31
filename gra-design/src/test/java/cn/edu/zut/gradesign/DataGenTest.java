package cn.edu.zut.gradesign;

import cn.edu.zut.gradesign.bean.user.User;
import cn.edu.zut.gradesign.service.UserService;
import cn.edu.zut.gradesign.utils.DataGenerator;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class DataGenTest {
    @Autowired
    private UserService userService;
    @Test
    public void insertUser(){
        int n=2000000;
        for (int i=0; i<n;i++){
            User user=new User();
            user.setDate(DateUtil.now().toString());
            user.setAcademy(DataGenerator.academy());
            user.setAddress(DataGenerator.address());
            String stuNum=DataGenerator.stuNum();
            user.setStuNum(stuNum);
            if (stuNum.length()>=6){
                user.setPassword(stuNum.substring(stuNum.length()-6,stuNum.length()));
            }else {
                user.setPassword("123456");
            }
            user.setCity(DataGenerator.cityName());
            user.setCollege(DataGenerator.college());
            user.setMail(DataGenerator.email(true));
            user.setPhone(DataGenerator.genPhone());
            user.setYear(4);
            user.setGrade(DataGenerator.grade());
            user.setName(DataGenerator.name());
            user.setStartDate(stuNum.substring(0,4)+"-09-01");
            user.setEndDate((Integer.valueOf(stuNum.substring(0,4))+4)+"07-01");
            user.setYear(4);
            user.setInfo("批量导入");
//            System.out.println(HideUtils.objToMap(user));
//            userService.listAll();
            userService.insert(user);
        }
    }
}
