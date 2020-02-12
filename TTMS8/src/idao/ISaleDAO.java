package idao;

import java.util.List;

import entity.Sale;



public interface ISaleDAO {
	public int insert(Sale sale);
	public int update(Sale sale);
	public int delete(int id);
	public List<Sale> select(String condt);
}
