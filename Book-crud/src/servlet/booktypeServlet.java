package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import service.booktypeService;
import service.Impl.booktypeServiceImpl;

@WebServlet("/booktypeServlet")
public class booktypeServlet extends HttpServlet {
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
			selectall(request, response);
		}
	}
	booktypeService bt=new booktypeServiceImpl();
	protected void selectall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Map<String, Object>> listbt=bt.selectall();
		PrintWriter out=response.getWriter();
		String json=JSON.toJSONStringWithDateFormat(listbt,"yyyy-MM-dd");
		out.print(json);
		out.flush();
		out.close();
	}
}
