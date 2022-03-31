package cn.edu.zut.gradesign.controller;
import cn.edu.zut.gradesign.bean.common.ResultVO;
import cn.edu.zut.gradesign.bean.common.TreeBean;
import cn.edu.zut.gradesign.bean.user.Clazz;
import cn.edu.zut.gradesign.bean.user.User;
import cn.edu.zut.gradesign.service.ClazzService;
import cn.edu.zut.gradesign.service.UserService;
import cn.edu.zut.gradesign.utils.HideUtils;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin //允许跨域
@RequestMapping("/api")
public class BasicController {
    @Autowired
    private UserService userService;
    @Autowired
    private ClazzService clazzService;
    @PostMapping(value = "/user/listAll")
    public ResultVO getUserByPage(@RequestBody Map body){
//        Integer page=Integer.valueOf(body.get("page").toString());
//        Integer limit=Integer.valueOf(body.get("limit").toString());
//        if (page==null){
//            page=1;
//        }
//        if(limit==null){
//            limit=20;
//        }
//        PageHelper.startPage(page, limit);
        ResultVO rs=new ResultVO();
        rs.setCode(200);
        List<User> lst=(List<User>)userService.listAll();
        Map map = new HashMap();
        Map map1 = new HashMap();
        //密码脱敏
        for(User user:lst){
            user.setPassword(user.getPassword().substring(3)+"***");
        }
        map.put("list",lst);
        map1.put("total",lst.size());
        map.put("pager",map1);
        rs.setData(map);
        return rs;
    }
    @PostMapping(value = "/user/add")
    public ResultVO insertUser(@RequestBody User user){
        user.setDate(DateUtil.now());
        int flag= userService.insert(user);
        ResultVO rs=new ResultVO();
        rs.setCode(200);
        rs.setMsg("success");
        return rs;
    }
   @PostMapping(value = "/user/update")
    public ResultVO updateUser(@RequestBody User user){
        user.setDate(DateUtil.now());
        int flag= userService.updateByPrimaryKey(user);
       ResultVO rs=new ResultVO();
        rs.setCode(200);
        rs.setMsg("success");
        return rs;
    }
    /**
     * 可批量删除
     * */
    @PostMapping(value = "/user/del")
    public ResultVO delUser(@RequestBody Map map){
        List<String> result = Arrays.asList(map.get("ids").toString().split(","));
        for (String i:result){
            userService.deleteByPrimaryKey(Integer.valueOf(i));
        }
        ResultVO rs=new ResultVO();
        rs.setCode(200);
        rs.setMsg("success");
        return rs;
    }
    /**
     * 班级列表
     * */
    @PostMapping(value = "/user/grade")
    public ResultVO grade(@RequestBody Map body){
        List lst = new LinkedList();
        List<Clazz> list=(List<Clazz>)clazzService.listAll();
        for (int i=0;i<list.size();i++){
            Map map=new HashMap();
            map.put("id",list.get(i).getId());
            map.put("name",list.get(i).getClazz());
            lst.add(map);
        }
        ResultVO rs=new ResultVO();
        rs.setCode(200);
        rs.setMsg("success");
        Map map=new HashMap();
        map.put("list",lst);
        map.put("pager",lst.size());
        rs.setData(map);
        return rs;
    }

    /**
     * 型封装树状接口
     * 班级列表  tree
     * */
    @PostMapping(value = "/user/tree")
    public ResultVO academy(@RequestBody Map body){
        List lst = new LinkedList();
        List<Clazz> list=(List<Clazz>)clazzService.listAll();
        for (int i=0;i<list.size();i++){
            Map map=new HashMap();
            map.put("label",list.get(i).getAcademy());
            map.put("id",list.get(i).getId());
            map.put("children","");
            lst.add(map);
        }
        ResultVO rs=new ResultVO();
        rs.setCode(200);
        rs.setMsg("success");
        Map map=new HashMap();
        map.put("list",lst);
        Map map1 = new HashMap();
        map1.put("total",lst.size());
        map.put("pager",map1);
        rs.setData(map);
        return rs;
    }
    /**
     * 型封装树状接口
     * 班级列表  tree
     * */
    @PostMapping(value = "/user/tree2")
    public ResultVO academyTree(@RequestBody Map body){
        List<Clazz> clazzList=clazzService.listAll();
        List academyList= HideUtils.oneValueINList(clazzList,"academy");
        System.out.println(clazzList);
        System.out.println(academyList);
        List lst=new ArrayList();
//        for (Object obj:academyList){
//            TreeBean treeBean=new TreeBean();
//            treeBean.setLabel(obj.toString());
//            treeBean.setId(Integer.valueOf(1));
//            String sql="select * from clazz where academy="+obj.toString();
//            List<Clazz> gradeClazzList=clazzService.listBySqlReturnEntity(sql);
//            List gradeList=HideUtils.oneValueINList(gradeClazzList,"grade");
//            for (Object obj1:gradeList){
//                treeBean.setLabel(obj1.toString());
//                treeBean.setId(Integer.valueOf(obj1.toString()));
//            }
//            lst.add(treeBean);
//        }
        for (int i=0;i<clazzList.size();i++){
            TreeBean treeBean=new TreeBean();
            treeBean.setLabel(academyList.get(i).toString());
            treeBean.setId(i);
            String sql="select * from clazz where academy="+"\'"+academyList.get(i).toString()+"\'";
            List<Clazz> gradeClazzList=clazzService.listBySqlReturnEntity(sql);
            List gradeList=HideUtils.oneValueINList(gradeClazzList,"grade");
            for (int j=0;j<gradeList.size();j++){
                treeBean.setLabel(gradeList.get(j).toString());
                treeBean.setId(j);
            }
            lst.add(treeBean);
        }
        ResultVO rs=new ResultVO();
        Map map = new HashMap();
        Map map1 = new HashMap();
        map.put("list",lst);
        map1.put("total",lst.size());
        map.put("pager",map1);
        rs.setData(map);
        System.out.println(rs.getData().toString());
        return rs;
    }
}