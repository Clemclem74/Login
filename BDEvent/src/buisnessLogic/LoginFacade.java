package buisnessLogic;



import dao.*;

public class LoginFacade {

	User ConnectedUser;

	/**
	 * 
	 * @param username
	 * @param password
	 */
	public LoginFacade(String username, String password) {
		// TODO - implement LoginFacade.LoginFacade
		
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		
		DAO<User> userDao = adf.getUserDAO();
		
		User user = userDao.find(username);
	
		if(user.getPassworduser().equals(password)) {
			System.out.println(user.getUsername() +" Connected");
			this.ConnectedUser = user;
		}
		else {
			System.out.println(user.getPassworduser() + " Email or Password Incorrect");
		}
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