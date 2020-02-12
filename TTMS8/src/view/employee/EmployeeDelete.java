package view.employee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.EmployeeSrv;

@WebServlet("/EmployeeDelete")
public class EmployeeDelete extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public EmployeeDelete() {
		super();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try{
			int id=Integer.valueOf(req.getParameter("emp_id"));
			resp.setContentType("text/html;charset=utf_8");
			PrintWriter out=resp.getWriter();
			out.write(""+new EmployeeSrv().delete(id));
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write("操作错误，请重试");
		}
	}
}
