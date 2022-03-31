package cn.edu.zut.gradesign.utils;

import cn.binarywang.tools.generator.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author o
 * 非空判断均不做
 */
public class DataGenerator {
    //手机号
    public static String genPhone(){
        String generatedMobileNum = ChineseMobileNumberGenerator.getInstance()
                .generate();
        return generatedMobileNum;
    }
    //身份证号

    public static  String idCard(){
        String idCard = ChineseIDCardNumberGenerator.getInstance().generate();
//        if (idCard.charAt(idCard.length()-2)%2 == 0){
//            System.err.println("女");
//        } else {
//            System.err.println("男");
//        }
        return idCard;
    }
    //mail

    public static String email(Boolean bool){
        Boolean flag=false;
        if (flag.equals(bool)){
            //常用邮箱
            List<String> emailSuff= Arrays.asList("@qq.com","@sina.com", "@gmail.com","@yahoo.com","@msn.com","@hotmail.com",
                    "@aol.com","@ask.com","@live.com","@163.com","@163.net","@yeah","@189.com");
//        String prefix= RandomStringUtils.randomAlphanumeric(10);
            String prefix= RandomStringUtils.randomNumeric(10);
            Random random = new Random();
            int n = random.nextInt(emailSuff.size());
            String email=prefix+emailSuff.get(n);
            return email;
        }
        String generatedEmail = EmailAddressGenerator.getInstance().generate();
        return generatedEmail;
    }
    //address
    public static String address(){
        String generatedAddress = ChineseAddressGenerator.getInstance()
                .generate();
        return generatedAddress;
    }
    //目的城市
    public static String cityName(){
        String city = ChineseAddressGenerator.getInstance()
                .generate();
        String cityAddress="";
        if (city.contains("省")&&city.contains("市")){
//            cityAddress=city.substring(city.indexOf("省")+1, city.indexOf("市"));
            cityAddress=city.substring(0, city.indexOf("市")+1);
        }else if (city.contains("市")&&!city.contains("省")){
            cityAddress=city.substring(0, city.indexOf("市")+1);
        } else {
            cityAddress=city.substring(0, city.indexOf("自")+1);
            cityAddress=cityAddress+"自治区";
        }
        return cityAddress;
    }

    //大学
    public static String college(){
        List list= Arrays.asList(
                "北京大学","中国人民大学","清华大学","北京交通大学","北京工业大学","北京航空航天大学","北京理工大学","北京科技大学",
                "北方工业大学","北京化工大学","北京工商大学","北京服装学院","北京邮电大学","北京印刷学院","北京建筑大学",
                "北京石油化工学院","北京电子科技学院","中国农业大学","北京农学院","北京林业大学","北京协和医学院","首都医科大学",
                "北京中医药大学","北京师范大学","首都师范大学","首都体育学院","北京外国语大学","北京第二外国语学院","北京语言大学",
                "中国传媒大学","中央财经大学","对外经济贸易大学","北京物资学院","首都经济贸易大学","外交学院","中国人民公安大学",
                "国际关系学院","北京体育大学","中央音乐学院","中国音乐学院","中央美术学院","中央戏剧学院","中国戏曲学院","北京电影学院",
                "北京舞蹈学院","中央民族大学","中国政法大学","华北电力大学","中华女子学院","北京信息科技大学","中国矿业大学（北京）",
                "中国石油大学（北京）","中国地质大学（北京）","北京联合大学","北京城市学院","中国青年政治学院","首钢工学院",
                "中国劳动关系学院","北京吉利学院","首都师范大学科德学院","北京工商大学嘉华学院","北京邮电大学世纪学院","北京工业大学耿丹学院","北京警察学院","北京第二外国语学院中瑞酒店管理学院","中国科学院大学","中国社会科学院大学","南开大学","天津大学","天津科技大学","天津工业大学","中国民航大学","天津理工大学","天津农学院","天津医科大学","天津中医药大学","天津师范大学","天津职业技术师范大学","天津外国语大学","天津商业大学","天津财经大学","天津体育学院","天津音乐学院","天津美术学院","天津城建大学","天津天狮学院","天津中德应用技术大学","天津外国语大学滨海外事学院","天津体育学院运动与文化艺术学院","天津商业大学宝德学院","天津医科大学临床医学院","南开大学滨海学院","天津师范大学津沽学院","天津理工大学中环信息学院","北京科技大学天津学院","天津大学仁爱学院","天津财经大学珠江学院","河北大学","河北工程大学","河北地质大学","河北工业大学","华北理工大学","河北科技大学","河北建筑工程学院","河北水利电力学院","河北农业大学","河北医科大学","河北北方学院","承德医学院","河北师范大学","保定学院","河北民族师范学院","唐山师范学院","廊坊师范学院","衡水学院","石家庄学院","邯郸学院",
                "邢台学院","沧州师范学院","石家庄铁道大学","燕山大学","河北科技师范学院","唐山学院","华北科技学院","中国人民武装警察部队学院","河北体育学院","河北金融学院","北华航天工业学院","防灾科技学院","河北经贸大学","中央司法警官学院","河北传媒学院","河北工程技术学院","河北美术学院","河北科技学院","河北外国语学院","河北大学工商学院","华北理工大学轻工学院","河北科技大学理工学院","河北师范大学汇华学院","河北经贸大学经济管理学院","河北医科大学临床学院","华北电力大学科技学院","河北工程大学科信学院","河北工业大学城市学院","燕山大学里仁学院","石家庄铁道大学四方学院","河北地质大学华信学院","河北农业大学现代科技学院","华北理工大学冀唐学院","中国地质大学长城学院","燕京理工学院","北京中医药大学东方学院","北京交通大学海滨学院","河北东方学院","河北中医学院","张家口学院","河北环境工程学院","山西大学","太原科技大学","中北大学","太原理工大学","山西农业大学","山西医科大学","长治医学院","山西师范大学","太原师范学院","山西大同大学","晋中学院","长治学院","运城学院","忻州师范学院","山西财经大学","山西中医药大学","吕梁学院","太原学院","山西警察学院","山西应用科技学院","山西大学商务学院","太原理工大学现代科技学院","山西农业大学信息学院","山西师范大学现代文理学院","中北大学信息商务学院","太原科技大学华科学院","山西医科大学晋祠学院","山西财经大学华商学院","山西工商学院","太原工业学院","山西传媒学院",
                "山西工程技术学院","山西能源学院","内蒙古大学","内蒙古科技大学","内蒙古工业大学","内蒙古农业大学","内蒙古医科大学","内蒙古师范大学","内蒙古民族大学","赤峰学院","内蒙古财经大学","呼伦贝尔学院","集宁师范学院","河套学院","呼和浩特民族学院","内蒙古大学创业学院","内蒙古师范大学鸿德学院","内蒙古艺术学院","鄂尔多斯应用技术学院","辽宁大学","大连理工大学","沈阳工业大学","沈阳航空航天大学","沈阳理工大学",
                "东北大学","辽宁科技大学","辽宁工程技术大学","辽宁石油化工大学","沈阳化工大学","大连交通大学","大连海事大学","大连工业大学","沈阳建筑大学","辽宁工业大学","沈阳农业大学","大连海洋大学","中国医科大学","锦州医科大学","大连医科大学","辽宁中医药大学","沈阳药科大学","沈阳医学院","辽宁师范大学","沈阳师范大学","渤海大学","鞍山师范学院","大连外国语大学","东北财经大学","中国刑事警察学院","沈阳体育学院","沈阳音乐学院","鲁迅美术学院","辽宁对外经贸学院","沈阳大学","大连大学","辽宁科技学院","辽宁警察学院","沈阳工程学院","辽东学院","大连民族大学","大连理工大学城市学院","沈阳工业大学工程学院","沈阳航空航天大学北方科技学院","沈阳工学院","大连工业大学艺术与信息工程学院","大连科技学院","沈阳城市建设学院","中国医科大学临床医药学院","大连医科大学中山学院","锦州医科大学医疗学院","辽宁师范大学海华学院","辽宁理工学院","大连财经学院","沈阳城市学院","辽宁石油化工大学顺华能源学院","大连艺术学院","辽宁中医药大学杏林学院","辽宁何氏医学院","沈阳科技学院","大连东软信息学院","辽宁财贸学院","辽宁传媒学院","营口理工学院","吉林大学","延边大学","长春理工大学","东北电力大学","长春工业大学","吉林建筑大学","吉林化工学院","吉林农业大学","长春中医药大学","东北师范大学","北华大学","通化师范学院","吉林师范大学","吉林工程技术师范学院","长春师范大学","白城师范学院","吉林财经大学","吉林体育学院","吉林艺术学院","吉林华桥外国语学院","吉林工商学院","长春工程学院","吉林农业科技学院","吉林警察学院","长春大学","长春光华学院","长春工业大学人文信息学院","长春理工大学光电信息学院","长春财经学院","吉林建筑大学城建学院","长春建筑学院","长春科技学院","吉林动画学院","吉林师范大学博达学院","长春大学旅游学院","东北师范大学人文学院","吉林医药学院","黑龙江大学","哈尔滨工业大学","哈尔滨理工大学","哈尔滨工程大学","黑龙江科技大学","东北石油大学","佳木斯大学","黑龙江八一农垦大学",
                "东北农业大学","东北林业大学","哈尔滨医科大学","黑龙江中医药大学","牡丹江医学院","哈尔滨师范大学","齐齐哈尔大学","牡丹江师范学院","哈尔滨学院","大庆师范学院","绥化学院","哈尔滨商业大学","哈尔滨体育学院","哈尔滨金融学院","齐齐哈尔医学院","黑龙江工业学院","黑龙江东方学院","哈尔滨信息工程学院","黑龙江工程学院","齐齐哈尔工程学院","黑龙江外国语学院","黑龙江财经学院","哈尔滨石油学院","黑龙江工商学院","哈尔滨远东理工学院","哈尔滨剑桥学院","黑龙江工程学院昆仑旅游学院","哈尔滨广厦学院","哈尔滨华德学院","黑河学院","哈尔滨音乐学院","复旦大学","同济大学","上海交通大学","华东理工大学","上海理工大学","上海海事大学","东华大学","上海电力学院","上海应用技术大学","上海健康医学院","上海海洋大学","上海中医药大学","华东师范大学","上海师范大学","上海外国语大学","上海财经大学","上海对外经贸大学","上海海关学院","华东政法大学","上海体育学院","上海音乐学院","上海戏剧学院","上海大学","上海公安学院","上海工程技术大学","上海立信会计金融学院","上海电机学院","上海杉达学院","上海政法学院","上海第二工业大学","上海商学院","上海建桥学院","上海兴伟学院","上海视觉艺术学院","上海外国语大学贤达经济人文学院","上海师范大学天华学院","上海科技大学","上海纽约大学","南京大学","苏州大学","东南大学","南京航空航天大学","南京理工大学","江苏科技大学","中国矿业大学","南京工业大学","常州大学","南京邮电大学","河海大学","江南大学","南京林业大学","江苏大学","南京信息工程大学","南通大学","盐城工学院","南京农业大学","南京医科大学","徐州医科大学","南京中医药大学","中国药科大学","南京师范大学","江苏师范大学","淮阴师范学院","盐城师范学院","南京财经大学","江苏警官学院","南京体育学院","南京艺术学院","苏州科技大学","常熟理工学院","淮阴工学院","常州工学院","扬州大学","三江学院","南京工程学院","南京审计大学","南京晓庄学院","江苏理工学院","淮海工学院","徐州工程学院","南京特殊教育师范学院","南通理工学院","南京森林警察学院","东南大学成贤学院","泰州学院","无锡太湖学院","金陵科技学院","中国矿业大学徐海学院","南京大学金陵学院","南京理工大学紫金学院",
                "南京航空航天大学金城学院","中国传媒大学南广学院","南京理工大学泰州科技学院","南京师范大学泰州学院","南京工业大学浦江学院","南京师范大学中北学院","南京医科大学康达学院","南京中医药大学翰林学院","南京信息工程大学滨江学院","苏州大学文正学院","苏州大学应用技术学院","苏州科技大学天平学院","江苏大学京江学院","扬州大学广陵学院","江苏师范大学科文学院","南京邮电大学通达学院","南京财经大学红山学院","江苏科技大学苏州理工学院","常州大学怀德学院","南通大学杏林学院","南京审计大学金审学院","宿迁学院","江苏第二师范学院","西交利物浦大学","昆山杜克大学","浙江大学","杭州电子科技大学","浙江工业大学","浙江理工大学","浙江海洋大学","浙江农林大学","温州医科大学","浙江中医药大学","浙江师范大学","杭州师范大学","湖州师范学院","绍兴文理学院","台州学院","温州大学","丽水学院","浙江工商大学","嘉兴学院","中国美术学院","中国计量大学","公安海警学院","浙江万里学院","浙江科技学院","宁波工程学院","浙江水利水电学院","浙江财经大学","浙江警察学院","衢州学院","宁波大学","浙江传媒学院","浙江树人学院","浙江越秀外国语学院","宁波大红鹰学院","浙江大学城市学院","浙江大学宁波理工学院","杭州医学院","浙江工业大学之江学院","浙江师范大学行知学院","宁波大学科学技术学院","杭州电子科技大学信息工程学院","浙江理工大学科技与艺术学院","浙江海洋大学东海科学技术学院","浙江农林大学暨阳学院","温州医科大学仁济学院","浙江中医药大学滨江学院","杭州师范大学钱江学院","湖州师范学院求真学院","绍兴文理学院元培学院","温州大学瓯江学院","浙江工商大学杭州商学院","嘉兴学院南湖学院","中国计量大学现代科技学院","浙江财经大学东方学院","温州商学院","同济大学浙江学院","上海财经大学浙江学院","浙江外国语学院","浙江音乐学院","宁波诺丁汉大学","温州肯恩大学","安徽大学","中国科学技术大学","合肥工业大学","安徽工业大学","安徽理工大学","安徽工程大学","安徽农业大学","安徽医科大学",
                "蚌埠医学院","皖南医学院","安徽中医药大学","安徽师范大学","阜阳师范学院","安庆师范大学","淮北师范大学","黄山学院","皖西学院","滁州学院","安徽财经大学","宿州学院","巢湖学院","淮南师范学院","铜陵学院","安徽建筑大学","安徽科技学院","安徽三联学院","合肥学院","蚌埠学院","池州学院","安徽新华学院","安徽文达信息工程学院","亳州学院","安徽外国语学院","安徽财经大学商学院","安徽大学江淮学院","安徽信息工程学院","安徽工业大学工商学院","安徽建筑大学城市建设学院","安徽农业大学经济技术学院","安徽师范大学皖江学院","安徽医科大学临床医学院","阜阳师范学院信息工程学院",
                "淮北师范大学信息学院","合肥师范学院","河海大学文天学院","厦门大学","华侨大学","福州大学","福建工程学院","福建农林大学","集美大学","福建医科大学","福建中医药大学","福建师范大学","闽江学院","武夷学院","宁德师范学院","泉州师范学院","闽南师范大学","厦门理工学院","三明学院","龙岩学院","福建商学院","福建警察学院","莆田学院","仰恩大学","厦门医学院","厦门华厦学院","闽南理工学院","福建师范大学闽南科技学院","福建农林大学东方学院","厦门工学院","阳光学院","厦门大学嘉庚学院","福州大学至诚学院","集美大学诚毅学院","福建师范大学协和学院","福州外语外贸学院","福建江夏学院","泉州信息工程学院","福州理工学院","福建农林大学金山学院","南昌大学","华东交通大学","东华理工大学","南昌航空大学","江西理工大学","景德镇陶瓷大学","江西农业大学","江西中医药大学","赣南医学院","江西师范大学","上饶师范学院","宜春学院","赣南师范大学","井冈山大学","江西财经大学","江西科技学院","景德镇学院","萍乡学院","江西科技师范大学","南昌工程学院","江西警察学院","新余学院","九江学院","江西工程学院","南昌理工学院","江西应用科技学院","江西服装学院","南昌工学院","南昌大学科学技术学院","南昌大学共青学院","华东交通大学理工学院","东华理工大学长江学院","南昌航空大学科技学院","江西理工大学应用科学学院","景德镇陶瓷大学科技艺术学院","江西农业大学南昌商学院","江西中医药大学科技学院","江西师范大学科学技术学院","赣南师范大学科技学院",
                "江西科技师范大学理工学院","江西财经大学现代经济管理学院","豫章师范学院","南昌师范学院","山东大学","中国海洋大学","山东科技大学","中国石油大学（华东）","青岛科技大学","济南大学","青岛理工大学","山东建筑大学","齐鲁工业大学","山东理工大学","山东农业大学","青岛农业大学","潍坊医学院","泰山医学院","滨州医学院","山东中医药大学","济宁医学院","山东师范大学","曲阜师范大学","聊城大学","德州学院","滨州学院","鲁东大学","临沂大学","泰山学院","济宁学院","菏泽学院","山东财经大学","山东体育学院","山东艺术学院","齐鲁医药学院","青岛滨海学院","枣庄学院","山东工艺美术学院","青岛大学","烟台大学","潍坊学院","山东警察学院","山东交通学院","山东工商学院","山东女子学院","烟台南山学院","潍坊科技学院","山东英才学院","青岛恒星科技学院","青岛黄海学院","山东现代学院","山东协和学院","烟台大学文经学院","聊城大学东昌学院","青岛理工大学琴岛学院","山东师范大学历山学院","山东财经大学燕山学院","中国石油大学胜利学院","山东科技大学泰山科技学院","山东华宇工学院","青岛工学院","青岛农业大学海都学院","齐鲁理工学院","山东财经大学东方学院","济南大学泉城学院","山东政法学院","齐鲁师范学院","山东青年政治学院","北京电影学院现代创意媒体学院","山东管理学院",
                "山东农业工程学院","华北水利水电大学","郑州大学","河南理工大学","郑州轻工业学院","河南工业大学","河南科技大学","中原工学院","河南农业大学","河南科技学院","河南牧业经济学院","河南中医药大学","新乡医学院","河南大学","河南师范大学","信阳师范学院","周口师范学院","安阳师范学院","许昌学院","南阳师范学院","洛阳师范学院","商丘师范学院","河南财经政法大学","郑州航空工业管理学院","黄淮学院","平顶山学院","郑州工程技术学院","洛阳理工学院","新乡学院","信阳农林学院","河南工学院","安阳工学院","河南工程学院","河南财政金融学院","南阳理工学院","河南城建学院","河南警察学院","黄河科技学院","铁道警察学院","郑州科技学院","郑州工业应用技术学院","郑州师范学院","郑州财经学院","黄河交通学院","商丘工学院","河南大学民生学院","河南师范大学新联学院","信阳学院","安阳学院","新乡医学院三全学院","河南科技学院新科学院","郑州工商学院","中原工学院信息商务学院","商丘学院","郑州成功财经学院","郑州升达经贸管理学院","武汉大学","华中科技大学","武汉科技大学","长江大学","武汉工程大学","中国地质大学（武汉）","武汉纺织大学","武汉轻工大学","武汉理工大学","湖北工业大学","华中农业大学","湖北中医药大学","华中师范大学","湖北大学","湖北师范大学","黄冈师范学院",
                "湖北民族学院","汉江师范学院","湖北文理学院","中南财经政法大学","武汉体育学院","湖北美术学院","中南民族大学","湖北汽车工业学院","湖北工程学院","湖北理工学院","湖北科技学院","湖北医药学院","江汉大学","三峡大学","湖北警官学院","荆楚理工学院","武汉音乐学院","湖北经济学院","武汉商学院","武汉东湖学院","汉口学院","武昌首义学院","武昌理工学院","武汉生物工程学院","武汉晴川学院","湖北大学知行学院","武汉科技大学城市学院","三峡大学科技学院","江汉大学文理学院","湖北工业大学工程技术学院","武汉工程大学邮电与信息工程学院","武汉纺织大学外经贸学院","武昌工学院","武汉工商学院","长江大学工程技术学院","长江大学文理学院","湖北商贸学院","湖北汽车工业学院科技学院","湖北医药学院药护学院","湖北民族学院科技学院","湖北经济学院法商学院","武汉体育学院体育科技学院","湖北师范大学文理学院","湖北文理学院理工学院","湖北工程学院新技术学院","文华学院","武汉学院","武汉工程科技学院","武汉华夏理工学院","武汉传媒学院","武汉设计工程学院","湖北第二师范学院","湘潭大学","吉首大学","湖南大学","中南大学","湖南科技大学","长沙理工大学","湖南农业大学","中南林业科技大学","湖南中医药大学","湖南师范大学","湖南理工学院","湘南学院","衡阳师范学院","邵阳学院","怀化学院","湖南文理学院","湖南科技学院","湖南人文科技学院","湖南商学院","南华大学","长沙医学院","长沙学院","湖南工程学院","湖南城市学院","湖南工学院","湖南财政经济学院","湖南警察学院","湖南工业大学","湖南女子学院","湖南第一师范学院","湖南医药学院","湖南涉外经济学院","湘潭大学兴湘学院","湖南工业大学科技学院","湖南科技大学潇湘学院","南华大学船山学院","湖南商学院北津学院","湖南师范大学树达学院","湖南农业大学东方科技学院","中南林业科技大学涉外学院","湖南文理学院芙蓉学院","湖南理工学院南湖学院","衡阳师范学院南岳学院","湖南工程学院应用技术学院","湖南中医药大学湘杏学院","吉首大学张家界学院","长沙理工大学城南学院","长沙师范学院","湖南应用技术学院","湖南信息学院","湖南交通工程学院","中山大学","暨南大学","汕头大学","华南理工大学","华南农业大学","广东海洋大学","广州医科大学","广东医科大学","广州中医药大学","广东药科大学","华南师范大学","韶关学院","惠州学院","韩山师范学院","岭南师范学院","肇庆学院","嘉应学院","广州体育学院","广州美术学院","星海音乐学院","广东技术师范学院","深圳大学","广东财经大学","广东白云学院","广州大学","广州航海学院","广东警官学院","仲恺农业工程学院","五邑大学","广东金融学院","电子科技大学中山学院","广东石油化工学院","东莞理工学院","广东工业大学","广东外语外贸大学","佛山科学技术学院","广东培正学院","南方医科大学","广东东软学院","华南理工大学广州学院","广州大学华软软件学院","中山大学南方学院","广东外语外贸大学南国商学院","广东财经大学华商学院","广东海洋大学寸金学院","华南农业大学珠江学院","广东技术师范学院天河学院","北京师范大学珠海分校","广东工业大学华立学院","广州大学松田学院","广州商学院","北京理工大学珠海学院","吉林大学珠海学院","广州工商学院","广东科技学院","广东理工学院","东莞理工学院城市学院","中山大学新华学院","广东第二师范学院","南方科技大学","北京师范大学-香港浸会大学联合国际学院","香港中文大学（深圳）","深圳北理莫斯科大学","广东以色列理工学院","广西大学","广西科技大学","桂林电子科技大学","桂林理工大学","广西医科大学","右江民族医学院","广西中医药大学","桂林医学院","广西师范大学","广西师范学院","广西民族师范学院",
                "河池学院","玉林师范学院","广西艺术学院","广西民族大学","百色学院","梧州学院","广西科技师范学院","广西财经学院","南宁学院","钦州学院","桂林航天工业学院","桂林旅游学院","贺州学院","广西警察学院","北海艺术设计学院","广西大学行健文理学院","广西科技大学鹿山学院","广西民族大学相思湖学院","广西师范大学漓江学院","广西师范学院师园学院","广西中医药大学赛恩斯新医药学院","桂林电子科技大学信息科技学院","桂林理工大学博文管理学院","广西外国语学院","北京航空航天大学北海学院","海南大学","海南热带海洋学院","海南师范大学","海南医学院","海口经济学院","琼台师范学院","三亚学院","重庆大学","重庆邮电大学","重庆交通大学","重庆医科大学","西南大学","重庆师范大学","重庆文理学院","重庆三峡学院","长江师范学院","四川外国语大学","西南政法大学","四川美术学院","重庆科技学院","重庆理工大学","重庆工商大学","重庆工程学院","重庆大学城市科技学院","重庆警察学院","重庆人文科技学院","四川外国语大学重庆南方翻译学院","重庆师范大学涉外商贸学院","重庆工商大学融智学院","重庆工商大学派斯学院","重庆邮电大学移通学院","重庆第二师范学院","四川大学","西南交通大学","电子科技大学","西南石油大学","成都理工大学","西南科技大学","成都信息工程大学","四川理工学院","西华大学","中国民用航空飞行学院","四川农业大学","西昌学院","西南医科大学","成都中医药大学","川北医学院","四川师范大学","西华师范大学","绵阳师范学院","内江师范学院","宜宾学院","四川文理学院","阿坝师范学院","乐山师范学院","西南财经大学","成都体育学院","四川音乐学院","西南民族大学","成都学院","成都工业学院","攀枝花学院","四川旅游学院","四川民族学院","四川警察学院","成都东软学院","电子科技大学成都学院","成都理工大学工程技术学院","四川传媒学院","成都信息工程大学银杏酒店管理学院","成都文理学院","四川工商学院","四川外国语大学成都学院","成都医学院","四川工业科技学院","四川大学锦城学院","西南财经大学天府学院","四川大学锦江学院","四川文化艺术学院","西南科技大学城市学院","西南交通大学希望学院","成都师范学院","四川电影电视学院","贵州大学","贵州医科大学","遵义医学院","贵阳中医学院","贵州师范大学","遵义师范学院","铜仁学院","兴义民族师范学院","安顺学院","贵州工程应用技术学院","凯里学院","黔南民族师范学院","贵州财经大学","贵州民族大学","贵阳学院","六盘水师范学院","贵州商学院","贵州警察学院","贵阳中医学院时珍学院","贵州财经大学商务学院","贵州大学科技学院",
                "贵州大学明德学院","贵州民族大学人文科技学院","贵州师范大学求是学院","遵义医学院医学与科技学院","贵州医科大学神奇民族医药学院","贵州师范学院","贵州理工学院","茅台学院","云南大学","昆明理工大学","云南农业大学","西南林业大学","昆明医科大学","大理大学","云南中医学院","云南师范大学","昭通学院","曲靖师范学院","普洱学院","保山学院","红河学院","云南财经大学","云南艺术学院","云南民族大学","玉溪师范学院","楚雄师范学院","云南警官学院","昆明学院","文山学院","云南经济管理学院","云南大学滇池学院","云南大学旅游文化学院","昆明理工大学津桥学院","云南师范大学商学院","云南师范大学文理学院","昆明医科大学海源学院","云南艺术学院文华学院","云南工商学院","滇西科技师范学院","滇西应用技术大学","西藏大学","西藏民族大学","西藏藏医学院","西藏农牧学院","西北大学","西安交通大学","西北工业大学","西安理工大学","西安电子科技大学","西安工业大学","西安建筑科技大学","西安科技大学","西安石油大学","陕西科技大学","西安工程大学","长安大学","西北农林科技大学","陕西中医药大学","陕西师范大学","延安大学","陕西理工大学","宝鸡文理学院","咸阳师范学院","渭南师范学院","西安外国语大学","西北政法大学","西安体育学院","西安音乐学院","西安美术学院","西安文理学院","榆林学院","商洛学院","安康学院","西安培华学院","西安财经学院","西安邮电大学","西安航空学院","西安医学院","西安欧亚学院","西安外事学院","西安翻译学院","西京学院","西安思源学院","陕西国际商贸学院","陕西服装工程学院","西安交通工程学院","西安交通大学城市学院","西北大学现代学院","西安建筑科技大学华清学院","西安财经学院行知学院","陕西科技大学镐京学院","西安工业大学北方信息工程学院","延安大学西安创新学院","西安电子科技大学长安学院","西北工业大学明德学院","长安大学兴华学院","西安理工大学高科学院","西安科技大学高新学院","陕西学前师范学院","兰州大学","兰州理工大学","兰州交通大学","甘肃农业大学","甘肃中医药大学","西北师范大学","兰州城市学院","陇东学院","天水师范学院","河西学院","兰州财经大学","西北民族大学","甘肃政法学院","甘肃民族师范学院","兰州文理学院","甘肃医学院","兰州工业学院","西北师范大学知行学院","兰州财经大学陇桥学院","兰州财经大学长青学院","兰州交通大学博文学院","兰州理工大学技术工程学院","青海大学","青海师范大学","青海民族大学","青海大学昆仑学院","宁夏大学","宁夏医科大学","宁夏师范学院","北方民族大学","宁夏理工学院","宁夏大学新华学院","银川能源学院","中国矿业大学银川学院","新疆大学","塔里木大学","新疆农业大学","石河子大学","新疆医科大学","新疆师范大学","喀什大学","伊犁师范学院","新疆财经大学","新疆艺术学院","新疆工程学院","昌吉学院","新疆警察学院","新疆大学科学技术学院","新疆农业大学科学技术学院","新疆医科大学厚博学院","新疆财经大学商务学院","石河子大学科技学院"
        );
        Random r=new Random();
        return list.get(r.nextInt(list.size())).toString();
    }

    //学院

    public static String academy() {
        List list = Arrays.asList("计算机学院", "软件学院",
                "建筑学院","经济管理学院","土木水利学院","环境学院","公共管理学院","马克思主义学院","人文学院","环境学院","机械工程学院","人文学院","航天航空学院","社会科学学院","信息科学技术学院","法学院","新闻与传播学院","材料学院","美术学院","电机工程与应用电子技术系","工程物理系","化学工程系","核能与新能源技术研究院","教育研究院","深圳国际研究生院","苏世民书院","全球创新学院","新雅书院","致理书院","日新书院","未央书院","探微书院","行健书院","求真书院","体育部","艺术教育中心","语言教学中心","出土文献研究与保护中心",
                "理学院","生命科学学院","医学院","药学院","万科公共卫生与健康学院","高等研究院","交叉信息研究院","航空发动机研究院","低碳能源实验室","数学科学中心","生物医学交叉研究院","未来实验室","脑与智能实验室","智能产业研究院","碳中和研究院","金融学院"
                );
        Random r = new Random();
        return list.get(r.nextInt(list.size())).toString();
    }
    //年级

    public static String grade(){
        Random r = new Random();
        String grade="";
        for (int i=0;i<100;i++){
            if (i<10){
                grade="0"+r.nextInt(99)+"级";
            }else{
                grade=r.nextInt(99)+"级";
            }
        }
        return grade;
    }

    //学号

    public static String stuNum(){
        String stuNum= RandomStringUtils.randomNumeric(8);
        Random r=new Random();
        return  (1980+r.nextInt(42))+""+stuNum;
    }
    //密码 暂时不采用随机生成
    //名字
    public static String name(){
        String generatedName = ChineseNameGenerator.getInstance().generateOdd();
        return  generatedName;
    }

}
