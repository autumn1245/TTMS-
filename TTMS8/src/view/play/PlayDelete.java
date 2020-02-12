package view.play;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PlaySrv;
@WebServlet("/PlayDelete")
public class PlayDelete extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public PlayDelete() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try{
			//System.out.println("id:" + req.getParameter("play_id"));
			//System.out.println("=====");
			//System.out.println("id:" + req.getParameter("id").trim());
			int id=Integer.valueOf(req.getParameter("play_id"));
			System.out.println("idlll:" + id);
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out=resp.getWriter();
			out.write(""+new PlaySrv().delete(id));
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write("操作错误，请重试");
		}
	}
}
