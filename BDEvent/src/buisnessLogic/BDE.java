package buisnessLogic;

public class BDE {

	private int idBDE;
	private String nameBDE;
	private String schoolBDE;
	private User creator; 
	private Member[] listOfMemberAppliance;
	private Member[] listOfMembers;

	
	
	
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public int getIdBDE() {
		return this.idBDE;
	}

	public String getNameBDE() {
		return this.nameBDE;
	}

	/**
	 * 
	 * @param nameBDE
	 */
	public void setNameBDE(String nameBDE) {
		this.nameBDE = nameBDE;
	}

	public String getSchoolBDE() {
		return this.schoolBDE;
	}

	/**
	 * 
	 * @param schoolBDE
	 */
	public void setSchoolBDE(String schoolBDE) {
		this.schoolBDE = schoolBDE;
	}

	public Member[] getListOfMemberAppliance() {
		return this.listOfMemberAppliance;
	}

	public Member[] getListOfMembers() {
		return this.listOfMembers;
	}

	/**
	 * 
	 * @param Member
	 */
	public void acceptAppliance(int Member) {
		// TODO - implement BDE.acceptAppliance
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Member
	 */
	public void declineAppliance(int Member) {
		// TODO - implement BDE.declineAppliance
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Member
	 */
	public void deleteMember(int Member) {
		// TODO - implement BDE.deleteMember
		throw new UnsupportedOperationException();
	}

}