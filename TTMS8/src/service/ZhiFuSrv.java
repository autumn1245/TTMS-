package service;

import entity.ZhiFu;
import idao.DAOFactory;
import idao.IZhiFuDAO;

public class ZhiFuSrv {
	private IZhiFuDAO zhiFuDAO = DAOFactory.createZhiFUDAO();
	
	public ZhiFu Fetch(int schedule_ID, int seat_id) {
		return zhiFuDAO.select(schedule_ID, seat_id);
	}
}
