package dao;

import java.util.List;

import entity.Play;
import util.DBUtil;

public class PlayDAOText {

	public static void main(String[] args) {
		PlayDAO dao = new PlayDAO();
		//加
//		Play play = new Play();
//		play.setName("1");
//		play.setTypeId(5);
//		play.setLangId(11);
//		play.setIntroduction("1");
//		play.setImage("1");
//		play.setLength(1);
//		play.setTicketPrice(1);
//		play.setStatus(0);
//		
//		int n = dao.insert(play);
//		System.out.println(n);
		
		//查
//		String sql = "play_name like '%4%'";
//		List<Play> list = dao.select(sql);
//		System.out.println(list.get(0).getId());
		
		//删
//		int n = dao.delete(3);
//		System.out.println(n);
		
		//修改
		Play play = new Play();
		play.setId(2);
		play.setName("7");
		play.setTypeId(7);
		play.setLangId(12);
		play.setIntroduction("7");
		play.setImage("7");
		play.setLength(7);
		play.setTicketPrice(7);
		play.setStatus(1);
		
		int n = dao.update(play);
		System.out.println(n);
	}

}
