package test;

import buisnessLogic.*;

public class MainTest {

	public static void main(String[] args) {
		int nb_erreurs = 0;
		UserFacade userFacade = new UserFacade();
		BDEFacade bdeFacade = new BDEFacade();
		int pre;
		int post;
		User u;
		BDE bde;
		
		
		
/* --------------------------------------------------------------------------------------------------------
		
								USER
								
   --------------------------------------------------------------------------------------------------------*/
		//Ajout User 
		pre = userFacade.getNumber();
		userFacade.register("T", "T@T.T", "T", "T", "T", "00");
		post = userFacade.getNumber();
		if (post != pre+1) {
			System.out.println("Erreur création User");
			nb_erreurs++;
		}
		
		//Modification User 
		u = userFacade.findByEmail("T@T.T");
		userFacade.modify(u.getId_user(), "V", u.getEmailuser(), u.getPassworduser(), u.getFirstname(), u.getLastname(), "01", u.getCurrentBDE());
		u = userFacade.findByEmail("T@T.T");
		if (u.getUsername().contentEquals("V")) {
			
		}
		else {
			System.out.println("Erreur à la modification");
			nb_erreurs++;
		}
		
		
/* --------------------------------------------------------------------------------------------------------
		
										BDE
		
--------------------------------------------------------------------------------------------------------*/		
		
		//Creation d'un BDE
		pre=bdeFacade.getNumber();
		bdeFacade.create(u, "T", "SchoolBDETest");
		post=bdeFacade.getNumber();
		if(pre != post-1) {
			System.out.println("Erreur création BDE");
			nb_erreurs++;
		}
		
		
		//Joindre un BDE
		bde=bdeFacade.findBySchool("SchoolBDETest");
		userFacade.join(u, bde.getIdBDE());
		u = userFacade.findByEmail("T@T.T");
		if(u.getCurrentBDE() != bde.getIdBDE()) {
			System.out.println("Erreur Join BDE");
			nb_erreurs++;
		}
		
		
		//Suppression BDE 
		bde=bdeFacade.findBySchool("SchoolBDETest");
		pre=bdeFacade.getNumber();
		bdeFacade.delete(bde);
		post=bdeFacade.getNumber();
		if (post != pre-1) {
			System.out.println("Erreur Suppression BDE");
			nb_erreurs++;
		}
		
		
		//Suppression User 
		u = userFacade.findByEmail("T@T.T");
		pre = userFacade.getNumber();
		userFacade.delete(u);
		post = userFacade.getNumber();
		if (post != pre-1) {
			System.out.println("Erreur Suppression User");
			nb_erreurs++;
		}
		
		System.out.println("Test finit avec " + nb_erreurs + " erreurs !");

	}
	
}
