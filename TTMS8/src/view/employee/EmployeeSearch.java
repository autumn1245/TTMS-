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
import entity.Seat;
import service.EmployeeSrv;
import service.SeatSrv;

@WebServlet("/EmployeeSearch")
public class EmployeeSearch extends HttpServlet{
	private static final long servialVersionUID=1L;
	
	public EmployeeSearch() {
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("emp_name");
//		int employeeId=Integer.valueOf(req.getParameter("employeeId"));
		name=new String(name.getBytes("ISO-8859-1"),"utf-8");
		List<Employee> result=null;
		if(name!=null){
			result=new EmployeeSrv().Fetch("emp_name like '%"+name+"%'");
		}else{
			result=new EmployeeSrv().FetchAll();
		}
		String jsonStr="[";
		for(Employee e:result){
			int power = e.getPower();
			String  n = null;
			if(power == 0) {
				n = "最高权限";
			} else if(power == 1) {
				n = "财务员";
			} else if(power == 2) {
				n = "售票员";
			}
			jsonStr+="{\"emp_id\":\""+e.getId() +
					"\",\"emp_name\":\""+e.getName()+
					"\",\"emp_tel_num\":\""+e.getTelNum() +
					"\",\"emp_addr\":\""+e.getAddress() +
					"\",\"emp_email\":\""+e.getEmail()+ 
					"\",\"emp_power\":\""+n+"\"}";
			jsonStr += ",";
			//System.out.println(jsonStr);
		}
		if(result.size()==0){
			jsonStr+="]";
		}else{
			jsonStr=jsonStr.substring(0,jsonStr.length()-1)+"]";
			resp.setContentType("application/json;charset=utf-8");
			resp.setCharacterEncoding("utf-8");
			PrintWriter out=resp.getWriter();
			out.write(jsonStr);
			out.close();
		}
	}
}
