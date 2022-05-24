package entity;

public class book {
	private String bookno;
	private String bookname;
	private double price;
	private String cometime;
	private String pic;
	private Integer btid;
	private String btname;
	public book(String bookno, String bookname, double price, String cometime, String pic, Integer btid,
			String btname) {
		super();
		this.bookno = bookno;
		this.bookname = bookname;
		this.price = price;
		this.cometime = cometime;
		this.pic = pic;
		this.btid = btid;
		this.btname = btname;
	}
	public book( String bookname, double price, String cometime, String pic, Integer btid,
			String btname) {
		super();
		
		this.bookname = bookname;
		this.price = price;
		this.cometime = cometime;
		this.pic = pic;
		this.btid = btid;
		this.btname = btname;
	}
	public book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getBookno() {
		return bookno;
	}
	public void setBookno(String bookno) {
		this.bookno = bookno;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCometime() {
		return cometime;
	}
	public void setCometime(String cometime) {
		this.cometime = cometime;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Integer getBtid() {
		return btid;
	}
	public void setBtid(Integer btid) {
		this.btid = btid;
	}
	public String getBtname() {
		return btname;
	}
	public void setBtname(String btname) {
		this.btname = btname;
	}
	
}
