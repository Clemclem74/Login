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
		PollFacade pollFacade = new PollFacade();
		MeetingFacade meetingFacade = new MeetingFacade();
		ActivityFacade activityFacade = new ActivityFacade();
		EventFacade eventFacade = new EventFacade();
		
		
		int pre;
		int post;
		User u;
		BDE bde;
		Fee f;
		Post p;
		Poll poll;
		Meeting meeting;
		Team t1;
		Team t2;
		Team t3;
		TeamMember tm1;
		TeamMember tm2;
		Event e;
		BDEActivity a1;
		StaffActivity a2;
		
		
		
/* --------------------------------------------------------------------------------------------------------
		
								USER
								
   --------------------------------------------------------------------------------------------------------*/
		//Ajout User 
		pre = userFacade.getNumber();
		userFacade.register("T", "T@T.T", "T", "T", "T", "00");
		post = userFacade.getNumber();
		if (post != pre+1) {
			System.out.println("Erreur cr�ation User");
			nb_erreurs++;
		}
		
		//Modification User 
		u = userFacade.findByEmail("T@T.T");
		userFacade.modify(u.getId_user(), "V", u.getEmailuser(), u.getPassworduser(), u.getFirstname(), u.getLastname(), "01", u.getCurrentBDE());
		u = userFacade.findByEmail("T@T.T");
		if (u.getUsername().contentEquals("V")) {
			
		}
		else {
			System.out.println("Erreur � la modification");
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
			System.out.println("Erreur cr�ation BDE");
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
		
		//Cr�ation de team
		pre = teamFacade.getNumber();
		teamFacade.create(bde, "Test1");
		teamFacade.create(bde, "Test2");
		teamFacade.create(bde, "Test3");
		post=teamFacade.getNumber();
		if (pre != post-3) {
			System.out.println("Erreur Cr�ation Team");
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
	
		
		
		//Creation Team Member (Ajout d'un User � une Team
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
			System.out.println("Erreur cr�ation Fee");
			nb_erreurs++;
		}

		//Modification Fee 
		f = feeFacade.find("T");
		feeFacade.modify(f.getId_fee(), "T", "Tbis" , 1);
		f = feeFacade.find("T");
		if (f.getComment_fee().contentEquals("Tbis")) {

		}
		else {
			System.out.println("Erreur � la modification de fee");
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
			System.out.println("Erreur cr�ation Post");
			nb_erreurs++;
		}

		//Modification Post 
		p = postFacade.find("T");
		postFacade.modify(p.getId_postBB(), u.getId_user(), "T", "Tbis" , bde.getIdBDE());
		p = postFacade.find("T");
		if (p.getText_postBB().contentEquals("Tbis")) {

		}
		else {
			System.out.println("Erreur � la modification de post");
			nb_erreurs++;
		}
		
/* --------------------------------------------------------------------------------------------------------
		
									POLL

--------------------------------------------------------------------------------------------------------*/
		
		//ajout Poll
		pre = pollFacade.getNumber();
		ArrayList<String> testArray = new ArrayList<>();
		testArray.add("1");
		testArray.add("2");
		testArray.add("3");
		pollFacade.createPoll(u.getId_user(),"T", testArray, bde.getIdBDE());
		post = pollFacade.getNumber();
		if (post != pre+1) {
			System.out.println("Erreur cr�ation Post");
			nb_erreurs++;
		}
		testArray.clear();
		testArray.add("a");
		testArray.add("b");
		testArray.add("c");
		
		//modification Poll
		poll = pollFacade.find("T");
		pollFacade.modify(poll.getId_pollBB(), u.getId_user(), "T", testArray , bde.getIdBDE());
		poll = pollFacade.find("T");
		if(poll.getchoices_pollBB().contentEquals("  a ,  b ,  c")) {
			
		}
		else {
			System.out.println("Erreur � la modification de poll");
			nb_erreurs++;
		}
		
/* --------------------------------------------------------------------------------------------------------
		
										MEETING

--------------------------------------------------------------------------------------------------------*/
		
		//ajout Meeting
		pre = meetingFacade.getNumber();
		meetingFacade.create(u.getId_user(), "T", "2020-01-08", bde.getIdBDE());
		post = meetingFacade.getNumber();
		if (post != pre+1) {
			System.out.println("Erreur cr�ation Post");
			nb_erreurs++;
		}
		
		//modification Meeting
		meeting = meetingFacade.find("T");
		meetingFacade.modify(meeting.getId_meeting(), u.getId_user(), "T", "2020-02-02", bde.getIdBDE());
		meeting = meetingFacade.find("T");
		if(meeting.getMeeting_date().contentEquals("2020-02-02")) {
			
		}
		else {
			System.out.println("Erreur � la modification de meeting");
			nb_erreurs++;
		}
		
		
		
		
/* --------------------------------------------------------------------------------------------------------
		
											EVENT

--------------------------------------------------------------------------------------------------------*/

//create Event
		pre = eventFacade.getNumber();
		Event event = new Event();
		event.setTitle("T");
		event.setDescription("T");
		event.setEvent_date("2019-02-01");
		event.setImage("T");
		event.setResponsible(0);
		eventFacade.create(event);
		post = eventFacade.getNumber();
		if (post != pre+1) {
			System.out.println("Erreur cr�ation Event");
			nb_erreurs++;
		}

		//modification Meeting
		event = eventFacade.find("T");
		event.setEvent_date("2020-02-02");
		eventFacade.modify(event.getId_event(),event);
		event = eventFacade.find("T");
		if(event.getEvent_date().contentEquals("2020-02-02")) {

		}
		else {
			System.out.println("Erreur � la modification de event");
			nb_erreurs++;
		}
		
/* --------------------------------------------------------------------------------------------------------
		
								Activity

--------------------------------------------------------------------------------------------------------*/

//create Activity
		pre = activityFacade.getNumber();
		BDEActivity acti = new BDEActivity();

		acti.setDate("2020-02-02");
		acti.setDuration("1h30");
		acti.setStart_hour("12h00");
		acti.setNb_users(2);
		activityFacade.create(acti);
		post = activityFacade.getNumber();
		if (post != pre+1) {
		System.out.println("Erreur cr�ation Activity");
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
		
		
		
		//Suppression Poll
		poll = pollFacade.find("T");
		pre = pollFacade.getNumber();
		pollFacade.delete(poll);
		post = pollFacade.getNumber();
		System.out.println(pre);
		System.out.println(post);
		if(post != pre-1){
			System.out.println("Erreur Suppression Poll");
			nb_erreurs++;
		}
		
		//Suppression Meeting
		meeting =meetingFacade.find("T");
		pre = meetingFacade.getNumber();
		meetingFacade.delete(meeting);
		post = meetingFacade.getNumber();
		if (post != pre-1) {
			System.out.println("Erreur Suppression Meeting");
			nb_erreurs++;
		}

		//Suppression Event
		event = eventFacade.find("T");
		pre = eventFacade.getNumber();
		eventFacade.delete(event);
		post = eventFacade.getNumber();
		if (post != pre-1) {
			System.out.println("Erreur Suppression Event");
			nb_erreurs++;
		}
		
		//Suppression Event
		pre = activityFacade.getNumber();
		activityFacade.delete(acti);
		post = activityFacade.getNumber();
		if (post != pre-1) {
			System.out.println("Erreur Suppression Activity");
			nb_erreurs++;	
		}
				
		System.out.println("Test finit avec " + nb_erreurs + " erreurs !");

	}
	

}