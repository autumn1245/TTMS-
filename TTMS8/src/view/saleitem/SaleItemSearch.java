package view.saleitem;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.SaleItem;
import entity.Seat;
import service.SaleItemSrv;

public class SaleItemSearch {
	private static final long servialVersionUID=1L;
	
	public SaleItemSearch() {
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String name=req.getParameter("name");
		int sale_ID=Integer.valueOf(req.getParameter("sale_ID"));
//		name=new String(name.getBytes("ISO-8859-1"),"utf-8");
		List<SaleItem> result = null;
		if(sale_ID != 0){
			result = new SaleItemSrv().Fetch("sale_ID like '%"+sale_ID+"%'");
		}else{
			result = new SaleItemSrv().FetchAll();
		}
		String jsonStr="[";
		for(SaleItem s:result){
			jsonStr+="{\"sale_item_id\":\""+s.getId() + 
					"\",\"ticket_id\":\""+s.getTicketId() +
					"\",\"sale_ID\":\""+ s.getSaleId()+
					"\",\"sale_item_price\":\""+s.getPrice()+ "\"}";
			jsonStr += ",";
		}
		if(result.size() == 0){
			jsonStr += "]";
		}else{
			jsonStr=jsonStr.substring(0,jsonStr.length()-1)+"]";
			resp.setContentType("application/json;charset=utf-8");
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.write(jsonStr);
			out.close();
		}
	}
}
