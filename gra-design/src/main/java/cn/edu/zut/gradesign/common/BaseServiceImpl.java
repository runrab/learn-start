package cn.edu.zut.gradesign.common;
import cn.edu.zut.gradesign.bean.common.Pager;
import cn.edu.zut.gradesign.utils.SystemContext;
import com.github.pagehelper.PageHelper;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
public abstract  class BaseServiceImpl<T> implements BaseService<T>{
    private BaseDao<T> baseDao;  
    public abstract BaseDao<T> getBaseDao();
	@Override
	public int insert(T entity) {
		return this.getBaseDao().insert(entity);
	}
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.getBaseDao().deleteByPrimaryKey(id);
	}
	@Override
	public int deleteByEntity(T entity) {
		return this.getBaseDao().deleteByEntity(entity);
	}
	@Override
	public int deleteByMap(Map<String, Object> params) {

		return this.getBaseDao().deleteByMap(params);
	}
	@Override
	public int update(T entity) {
		return this.getBaseDao().update(entity);
	}
	@Override
	public int updateByPrimaryKey(T entity) {
         return this.getBaseDao().updateByPrimaryKey(entity);
	}
	@Override
	public List<T> listByMap(Map<String, Object> params) {
		return this.getBaseDao().listByMap(params);
	}
	@Override
	public List<T> listAll() {
		return this.getBaseDao().listAll();
	}
	@Override
	public List<T> listAllByEntity(T entity) {
		return this.getBaseDao().listAllByEntity(entity);
	}
	@Override
	public T load(Integer id) {
		return this.getBaseDao().load(id);
	}
	@Override
	public T getById(Integer id) {
		return this.getBaseDao().getById(id);
	}
	@Override
	public T getByMap(Map<String, Object> params) {
		return this.getBaseDao().getByMap(params);
	}
	@Override
	public T getByEntity(T entity) {
		return this.getBaseDao().getByEntity(entity);
	}
	@Override
	public Pager<T> findByMap(Map<String, Object> params) {
    	Integer pageSize = SystemContext.getPageSize();
		Integer pageOffset = SystemContext.getPageOffset();
		if(pageOffset==null||pageOffset<0) {
			pageOffset = 0;
		}
		if(pageSize==null||pageSize<0) {
			pageSize = 15;
		}
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();
		Integer pageNum = null;
		if(pageOffset == 0){
			pageNum = 1;
		}else{
			pageNum = pageOffset/pageSize+1;
		}
		PageHelper.startPage(pageNum, pageSize);
//		Pager<T> pages = new Pager<T>(this.getBaseDao().findByMap(params));//		将这一行替换为下边三行
		Pager<T> pages = new Pager<T>();
		List<T> lst=this.getBaseDao().findByMap(params);
		pages.setDatas(lst);
    	return pages;
	}
    @Override
	public Pager<T> findByEntity(T entity){
    	Integer pageSize = SystemContext.getPageSize();
		Integer pageOffset = SystemContext.getPageOffset();
		if(pageOffset==null||pageOffset<0) {
			pageOffset = 0;
		}
		if(pageSize==null||pageSize<0) {
			pageSize = 15;
		}
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();
		Integer pageNum = null;
		if(pageOffset == 0){
			pageNum = 1;
		}else{
			pageNum = pageOffset/pageSize+1;
		}
		PageHelper.startPage(pageNum, pageSize);
//		Pager<T> pages = new Pager<T>(this.getBaseDao().findByEntity(entity));//		将这一行替换为下边三行
		List<T> lst=this.getBaseDao().findByEntity(entity);
		Pager<T> pages = new Pager<T>();
		pages.setDatas(lst);

    	return pages;
    }
	 @Override
	 public void insertBatch(List<T> list) {
		 this.getBaseDao().insertBatch(list);
	}
	@Override
	public void updateBatch(List<T> list) {
		 this.getBaseDao().updateBatch(list);
	}
	//=====================自定义sql=========================================
	@Override
	public Map<String, Object> getBySql(String sql) {
		
		return  this.getBaseDao().getBySqlReturnMap(sql);
	}
	@Override
	public T getBySqlReturnEntity(String sql) {
		return this.getBaseDao().getBySqlReturnEntity(sql);
	}
	@Override
	public List<Map<String, Object>> listBySqlReturnMap(String sql) {
		return this.getBaseDao().listBySqlReturnMap(sql);
	}
	@Override
	public List<T> listBySqlReturnEntity(String sql) {
		return this.getBaseDao().listBySqlReturnEntity(sql);
	}
	@Override
	public Pager<T> findBySqlRerturnEntity(String sql) {
    	Integer pageSize = SystemContext.getPageSize();
		Integer pageOffset = SystemContext.getPageOffset();
		if(pageOffset==null||pageOffset<0) {
			pageOffset = 0;
		}
		if(pageSize==null||pageSize<0) {
			pageSize = 15;
		}
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();
		Integer pageNum = null;
		if(pageOffset == 0){
			pageNum = 1;
		}else{
			pageNum = pageOffset/pageSize+1;
		}
		PageHelper.startPage(pageNum, pageSize);
//		Pager<T> pages = new Pager<T>(this.getBaseDao().findBySqlRerturnEntity(sql));//		将这一行替换为下边三行
		Pager<T> pages = new Pager<T>();
		List<T> lst=this.getBaseDao().findBySqlRerturnEntity(sql);
		pages.setDatas(lst);
    	return pages;
	}
	@Override
	public void updateBysql(String sql) {
		this.getBaseDao().updateBysql(sql);
	}
	@Override
	public void deleteBySql(String sql) {
		this.getBaseDao().deleteBySql(sql);
	}
	public boolean isEmpty(String str) {
		return (null == str) || (str.trim().length() <= 0);
	}
	public boolean isEmpty(Character cha) {
		return (null == cha) || cha.equals(' ');
	}
	public boolean isEmpty(Object obj) {
		return (null == obj);
	}
	public boolean isEmpty(Object[] objs) {
		return (null == objs) || (objs.length <= 0);
	}
	public boolean isEmpty(Collection<?> obj) {
		return (null == obj) || obj.isEmpty();
	}
	public boolean isEmpty(Set<?> set) {
		return (null == set) || set.isEmpty();
	}
	public boolean isEmpty(Serializable obj) {
		return null == obj;
	}
	public boolean isEmpty(Map<?, ?> map) {
		return (null == map) || map.isEmpty();
	}
}
