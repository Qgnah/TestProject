package dao;

import java.util.List;
import java.util.Map;

/*
 * 公共的dao  T 表示：你调用我的时候给我传的泛型是什么，T就代表什么
 * 
 * 如：service层：  BaseDao<Users>  : T==Users
 */
public interface BaseDao<T> {
	//基础的添加  修改  删除
//	public int add(Object[] obj,String sql);
//	public int update(Object[] obj,String sql);
//	public int delete(Object[] obj,String sql);
	//三合一
	public int add_update_delete(Object[] obj,String sql);
	
	//基础的查询:单个查询
	//public T selectone(Object[] obj,String sql);
	public Map<String, Object> selectone(Object[] obj,String sql);
	//查询全部：集合的泛型是T
	//public List<T> selectall();
	public List<Map<String, Object>> selectallMap(String sql,Object[] obj);
	//查询总条数
	public Long count(Object[] obj,String sql);
	
	
	
	

}
