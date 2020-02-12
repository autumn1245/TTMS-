package view.schedule;

import java.util.List;

import entity.Play;
import entity.Studio;
import service.PlaySrv;
import service.StudioSrv;
import view.play.PlaySearch;

public class NameToId {
	public static List<Studio> changeStudio(String name) {
		List<Studio> list = null;
		
		if(name != null && name.length()>0) {
			list = new StudioSrv().Fetch("studio_name LIKE '%" + name +"%'");
		}else {
			list = new StudioSrv().FetchAll();
		}
		
		return list;
	}
	
	public static int changePlay2(String name) {
		List<Play> list = null;
		int n = -1;
		
		if (name != null && name.length()>0) {
			list = new PlaySrv().Fetch("play_name = '"+ name +"'");
		}
		for (Play play : list) {
			n = play.getId();
		}
		
		return n;
	}
	
	public static List<Play> changePlay(String name) {
		List<Play> list = null;

		if (name != null && name.length()>0) {
			list = new PlaySrv().Fetch("play_name like '%"+ name +"%'");
		}
		
		return list;
	}
	
	public static int changeStudio2(String name) {
		int n = -1;
		List<Studio> list = null;

		if (name != null && name.length()>0) {
			list = new StudioSrv().Fetch("studio_name = '"+ name +"'");
		}
		for (Studio studio : list) {
			n = studio.getID();
		}
		
		return n;
	}
	
}
