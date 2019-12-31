package buisnessLogic;

public class ActivityList {

	private String title;
	private String start;
	private String end;
	
	//User constructor
	public ActivityList() {
		
		title="";
		start="";
		end="";
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name_activity) {
		this.title = name_activity;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	
	
	
}
