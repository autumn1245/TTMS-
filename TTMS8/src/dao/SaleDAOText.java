package dao;

import java.util.Date;
import java.util.List;

import entity.Sale;

public class SaleDAOText {

	public static void main(String[] args) {
		SaleDAO dao = new SaleDAO();
		
		Sale sale = new Sale();
		
//		sale.setEmpId(5);
//		sale.setTime(new Date());
//		sale.setPayment(80);
//		sale.setType(1);
//		sale.setStatus(1);
//		
//		int n = dao.insert(sale);
//		System.out.println(n);
//		System.out.println(sale.getId());
		
//		sale.setId(1);
//		sale.setEmpId(4);
//		sale.setTime(new Date());
//		sale.setPayment(70);
//		sale.setType(0);
//		sale.setStatus(1);
//		
//		int n = dao.update(sale);
//		System.out.println(n);
		
//		String sql = "emp_id = 5";
//		List<Sale> list = dao.select(sql);
//		for (Sale sale2 : list) {
//			System.out.println(sale2.toString());
//		}
		
		int id = 1;
		System.out.println(dao.delete(id));
	}

}
