package idao;

import java.util.List;

import entity.SaleItem;


public interface ISaleItemDAO {
	public int insert(SaleItem saleitem);
	public int update(SaleItem saleitem);
	public int delete(int id);
	public List<SaleItem> select(String condt);
}
