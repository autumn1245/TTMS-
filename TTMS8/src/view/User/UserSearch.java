package view.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Studio;
import entity.User;
import service.StudioSrv;
import service.UserSrv;

@WebServlet("/UserSearch")
public class UserSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserSearch() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("username");
		System.out.println(name);
		String pass = request.getParameter("password");
		System.out.println(pass);
		PrintWriter out = response.getWriter();
		List<User> list = null;
		if(name != null && pass != null) {
			list = new UserSrv().Fetch(name);
		}
		User user = new User();
		user = list.get(0);
		if(user != null && pass.equals(user.getUserPassword()))
			out.write("1");
		else
			out.write("0");			
		out.close();
		
//		if(name.equals("aaa") && pass.equals("bbb"))
//			out.write("1");
//		else
//			out.write("0");			
//		out.close();
	}
}
