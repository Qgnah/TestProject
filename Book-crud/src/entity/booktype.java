package entity;

public class booktype {
	
	private int id;
	private String btname;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBtname() {
		return btname;
	}
	public void setBtname(String btname) {
		this.btname = btname;
	}
	public booktype(int id, String btname) {
		super();
		this.id = id;
		this.btname = btname;
	}
	
	public booktype(String btname) {
		super();
		this.btname = btname;
	}
	public booktype(){}

}
