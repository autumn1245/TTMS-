package idao;

import java.util.List;

import entity.Seat;


public interface ISeatDAO {
	public int insert(Seat seat);
	public int update(Seat seat);
	public int delete(int id);
	public List<Seat> select(String conit);
}
