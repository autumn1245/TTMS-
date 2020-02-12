package view.play;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Play;
import service.DataDictSrv;
import service.PlaySrv;
@WebServlet("/PlayUpdate")
public class PlayUpdate extends HttpServlet{
	private static final long serialVersionUID=1L;
	
	public PlayUpdate() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Play play=null;
		String type=req.getParameter("type");
		System.out.println("type:" + type);
		int id=0;
		try{
			if(type.equals("modify")){
				id=Integer.valueOf(req.getParameter("play_id"));
			}
			String typei = req.getParameter("play_type_id");
			typei=new String(typei.getBytes("ISO-8859-1"),"utf-8");
			System.out.println("play_type:"+typei);
			int typeid = new DataDictSrv().Fetch1(typei);
			System.out.println("play_type_id:" + typeid);
			String lang1 = req.getParameter("play_lang_id");
			lang1=new String(lang1.getBytes("ISO-8859-1"),"utf-8");
			System.out.println("play_lang:" + lang1);
			int langid = new DataDictSrv().Fetch1(lang1);
			System.out.println("play_lang_id:" + langid);
			String name=req.getParameter("play_name");
			name=new String(name.getBytes("ISO-8859-1"),"utf-8");
			System.out.println("name:" + name);
			String introduction=req.getParameter("play_introduction");
			introduction=new String(introduction.getBytes("ISO-8859-1"),"utf-8");
			System.out.println("introduction:" + introduction);
			String image=req.getParameter("play_image");
			image=new String(image.getBytes("ISO-8859-1"),"utf-8");
			System.out.println("image:" + image);
			System.out.println("======");
			System.out.println("--" + req.getParameter("play_length"));
			int length=Integer.valueOf(req.getParameter("play_length").trim());
			System.out.println("length:" + length);
			float ticketPrice=Float.valueOf(req.getParameter("play_ticket_price"));
			String st = req.getParameter("play_status");
			st=new String(st.getBytes("ISO-8859-1"),"utf-8");
			System.out.println("---" + st);
			int status=0;
			if("待安排演出".equals(st)) {
				status = 0;
			} else if("已安排演出".equals(st)) {
				status = 1;
			} else if("下线".equals(st)) {
				status = -1;
			}
			System.out.println("status:" + status);
			play=new Play(id,typeid,langid,name,introduction,image,length,ticketPrice,status);
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out=resp.getWriter();
			if(type.equals("add")){
				if(new PlaySrv().add(play)==1){
					out.write("数据添加成功");
				}else{
					out.write("数据添加失败，请重试");
				}
			}else{
				if(new PlaySrv().modify(play)==1){
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
