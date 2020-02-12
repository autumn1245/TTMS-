package dao;

import entity.ZhiFu;

public class ZhiFuDAOText {

	public static void main(String[] args) {
		ZhiFuDAO dao = new ZhiFuDAO();
		ZhiFu zhiFu = dao.select(2, 1);
		System.out.println(zhiFu);
	}

}
