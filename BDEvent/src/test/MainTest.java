package test;

import java.util.ArrayList;

import buisnessLogic.*;

public class MainTest {

	public static void main(String[] args) {
		int nb_erreurs = 0;
		UserFacade userFacade = new UserFacade();
		BDEFacade bdeFacade = new BDEFacade();
		TeamFacade teamFacade = new TeamFacade();
		TeamMemberFacade teamMemberFacade = new TeamMemberFacade();
		PostFacade postFacade = new PostFacade();
		FeeFacade feeFacade = new FeeFacade();
		
		int pre;
		int post;
		User u;
		BDE bde;
		Fee f;
		Post p;
		Team t1;
		Team t2;
		Team t3;
		TeamMember tm1;
		TeamMember tm2;
		
		
		
		
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
		
		
		//Modifier un BDE 
		bde=bdeFacade.findBySchool("SchoolBDETest");
		bdeFacade.modify(bde.getIdBDE(), "V", bde.getSchoolBDE());
		bde=bdeFacade.findBySchool("SchoolBDETest");
		if(bde.getNameBDE().contentEquals("V")) {
			
		}
		else {
			System.out.println("Erreur Modify BDE");
			nb_erreurs++;
		}
		
		

		
/* --------------------------------------------------------------------------------------------------------
		
								TEAM

--------------------------------------------------------------------------------------------------------*/	
		
		//Création de team
		pre = teamFacade.getNumber();
		teamFacade.create(bde, "Test1");
		teamFacade.create(bde, "Test2");
		teamFacade.create(bde, "Test3");
		post=teamFacade.getNumber();
		if (pre != post-3) {
			System.out.println("Erreur Création Team");
			nb_erreurs++;
		}
		
		
		//Modification de team 
		t1 = teamFacade.findByName("Test1");
		System.out.println(t1.getNameTeam());
		t2 = teamFacade.findByName("Test2");
		t3 = teamFacade.findByName("Test3");
		teamFacade.modify(t1.getIdTeam(), "Test4", bde.getIdBDE());
		t1 = teamFacade.findByName("Test4");
		if (t1.getNameTeam().contentEquals("Test4")){
			teamFacade.modify(t1.getIdTeam(), "Test1", bde.getIdBDE());
		}
		else {
			System.out.println("Erreur Modification Team");
			nb_erreurs++;
		}
		
		
		
		
		
/* --------------------------------------------------------------------------------------------------------
		
								TEAM MEMBER 

-------------------------------------------------------------------------------------------------------*/
		
		
		
		//Creation Team Member (Ajout d'un User à une Team
		pre = teamMemberFacade.getNumber();

		teamMemberFacade.add_member(u, t1.getIdTeam(), false);
		post = teamMemberFacade.getNumber();
		if (pre != post-1) {
			System.out.println("Erreur Ajout Team member");
			nb_erreurs++;
		}
		
		
		//isChief test
		if (teamMemberFacade.isChief(u)) {
			System.out.println("Erreur isChief 1");
			nb_erreurs++;
		}
		teamMemberFacade.add_member(u, t2.getIdTeam(), true);
		if (teamMemberFacade.isChief(u) == false) {
			System.out.println("Erreur isChief 2");
			nb_erreurs++;
		}
		
		
		// Teams d'un user 
		ArrayList<Integer> teams = teamMemberFacade.findUserTeam(u.getId_user());
		if (teams.size() != 2) {
			System.out.println("Erreur Teams user");
			nb_erreurs++;
		}
		
		
		// Teams d'un BDE 
		ArrayList<Integer> teamsbde = bdeFacade.getListTeams(bde.getIdBDE());
		if (teamsbde.size() != 3) {
			System.out.println("Erreur Teams bde");
			nb_erreurs++;
		}
		
		
		// Membres d'une team 
		ArrayList<Integer> memberteam = teamMemberFacade.isPartOfTeam(t1.getIdTeam());
		if (memberteam.size() != 1) {
			System.out.println("Erreur members team");
			nb_erreurs++;
		}
		
/* --------------------------------------------------------------------------------------------------------
		
										FEE

--------------------------------------------------------------------------------------------------------*/		
		
		//Ajout Fee 
		pre = feeFacade.getNumber();
		feeFacade.create(u.getId_user(), "T", "T", 1);
		post=feeFacade.getNumber();
		if (post != pre+1) {
			System.out.println("Erreur création Fee");
			nb_erreurs++;
		}

		//Modification Fee 
		f = feeFacade.find("T");
		feeFacade.modify(f.getId_fee(), "T", "Tbis" , 1);
		f = feeFacade.find("T");
		if (f.getComment_fee().contentEquals("Tbis")) {

		}
		else {
			System.out.println("Erreur à la modification de fee");
			nb_erreurs++;
		}
		
		
/* --------------------------------------------------------------------------------------------------------
		
										POST

--------------------------------------------------------------------------------------------------------*/		

		//Ajout Post 
		pre = postFacade.getNumber();
		postFacade.create(u.getId_user(), "T", "T", bde.getIdBDE());
		post = postFacade.getNumber();
		if (post != pre+1) {
			System.out.println("Erreur création Post");
			nb_erreurs++;
		}

		//Modification Post 
		p = postFacade.find("T");
		postFacade.modify(p.getId_postBB(), u.getId_user(), "T", "Tbis" , bde.getIdBDE());
		p = postFacade.find("T");
		if (p.getText_postBB().contentEquals("Tbis")) {

		}
		else {
			System.out.println("Erreur à la modification de post");
			nb_erreurs++;
		}

/* --------------------------------------------------------------------------------------------------------
		
								SUPPRESSIONS

-------------------------------------------------------------------------------------------------------*/	
		
		//Suppression Team Member
		tm1 = teamMemberFacade.findByUserTeam(u, t1);
		tm2 = teamMemberFacade.findByUserTeam(u, t2);
		pre = teamMemberFacade.getNumber();
		teamMemberFacade.delete(tm1);
		post = teamMemberFacade.getNumber();
		if (post != pre-1) {
			System.out.println("Erreur Suppression Team Member ");
			nb_erreurs++;
		}
		else {
			teamMemberFacade.delete(tm2);
		}
		
		
		
		//Suppression Team 
		t1 = teamFacade.findByName("Test1");
		pre = teamFacade.getNumber();
		teamFacade.delete(t1);
		post = teamFacade.getNumber();
		if (post != pre-1) {
			System.out.println("Erreur Suppression Team");
			nb_erreurs++;
		}
		else {
			t2 = teamFacade.findByName("Test2");
			t3 = teamFacade.findByName("Test3");
			teamFacade.delete(t2);
			teamFacade.delete(t3);
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
		
		//Suppression Fee 
		f = feeFacade.find("T");
		pre = feeFacade.getNumber();
		feeFacade.delete(f);
		post = feeFacade.getNumber();
		if (post != pre-1) {
			System.out.println("Erreur Suppression Fee");
			nb_erreurs++;
		}
		
		//Suppression post 
		p = postFacade.find("T");
		pre = postFacade.getNumber();
		postFacade.delete(p);
		post = postFacade.getNumber();
		if (post != pre-1) {
			System.out.println("Erreur Suppression Post");
			nb_erreurs++;
		}
		
		System.out.println("Test finit avec " + nb_erreurs + " erreurs !");

	}
	
}
