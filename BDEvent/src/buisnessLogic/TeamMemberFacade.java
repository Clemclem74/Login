package buisnessLogic;

import java.util.ArrayList;

import dao.*;

public class TeamMemberFacade {

	AbstractDAOFactory adf;

	/**
	 *
	 * @param username
	 * @param password
	 */

	public TeamMemberFacade() {
		this.adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.ORACLE_DAO_FACTORY);
	}

	public int add_member(User current_user, int idTeam, boolean chief) {

		TeamMember obj = new TeamMember();
		TeamFacade teamFacade = new TeamFacade();
		obj.setUser(current_user);
		obj.setTeam(teamFacade.findById(idTeam));
		obj.setChief(chief);

		OracleDAO<TeamMember> teamMemberDao = adf.getTeamMemberDAO();
		int res = teamMemberDao.create(obj);
		if (res >= 0) {
			System.out.println(current_user.getUsername() + " Add in " + obj.getTeam().getNameTeam());
			return res;
		} else {
			System.out.println("Error while creating Team Member");
			return -1;
		}
	}

	public boolean isChief(User user) {

		OracleDAO<TeamMember> teamDao = this.adf.getTeamMemberDAO();
		return teamDao.isChief(user.getId_user());
	}

	public int modify(int idBDE, String nameBDE, String schoolBDE) {
		return 0;
	}

	public int delete(TeamMember teamMember) {

		OracleDAO<TeamMember> teamMemberDao = adf.getTeamMemberDAO();
		if (teamMemberDao.delete(teamMember)) {
			System.out.println("TeamMember deleted");
			return 1;
		} else {
			System.out.println("Error while deleting BDE");
			return -1;
		}
	}

	public ArrayList<Integer> findUserTeam(int idUser) {
		OracleDAO<TeamMember> teamMemberDao = this.adf.getTeamMemberDAO();
		return teamMemberDao.findTeamsByUser(idUser);
	}

	public ArrayList<Integer> getListTeams(int idBDE) {
		OracleDAO<BDE> bdeDao = this.adf.getBDEDAO();
		ArrayList<Integer> idTeams = bdeDao.findTeams(idBDE);
		return idTeams;
	}

	public ArrayList<Integer> isPartOfTeam(int idTeam) {
		OracleDAO<TeamMember> tmDao = this.adf.getTeamMemberDAO();
		ArrayList<Integer> idusers = tmDao.findMembersByTeam(idTeam);
		return idusers;
	}

	public void sendError() {
		// TODO - implement LoginFacade.sendError
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param user
	 */
	public void sendUser(User user) {
		// TODO - implement LoginFacade.sendUser
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param user
	 * @param password
	 */
	public void verifyInformation(User user, String password) {
		// TODO - implement LoginFacade.verifyInformation
		throw new UnsupportedOperationException();
	}

	public int getIdUserConnected() {
		// TODO - implement LoginFacade.getIdUserConnected
		throw new UnsupportedOperationException();
	}

	public String getUsernameConnected() {
		// TODO - implement LoginFacade.getUsernameConnected
		throw new UnsupportedOperationException();
	}

	public String getFirstNameConnected() {
		// TODO - implement LoginFacade.getFirstNameConnected
		throw new UnsupportedOperationException();
	}

	public String getLastNameConnected() {
		// TODO - implement LoginFacade.getLastNameConnected
		throw new UnsupportedOperationException();
	}

	public String getEmailUserConnected() {
		// TODO - implement LoginFacade.getEmailUserConnected
		throw new UnsupportedOperationException();
	}

	public String getPhoneNumberUserConnected() {
		// TODO - implement LoginFacade.getPhoneNumberUserConnected
		throw new UnsupportedOperationException();
	}

}
