package buisnessLogic;

public class Team {

	private int idTeam;
	private String nameTeam;
	private BDE bde;
	private Member[] listMemberTeam;

	
	
	public int getIdTeam() {
		return this.idTeam;
	}
	
	public void setIdTeam(int idteam) {
		this.idTeam=idteam;
	}
	
	public String getNameTeam() {
		return this.nameTeam;
	}

	/**
	 * 
	 * @param nameTeam
	 */
	public void setNameTeam(String nameTeam) {
		this.nameTeam = nameTeam;
	}



	public Member[] getListMemberTeam() {
		return this.listMemberTeam;
	}
	
	//Read in the database all the members
	public void setListMembers() {
		
	}


	public BDE getBde() {
		return bde;
	}

	public void setBde(BDE bde) {
		this.bde = bde;
	}

	public void addMember(int Member) {
		// TODO - implement Team.addMember
		throw new UnsupportedOperationException();
	}

	
	public void removeMember(int Member) {
		// TODO - implement Team.removeMember
		throw new UnsupportedOperationException();
	}

}