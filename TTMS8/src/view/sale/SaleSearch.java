package view.sale;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Sale;
import entity.Seat;
import service.SaleSrv;
import service.SeatSrv;

public class SaleSearch {
	private static final long servialVersionUID=1L;
	
	public SaleSearch() {
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String name=req.getParameter("name");
		int empId=Integer.valueOf(req.getParameter("empId"));
//		name=new String(name.getBytes("ISO-8859-1"),"utf-8");
		List<Sale> result=null;
		if(empId!=0){
			result=new SaleSrv().Fetch("emp_id like '%"+empId+"%'");
		}else{
			result=new SaleSrv().FetchAll();
		}
		String jsonStr="[";
		for(Sale s:result){
			jsonStr+="{\"id\":\""+s.getId() + 
					"\",\"empId\":\""+s.getEmpId() +
					"\",\"time\":\""+s.getTime()+
					"\",\"payment\":\""+s.getPayment()+
					"\",\"change\":\""+s.getChange()+
					"\",\"type\":\""+s.getType()+
					"\",\"status\":\""+s.getStatus()+ "\"}";
			jsonStr += ",";
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
