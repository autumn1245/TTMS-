package service;

import java.util.List;

import entity.SaleItem;
import idao.DAOFactory;
import idao.ISaleItemDAO;

public class SaleItemSrv {
	private ISaleItemDAO playDAO=DAOFactory.createSaleItemDAO();
	public int add(SaleItem saleitem){
		return playDAO.insert(saleitem);
	}
	public int modify(SaleItem saleitem){
		return playDAO.update(saleitem);
	}
	public int delete(int id){
		return playDAO.delete(id);
	}
	public List<SaleItem> Fetch(String condt){
		return playDAO.select(condt);
	}
	public List<SaleItem> FetchAll(){
		return playDAO.select("");
	}
}
