package service;

import java.util.List;
import java.util.Map;

import entity.book;

public interface bookService {
	public int addbook(book b);
	public int updatebook(book b);
	public int delbook(String bookno);
	
	public Map<String, Object> selectonebook(String bookno);
	public Long countbook(book b);
	public List<Map<String,Object>> selectallbook(book b,int page);
	
	public List<Map<String,Object>> dcbook();
}
