package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.alibaba.fastjson.JSON;
import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;

import entity.DataGrideBean;
import entity.book;
import service.bookService;
import service.Impl.bookServiceImpl;
import utils.UsePoiOutExcel;


@WebServlet("/bookServlet")
public class bookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		doPost(request, response);	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String qf = request.getParameter("qf");
		if("1".equals(qf)){
			addbook(request, response);
		}else if("2".equals(qf)){
			selectallbook(request, response);
		}else if("3".equals(qf)){
			delbook(request, response);
		}else if("4".equals(qf)){
			selectonebook(request, response);
		}else if("5".equals(qf)){
			updatebook(request, response);
		}else if("6".equals(qf)){
			dcbook(request, response);
		}
	}
	bookService bs=new bookServiceImpl();
	protected void addbook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//取文本框的值；
				SmartUpload su=new SmartUpload();
				//SmartUpload里面包含了那些功能：
				//把jsp的内置对象转化  可以在servlet中使用
				PageContext pc=JspFactory.getDefaultFactory()
						.getPageContext(this, request, response, null, true, 8192, true);
				//初始化
				su.initialize(pc);
				File file=null;
				//允许你上传图片的格式
				String allowed="gif,jpg,png";
				//不允许
				String denied="exe,bat,html,jsp";
				//上传图片的大小
				int file_size=2*1024*1024;
				String filepath = null;
				try {
						//定义允许上传文件类型   
						su.setAllowedFilesList(allowed);
						//不允许上传文件类型   
						su.setDeniedFilesList(denied);		
						//单个文件最大限制   
						su.setMaxFileSize(file_size);						
						su.setCharset("utf-8");
						//执行上传
						su.upload();
						//得到单个上传文件的信息
						file = su.getFiles().getFile(0);
						if(!file.isMissing()){
							//设置文件在服务器的保存位置
							filepath = "img/";
							filepath += file.getFileName();
							//文件另存为   
							file.setCharset("utf-8");
				file.saveAs(filepath, SmartUpload.SAVE_VIRTUAL);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				System.out.println("图片路径："+filepath);
				
				 //用了smartupload  servlet的request失效，不能用request获取文本框的值
				String bookno=su.getRequest().getParameter("bookno");
				String bookname=su.getRequest().getParameter("bookname");
				String price=su.getRequest().getParameter("price");
				String cometime=su.getRequest().getParameter("cometime");
				String btid=su.getRequest().getParameter("booktypeid");
          int a= bs.addbook(new book(bookno, bookname, Double.parseDouble(price), cometime, filepath, Integer.parseInt(btid), null));
          if(a>0){
        	  response.sendRedirect("showbook.html");
          }
          
	}
	protected void selectallbook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		List<Map<String,Object>> listbook=bs.selectallbook(null, Integer.parseInt(page));
		Long count = bs.countbook(null);
		Long sumpage=count%2>0?(count/2+1):count/2;
		System.out.println(page+"=="+count+"=="+sumpage+"==="+listbook.size());
		DataGrideBean dgb=new DataGrideBean(Integer.parseInt(page), sumpage, listbook);
		PrintWriter out=response.getWriter();
		String json=JSON.toJSONStringWithDateFormat(dgb, "yyyy-MM-dd");
		out.print(json);
		out.flush();
		out.close();
	}
	protected void delbook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookno = request.getParameter("bookno");
		int a = bs.delbook(bookno);
		 if(a>0){
			  response.sendRedirect("showbook.html");
		  }
	}
	protected void selectonebook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookno = request.getParameter("bookno");
		Map<String,Object> book = bs.selectonebook(bookno);
		PrintWriter out=response.getWriter();
		String json=JSON.toJSONStringWithDateFormat(book, "yyyy-MM-dd");
		out.print(json);
		out.flush();
		out.close();
	}
	protected void updatebook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookno=request.getParameter("bookno");
		String bookname=request.getParameter("bookname");
		String price=request.getParameter("price");
		String cometime=request.getParameter("cometime");
		String btid=request.getParameter("booktypeid");
		System.out.println(bookname+price+cometime+btid);
		book b = new book(bookno,bookname,Double.parseDouble(price), cometime, null, Integer.parseInt(btid), null);
		int a = bs.updatebook(b);
		if(a>0){
			response.sendRedirect("showbook.html");
		}
	}
	protected void dcbook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] ob={"图书编号","图书名称","类型","价格","时间"};
		List<Map<String, Object>> list=bs.dcbook();
		//存放表头+数据
		List<String[]> alls=new ArrayList<String[]>();
	        alls.add(ob);
	        
	     for(int i=0;i<list.size();i++){
	    	 Map<String, Object> b=list.get(i);
	    	  String[] s={b.get("bookno").toString(),b.get("bookname").toString(),b.get("btname").toString(),String.valueOf(b.get("price")),b.get("cometime").toString()};
	    	  alls.add(s);
	      }
	       String excelName = "图书表"; //导出的文件名称
		String[] cellFormatType = {"t","t","t","t","t"};//这个是有多少列明 就有多少个t
		UsePoiOutExcel.writeExcel(alls, 5, response, excelName,excelName + ".xls", cellFormatType);
		
	}
}
