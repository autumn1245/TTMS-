//package view.User;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import service.UserSrv;
//
//public class UserDelete extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	
//	public UserDelete() {
//		super();
//	}
//	
//	protected void doGet(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//	}
//
//	protected void doPost(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		try {
//			String name = new String(request.getParameter("username"));
//			response.setContentType("text/html;charset=utf-8");
//			PrintWriter out = response.getWriter();
//			out.write(""+new UserSrv().delete(name));
//			out.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//			response.setContentType("text/html;charset=utf-8");
//			response.getWriter().write("操作错误，请重试");
//		}
//	}
//}
