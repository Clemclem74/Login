package buisnessLogic;

public class Event {

	private int id_event;
	private String title;
	private String description;
	private String event_date;
	private String image;
	private int responsible;
	
	//User constructor
	public Event(){
		this.id_event=0;
		this.title="";
		this.description="";
		this.event_date="";
		this.setImage("");
		this.responsible=0;
	}

	public int getId_event() {
		return id_event;
	}

	public void setId_event(int id_event) {
		this.id_event = id_event;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEvent_date() {
		return event_date;
	}

	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getResponsible() {
		return responsible;
	}

	public void setResponsible(int responsible) {
		this.responsible = responsible;
	}
	
}
