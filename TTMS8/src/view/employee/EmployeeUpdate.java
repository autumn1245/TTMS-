package view.employee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Employee;
import service.EmployeeSrv;

@WebServlet("/EmployeeUpdate")
public class EmployeeUpdate extends HttpServlet{
	private static final long serialVersionUID=1L;
	
	public EmployeeUpdate() {
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("员工到此一游");
		Employee emp=null;
		String type=req.getParameter("type");
		//System.out.println("type:" + type);
		int id=0;
		try{
			if(type.equals("modify")){
				id=Integer.valueOf(req.getParameter("emp_id"));
			}
			resp.setContentType("text/html;charset=utf-8");
			String name=req.getParameter("emp_name");
			name=new String(name.getBytes("ISO-8859-1"),"utf-8");
			String telNum=req.getParameter("emp_tel_num");
			telNum=new String(telNum.getBytes("ISO-8859-1"),"utf-8");
			String addr=req.getParameter("emp_addr");
			addr=new String(addr.getBytes("ISO-8859-1"),"utf-8");
			String email=req.getParameter("emp_email");
			email=new String(email.getBytes("ISO-8859-1"),"utf-8");
			String power = req.getParameter("emp_power");
			power=new String(power.getBytes("ISO-8859-1"),"utf-8");
			int n = -1;
			if("最高权限".equals(power)) {
				n = 0;
			} else if("财务员".equals(power)) {
				n = 1;
			} else if("售票员".equals(power)) {
				n = 2;
			}
			emp = new Employee(id, name, telNum, addr, email, n);
			PrintWriter out=resp.getWriter();
			if(type.equals("add")){
				if(new EmployeeSrv().add(emp)==1){
					out.write("数据添加成功");
				}else{
					out.write("数据添加失败，请重试");
				}
			}else{
				if(new EmployeeSrv().modify(emp)==1){
					out.write("数据修改成功");
				}else{
					out.write("数据修改失败，请重试");
				}
			}
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write("操作错误，请重试");
		}
	}
}
