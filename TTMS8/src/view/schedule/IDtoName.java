package view.schedule;

import java.util.List;

import entity.Play;
import entity.Studio;
import service.PlaySrv;
import service.StudioSrv;

public class IDtoName {
	public static String changePlay(int play_id) {
		String play_name = null;
		List<Play> list = null;
		
		list = new PlaySrv().Fetch("play_id = '"+ play_id +"'");
		
		for (Play play : list) {
			play_name = play.getName();
		}
		
		return play_name;
	}
	
	public static String changeStudio(int studio_id) {
		String studio_name = null;
		List<Studio> list = null;
		
		list = new StudioSrv().Fetch("studio_id = '"+ studio_id +"'");
		
		for (Studio studio : list) {
			studio_name = studio.getName();
		}
		
		return studio_name;
	}
}
