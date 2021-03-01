/**
 * Entity class for Student
 * @author Ang Wei Jun
 * @author Alvin Chan
 * @author Choar Choong Leong
 * @author Tan Zhi Yong
 * @version 1.1
 * @since 2020-10-29
 */
package Entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Control.DataMgmt;
import Interface.LoginInterface;


public class Student implements Serializable, LoginInterface
{
	
	private static final long serialVersionUID = 1L;
	/**
	 * Student ID of this student
	 */
	private String studentID;
	/**
	 * Student password of this student
	 */
	private String password;
	/**
	 * Student name of this student
	 */
	private String name;
	/**
	 * Student matriculation number of this student
	 */
	private String matric;
	/**
	 * gender of this student
	 */
	private char gender;
	/**
	 * nationality of this student
	 */
	private String nationality;
	/**
	 * school of this student
	 */
	private String school;
	/**
	 * Schedule of this student
	 */
	private ArrayList<Lesson> schedule;
	/**
	 * List of courses of this student
	 */
	private ArrayList<String> courseList;
	/**
	 * List of indexes of this student
	 */
	private ArrayList<Integer> indexGroupList;
	/**
	 * Wait list of this student
	 */
	private ArrayList<Integer> waitList;
	/**
	 * email of this student
	 */
	private String email;
	
	/**
	 * Create a student without attributes
	 */
	public Student() {

	}
	
	/**
	 * Create a student with an attribute
	 * @param studentID Student's ID
	 */
	public Student(String studentID) {
		this.studentID = studentID;
	}
	
	/**
	 * Create a student with the following attributes
	 * @param studentID Student's ID
	 * @param password Student's password
	 * @param name Student's name
	 * @param matric Student's matriculation number
	 * @param gender Student's gender
	 * @param nationality Student's nationality
	 * @param school Student's school
	 * @param schedule List of lessons
	 * @param courseList List of courses
	 * @param indexGroupList List of indexes
	 * @param waitList List of wait list
	 * @param email Student's email
	 */
	public Student(String studentID, String password, String name, String matric, char gender, String nationality, String school, 
		ArrayList<Lesson> schedule, ArrayList<String> courseList, ArrayList<Integer> indexGroupList, 
		ArrayList<Integer> waitList, String email) {

		this.studentID = studentID;
		this.password = password;
		this.name = name;
		this.matric = matric;
		this.gender = gender;
		this.nationality = nationality;
		this.school = school;
		this.schedule = schedule;
		this.courseList = courseList;
		this.indexGroupList = indexGroupList;
		this.waitList = waitList;
		this.email = email;
	}
	
	/**
	 * Get student ID of this student
	 * @return studentID
	 */
	public String getStudentID(){
		return studentID;
	}

	/**
	 * Change student ID of this student
	 * @param studentID new studentID
	 */
	public void setStudentID(String studentID){
		this.studentID = studentID;
	}

	/**
	 * Get student password of this student
	 * @return password
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 * Change student password of this student
	 * @param password new password
	 */
	public void setPassword(String password){
		this.password = password;
	}

	/**
	 * Get name of this student
	 * @return name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Change name of this student
	 * @param name new name
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Get matriculation number of this student
	 * @return matric
	 */
	public String getMatric(){
		return matric;
	}
	
	/**
	 * Change matriculation number of this student
	 * @param matric new matric
	 */
	public void setMatric(String matric){
		this.matric = matric;
	}
	
	/**
	 * Get gender of this student
	 * @return gender
	 */
	public char getGender(){
		return gender;
	}

	/**
	 * Change gender of this student
	 * @param gender new gender
	 */
	public void setGender(char gender){
		this.gender = gender;
	}
	
	/**
	 * Get nationality of this student
	 * @return nationality
	 */
	public String getNationality(){
		return nationality;
	}

	/**
	 * Change nationality of this student
	 * @param nationality new nationality
	 */
	public void setNationality(String nationality){
		this.nationality = nationality;
	}

	/**
	 * Get school of this student
	 * @return school
	 */
	public String getSchool(){
		return school;
	}
	
	/**
	 * Change school of this student
	 * @param School new School
	 */
	public void setSchool(String School){
		this.school = school;
	}

	/**
	 * Get schedule of this student
	 * @return schedule
	 */
	public ArrayList<Lesson> getSchedule(){
		return schedule;
	}
	
	/**
	 * Change schedule of this student
	 * @param schedule new schedule list
	 */
	public void setSchedule(ArrayList<Lesson> schedule){
		this.schedule = schedule;
	}

	/**
	 * Get list of courses of this student
	 * @return courseList
	 */
	public ArrayList<String> getCourseList() {
		return courseList;
	}

	/**
	 * Change list of courses of this student
	 * @param courseList new courseList
	 */
	public void setCourseList(ArrayList<String> courseList) {
		this.courseList = courseList;
	}

	/**
	 * Get list of indexes of this student
	 * @return indexGroupList
	 */
	public ArrayList<Integer> getIndexGroupList() {
		return indexGroupList;
	}

	/**
	 * Change list of indexes of this student
	 * @param indexGroupList new indexGroupList
	 */
	public void setIndexGroupList(ArrayList<Integer> indexGroupList) {
		this.indexGroupList = indexGroupList;
	}

	/**
	 * Get waitlist of this student
	 * @return waitList
	 */
	public ArrayList<Integer> getWaitList() {
		return waitList;
	}

	/**
	 * Change waitlist of this student
	 * @param waitList new waitList
	 */
	public void setWaitList(ArrayList<Integer> waitList) {
		this.waitList = waitList;
	}
	
	/**
	 * Get email of this student
	 * @return email
	 */
	public String getEmail(){
		return email;
	}

	/**
	 * Change email of this student
	 * @param email new email
	 */
	public void setEmail(String email){
		this.email = email;
	}
	
	/**
	 * Read all student object from txt files
	 * @return list of students
	 */
	@SuppressWarnings({"unused", "unchecked"})
	public List<Student> getAllStudObj() {
		String dir = "student.txt";
		DataMgmt dm = new DataMgmt();
		List<Student> studList = new ArrayList<Student>();
		try {
			studList = dm.readSerialObj(dir);

			if (studList == null) {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studList;
	}
	
	/**
	 * Get a Student object
	 * @param studentID Student's ID
	 * @return Student object
	 */
	@SuppressWarnings({"unused", "unchecked"})
	public Student getStudObj(String studentID) {
		Student stud = null;
		Student student = new Student();

		try {
			List<Student> studList = student.getAllStudObj();

			int i = 0;
			while(i < studList.size()) {
				if (studList.get(i).getStudentID().equals(studentID)) {
					stud = studList.get(i);
					break;
				}
				i++;
			}

			if (stud == null) {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stud;
	}

	/**
	 * Update and write back student list to txt file
	 * @param s Student object
	 */
	public void updateStudent(Student s) {
		Student stud = new Student();
		String dir = "student.txt";
		DataMgmt dm = new DataMgmt();
		try {
			List<Student> studList = stud.getAllStudObj();

			int i = 0;
			while(i < studList.size()){

				if (studList.get(i).getStudentID().equals(s.getStudentID())) {
					studList.set(i, s);
					break;
				}

				i++;
			}

			dm.writeSerialObj(studList,dir);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check login access for student
	 * @param studentID student's ID
	 * @param password student's password
	 * @return access:True/False
	 */
	@SuppressWarnings({"unused", "unchecked"})
	public boolean login(String studentID, String password) {
		boolean access = false;
		List<Student> studentList = new ArrayList<Student>();
		DataMgmt dm = new DataMgmt();
		String dir = "student.txt";

		try {
			studentList = dm.readSerialObj(dir);
			
			for(int i = 0; i < studentList.size(); i++) 
			{
				Student stud = (Student)studentList.get(i);
								
				if (stud != null) 
				{
					if (stud.getStudentID().equals(studentID)) 
					{
						if (stud.getPassword().equals(password)) 
							access = true;
					} 
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return access;
	}
	
	
}