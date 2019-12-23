package buisnessLogic;

import java.util.ArrayList;
import java.util.List;

public class BDE {

	private int idBDE;
	private String nameBDE;
	private String schoolBDE;
	private User creator; 
	private BlackBoard blackboard;
	private List<TeamMember> listOfMemberAppliance;
	private List<TeamMember> listOfMembers = new ArrayList();

	
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public int getIdBDE() {
		return this.idBDE;
	}
	
	public void setIdBDE(int idBDE) {
		this.idBDE=idBDE;
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

	public List<TeamMember> getListOfMemberAppliance() {
		return this.listOfMemberAppliance;
	}

	public List<TeamMember> getListOfMembers() {
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
	 * @param TeamMember
	 */
	
	public void addMember(TeamMember member) {
		this.listOfMembers.add(member);
	}
	
	
	public void deleteMember(int Member) {
		// TODO - implement BDE.deleteMember
		throw new UnsupportedOperationException();
	}

}