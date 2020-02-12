package view.seat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Seat;
import entity.Studio;
import service.PlaySrv;
import service.SeatSrv;
import service.StudioSrv;
@WebServlet("/SeatSearch")
public class SeatSearch extends HttpServlet{
	private static final long servialVersionUID = 1L;
	
	public SeatSearch() {
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String name=req.getParameter("name");
		String studio_name = req.getParameter("studio_name");
		studio_name=new String(studio_name.getBytes("ISO-8859-1"),"utf-8");
		System.out.println(studio_name);
		int studioId = NameToId.changeStudio2(studio_name);
		System.out.println(studioId);
		
		List<Seat> result=null;
		if(studioId!=0){
			result=new SeatSrv().Fetch("studio_id like '%"+studioId+"%'");
		}else{
			result=new SeatSrv().FetchAll();
		}
		List<Studio> list = null;
		list = new StudioSrv().Fetch("studio_id = '"+studioId+"'");
		Studio studio = list.get(0);
		String jsonStr="[";
		for(Seat s:result){
			jsonStr+="{\"id\":\""+s.getId() + 
					"\",\"studioId\":\""+s.getStudioId() +
					"\",\"row\":\""+s.getRow()+
					"\",\"column\":\""+s.getColumn()+ 
					"\",\"seat_status\":\""+ s.getStatus()+ "\"}";
			
			jsonStr += ",";
		}
		if(result.size()==0){
			jsonStr+="{\"rowcount\":\"" + studio.getRowCount() +
					"\",\"columncount\":\"" + studio.getColCount() + "\"}";
			jsonStr+="]";
		}else{
			jsonStr+="{\"rowcount\":\"" + studio.getRowCount() +
					"\",\"columncount\":\"" + studio.getColCount() + "\"}";
			jsonStr=jsonStr.substring(0,jsonStr.length())+"]";
			resp.setContentType("application/json;charset=utf-8");
			resp.setCharacterEncoding("utf-8");
			PrintWriter out=resp.getWriter();
			out.write(jsonStr);
			out.close();
		}
	}
}
