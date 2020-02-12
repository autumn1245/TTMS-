package view.play;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.DataDict;
import entity.Play;
import entity.Studio;
import service.DataDictSrv;
import service.PlaySrv;
@WebServlet("/PlaySearch")
public class PlaySearch extends HttpServlet{
	private static final long servialVersionUID=1L;
	
	public PlaySearch() {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		
	}
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("=======");
		//System.out.println(request.getParameter("play_name"));
		String name = request.getParameter("play_name");
		name=new String(name.getBytes("ISO-8859-1"),"utf-8");
		System.out.println("name:"+ name);
		List<Play> result = null;
		
		if(name!=null && name.length()>0) {
			result = new PlaySrv().Fetch("play_name like '%" + name + "%'");
		} else {
			result = new PlaySrv().FetchAll();
		}
		
		String jsonStr = "[";		
		for (Play s : result) {
			//获得类型
			System.out.println(s);
			int lang = s.getTypeId();
			List<DataDict> data = new DataDictSrv().Fetch(lang);
			String type = data.get(0).getValue();
			
			//获得语言
			int language = s.getLangId();
			List<DataDict> data1 = new DataDictSrv().Fetch(language);
			String la = data1.get(0).getValue();
			
			jsonStr += "{\"play_id\":\""+s.getId() + 
					"\",\"play_type_id\":\""+type +
					"\",\"play_name\":\""+s.getName()+
					"\",\"play_introduction\":\""+s.getIntroduction()+
					"\",\"play_lang_id\":\""+la+
					"\",\"play_image\":\""+s.getImage()+
					"\",\"play_length\":\""+s.getLength()+
					"\",\"play_ticket_price\":\""+s.getTicketPrice()+
					"\",\"play_status\":\""+s.getStatus()+ "\"}";
			jsonStr += ",";
		}
		if(result.size()==0)
			jsonStr += "]";
		else
		    jsonStr = jsonStr.substring(0, jsonStr.length()-1)+"]";
		//System.out.println(jsonStr);
		response.setContentType("application/json;charset=utf-8");  // 指定返回的格式为JSON格式
		response.setCharacterEncoding("UTF-8");  // setContentType与setCharacterEncoding的顺序不能调换，否则无法解决中文乱码的问题
		PrintWriter out = response.getWriter();
		out.write(jsonStr);
		out.close();
	}
}
