package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import entity.Sale;
import idao.ISaleDAO;
import util.DB2;
import util.DBUtil;

public class SaleDAO implements ISaleDAO{

	@Override
	public int insert(Sale sale) {
		try{
			String sql="insert into sale(emp_id，sale_time，sale_payment，sale_change，sale_type，sale_status) values(?,?,?,?,?)";
			List list=new ArrayList<>();
			list.add(sale.getEmpId());
			list.add(sale.getTime());
			list.add(sale.getPayment());
			list.add(sale.getChange());
			list.add(sale.getType());
			list.add(sale.getStatus());
			DB2 db=new DB2();
			ResultSet rst = db.getInsertObjectIDs(sql,list);
			if(rst!=null&&rst.first()){
				sale.setId(rst.getInt(1));
				return 1;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(Sale sale) {
		String sql="update sale set emp_id=?，sale_time=?，sale_payment=?，sale_change=?，sale_type=?，sale_status=?";
		sql+=" where sale_id= "+sale.getId();
		List list=new ArrayList<>();
		list.add(sale.getEmpId());
		list.add(sale.getTime());
		list.add(sale.getPayment());
		list.add(sale.getChange());
		list.add(sale.getType());
		list.add(sale.getStatus());
		DB2 db=new DB2();
		return db.execCommand(sql, list);
	}

	@Override
	public int delete(int id) {
		String sql=" delete from sale ";
		sql += " where sale_id = " + id;
		DBUtil db = new DBUtil();
		return db.execCommand(sql);
	}

	@Override
	public List<Sale> select(String condt) {
		List<Sale> saleList=null;
		saleList=new LinkedList<>();
		try {
			String sql="select * from sale ";
			condt.trim();
			if(!condt.isEmpty()){
				sql+=" where "+ condt;
			}
			DBUtil db=new DBUtil();
			ResultSet rst=db.execQuery(sql);
			if(rst!=null){
				while(rst.next()){
					Sale sale=new Sale();
					sale.setId(rst.getInt("sale_ID"));
					sale.setEmpId(rst.getInt("emp_id"));
					sale.setTime(rst.getDate("sale_time"));
					sale.setPayment(rst.getInt("sale_payment"));
					sale.setChange(rst.getFloat("sale_change"));
					sale.setType(rst.getInt("sale_type"));
					sale.setStatus(rst.getInt("sale_status"));
					saleList.add(sale);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return saleList;
	}
}
