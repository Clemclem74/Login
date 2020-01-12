package buisnessLogic;

import java.util.ArrayList;

import dao.AbstractDAOFactory;
import dao.OracleDAO;

public class ParticipeFacade {
	
	User connectedUser;
	
	AbstractDAOFactory adf;
	
	public ParticipeFacade() {
		this.adf=AbstractDAOFactory.getFactory(AbstractDAOFactory.ORACLE_DAO_FACTORY);
	}
	
	public int addParticipe(int idUser, int idMeeting, String choice, int idBDE) {
		Participe obj = new Participe();
		
		obj.setId_participer(idUser);
		obj.setId_meeting(idMeeting);
		obj.setChoice(choice);
		obj.setId_bde_participer(idBDE);
		
		OracleDAO<Participe> participeDao = adf.getParticipeDAO();
		
		int res = participeDao.create(obj);
			System.out.println("creation obj database");
	        if(res>=0) {
	        	System.out.println( "the participe has been created");
	        	return res;
	        }
	        else {
	        	System.out.println("Error while creating the particpe");
	        	return -1;
	        }

	}
	
	public ArrayList<Participe> findAllParticipeBDE(int idMeeting, User user){
		OracleDAO<Participe> participeDAO = this.adf.getParticipeDAO();
		ArrayList<Participe> allparticipe = participeDAO.findAllParticipeByBDE(user);
		if(allparticipe == null) {
			System.out.println("no participes found");
			return null;
		}
		else {
			return allparticipe;
		}	
	}
	
	public ArrayList<Integer> CounterParticipes(ArrayList<Participe> list, int idmeeting) {
		int oui=0, non=0;
		ArrayList<Integer> ret = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getChoice().contentEquals("oui") && idmeeting==list.get(i).getId_meeting()) {
				oui = oui + 1;
			}
			else if(list.get(i).getChoice().contentEquals("non") && idmeeting==list.get(i).getId_meeting()) {
				non = non + 1;
			}
		}
		System.out.println(oui);
		ret.add(oui);
		ret.add(non);
		return ret;
	}
	
	public boolean alreadyParticiped(int idMeeting, User user) {
		OracleDAO<Participe> participeDAO = this.adf.getParticipeDAO();
		ArrayList<Participe> allparticipe = participeDAO.findAllParticipeByBDE(user);
		if(allparticipe==null) {
			System.out.println("no particpes found");
		}
		else {
			for(int i=0;i<allparticipe.size();i++) {
				if(allparticipe.get(i).getId_meeting()==idMeeting && allparticipe.get(i).getId_participer()==user.getId_user()) {
					return true;
				}
			}
			return false;
		}
		return false;
	}

}
