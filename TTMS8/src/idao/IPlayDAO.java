package idao;

import java.util.List;

import entity.Play;



public interface IPlayDAO {
	public int insert(Play play);
	public int update(Play play);
	public int delete(int id);
	public List<Play> select(String condt);
}
