package idao;

import java.util.List;

import entity.Ticket;


public interface ITicketDAO {
	public int insert(Ticket ticket);
	public int update(Ticket ticket);
	public int delete(int id);
	public List<Ticket> select(String condt);
}
