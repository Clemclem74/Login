package buisnessLogic;

public class TeamMember extends User {
	
	private User user;
	private Team team;
	private boolean isChief; 
	

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public boolean isChief() {
		return isChief;
	}

	public void setChief(boolean isChief) {
		this.isChief = isChief;
	}

	public void modifySelfUser() {
		// TODO - implement Member.modifySelfUser
		throw new UnsupportedOperationException();
	}

	public void operation() {
		// TODO - implement Member.operation
		throw new UnsupportedOperationException();
	}

}