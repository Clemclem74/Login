package dao;

import buisnessLogic.BDE;

public class XMLDAOFactory extends AbstractDAOFactory {
	  
	public OracleDAO getUserDAO() {      
	    return null;
	}

	@Override
	public OracleDAO<BDE> getBDEDAO() {
		// TODO Auto-generated method stub
		return null;
	}
	
}