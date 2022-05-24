package service.Impl;

import java.util.List;
import java.util.Map;

import dao.BaseDao;
import dao.impl.BaseDaoImpl;
import entity.booktype;
import service.booktypeService;

public class booktypeServiceImpl implements booktypeService{
	BaseDao<booktype> bd=new BaseDaoImpl<booktype>();

	@Override
	public List<Map<String, Object>> selectall() {
		String sql="select * from booktype";
		return bd.selectallMap(sql, null);
	}

}
