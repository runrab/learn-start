package cn.edu.zut.gradesign.utils;

import cn.edu.zut.gradesign.bean.user.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * 自动生成service接口以及实现类，自动生成controller增删改查的接口
 *
 */
public class CodeGenerator {
	//generate  SV interface
	public static String ISVCode(Class t){
		String clzNm=t.getName();
		int index =clzNm.lastIndexOf(".");
		String tableName = clzNm.substring(index+1);
		String rsString = "";
		rsString +="import "+clzNm+";\n"
					+"import "+clzNm+"Example;\n\r"
					+"import java.util.List;\n"
					+"import java.util.Map;\n"
					+"import org.apache.ibatis.annotations.Param;\n\r"
					+"public interface "+"I"+tableName+"SV {\n"
					+"    public int countByExample("+tableName+"Example example)throws Exception;\n\r"
					+"    public int deleteByExample("+tableName+"Example example) throws Exception;\n\r"
					+"    public int deleteByPrimaryKey(Long id) throws Exception;\n\r"
					+"    public int insert("+tableName+" record) throws Exception;\n\r"
					+"    public int insertSelective("+tableName+" record) throws Exception;\n\r"
					+"    public List<"+tableName+"> selectByExample("+tableName+"Example example) throws Exception;\n\r"
					+"    public "+tableName+" selectByPrimaryKey(Long id) throws Exception;\n\r"
					+"    public int updateByExampleSelective(@Param(\"record\") "+tableName+" record, @Param(\"example\") "+tableName+"Example example) throws Exception;\n\r"
					+"    public int updateByExample(@Param(\"record\") "+tableName+" record, @Param(\"example\") "+tableName+"Example example) throws Exception;\n\r"
					+"    public int updateByPrimaryKeySelective("+tableName+" record) throws Exception;\n\r"
					+"    public int updateByPrimaryKey("+tableName+" record) throws Exception;\n\r"
					+"    public "+tableName+" create"+tableName+"(Map<String, String>req)throws Exception;\n\r"
					+"    public "+tableName+"Example create"+tableName+"Exm(Map<String, String>req)throws Exception;\n\r"
					+"    public List<"+tableName+"> selectByExample("+tableName+"Example example, int pageNum, int pageSize) throws Exception;\n\r"
					+"}";
		
		return rsString;
	}
	
	
	//generate SV implement
	public static String SVImplCode(Class t){
		String clzNm=t.getName();
		int index =clzNm.lastIndexOf(".");
		String tableName = clzNm.substring(index+1);
		String lowName = tableName.substring(0, 1).toLowerCase()+tableName.substring(1);
		String rsString = "";
		//miss importing interface
		rsString +=  "import java.text.SimpleDateFormat;\n"
					+"import java.util.List;\n"
					+"import java.util.Map;\n\r"
					+"import org.apache.commons.lang3.StringUtils;\n"
					+"import org.springframework.beans.factory.annotation.Autowired;\n\r"
					+"import "+clzNm+";\n"
					+"import "+clzNm+"Example;\n"
//					+"import "+clzNm+"DAO;\n"
					+"import com.github.pagehelper.PageHelper;\n\r"
					+"import org.apache.ibatis.annotations.Param;\n\r"
					+"import com.github.pagehelper.PageHelper;\n\r"
					+"@Service\n"
					+"public class "+tableName+"SVImpl implements I"+tableName+"SV {\n"
					+"    @Autowired\n"
					+"    "+tableName+"DAO dao;\n"
					+"    @Override\n"
					+"    public int countByExample("+tableName+"Example example)throws Exception {\n"
					+"        return dao.countByExample(example);\n"
					+"    }\n\r"
					+"    @Override\n"
					+"    public int deleteByExample("+tableName+"Example example) throws Exception {\n"
					+"        return dao.deleteByExample(example);\n"
					+"    }\n\r"
					+"    @Override\n"
					+"    public int deleteByPrimaryKey(Long id) throws Exception {\n"
					+"        return dao.deleteByPrimaryKey(id);\n"
					+"    }\n\r"
					+"    @Override\n"
					+"    public int insert("+tableName+" record) throws Exception {\n"
					+"        return dao.insert(record);\n"
					+"    }\n\r"
					+"    @Override\n"
					+"    public int insertSelective("+tableName+" record) throws Exception {\n"
					+"        return dao.insertSelective(record);\n"
					+"    }\n\r"
					+"    @Override\n"
					+"    public List<"+tableName+"> selectByExample("+tableName+"Example example) throws Exception {\n"
					+"        return dao.selectByExample(example);\n"
					+"    }\n\r"
					+"    @Override\n"
					+"    public "+tableName+" selectByPrimaryKey(Long id) throws Exception {\n"
					+"        return dao.selectByPrimaryKey(id);\n"
					+"    }\n\r"
					+"    @Override\n"
					+"    public int updateByExampleSelective(@Param(\"record\") "+tableName+" record, @Param(\"example\") "+tableName+"Example example) throws Exception {\n"
					+"        return dao.updateByExampleSelective(record, example);\n"
					+"    }\n\r"
					+"    @Override\n"
					+"    public int updateByExample(@Param(\"record\") "+tableName+" record, @Param(\"example\") "+tableName+"Example example) throws Exception {\n"
					+"        return dao.updateByExample(record, example);\n"
					+"    }\n\r"
					+"    @Override\n"
					+"    public int updateByPrimaryKeySelective("+tableName+" record) throws Exception {\n"
					+"        return dao.updateByPrimaryKeySelective(record);\n"
					+"    }\n\r"
					+"    @Override\n"
					+"    public int updateByPrimaryKey("+tableName+" record) throws Exception {\n"
					+"        return dao.updateByPrimaryKey(record);\n"
					+"    }\n\r"
					+"    @Override\n"
					+"    public "+tableName+" create"+tableName+"(Map<String, String>req)throws Exception {\n"
					+MapCode(t)
					+"        "+tableName+" "+lowName+" = new "+tableName+"();\n"
					+"        SimpleDateFormat sdf = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");\n"
					+clsCode(t)
					+"        return "+lowName+";\n"
					+"    }\n\r"
					+"    @Override\n"
					+"    public "+tableName+"Example create"+tableName+"Exm(Map<String, String>req)throws Exception{\n"
					+MapCodeExm(t)
					+"        "+tableName+"Example"+" example = new "+tableName+"Example();\n"
					+"        "+tableName+"Example.Criteria criteria = example.createCriteria();\n"
					+"        SimpleDateFormat sdf = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");\n"
					+exmCode(t)
					+"        return example;\n"
					+"    }\n\r"
					+"    @Override\n"
					+"    public List<"+tableName+"> selectByExample("+tableName+"Example example, int pageNum, int pageSize) throws Exception {\n"
					+"        PageHelper.startPage(pageNum, pageSize);\n"
					+"        return dao.selectByExample(example);\n"
					+"    }\n"
					+"}";
		
		return rsString;
	}
	
	
	//generate controller
	public static String ControllerCode(Class t){
		
		String clzNm=t.getName();
		int index =clzNm.lastIndexOf(".");
		String tableName = clzNm.substring(index+1);
		String lowName = tableName.substring(0, 1).toLowerCase()+tableName.substring(1);
		String rsString = "";
		rsString += "import java.text.SimpleDateFormat;\n"
					+"import java.util.ArrayList;\n"
					+"import java.util.Date;\n"
					+"import java.util.HashMap;\n"
					+"import java.util.List;\n"
					+"import java.util.Map;\n\r"
					+"import javax.servlet.http.HttpServletRequest;\n\r"
					+"import org.apache.commons.lang3.StringUtils;\n"
//					+"import org.springframework.validation.annotation.Validated;\n"
					+"import org.springframework.web.bind.annotation.RequestBody;\n"
					+"import org.springframework.web.bind.annotation.RequestMapping;\n"
					+"import org.springframework.web.bind.annotation.RequestMethod;\n"
					+"import org.springframework.web.bind.annotation.RequestParam;\n"
					+"import org.springframework.web.bind.annotation.ResponseBody;\n"
					+"import org.springframework.web.bind.annotation.RestController;\n\r"
					+"import cn.edu.zut.gradesign.bean.common.ResultVO;\n"
					+"import "+clzNm+";\n"
					+"import "+clzNm+"Example;\n"
					+"import cn.edu.zut.gradesign.utils.SeqGenerateUtil;\n\r"
					+"@RestController\n"
					+"@RequestMapping(\"\")\n"
					+"public class "+tableName+"Controller {\n\r"
					+"    @Reference\n"
					+"    I"+tableName+"SV "+lowName+"SV;\n\r"
//					+"    @PreAuthorize(\"hasAnyAuthority('SUPERADMIN','"+lowName+"Qry')\")\n"
					+"    @RequestMapping(value=\"/"+lowName+"Qry\""+",method=RequestMethod.GET)\n"
					+"    public Result "+lowName+"Qry(\n"
					+MapCodeQry(t)
					+"             @RequestParam(name=\"page\",required=false)String page,@RequestParam(name=\"limit\",required=false)String limit)throws Exception{\n"
					+"        Result<Map> r = new Result<>();\n"
					+"        List<Map> mapList = new ArrayList<>();\n"
					+"        int pageNum =  page == null ? 1 : Integer.parseInt(page);\n"
					+"        int pageSize =  limit == null ? 10 : Integer.parseInt(limit);\n"
					+"        Map<String, String> "+lowName+"ExmMap = new HashMap<>();\n"
					+"        SimpleDateFormat sdf = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");\n"
					+"        try {\n"
					+MapCodeQryExm(t)
					+"            "+tableName+"Example example = "+lowName+"SV.create"+tableName+"Exm("+lowName+"ExmMap);\n"
					+"            List<"+tableName+"> "+lowName+"List = "+lowName+"SV.selectByExample(example,pageNum,pageSize);\n"
					+"            int total = "+lowName+"SV.countByExample(example);\n"
					+"            for ("+tableName+" "+lowName+" : "+lowName+"List) {\n"
					+"                Map<String,Object> tMap = new HashMap<>();\n"
					+MapCodeQryOut(t)
					+"                mapList.add(tMap);\n"
					+"            }\n"
					+"            Bean bean = new Bean(total);\n"
					+"            r.setBean(bean);\n"
					+"            r.setBeans(mapList);\n"
					+"        } catch (Exception e) {\n"
					+"            logger.error(e.getMessage());\n"
					+"        }\n"
					+"        return r;\n"
					+"    }\n\r"
//					+"    @PreAuthorize(\"hasAnyAuthority('SUPERADMIN','add"+tableName+"')\")\n"
					+"    @RequestMapping(value=\"/add"+tableName+"\",method=RequestMethod.POST)\n"
					+"    public Map add"+tableName+"(@RequestBody Map<String, String>req)throws Exception{\n"
					+"        //获取主键等非空字段，这里主键名称需要更改！\n"
					+"        String id = req.get(\"id\");\n"
					+"        Map<String,Object> resultMap = new HashMap<>();\n"
					+"        try {\n"
					+"            //判断非空字段是否为空 以及设置创建时间\n"
					+"            if(StringUtils.isEmpty(id)){\n"
					+"                id = SeqGenerateUtil.seqGenerate().toString();\n"
					+"                req.put(\"id\", id.toString());\n"
					+"            }\n"
					+"            "+tableName+" "+lowName+" = "+lowName+"SV.create"+tableName+"(req);\n"
					+"            if("+lowName+".getCrtTime()==null){\n"
					+"                "+lowName+".setCrtTime(new Date());\n"
					+"            }\n"
					+"            if ("+lowName+"SV.insertSelective("+lowName+") == 1) {\n"
					+"                resultMap.put(\"rtnCode\", Constants.OK);\n"
					+"                resultMap.put(\"rtnMsg\", \"添加成功!\");\n"
					+"                //resultMap.put(\"id\", id);\n"
					+"            }else {\n"
					+"                resultMap.put(\"rtnCode\", Constants.FAILURE);\n"
					+"                resultMap.put(\"rtnMsg\", \"添加失败!\");\n"
					+"            }\n"
					+"        } catch (Exception e) {\n"
					+"            logger.error(e.getMessage());\n"
					+"            resultMap.put(\"rtnCode\", Constants.FAILURE);\n"
					+"            resultMap.put(\"rtnMsg\", \"添加失败!\");\n"
					+"        }\n"
					+"        return resultMap;\n"
					+"    }\n\r"
//					+"    @PreAuthorize(\"hasAnyAuthority('SUPERADMIN','update"+tableName+"')\")\n"
					+"    @RequestMapping(value=\"/update"+tableName+"\",method=RequestMethod.POST)\n"
					+"    public Map update"+tableName+"(@RequestBody Map<String, String>req)throws Exception{\n"
					+"        //获取主键,这里主键名称需要更改！\n"
					+"        String id = req.get(\"id\");\n"
					+"        Map<String,Object> resultMap = new HashMap<>();\n"
					+"        try {\n"
					+"            if(StringUtils.isEmpty(id)){\n"
					+"                resultMap.put(\"rtnCode\", Constants.FAILURE);\n"
					+"                resultMap.put(\"rtnMsg\", \"缺少主键，更新失败!\");\n"
					+"                return resultMap;\n"
					+"            }\n"
					+"            "+tableName+" "+lowName+" = "+lowName+"SV.create"+tableName+"(req);\n"
					+"            if ("+lowName+"SV.updateByPrimaryKeySelective("+lowName+") == 1) {\n"
					+"                resultMap.put(\"rtnCode\", Constants.OK);\n"
					+"                resultMap.put(\"rtnMsg\", \"更新成功!\");\n"
					+"                //resultMap.put(\"id\", id);\n"
					+"            }else {\n"
					+"                resultMap.put(\"rtnCode\", Constants.FAILURE);\n"
					+"                resultMap.put(\"rtnMsg\", \"更新失败!\");\n"
					+"            }\n"
					+"        } catch (Exception e) {\n"
					+"            logger.error(e.getMessage());\n"
					+"            resultMap.put(\"rtnCode\", Constants.FAILURE);\n"
					+"            resultMap.put(\"rtnMsg\", \"更新失败!\");\n"
					+"        }\n"
					+"        return resultMap;\n"
					+"    }\n\r"
//					+"    @PreAuthorize(\"hasAnyAuthority('SUPERADMIN','delete"+tableName+"')\")\n"
					+"    @RequestMapping(value=\"/delete"+tableName+"\",method=RequestMethod.POST)\n"
					+"    public Map delete"+tableName+"(@RequestBody Map<String, Object>req)throws Exception{\n"
					+"        List<String> idList = (List<String>) req.get(\"ids\");\n"
					+"        Map<String, Object> resultMap = new HashMap<>();\n"
					+"        String strSuc = \"\";\n"
					+"        String strFail = \"\";\n"
					+"        String strNotExist = \"\";"
					+"        try {\n"
					+"            if (idList.size() != 0 && idList != null) {\n"
					+"                for (int i = 0; i < idList.size(); i++) {\n"
					+"                    Long id = Long.parseLong(idList.get(i));\n"
					+"                    if ("+lowName+"SV.selectByPrimaryKey(id) != null) {\n"
					+"                        if ("+lowName+"SV.deleteByPrimaryKey(id) == 1) {\n"
					+"                            strSuc += (id + \" \");\n"
					+"                        } else {\n"
					+"                            strFail += (id + \" \");\n"
					+"                        }\n"
					+"                   } else {\n"
					+"                       strNotExist += (id + \" \");"
					+"                   }\n"
					+"               }\n"
					+"                if (strNotExist.equals(\"\") && strFail.equals(\"\")) {\n"
					+"                    resultMap.put(\"rtnCode\", \"0\");\n"
					+"                    resultMap.put(\"rtnMsg\", strSuc + \"删除成功\");\n"
					+"                } else {\n"
					+"                    resultMap.put(\"rtnCode\", \"-9999\");\n"
					+"                    resultMap.put(\"rtnMsg\", strFail + strNotExist + \"删除失败\");\n"
					+"                }\n"
					+"            } else {\n"
					+"                resultMap.put(\"rtnCode\", \"-9999\");\n"
					+"                resultMap.put(\"rtnMsg\", \"未获取到ids\");\n"
					+"            }\n"
					+"        } catch (Exception e) {\n"
					+"            logger.error(e.getMessage());\n"
					+"        }\n"
					+"        return resultMap;\n"
					+"    }\n"
					+"}";

		
		return rsString;
	}
	
	
	
	
	
	
	
	
	//generate class getting value
	public static String MapCode(Class t){
		String clzNm=t.getName();
		int index =clzNm.lastIndexOf(".");
		String tableName = clzNm.substring(index+1);
		String rsString = "";
		String lowName = tableName.substring(0, 1).toLowerCase()+tableName.substring(1);
		Field[]  f = t.getDeclaredFields();
		
		for(Field ff:f)            //遍历字段
		{
//		  System.out.println(ff.getType().toString());    //获取字段类型
//		  System.out.println(ff.getName());    //获取字段名
		  String name = ff.getName();
		  if (!ff.getName().equals("serialVersionUID")) {
			  rsString += "        String "+name+" = req.get(\""+name+"\");\n";
			
		  }
		}
		
		
		return rsString;
	}
	
	//generate class getting value for creating example
	public static String MapCodeExm(Class t){
		String clzNm=t.getName();
		int index =clzNm.lastIndexOf(".");
		String tableName = clzNm.substring(index+1);
		String rsString = "";
		String lowName = tableName.substring(0, 1).toLowerCase()+tableName.substring(1);
		Field[]  f = t.getDeclaredFields();
		
		for(Field ff:f)            //遍历字段
		{
//		  System.out.println(ff.getType().toString());    //获取字段类型
//		  System.out.println(ff.getName());    //获取字段名
		  String name = ff.getName();
		  if (!ff.getName().equals("serialVersionUID")) {
			  if (ff.getType().toString().equals("class java.util.Date")) {
				  rsString += "        String "+name+"Start = req.get(\""+name+"Start\");\n";
				  rsString += "        String "+name+"End = req.get(\""+name+"End\");\n";
			}else {
				rsString += "        String "+name+" = req.get(\""+name+"\");\n";
				
			}
			
		  }
		}
		
		
		return rsString;
	}
	
	//generate class setting value
	public static String clsCode(Class t){
		String clzNm=t.getName();
		int index =clzNm.lastIndexOf(".");
		String tableName = clzNm.substring(index+1);
		String rsString = "";
		String lowName = tableName.substring(0, 1).toLowerCase()+tableName.substring(1);
		Field[]  f = t.getDeclaredFields();
		
	
		
		for(Field ff:f)            //遍历字段
		{
//		  System.out.println(ff.getType().toString());    //获取字段类型
//		  System.out.println(ff.getName());    //获取字段名
		  String name = ff.getName();
		  String upName = name.substring(0, 1).toUpperCase()+name.substring(1);
		  if (!ff.getName().equals("serialVersionUID")) {
			  if (ff.getType().toString().equals("class java.lang.Long")) {
				  rsString += "        if (StringUtils.isNotEmpty("+name+")) {\n"
						     +"            "+lowName+".set"+upName+"(Long.parseLong("+name+"));\n"
						     +"        }\n";
					 
			  }
			  
			  if (ff.getType().toString().equals("class java.lang.String")) {
				  rsString += "        if (StringUtils.isNotEmpty("+name+")) {\n"
						  	 +"            "+lowName+".set"+upName+"("+name+");\n"
						  	 +"        }\n";
			  }
			  
			  if (ff.getType().toString().equals("class java.lang.Integer")) {
				  rsString += "        if (StringUtils.isNotEmpty("+name+")) {\n"
						  	 +"            "+lowName+".set"+upName+"(Integer.parseInt("+name+"));\n"
						  	 +"        }\n";
			  }
			  
			  if (ff.getType().toString().equals("class java.util.Date")) {
				  
				  rsString += "        try{\n"
						  	 +"            if (StringUtils.isNotEmpty("+name+")) {\n"
						  	 +"                "+lowName+".set"+upName+"(sdf.parse("+name+")); \n"
						  	 +"            }\n"
						  	 +"        }catch (Exception e) {\n"
						  	 +"            e.printStackTrace();\n"
						  	 +"        }\n";
			  }
			  
			  if (ff.getType().toString().equals("class java.lang.Double")) {
				  rsString += "        if (StringUtils.isNotEmpty("+name+")) {\n"
				  			+ "            "+lowName+".set"+upName+"(Double.parseDouble("+name+")); \n"
				  			+ "        }\n";
			  }
		  }
		}
		
		return rsString;
	}
	
	//generate example 
	public static String exmCode(Class t){
		String clzNm=t.getName();
		int index =clzNm.lastIndexOf(".");
		String tableName = clzNm.substring(index+1);
		String rsString = "";
		String lowName = tableName.substring(0, 1).toLowerCase()+tableName.substring(1);
		Field[]  f = t.getDeclaredFields();
		for(Field ff:f)            //遍历字段
		{
//		  System.out.println(ff.getType().toString());    //获取字段类型
//		  System.out.println(ff.getName());    //获取字段名
		  String name = ff.getName();
		  String upName = name.substring(0, 1).toUpperCase()+name.substring(1);
		  if (!ff.getName().equals("serialVersionUID")) {
			  if (ff.getType().toString().equals("class java.lang.Long")) {
				  rsString += "        if (StringUtils.isNotEmpty("+name+")) {\n"
				  			+ "            criteria.and"+upName+"EqualTo(Long.parseLong("+name+"));\n"
				  			+ "        }\n";
					 
			  }
			  if (ff.getType().toString().equals("class java.lang.String")) {
				  rsString += "        if (StringUtils.isNotEmpty("+name+")) {\n"
				  			+ "            criteria.and"+upName+"EqualTo("+name+");\n"
				  			+ "        }\n";
			  }
			  if (ff.getType().toString().equals("class java.util.Date")) {
				  rsString += "        try{\n"
						  	+ "            if (StringUtils.isNotEmpty("+name+"Start) && StringUtils.isNotEmpty("+name+"End)) {\n"
						  	+ "                criteria.and"+upName+"Between(sdf.parse("+name+"Start),sdf.parse("+name+"End));\n"
						  	+ "            }\n"
						  	 +"        }catch (Exception e) {\n"
						  	 +"            e.printStackTrace();\n"
						  	 +"        }\n";
			  }
			  if (ff.getType().toString().equals("class java.lang.Double")) {
				  rsString += "        if (StringUtils.isNotEmpty("+name+")) {\n"
				  			+ "            criteria.and"+upName+"EqualTo(Double.parseDouble("+name+"));\n"
				  			+ "        }\n";
			  }
			  
			  if (ff.getType().toString().equals("class java.lang.Integer")) {
				  rsString += "        if (StringUtils.isNotEmpty("+name+")) {\n"
				  			+ "            criteria.and"+upName+"EqualTo(Integer.parseInt("+name+"));\n"
				  			+ "        }\n";
			  }
		  }
		}
		return rsString;
	}

	//generate class getting value
	public static String MapCodeQry(Class t){
		String clzNm=t.getName();
		int index =clzNm.lastIndexOf(".");
		String tableName = clzNm.substring(index+1);
		String rsString = "";
		String lowName = tableName.substring(0, 1).toLowerCase()+tableName.substring(1);
		Field[]  f = t.getDeclaredFields();
		
		for(Field ff:f)            //遍历字段
		{
//			  System.out.println(ff.getType().toString());    //获取字段类型
//			  System.out.println(ff.getName());    //获取字段名
		  String name = ff.getName();
		  if (!ff.getName().equals("serialVersionUID")) {
			  if (ff.getType().toString().equals("class java.util.Date")) {
				  rsString += "             @RequestParam(name=\""+name+"Start\",required=false)String "+name+"Start,\n";
				  rsString += "             @RequestParam(name=\""+name+"End\",required=false)String "+name+"End,\n";
			  }else {
				  rsString += "             @RequestParam(name=\""+name+"\",required=false)String "+name+",\n";
				
			}
			
		  }
		}
		
		
		return rsString;
	}
	//generate controller example 
	public static String MapCodeQryExm(Class t){
		String clzNm=t.getName();
		int index =clzNm.lastIndexOf(".");
		String tableName = clzNm.substring(index+1);
		String rsString = "";
		String lowName = tableName.substring(0, 1).toLowerCase()+tableName.substring(1);
		Field[]  f = t.getDeclaredFields();
		
		for(Field ff:f)            //遍历字段
		{
//			  System.out.println(ff.getType().toString());    //获取字段类型
//			  System.out.println(ff.getName());    //获取字段名
		  String name = ff.getName();
		  if (!ff.getName().equals("serialVersionUID")) {
			  if (ff.getType().toString().equals("class java.util.Date")) {
				  rsString += "            "+lowName+"ExmMap.put(\""+name+"Start\", "+name+"Start);\n";
				  rsString += "            "+lowName+"ExmMap.put(\""+name+"End\", "+name+"End);\n";
			  }else {
				  rsString += "            "+lowName+"ExmMap.put(\""+name+"\", "+name+");\n";
				
			}
			
		  }
		}
		
		
		return rsString;
	}
	//generate controller output
	public static String MapCodeQryOut(Class t){
		String clzNm=t.getName();
		int index =clzNm.lastIndexOf(".");
		String tableName = clzNm.substring(index+1);
		String rsString = "";
		String lowName = tableName.substring(0, 1).toLowerCase()+tableName.substring(1);
		Field[]  f = t.getDeclaredFields();
		
		for(Field ff:f)            //遍历字段
		{
//			  System.out.println(ff.getType().toString());    //获取字段类型
//			  System.out.println(ff.getName());    //获取字段名
		  String name = ff.getName();
		  String upName = name.substring(0, 1).toUpperCase()+name.substring(1);
		  if (!ff.getName().equals("serialVersionUID")) {
			  if (ff.getType().toString().equals("class java.lang.String")) {
				  rsString += "                tMap.put(\""+name+"\", "+lowName+".get"+upName+"());\n";
			  }else {
				if (ff.getType().toString().equals("class java.util.Date")) {
					 rsString += "                if("+lowName+".get"+upName+"()!=null){\n"
						  	 	+"                    tMap.put(\""+name+"\", sdf.format("+lowName+".get"+upName+"()));\n"
						  	 	+"                }\n";
				}else {
					 rsString += "                if("+lowName+".get"+upName+"()!=null){\n"
						  	 	+"                    tMap.put(\""+name+"\", "+lowName+".get"+upName+"().toString());\n"
						  	 	+"                }\n";
				  			
				}
			  }
			  
		  }
		}
		
		
		return rsString;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(SVImplCode(SecAuthority.class));
//		System.out.println(ControllerCode(ReportlcStatisData.class));
		///// 只需要改这里
		Class t = User.class;
		////
		String isv = ISVCode(t); 
		String svimpl = SVImplCode(t);
		String controller = ControllerCode(t);
		String clzNm=t.getName();
		int index =clzNm.lastIndexOf(".");
		String tableName = clzNm.substring(index+1);
		 try {  
			   System.out.println(tableName);
	            File file = new File("D:/autoCode/I"+tableName+"SV.java");
	            // if file doesnt exists, then create it  
	            if (!file.exists()) {  
	                file.createNewFile();  
	            }  
	            FileWriter fw = new FileWriter(file, true);  
	            BufferedWriter bw = new BufferedWriter(fw);  
	            bw.write(isv);  
	            bw.flush();  
	            bw.close();  
	            System.out.println(file.getName());
	            
//	            
	            File fileSVimpl = new File("D:/autoCode/"+tableName+"SVImpl.java");
	            // if file doesnt exists, then create it  
	            if (!fileSVimpl.exists()) {  
	            	fileSVimpl.createNewFile();  
	            }  
	            FileWriter fwSVimpl = new FileWriter(fileSVimpl, true);  
	            BufferedWriter bwSVimpl = new BufferedWriter(fwSVimpl);  
	            bwSVimpl.write(svimpl);  
	            bwSVimpl.flush();  
	            bwSVimpl.close();  
	            System.out.println(fileSVimpl.getName());
	            
	           // File fileCon = new File("E:/codeGen/"+tableName+"Controller.java");  
	            File fileCon = new File("D:/autoCode/"+tableName+"Controller.java");
	            // if file doesnt exists, then create it  
	            if (!fileCon.exists()) {  
	            	fileCon.createNewFile();  
	            }  
	            FileWriter fwCon = new FileWriter(fileCon, true);  
	            BufferedWriter bwCon = new BufferedWriter(fwCon);  
	            bwCon.write(controller);  
	            bwCon.flush();  
	            bwCon.close();  
	            System.out.println(fileCon.getName());
	            
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	}

}
