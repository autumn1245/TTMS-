package service;

import java.util.List;

import entity.Sale;
import idao.DAOFactory;
import idao.ISaleDAO;

public class SaleSrv {
	private ISaleDAO saleDAO=DAOFactory.createSaleDAO();
	public int add(Sale sale){
		return saleDAO.insert(sale);
	}
	public int modify(Sale sale){
		return saleDAO.update(sale);
	}
	public int delete(int id){
		return saleDAO.delete(id);
	}
	public List<Sale> Fetch(String condt){
		return saleDAO.select(condt);
	}
	public List<Sale> FetchAll(){
		return saleDAO.select("");
	}
}
