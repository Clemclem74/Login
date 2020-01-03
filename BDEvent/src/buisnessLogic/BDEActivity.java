package buisnessLogic;

public class BDEActivity extends Activity{

	private String date;
	private String start_hour;
	private String duration;
	private int nb_users;
	
	//User constructor
	public BDEActivity() {
		super();
		setDate("");
		setDuration("");
		setStart_hour("");
		setNb_users(0);
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getStart_hour() {
		return start_hour;
	}

	public void setStart_hour(String start_hour) {
		this.start_hour = start_hour;
	}

	public int getNb_users() {
		return nb_users;
	}

	public void setNb_users(int nb_users) {
		this.nb_users = nb_users;
	}



	
	
}
