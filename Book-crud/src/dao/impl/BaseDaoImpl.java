package dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import utils.utils;

import dao.BaseDao;

/*
 * DBUtils工具类：BeanListHandler、BeanHandler、Maphandler、MapListHandler
 * 
 * 	我们执行sql用的是jdbc的一个工具类：DBUtils,他提供了一个QueryRunner类
 * 
 * 添加、修改、删除   都用update方法，方法里的参数：
 *      第一个是链接数据库信息的
 *      第二个参数传递的是需要执行的sql
 *      第三个参数是为？赋予具体的值（一般都会把？需要赋值的参数封装成object数组，进行传递）
 * 
 * 多个查询：用的是 query方法：里面的参数介绍：
 *      第一个是链接数据库信息的
 *      第二个参数传递的是需要执行的sql
 *      第三个参数是根据查询的结果来定的，单个查询只需要返回一条数据即可，所用用 new BeanListHandler
 * 
 * 多个查询集合泛型是map的时候 用的是new MapListHandler()
 * 
 * 单个查询：单个查询用的是 query方法：里面的参数介绍：
 *      第一个是链接数据库信息的
 *      第二个参数传递的是需要执行的sql
 *      第三个参数是根据查询的结果来定的，单个查询只需要返回一条数据即可，所用用 new BeanHandler
 *      第四个参数就是需要给sql中？赋值
 *      ---------------------------
 * 单个查询 不想要返回的实体类，我需要用map:new MapHandler();
 *      
 * 查询条数：
 */
public class BaseDaoImpl<T> implements BaseDao<T> {
	//链接数据库
	Connection con=utils.getcon();
	//三合一：添加 修改  删除  公用一个dao方法
	@Override
	public int add_update_delete(Object[] obj, String sql) {
		int a=0;
		try {
			QueryRunner q=new QueryRunner();
			a=q.update(con, sql, obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	//单个查询 ：返回的是map
	@Override
	public Map<String, Object> selectone(Object[] obj, String sql) {
		Map<String, Object> map=new HashMap();
		try {
			QueryRunner q=new QueryRunner();
			map = q.query(con,sql,new MapHandler(),obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	//查询全部
	@Override
	public List<Map<String, Object>> selectallMap(String sql, Object[] obj) {
		List<Map<String, Object>> list=new ArrayList();
		try {
			QueryRunner q=new QueryRunner();
			list = q.query(con,sql,new MapListHandler(),obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	//查询数据条数
	@Override
	public Long count(Object[] obj, String sql) {
		Long c=null;
		try {
			QueryRunner q=new QueryRunner();
			c =  q.query(con,sql,new ScalarHandler<>(),obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

}
