package cn.edu.zut.gradesign;

import cn.binarywang.tools.generator.*;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
public class RadomTest {
    @Test
    public void generateRandomDate() {
//        Date randomDate = ChineseIDCardNumberGenerator.randomDate();
//        System.err.println(randomDate);
//        assertNotNull(randomDate);
    }

    @Test
    public void testGenerate() {
        String idCard = ChineseIDCardNumberGenerator.getInstance().generate();
        System.err.println(idCard);
        assertNotNull(idCard);
        if (idCard.charAt(idCard.length()-2)%2 == 0){
            System.err.println("女");
        } else {
            System.err.println("男");
        }
    }
    @Test
    public void testGenerateIssueOrg() {
        String issueOrg = ChineseIDCardNumberGenerator.generateIssueOrg();
        System.err.println(issueOrg);
        assertNotNull(issueOrg);
    }
    @Test
    public void testGenerateValidPeriod() {
        String result = ChineseIDCardNumberGenerator.generateValidPeriod();
        System.err.println(result);
        assertNotNull(result);
    }

    //中国手机号生成
    @Test
    public void testGenerate1() {
        String generatedMobileNum = ChineseMobileNumberGenerator.getInstance()
                .generate();
        assertNotNull(generatedMobileNum);
        System.err.println(generatedMobileNum);
    }

   @Test
    public void testGgenerateFake() {
        String generatedMobileNum = ChineseMobileNumberGenerator.getInstance()
                .generateFake();
        assertNotNull(generatedMobileNum);
        System.err.println(generatedMobileNum);
    }
    //email
    @Test
    public void testGenerate2() {
        String generatedEmail = EmailAddressGenerator.getInstance().generate();
        System.err.println(generatedEmail);
        assertNotNull(generatedEmail);
    }
    //name
    @Test
    public void testGenerate3() {
        String generatedName = ChineseNameGenerator.getInstance().generate();
        assertNotNull(generatedName);
        System.err.println(generatedName);
    }

    @Test
    public void testGenerateOdd() {
        String generatedName = ChineseNameGenerator.getInstance().generateOdd();
        assertNotNull(generatedName);
        System.err.println(generatedName);
    }

    //address
    @Test
    public void testGenerate4() {
        String generatedAddress = ChineseAddressGenerator.getInstance()
                .generate();
        System.err.println(generatedAddress);
        assertNotNull(generatedAddress);
    }
}
