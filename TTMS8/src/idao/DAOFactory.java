package idao;
import dao.DataDictDAO;
import dao.EmployeeDAO;
import dao.PlayDAO;
import dao.SaleDAO;
import dao.SaleItemDAO;
import dao.ScheduleDAO;
import dao.SeatDAO;
import dao.StudioDAO;
import dao.TicketDAO;
import dao.UserDAO;
import dao.ZhiFuDAO;
public class DAOFactory {
	
	public static IStudioDAO creatStudioDAO(){
		return new StudioDAO();
	}
	public static IUserDAO creatUserDAO() {
		return new UserDAO();
	}
	
	public static IPlayDAO createPlayDAO(){
		return new PlayDAO();
	}
	public static ISeatDAO createSeatDAO(){
		return new SeatDAO();
	}
	public static ITicketDAO createTicketDAO(){
		return new TicketDAO();
	}
	public static IDataDictDAO createDataDictDAO(){
		return new DataDictDAO();
	}
	public static ISaleDAO createSaleDAO(){
		return new SaleDAO();
	}
	public static IEmployeeDAO createEmployeeDAO(){
		return new EmployeeDAO();
	}
	public static ISaleItemDAO createSaleItemDAO(){
		return new SaleItemDAO();
	}
	public static IScheduleDAO createScheduleDAO(){
		return new ScheduleDAO();
	}
	
	public static IZhiFuDAO createZhiFUDAO() {
		return new ZhiFuDAO();
	}
}
