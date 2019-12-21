package buisnessLogic;

import java.util.ArrayList;
import java.util.List;

public class BDE {

	private int idBDE;
	private String nameBDE;
	private String schoolBDE;
	private User creator; 
	private List<Member> listOfMemberAppliance;
	private List<Member> listOfMembers = new ArrayList();

	
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

	public List<Member> getListOfMemberAppliance() {
		return this.listOfMemberAppliance;
	}

	public List<Member> getListOfMembers() {
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
	
	public void addMember(Member member) {
		this.listOfMembers.add(member);
	}
	
	
	public void deleteMember(int Member) {
		// TODO - implement BDE.deleteMember
		throw new UnsupportedOperationException();
	}

}