package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import entity.Play;
import idao.IPlayDAO;
import util.DB2;
import util.DBUtil;

public class PlayDAO implements IPlayDAO{

	@Override
	public int insert(Play play) {
		int n = -1;
		
		String sql = "insert into play(play_type_id, play_name, play_introduction, play_lang_id, "
				+ "play_image, play_length, play_ticket_price, play_status) "
				+ "values('"+play.getTypeId()+"', '"+play.getName()+"', '"+play.getIntroduction()+"', "
						+ "'"+play.getLangId()+"', '"+play.getImage()+"', '"+play.getLength()+"', "
								+ "'"+play.getTicketPrice()+"', '"+play.getStatus()+"')";
		System.out.println(sql);
		
		DBUtil db = new DBUtil();	
		n = db.execCommand(sql);
		if(n > 0) {
			System.out.println("添加成功");
			return 1;
		}
		
		return n;
	}

	@Override
	public int delete(int id) {
		String sql=" delete from play";
		sql += " where play_id = " + id;
		DBUtil db = new DBUtil();
		return db.execCommand(sql);
	}

	@Override
	public List<Play> select(String condt) {
		List<Play> list = new ArrayList<Play>();
		try {
			String sql="select * from play";
			condt.trim();
			if(!condt.isEmpty()){
				sql+=" where " + condt;
			}
			DBUtil db=new DBUtil();
			ResultSet rst=db.execQuery(sql);
			if(rst!=null){
				while(rst.next()){
					Play play=new Play();
					play.setId(rst.getInt("play_id"));
					play.setTypeId(rst.getInt("play_type_id"));
					play.setLangId(rst.getInt("play_lang_id"));
					play.setName(rst.getString("play_name"));
					play.setIntroduction(rst.getString("play_introduction"));
					play.setImage(rst.getString("play_image"));
					play.setLength(rst.getInt("play_length"));
					play.setTicketPrice(rst.getInt("play_ticket_price"));
					play.setStatus(rst.getInt("play_status"));
					list.add(play);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int update(Play play) {
		String sql = "update play set play_type_id = '" + play.getTypeId() + "', "
				+ "play_name = '" + play.getName() + "', "
				+ "play_introduction = '" + play.getIntroduction() + "', "
				+ "play_lang_id = '" + play.getLangId() + "', "
				+ "play_image = '" + play.getImage() + "', "
				+ "play_length = '" + play.getLength() + "', "
				+ "play_ticket_price = '" + play.getTicketPrice() + "', "
				+ "play_status = '" + play.getStatus() + "' ";
		sql += "where play_id = '" + play.getId() + "'";
		
		DBUtil db = new DBUtil();

		return db.execCommand(sql);
	}
}
