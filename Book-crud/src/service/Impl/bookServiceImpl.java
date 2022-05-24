package service.Impl;

import java.util.List;
import java.util.Map;

import dao.BaseDao;
import dao.impl.BaseDaoImpl;
import entity.book;
import service.bookService;

public class bookServiceImpl implements bookService{
	BaseDao<book> bd = new BaseDaoImpl<book>();
	@Override
	public int addbook(book b) {
		String sql="insert into book(bookno,bookname,price,cometime,pic,btid) values(?,?,?,?,?,?)";
		Object[] obj={b.getBookno(),b.getBookname(),b.getPrice(),b.getCometime(),b.getPic(),b.getBtid()};
		return bd.add_update_delete(obj, sql);
	}

	@Override
	public int updatebook(book b) {
		System.out.println(b.getBookno());
		String sql="update book set bookname=?,price=?,cometime=?,btid=? where bookno=?";
		Object[] obj={b.getBookname(),b.getPrice(),b.getCometime(),b.getBtid(),b.getBookno()};
		return bd.add_update_delete(obj, sql);
	}

	@Override
	public int delbook(String bookno) {
		String sql="delete from book where bookno='"+bookno+"'";
		return bd.add_update_delete(null, sql);
	}

	@Override
	public Map<String, Object> selectonebook(String bookno) {
		String sql="select * from book where bookno=?";
		Object obj[]={bookno};
		return bd.selectone(obj, sql);
	}

	@Override
	public Long countbook(book b) {
		String sql="select count(1) c from book b,booktype bt where b.btid=bt.id";
		return bd.count(null, sql);
	}

	@Override
	public List<Map<String, Object>> selectallbook(book b, int page) {
		String sql="select b.*,bt.btname from book b,booktype bt where b.btid=bt.id limit "+(page-1)*2+",2";
		return bd.selectallMap(sql, null);
	}

	@Override
	public List<Map<String, Object>> dcbook() {
		String sql="select * from book b,booktype bt where bt.id=b.btid";
		return bd.selectallMap(sql,null);
	}
	
}
