package buisnessLogic;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ActivityList {

	private int id;
	private String title;
	private String start;
	private String end;
	private String nb_user;
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNb_user() {
		return nb_user;
	}

	public void setNb_user(String nb_user) {
		this.nb_user = nb_user;
	}

	
	
	
}
