package view.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Employee;
import service.EmployeeSrv;
@WebServlet("/EmployeeLogin")
public class EmployeeLogin extends HttpServlet{
private static final long servialVersionUID=1L;
	
	public EmployeeLogin() {
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("username");
		name=new String(name.getBytes("ISO-8859-1"),"utf-8");
		System.out.println(name);
		String tel = req.getParameter("password");
		tel=new String(tel.getBytes("ISO-8859-1"),"utf-8");
		System.out.println(tel);
		
		PrintWriter out = resp.getWriter();
		List<Employee> list = null;
		if(name != null && tel != null) {
			list = new EmployeeSrv().Fetch("emp_name = '"+name+"'");
		}
		Employee employee = new Employee();
		employee = list.get(0);
		System.out.println(employee);
		if(employee != null && tel.equals(employee.getTelNum())) {
			int power = employee.getPower();
			if(power == 0) {
				out.write("0");
				System.out.println("最高权限");
			} else if(power == 1){
				out.write("1");
				System.out.println("财务员");
			} else if(power == 2) {
				out.write("2");
				System.out.println("售票员");
			}
		}else {
			out.write("-1");
			System.out.println("登陆失败");
		}
		
		
		out.close();
	}
}
