package buisnessLogic;

import java.util.ArrayList;

public class Poll {
	
	private int id_pollBB;
	private int id_user_publisher;
	private String title_pollBB;
	private ArrayList<String> choices = new ArrayList<>();
	private int id_BDE_pollBB;
	private int state;
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getId_BDE_pollBB() {
		return id_BDE_pollBB;
	}
	public void setId_BDE_pollBB(int id_BDE_pollBB) {
		this.id_BDE_pollBB = id_BDE_pollBB;
	}
	// a voir : private int id_BDE;
	public int getId_pollBB() {
		return id_pollBB;
	}
	public void setId_pollBB(int id_pollBB) {
		this.id_pollBB = id_pollBB;
	}
	public int getId_user_publisher() {
		return id_user_publisher;
	}
	public void setId_user_publisher(int id_user_publisher) {
		this.id_user_publisher = id_user_publisher;
	}
	public String getTitle_pollBB() {
		return title_pollBB;
	}
	public void setTitle_pollBB(String title_pollBB) {
		this.title_pollBB = title_pollBB;
	}
	public String getchoices_pollBB() {
		String s="";
		for(String i : choices) {
			s = s + " "+i+" , ";
		}
		if(s != "" & s.length() > 3) {
			return s.substring(0, s.length() - 3);
		}
		else return "string too short";
	}
	public void setChoices_pollBB(String choicesBB) {
		choices.add(choicesBB);
	}
	
	public void removeChoices_pollBB(String choicesBB) {
		for(int i=0;i<choices.size();i++) {
			if(choices.get(i).equals(choicesBB)) {
				choices.remove(i);
			}
		}
	}
	
	public void removeAllChoices(ArrayList<String> array) {
		choices.removeAll(array);
		
	}
	
	public ArrayList<String> splitChoices(String s) {
		ArrayList<String> array = new ArrayList<>();
		String [] list = s.split(" , ");
		for(int i=0;i<list.length;i++) {
			array.add(list[i]);
		}
		return array;
	}

}
