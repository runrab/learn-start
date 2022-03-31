package cn.edu.zut.gradesign.common;
import cn.edu.zut.gradesign.bean.common.Pager;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;


public interface BaseService<T> {
	int insert(T entity) ;
    int deleteByPrimaryKey(Integer id);
    int update(T entity);
    int updateByPrimaryKey(T entity);
    int deleteByEntity(T entity);
    int deleteByMap(Map<String,Object> params);
    public List<T> listByMap(Map<String,Object> params);
    List<T> listAll();
    List<T> listAllByEntity(T entity);
    T load(Integer id);
    T getById(Integer id);
    T getByMap(Map<String,Object> params);
    T getByEntity(T entity);
    //=======================一下是分页方法================================
    public Pager<T> findByMap(Map<String,Object> params);
    public Pager<T> findByEntity(T entity);
    public void insertBatch(List<T> list);
    public void updateBatch(List<T> list);
    //============================通过sql=========================================
    public Map<String,Object> getBySql(@Param("sql")String sql);
    public T getBySqlReturnEntity(@Param("sql")String sql);
    public List<Map<String,Object>> listBySqlReturnMap(@Param("sql")String sql);
    public List<T> listBySqlReturnEntity(@Param("sql")String sql);
    public Pager<T> findBySqlRerturnEntity(@Param("sql")String sql);
    public void updateBysql(@Param("sql")String sql);
    public void deleteBySql(@Param("sql")String sql);
}
