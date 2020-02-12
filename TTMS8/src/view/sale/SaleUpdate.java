 package view.sale;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import  java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Sale;
import service.SaleSrv;
import service.SeatSrv;

public class SaleUpdate {
	private static final long serialVersionUID=1L;
	
	public SaleUpdate() {
		super();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Sale sale=null;
//		String type=req.getParameter("type");
//		int id=0;
//		try{
//			if(type.equals("modify")){
//				id=Integer.valueOf(req.getParameter("saleid"));
//			}
//			resp.setContentType("text/html;charset=utf-8");
////			int studioId=Integer.valueOf(req.getParameter("studioid"));
////			int row=Integer.valueOf(req.getParameter("row"));
////			int column=Integer.valueOf(req.getParameter("column"));
////			seat=new Seat(id,studioId,row,column);
//			int empId=Integer.valueOf(req.getParameter("empId"));
//			String time1=req.getParameter("time");
//			Date time=new SimpleDateFormat("").parse("time1");
//			float payment=Float.valueOf(req.getParameter("payment"));
//			float change=Float.valueOf(req.getParameter("change"));
//			int saleType=Integer.valueOf(req.getParameter("type"));
//			int status=Integer.valueOf(req.getParameter("status"));
//			sale=new Sale(id, empId, time, payment, change, saleType, status);
//			PrintWriter out=resp.getWriter();
//			if(type.equals("add")){
//				if(new SaleSrv().add(sale)==1){
//					out.write("数据添加成功");
//				}else{
//					out.write("数据添加失败，请重试");
//				}
//			}else{
//				if(new SaleSrv().modify(sale)==1){
//					out.write("数据添加成功");
//				}else{
//					out.write("数据添加失败，请重试");
//				}
//			}
//			out.close();
//		}catch(Exception e){
//			e.printStackTrace();
//			resp.setContentType("text/html;charset=utf-8");
//			resp.getWriter().write("操作错误，请重试");
//		}
	}
}
