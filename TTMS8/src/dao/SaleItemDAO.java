package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import entity.SaleItem;
import idao.ISaleItemDAO;
import util.DB2;
import util.DBUtil;

public class SaleItemDAO implements ISaleItemDAO{

	@Override
	public int insert(SaleItem saleitem) {
		int sale_item_id = -1;
		int ticket_id = saleitem.getTicketId();
		int sale_ID = saleitem.getSaleId();
		float sale_item_price = saleitem.getPrice();
		
		String sql = "insert into sale_item(ticket_id, sale_ID, sale_item_price) values ('"+ ticket_id +"','"+ sale_ID +"','"+ sale_item_price +"')";
		
		DBUtil dbUtil = new DBUtil();
		int n = dbUtil.execCommand(sql);
		try {
			if (n > 0) {
				String sql2 = "select * from sale_item where ticket_id = '"+ ticket_id +"' and sale_ID = '"+ sale_ID +"' and sale_item_price = '"+ sale_item_price +"'";
				ResultSet rs = dbUtil.execQuery(sql2);
				while(rs.next()) {
					sale_item_id = rs.getInt("sale_item_id");
				}
				saleitem.setId(sale_item_id);
				return 1;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(SaleItem saleitem) {
		int sale_item_id = saleitem.getId();
		int ticket_id = saleitem.getTicketId();
		int sale_ID = saleitem.getSaleId();
		float sale_item_price = saleitem.getPrice();
		
		String sql = "update sale_item set ticket_id = '"+ ticket_id +"',sale_ID = '"+ sale_ID +"',sale_item_price = '"+ sale_item_price +"' where sale_item_id = '"+ sale_item_id +"'";
		DBUtil dbUtil = new DBUtil();
		return dbUtil.execCommand(sql);
	}

	@Override
	public int delete(int id) {
		String sql="delete from sale_item";
		sql += " where sale_item_id = " + id;
		DBUtil db = new DBUtil();
		return db.execCommand(sql);
	}

	@Override
	public List<SaleItem> select(String condt) {
		List<SaleItem> saleitemList=null;
		saleitemList=new LinkedList<>();
		try {
			String sql="select * from sale_item";
			condt.trim();
			if(!condt.isEmpty()){
				sql+=" where "+ condt;
			}
			DBUtil db=new DBUtil();
			ResultSet rst=db.execQuery(sql);
			if(rst!=null){
				while(rst.next()){
					SaleItem saleitem=new SaleItem();
					saleitem.setId(rst.getInt("sale_item_id"));
					saleitem.setTicketId(rst.getInt("ticket_id"));
					saleitem.setSaleId(rst.getInt("sale_ID"));
					saleitem.setPrice(rst.getFloat("sale_item_price"));
					saleitemList.add(saleitem);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return saleitemList;
	}

}
