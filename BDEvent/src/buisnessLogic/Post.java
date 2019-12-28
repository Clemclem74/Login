package buisnessLogic;

import java.util.ArrayList;
import java.util.List;

public class Post {

	private int id_postBB;
	private int id_user_publisher;
	private String title_postBB;
	private String text_postBB;
	private int id_BDE_postBB;
	private int state;
	
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getId_BDE_postBB() {
		return id_BDE_postBB;
	}
	public void setId_BDE_postBB(int id_BDE_postBB) {
		this.id_BDE_postBB = id_BDE_postBB;
	}
	// a voir : private int id_BDE;
	public int getId_postBB() {
		return id_postBB;
	}
	public void setId_postBB(int id_postBB) {
		this.id_postBB = id_postBB;
	}
	public int getId_user_publisher() {
		return id_user_publisher;
	}
	public void setId_user_publisher(int id_user_publisher) {
		this.id_user_publisher = id_user_publisher;
	}
	public String getTitle_postBB() {
		return title_postBB;
	}
	public void setTitle_postBB(String title_postBB) {
		this.title_postBB = title_postBB;
	}
	public String getText_postBB() {
		return text_postBB;
	}
	public void setText_postBB(String text_postBB) {
		this.text_postBB = text_postBB;
	}
	
	
	
}