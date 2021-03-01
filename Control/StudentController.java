/**
 * Control class to manage the student tasks
 * @author Ang Wei Jun
 * @author Alvin Chan
 * @author Choar Choong Leong
 * @author Tan Zhi Yong
 * @version 1.1
 * @since 2020-10-29
 */
package Control;

import java.util.*;

import Entity.Course;
import Entity.Index;
import Entity.Lesson;
import Entity.Student;

import Control.NotificationController;

import java.io.*;
import java.time.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class StudentController{

	/**
	 * To check the input from the user
	 * @return input selection of 1 or 2
	 */
	public static char confirmInput(){
		Scanner sc = new Scanner(System.in);
		System.out.println("1) Confirm");
		System.out.println("2) Cancel");
		char cfm = sc.next().charAt(0);
		char input = cfm;
		if(cfm == '1'){
			input = '1';
		}
		else if(cfm == '2'){
			input = '2';
		}
		else{
			while (cfm != '1' || cfm != '2') {
				System.out.println("Enter 1 or 2 only");
				cfm = sc.next().charAt(0);
				if(cfm == '1'){
					input = '1';
					break;
				}
				else if(cfm == '2'){
					input = '2';
					break;
				}
			}
		}
		return input;
	}

	/**
	 * Allows student to swap index with their peer
	 * @param stud Student object
	 * @return result of swapping index
	 */
	public static boolean swapIndex(Student stud){
		Scanner sc = new Scanner(System.in);
		Console cons = System.console();
		Student peer = new Student();
		Index index = new Index();

		System.out.println("Please enter the user ID of your peer: ");
		String peerID = sc.nextLine();
		char[] password = cons.readPassword("Please enter your password: ");
		String pw = new String(password);
		String hashedpw = HashPwController.hash(pw);

		if(!peer.login(peerID,hashedpw)){
			System.out.println("Invalid ID/password.");
			return false;
		}

		peer = peer.getStudObj(peerID);
		
		System.out.println("Enter the course of the index you want to swap: ");
		String courseID = sc.nextLine();

		ArrayList<String> studCourseList = stud.getCourseList();
		ArrayList<String> peerCourseList = peer.getCourseList();
		Course courseToChange = new Course();
		courseToChange = courseToChange.getCourseObj(courseID);

		if(!checkStudRegisteredCourse(studCourseList, courseID)){				//check if both stud have registered for this course//
			System.out.println("You have not registered for this course.");
			return false;
		}

		if(!checkStudRegisteredCourse(peerCourseList, courseID)){
			System.out.println("Your peer have not registered for this course.");
			return false;
		}
													///////////////////////////////////////////////////////
		boolean flag = false;
		System.out.println("Enter the index you want to swap: ");
		int indexID = 0;
		do{
			try {
				indexID = sc.nextInt();
				sc.nextLine();
				if (indexID > 0) {
					flag = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input.");
				//sc.nextLine();
			}
		}while(!flag);
		flag = false;

		ArrayList<Integer> courseIndexList = courseToChange.getIndexGroupList();	//check if index exist in this course//

		if(!checkIndexExistInCourse(courseIndexList, indexID)){
			System.out.println("Index does not exist in this course.");
			return false;
		}

		ArrayList<Integer> studIndexList = stud.getIndexGroupList();			//check if student have registered this index//

		if(!checkStudRegisteredIndex(studIndexList, indexID)){
			System.out.println("You have not registered for this index");
			return false;
		}
///////////////////////////////////
		System.out.println("Enter the index you want to swap with: ");
		int peerIndexID = 0;
		do{
			try {
				peerIndexID = sc.nextInt();
				sc.nextLine();
				if (peerIndexID > 0) {
					flag = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input.");
				//sc.nextLine();
			}
		}while(!flag);
		flag = false;

		if(!checkIndexExistInCourse(courseIndexList, peerIndexID)){				//check if index exist in this course//
			System.out.println("Index does not exist in this course.");
			return false;
		}

		ArrayList<Integer> peerIndexList = peer.getIndexGroupList();			//check if peer have registered this index//

		if(!checkStudRegisteredIndex(peerIndexList, peerIndexID)){
			System.out.println("Your peer have not registered for this index");
			return false;
		}

		Index studIndex = index.getIndexObj(indexID);
		Index peerIndex = index.getIndexObj(peerIndexID);

		ArrayList<Lesson> indSchedule = studIndex.getSchedule();
		ArrayList<Lesson> peerIndSchedule = peerIndex.getSchedule();


		System.out.println("Confirm to change index?");
		char input = confirmInput();

		if(input == '1'){
			ArrayList<Lesson> studSchedule = stud.getSchedule();
			ArrayList<Lesson> peerSchedule = peer.getSchedule();
			studSchedule.removeIf(n ->(n.getCourse().equals(courseID)));	//remove schedule for student//
			peerSchedule.removeIf(n ->(n.getCourse().equals(courseID)));	//remove schedule for peer//
			
			if(!checkStudSchedule(studSchedule, peerIndSchedule)){			//check stud schedule clash//
				return false;
			}
			if(!checkStudSchedule(peerSchedule, indSchedule)){			//check peer schedule clash//
				return false;
			}

															//swap index//
			for(int j=0; j<studIndexList.size(); j++){			//remove index from stud obj//
				if(studIndexList.get(j).equals(indexID)){
					studIndexList.remove(j);
				}
			}

			for(int j=0; j<peerIndexList.size(); j++){			//remove index from PEER obj//
				if(peerIndexList.get(j).equals(peerIndexID)){
					peerIndexList.remove(j);
				}
			}

			for(int i=0; i<studCourseList.size(); i++){			//remove course from stud obj//
				if(studCourseList.get(i).equals(courseID)){
					studCourseList.remove(i);
				}
			}

			for(int i=0; i<peerCourseList.size(); i++){			//remove course from PEER obj//
				if(peerCourseList.get(i).equals(courseID)){
					peerCourseList.remove(i);
				}
			}

			studIndexList.add(peerIndexID);						//add index to student obj//
			peerIndexList.add(indexID);						//add index to PEER obj//
			studCourseList.add(courseID);					//add course to student obj//
			peerCourseList.add(courseID);					//add course to PEER obj//

			for(int i=0; i<peerIndSchedule.size(); i++){				//add new schedule to student//
				Lesson lessonToAdd = (Lesson) peerIndSchedule.get(i);
				studSchedule.add(lessonToAdd);
			}

			for(int i=0; i<indSchedule.size(); i++){				//add new schedule to PEER//
				Lesson peerLessonToAdd = (Lesson) indSchedule.get(i);
				peerSchedule.add(peerLessonToAdd);
			}

			stud.setCourseList(studCourseList);					//update student obj//
			stud.setSchedule(studSchedule);						
			stud.setIndexGroupList(studIndexList);
			stud.updateStudent(stud);

			peer.setCourseList(peerCourseList);					//update PEER obj//
			peer.setSchedule(peerSchedule);						
			peer.setIndexGroupList(peerIndexList);
			peer.updateStudent(peer);

			ArrayList<Student> indStudentList = studIndex.getStudentList();			//remove student from stud's index obj//
			for(int j=0; j<indStudentList.size(); j++){
				if(indStudentList.get(j).getStudentID().equals(stud.getStudentID())){
					indStudentList.remove(j);
				}
			}

			ArrayList<Student> peerIndStudentList = peerIndex.getStudentList();		//remove peer from peer's index obj//
			for(int j=0; j<peerIndStudentList.size(); j++){
				if(peerIndStudentList.get(j).getStudentID().equals(peer.getStudentID())){
					peerIndStudentList.remove(j);
				}
			}

			indStudentList.add(peer);						//add peer into stud's index//
			peerIndStudentList.add(stud);						//add stud into peer's index//

			studIndex.setStudentList(indStudentList);				//update stud index obj//
			studIndex.updateIndex(studIndex);
			peerIndex.setStudentList(peerIndStudentList);				//update peer index obj//
			peerIndex.updateIndex(peerIndex);


			System.out.println("Index swapped.");

		}
		else if(input == '2'){
			System.out.println("Cancelled.");
			return false;
		}
		
		return true;
	}

	/**
	 * Check student's registered indexes
	 * @param studIndexList List of indexes the student registered
	 * @param indexID Index number
	 * @return result of checking if index already registered
	 */
	public static boolean checkStudRegisteredIndex(ArrayList<Integer> studIndexList, Integer indexID){	//returns true if registered//

		for(int i=0; i<studIndexList.size(); i++){
			if(studIndexList.get(i).equals(indexID)){
				return true;
			}
		}

		return false;
	}
	
	/**
	 * Check student's registered course
	 * @param studCourseList List of courses the student registered
	 * @param courseID Course ID
	 * @return result of checking if course already registered
	 */
	public static boolean checkStudRegisteredCourse(ArrayList<String> studCourseList, String courseID){	//returns true if registered//

		for(int i=0; i<studCourseList.size(); i++){
			if(studCourseList.get(i).equals(courseID)){
				return true;
			}
		}

		return false;
	}
	
	/**
	 * Allows student to change index of an course to another index
	 * @param stud Student object
	 * @return result of changing index
	 */
	public static boolean changeIndex(Student stud){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the course of index you want to change: ");
		String courseChangeStr = sc.nextLine();
		ArrayList<String> studCourseList = stud.getCourseList();

		boolean flag = false;							//check if stud have registered for this course//
		
		if(!checkStudRegisteredCourse(studCourseList, courseChangeStr)){
			System.out.println("You have not registered for this course.");
			return false;
		}
		
		Course courseToChange = new Course();
		courseToChange = courseToChange.getCourseObj(courseChangeStr);

		System.out.println("Enter the index you want to remove: ");
		int indexRemoveStr = 0;
		do{
			try {
				indexRemoveStr = sc.nextInt();
				sc.nextLine();
				if (indexRemoveStr > 0) {
					flag = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input.");
				//sc.nextLine();
			}
		}while(!flag);
		flag = false;

		ArrayList<Integer> courseIndexList = courseToChange.getIndexGroupList();	//check if both index exist in this course//

		if(!checkIndexExistInCourse(courseIndexList, indexRemoveStr)){
			System.out.println("Index does not exist in this course.");
			return false;
		}
		
		System.out.println("Enter the index you want to change to: ");
		int indexAddStr = 0;
		do{
			try {
				indexAddStr = sc.nextInt();
				sc.nextLine();
				if (indexAddStr > 0) {
					flag = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input.");
				//sc.nextLine();
			}
		}while(!flag);
		flag = false;
		
		if(!checkIndexExistInCourse(courseIndexList, indexAddStr)){
			System.out.println("Index does not exist in this course.");
			return false;
		}

		System.out.println("Confirm to change index?");
		char input = confirmInput();

		Index index = new Index();
		Index indRemove = index.getIndexObj(indexRemoveStr);
		Index indAdd = index.getIndexObj(indexAddStr);

		ArrayList<Lesson> studSchedule = stud.getSchedule();
		ArrayList<Integer> studIndexList = stud.getIndexGroupList();
		ArrayList<Lesson> indSchedule = indAdd.getSchedule();

		studSchedule.removeIf(n ->(n.getCourse().equals(courseChangeStr)));	//remove schedule for student//

		if(!checkStudSchedule(studSchedule, indSchedule)){			//check schedule clash//
			return false;
		}

		if(input == '1'){

			for(int j=0; j<studIndexList.size(); j++){			//remove index from stud obj//
				if(studIndexList.get(j).equals(indexRemoveStr)){
					studIndexList.remove(j);
				}
			}

			for(int i=0; i<studCourseList.size(); i++){			//remove course from stud obj//
				if(studCourseList.get(i).equals(courseChangeStr)){
					studCourseList.remove(i);
				}
			}									

			studIndexList.add(indexAddStr);						//add index to student obj//
			studCourseList.add(courseChangeStr);					//add course to student obj//

			for(int i=0; i<indSchedule.size(); i++){				//add new schedule to student//
				Lesson lessonToAdd = (Lesson) indSchedule.get(i);
				studSchedule.add(lessonToAdd);
			}

			stud.setCourseList(studCourseList);					//update student obj//
			stud.setSchedule(studSchedule);						
			stud.setIndexGroupList(studIndexList);
			stud.updateStudent(stud);
														
			ArrayList<Student> indStudentList = indRemove.getStudentList();		//remove student from indexRemove obj//
			for(int j=0; j<indStudentList.size(); j++){
				if(indStudentList.get(j).getStudentID().equals(stud.getStudentID())){
					indStudentList.remove(j);
				}
			}
			indRemove.setStudentList(indStudentList);				//update indexRemove obj//
			Integer tempVacancy = indRemove.getVacancy();
			tempVacancy++;
			indRemove.setVacancy(tempVacancy);
			indRemove.updateIndex(indRemove);


			ArrayList<Student> indStudList = indAdd.getStudentList();		//add student to indexAdd obj//
			indStudList.add(stud);
			indAdd.setStudentList(indStudList);					//update indexAdd obj//
			tempVacancy = indAdd.getVacancy();
			tempVacancy --;
			indAdd.setVacancy(tempVacancy);
			indAdd.updateIndex(indAdd);

			System.out.println("Index changed.");

		}
		else if(input == '2'){
			System.out.println("Cancelled.");
			return false;
		}		
		

		return true;
	}

	/**
	 * Check if an index exist in the course
	 * @param courseIndexList List of indexes
	 * @param indexID Index number
	 * @return result of checking if index exist in course
	 */
	public static boolean checkIndexExistInCourse(ArrayList<Integer> courseIndexList, int indexID){		//returns true if exist//

		for(int i=0; i<courseIndexList.size(); i++){
			if(courseIndexList.get(i).equals(indexID)){
				return true;
				
			}
		}
		
		return false;
	}

	/**
	 * Allows student to drop course
	 * @param stud Student object
	 * @return result of dropping course
	 */
	public static boolean dropCourse(Student stud){
		Scanner sc = new Scanner(System.in);


		System.out.println("Enter the course you want to drop.");
		String courseID = sc.nextLine();	

		ArrayList<String> studCourseList = stud.getCourseList();

		boolean found = false;

		for(int i=0; i<studCourseList.size(); i++){
			if(studCourseList.get(i).equals(courseID)){
				found = true;
				break;
			}
		}
		if(found != true){
			System.out.println("You have not registered for this course");
			return false;
		}

		System.out.println("Confirm to drop?");
		char input = confirmInput();
		
		ArrayList<Lesson> studSchedule = stud.getSchedule();
		ArrayList<Integer> studIndexList = stud.getIndexGroupList();
		Integer indexID = 0;
		Index ind = new Index();
		Course course = new Course();
		

		if(input == '1'){
			
			for(int j=0; j<studSchedule.size(); j++){				//remove from stud obj//
				if(studSchedule.get(j).getCourse().equals(courseID)){
					indexID = studSchedule.get(j).getIndex();
					break;
				}
			}
			studSchedule.removeIf(n ->(n.getCourse().equals(courseID)));
			
			for(int j=0; j<studCourseList.size(); j++){
				if(studCourseList.get(j).equals(courseID)){
					studCourseList.remove(j);
				}
			}

			for(int j=0; j<studIndexList.size(); j++){
				if(studIndexList.get(j).equals(indexID)){
					studIndexList.remove(j);
				}
			}
			stud.setSchedule(studSchedule);
			stud.setCourseList(studCourseList);
			stud.setIndexGroupList(studIndexList);
			stud.updateStudent(stud);
			
			ind = ind.getIndexObj(indexID);						//remove from index obj//
			ArrayList<Student> indStudentList = ind.getStudentList();
			for(int j=0; j<indStudentList.size(); j++){
				if(indStudentList.get(j).getStudentID().equals(stud.getStudentID())){
					indStudentList.remove(j);
				}
			}
			ind.setStudentList(indStudentList);
			Integer tempVacancy = ind.getVacancy();
			tempVacancy++;
			ind.setVacancy(tempVacancy);
			ind.updateIndex(ind);

			course = course.getCourseObj(courseID);					//remove from course obj//
			ArrayList<Student> courseStudentList = course.getStudentList();
			for(int j=0; j<courseStudentList.size(); j++){
				if(courseStudentList.get(j).getStudentID().equals(stud.getStudentID())){
					courseStudentList.remove(j);
				}
			}
			course.setStudentList(courseStudentList);
			course.updateCourseObj(course);

			System.out.println("Course dropped.");
			
			ArrayList<Student> indWaitList = ind.getWaitList();			//check waitlist and add if got slot////////////////////////////////////////////
			
			if(tempVacancy != 0 && indWaitList.size() != 0){
				Student studInWaitList = indWaitList.get(0);
				String studInWLEmail = studInWaitList.getEmail();
				NotificationController nc = new NotificationController();
				if(addFromWaitList(studInWaitList, ind, course)){
					nc.sendEmail(studInWLEmail,indexID);
				}
			}

			

		}
		else if(input == '2'){
			System.out.println("Cancelled.");
			return false;
		}
		

		return true;
	}

	/**
	 * Add student from wait list to course
	 * @param stud Student object
	 * @param index Index object
	 * @param course Course object
	 * @return Result of adding from wait list to course
	 */
	public static boolean addFromWaitList(Student stud, Index index, Course course){


		String studentID = stud.getStudentID();
		ArrayList<Integer> studIndexList = stud.getIndexGroupList();
		ArrayList<String> studCourseList = stud.getCourseList();
		ArrayList<Lesson> studSchedule = stud.getSchedule();
		ArrayList<Integer> studWaitList = stud.getWaitList();

		Integer indexID = index.getIndexID();
		Integer vacancy = index.getVacancy();
		ArrayList<Student> indWaitList = index.getWaitList();
		ArrayList<Student> indStudList = index.getStudentList();
		ArrayList<Lesson> indSchedule = index.getSchedule();

		String courseID = course.getCourseID();
		ArrayList<Student> courseStudentList = course.getStudentList();

		if(checkStudRegisteredCourse(studCourseList, courseID)){		//check if student reg for other index of this course//
			return false;
		}

		if(!checkStudSchedule(studSchedule, indSchedule)){			//check for schedule clash// 	////////////////////////////////////////////////////////
			return false;
		}

		studIndexList.add(indexID);						//add index, course and lesson to stud obj//
		studCourseList.add(courseID);
		for(int i=0; i<indSchedule.size(); i++){
			Lesson lessonToAdd = (Lesson) indSchedule.get(i);
			studSchedule.add(lessonToAdd);
		}
		stud.setIndexGroupList(studIndexList);					//update stud obj//
		stud.setCourseList(studCourseList);
		stud.setSchedule(studSchedule);
		stud.updateStudent(stud);


		courseStudentList.add(stud);						//add student to course student list//
		course.setStudentList(courseStudentList);				//update course obj//
		course.updateCourseObj(course);


		indStudList.add(stud);							//update index obj//
		index.setStudentList(indStudList);
		vacancy --;
		index.setVacancy(vacancy);
		for(int i=0; i<indWaitList.size(); i++){				//remove stud from waitlist//
			if(indWaitList.get(i).getStudentID().equals(studentID)){
				indWaitList.remove(i);
			}
		}
		index.setWaitList(indWaitList);
		index.updateIndex(index);

		return true;

	}

	/**
	 * Allows student to add an course
	 * @param stud Student object
	 * @return Result of adding course
	 */
	public static boolean addCourse(Student stud){
		boolean flag = false;
		//boolean wlflag = false;
		Scanner sc = new Scanner(System.in);
		Index index = new Index();
		ArrayList<Integer> studWaitList;
		ArrayList<Integer> studIndexList;
		ArrayList<String> studCourseList;
		Integer indexID = 0;
		DataMgmt dm = new DataMgmt();
		Course course = new Course();


		System.out.println("Enter the course you want to add");
		String courseID = sc.nextLine();

		Course courseToAdd = course.getCourseObj(courseID);
		if(courseToAdd == null){
			System.out.println("Course does not exist.");
			return false;
		}

		System.out.println("Enter the index you want to add");
		
		do{
			try {
				indexID = sc.nextInt();
				//sc.nextLine();
				if (indexID > 0) {
					flag = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input.");
				//sc.nextLine();
			}
		}while(!flag);
		flag = false;

		ArrayList<Integer> courseIndexList = courseToAdd.getIndexGroupList();

		boolean found=false;
		for(int i=0; i<courseIndexList.size(); i++){
			if(courseIndexList.get(i).equals(indexID)){
				found = true;
				break;
			}
		}


		if(found != true){
			System.out.println("No such index in this course.");
			return false;
		}

		index = index.getIndexObj(indexID);
		if(index == null){
			System.out.println("Index does not have a lesson yet. Please contact administrator.");
			return false;
		}

		int vacancy = index.getVacancy();
		studWaitList = stud.getWaitList();
		studIndexList = stud.getIndexGroupList();
		studCourseList = stud.getCourseList();
		ArrayList<Lesson> studSchedule = stud.getSchedule();

		if(checkStudRegisteredCourse(studCourseList, courseID)){
			System.out.println("You have already registered for this course.");
			return false;
		}


		ArrayList<Lesson> indSchedule = index.getSchedule();

		if(!checkStudSchedule(studSchedule, indSchedule)){			//check for schedule clash//
			return false;
		}

		ArrayList<Student> indWaitList = index.getWaitList();

		if(vacancy == 0){
			for(int i=0; i<studWaitList.size(); i++){			//check if student alrdy on wait list//
				if(studWaitList.get(i) == indexID){
					System.out.println("Already in wait list");
					return false;
				}
			}
						
			System.out.println("Add yourself to wait list?");
			char cfm = confirmInput();

			if(cfm == '1'){
				indWaitList.add(stud);
				index.setWaitList(indWaitList);
				index.updateIndex(index);
				System.out.println("Added to wait list.");
				return true;
			}
			else if(cfm == '2'){
				System.out.println("Cancelled.");
				return false;
			}
			
			
		}
		else if(vacancy > 0){
			System.out.println("Confirm to add?");
			char input = confirmInput();

			if(input == '1'){
				studIndexList.add(indexID);						//update student obj//
				studCourseList.add(courseID);
				for(int i=0; i<indSchedule.size(); i++){
					Lesson lessonToAdd = (Lesson) indSchedule.get(i);
					studSchedule.add(lessonToAdd);
				}
				stud.setIndexGroupList(studIndexList);
				stud.setCourseList(studCourseList);
				stud.setSchedule(studSchedule);
				stud.updateStudent(stud);
				
				ArrayList<Student> courseStudList = courseToAdd.getStudentList();	//update course obj//
				courseStudList.add(stud);
				courseToAdd.setStudentList(courseStudList);
				course.updateCourseObj(courseToAdd);

				ArrayList<Student> indStudList = index.getStudentList();		//update index obj//
				indStudList.add(stud);
				index.setStudentList(indStudList);
				int tempVacancy = index.getVacancy();
				tempVacancy --;
				index.setVacancy(tempVacancy);
				index.updateIndex(index);
				System.out.println("Course added.");
				
			}
			else if(input == '2'){
				System.out.println("Cancelled.");
				return false;
			}
			

		}

	   
		return true;
	}

	/**
	 * Check if student's schedule clash with the index the student wanted to register
	 * @param studSchedule List of student's lessons
	 * @param indSchedule List of lessons
	 * @return Result of checking clash/no clash
	 */
	public static boolean checkStudSchedule(ArrayList<Lesson> studSchedule, ArrayList<Lesson> indSchedule){		//returns FALSE if clash//
		for(int j=0; j<studSchedule.size(); j++){			
			Lesson l1 = (Lesson) studSchedule.get(j);
			for(int k=0; k<indSchedule.size(); k++){
				Lesson l2 = (Lesson) indSchedule.get(k);
				if(l1.checkConflict(l2) == true){
					System.out.println("Index's schedule clashes with your schedule.");
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Print the list of courses the particular student registered
	 * @param studentID Student ID
	 */
	public static void printCourseReg(String studentID){
		Student stud = new Student();
		Student student = new Student();
		stud = student.getStudObj(studentID);
		ArrayList<String> courseList = stud.getCourseList();
		ArrayList<Integer> indexList = stud.getIndexGroupList();

		System.out.println("Courses you registered: ");

		for(int i=0; i<courseList.size(); i++){
			System.out.println(courseList.get(i)+": "+indexList.get(i));
		}
		System.out.println("\n");
	}

	/**
	 * Print the vacancy of an index
	 * @return result of checking vacancy
	 */
	public static boolean checkVacancy(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter index you want to check: ");
		int index = sc.nextInt();

		Index ind = new Index();
		Index indGroup = new Index();
		if(indGroup.getIndexObj(index) == null){
			System.out.println("Index not created yet");
			return false;
		}
		ind = indGroup.getIndexObj(index);

		System.out.println("Vacancy for index "+index+": "+ind.getVacancy());
		return true;
	}

	/**
	 * Check access period for student
	 * @return Result of access
	 */
	public static boolean checkAccess() {
		boolean access = false;
		Calendar now = Calendar.getInstance();
		try{
			File f = new File("accessTime.txt");
        		FileInputStream fis = new FileInputStream(f);
	        	ObjectInputStream ois = new ObjectInputStream(fis);
	        	Calendar start = (Calendar) ois.readObject();
	        	Calendar end = (Calendar) ois.readObject();
	        
	        	if(now.after(start) && now.before(end)) {
	        		System.out.println("access granted");
	        		access = true;
	        	}
	        	else {
	  
				System.out.println("Access period: "+start.getTime().toString()+" ~ "+end.getTime().toString());
	        	}
		}catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return access;
	}

	
}