package buisnessLogic;

public class Appliance extends Activity{

	private int id_staff_activity;
	private int id_user;
	private int id_event;
	
	//User constructor
	public Appliance() {
		super();
		
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getId_event() {
		return id_event;
	}

	public void setId_event(int id_event) {
		this.id_event = id_event;
	}

	public int getId_staff_activity() {
		return id_staff_activity;
	}

	public void setId_staff_activity(int id_staff_activity) {
		this.id_staff_activity = id_staff_activity;
	}

}
