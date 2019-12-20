package buisnessLogic;

public class User {

	private int id_user;
	private String username;
	private String lastname;
	private String firstname;
	private String emailuser;
	private String passworduser;
	private String phonenumberuser;
	private int currentBDE;
	
	//User constructor
	public User(){
		this.id_user=0;
		this.username="";
		this.lastname="";
		this.firstname="";
		this.emailuser="";
		this.passworduser="";
		this.phonenumberuser="";
		this.currentBDE=-1;
	}

	
	
	public int getCurrentBDE() {
		return currentBDE;
	}



	public void setCurrentBDE(int currentBDE) {
		this.currentBDE = currentBDE;
	}



	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmailuser() {
		return emailuser;
	}

	public void setEmailuser(String emailuser) {
		this.emailuser = emailuser;
	}

	public String getPassworduser() {
		return passworduser;
	}

	public void setPassworduser(String passworduser) {
		this.passworduser = passworduser;
	}

	public String getPhonenumberuser() {
		return phonenumberuser;
	}

	public void setPhonenumberuser(String phonenumberuser) {
		this.phonenumberuser = phonenumberuser;
	}
	
	public boolean isPartOfBDE() {
		if (this.currentBDE == -1) {
			return false;
		}
		else {
			return true;
		}	
	}
	
}
