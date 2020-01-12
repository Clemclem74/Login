package buisnessLogic;

public class Meeting {
	
	private int id_meeting;
	private String title;
	private String meeting_date;
	private int publisher_meeting;
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
	public void setMeeting_date(String meeting_date) {
		this.meeting_date = meeting_date;
	}
	public int getPublisher_meeting() {
		return publisher_meeting;
	}
	public void setPublisher_meeting(int publisher_meeting) {
		this.publisher_meeting = publisher_meeting;
	}
	
	

}
