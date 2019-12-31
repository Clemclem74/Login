package buisnessLogic;

public class BDEActivity extends Activity{

	private String date;
	private String duration;
	
	//User constructor
	public BDEActivity() {
		super();
		setDate("");
		setDuration("");
		
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



	
	
}
