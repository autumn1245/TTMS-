package service;

import java.util.List;

import entity.Seat;
import idao.DAOFactory;
import idao.ISeatDAO;

public class SeatSrv {
	private ISeatDAO seatDAO=DAOFactory.createSeatDAO();
	public int add(Seat seat){
		return seatDAO.insert(seat);
	}
	public int modify(Seat seat){
		return seatDAO.update(seat);
	}
	public int delete(int id){
		return seatDAO.delete(id);
	}
	public List<Seat> Fetch(String condt){
		return seatDAO.select(condt);
	}
	public List<Seat> FetchAll(){
		return seatDAO.select("");
	}
}
