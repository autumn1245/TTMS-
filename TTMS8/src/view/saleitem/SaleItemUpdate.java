package view.saleitem;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.SaleItem;
import service.SaleItemSrv;

public class SaleItemUpdate {
	private static final long serialVersionUID=1L;
	
	public SaleItemUpdate() {
		super();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SaleItem saleitem = null;
		String type = req.getParameter("type");
		int id = 0;
		try{
			if(type.equals("modify")){
				id = Integer.valueOf(req.getParameter("sale_item_id"));
			}
			resp.setContentType("text/html;charset=utf-8");
			int ticketId=Integer.valueOf(req.getParameter("ticket_id"));
			int saleId=Integer.valueOf(req.getParameter("sale_ID"));
			int itemPrice=Integer.valueOf(req.getParameter("sale_item_price"));
			saleitem = new SaleItem(id, ticketId, saleId, itemPrice);
			PrintWriter out = resp.getWriter();
			if(type.equals("add")){
				if(new SaleItemSrv().add(saleitem)==1){
					out.write("数据添加成功");
				}else{
					out.write("数据添加失败，请重试");
				}
			}else{
				if(new SaleItemSrv().modify(saleitem)==1){
					out.write("数据添加成功");
				}else{
					out.write("数据添加失败，请重试");
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
