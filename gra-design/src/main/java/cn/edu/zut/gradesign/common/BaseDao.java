package cn.edu.zut.gradesign.common;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
public interface BaseDao<T>{
	int insert(T entity) ;
    int deleteByPrimaryKey(Integer id);
    int deleteByEntity(T entity);
    int deleteByMap(Map<String,Object> params);
    int update(T entity);
    int updateByPrimaryKey(T entity);
    public List<T> listByMap(Map<String,Object> params);
    List<T> listAll();
    List<T> listAllByEntity(T entity);
    T load(Integer id);
    T getById(Integer id);
    T getByMap(Map<String,Object> params);
    public T getByEntity(T entity);
    public List<T> findByMap(Map<String,Object> params);
    public List<T> findByEntity(T entity);
    public int insertBatch(List<T> list);
    public int updateBatch(List<T> list);
    //==============================封装纯sql语法================================
    public Map<String,Object> getBySqlReturnMap(@Param("sql")String sql);
    public T getBySqlReturnEntity(@Param("sql")String sql);
    public List<Map<String,Object>> listBySqlReturnMap(@Param("sql")String sql);
    public List<T> listBySqlReturnEntity(@Param("sql")String sql);
    public List<T> findBySqlRerturnEntity(@Param("sql")String sql);
    public void updateBysql(@Param("sql")String sql);
    public void deleteBySql(@Param("sql")String sql);
}
