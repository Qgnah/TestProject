package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataGrideBean {
	
	private int page;
	private Long sumpage;
	private List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public Long getSumpage() {
		return sumpage;
	}
	public void setSumpage(Long sumpage) {
		this.sumpage = sumpage;
	}
	public List<Map<String, Object>> getList() {
		return list;
	}
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}
	public DataGrideBean(int page, Long sumpage, List<Map<String, Object>> list) {
		super();
		this.page = page;
		this.sumpage = sumpage;
		this.list = list;
	}
	public DataGrideBean(){}

}
