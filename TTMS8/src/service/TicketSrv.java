package service;

import java.util.List;

import entity.Ticket;
import idao.DAOFactory;
import idao.ITicketDAO;

public class TicketSrv {
	private ITicketDAO ticketDAO=DAOFactory.createTicketDAO();
	public int add(Ticket ticket){
		return ticketDAO.insert(ticket);
	}
	public int modify(Ticket ticket){
		return ticketDAO.update(ticket);
	}
	public int delete(int id){
		return ticketDAO.delete(id);
	}
	public List<Ticket> Fetch(String condt){
		return ticketDAO.select(condt);
	}
	public List<Ticket> FetchAll(){
		return ticketDAO.select("");
	}
}
