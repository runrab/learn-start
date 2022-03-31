package cn.edu.zut.gradesign;

import cn.edu.zut.gradesign.bean.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@SpringBootTest
public class GenTest {
    @Test
    public void getRandom(){
        Random r = new Random(new Date().getTime());
        Map map = new HashMap();
        User user=new User();
        user.setName("aa");
        try {
            User user2= (User) user.clone();
            System.out.println(user==user2);
            System.out.println(user.hashCode());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
