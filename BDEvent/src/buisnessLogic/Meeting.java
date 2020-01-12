package buisnessLogic;

import java.util.Date;

public class Meeting {
	
	private int id_meeting;
	private String title;
	private String meeting_date;
	private int publisher_meeting;
	private int id_bde_meeting;
	
	public int getId_bde_meeting() {
		return id_bde_meeting;
	}
	public void setId_bde_meeting(int id_bde_meeting) {
		this.id_bde_meeting = id_bde_meeting;
	}
	public int getId_meeting() {
		return id_meeting;
	}
	public void setId_meeting(int id_meeting) {
		this.id_meeting = id_meeting;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMeeting_date() {
		return meeting_date;
	}
	public void setMeeting_date(String date_meeting) {
		this.meeting_date = date_meeting;
	}
	public int getPublisher_meeting() {
		return publisher_meeting;
	}
	public void setPublisher_meeting(int publisher_meeting) {
		this.publisher_meeting = publisher_meeting;
	}
	
	

}
