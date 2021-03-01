/**
 * Entity class for Course
 * @author Ang Wei Jun
 * @author Alvin Chan
 * @author Choar Choong Leong
 * @author Tan Zhi Yong
 * @version 1.1
 * @since 2020-10-29
 */
package Entity;

import java.io.*;
import java.util.*;

import Control.DataMgmt;

public class Course implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	/**
	 * Course ID of this course
	 */
	private String courseID;
	/**
	 * School of this course
	 */
	private String school;
	/**
	 * List of indexes of this course
	 */
	private ArrayList<Integer> indexGroupList;
	/**
	 * List of students of this course
	 */
	private ArrayList<Student> studentList;
	
	/**
	 * Create a course without attributes
	 */
	public Course() {
		
	}
	
	/**
	 * Create a course with the following attributes
	 * @param courseID Course ID
	 * @param school School
	 * @param indexGroupList List of indexes
	 * @param studentList List of students
	 */
	public Course(String courseID, String school, ArrayList<Integer> indexGroupList, ArrayList<Student> studentList) {
		this.courseID = courseID;
		this.school = school;
		this.indexGroupList = indexGroupList;
		this.studentList = studentList;
	}

	/**
	 * Get the course ID of this course
	 * @return this course's courseID
	 */
	public String getCourseID(){
		return this.courseID;
	}

	/**
	 * Change the course ID of this course
	 * @param courseID this course's new courseID
	 */
	public void setCourseID(String courseID){
		this.courseID= courseID;
	}	

	/**
	 * Get the School of this course
	 * @return this school's School
	 */
	public String getSchool(){
		return this.school;
	}

	/**
	 * Change the school of this course
	 * @param school this school's new School
	 */
	public void setSchool(String school){
		this.school= school;
	}

	/**
	 * Get List of index of this course
	 * @return indexGroupList of this course
	 */
	public ArrayList<Integer> getIndexGroupList(){
		return this.indexGroupList;
	}
	
	/**
	 * Change the list of index of this course
	 * @param indexGroupList indexGroupList
	 */
	public void setIndexGroupList(ArrayList<Integer> indexGroupList){
		this.indexGroupList= indexGroupList;
	}

	/**
	 * Get List of students of this course
	 * @return studentList of this course
	 */
	public ArrayList<Student> getStudentList(){
		return this.studentList;
	}
	
	/**
	 * Change the list of students of this course
	 * @param studentList studentList
	 */
	public void setStudentList(ArrayList<Student> studentList){
		this.studentList= studentList;
	}

	/**
	 * Get all the Course object
	 * @return List of courses
	 */
	@SuppressWarnings("unchecked")
	public List<Course> getAllCourseObj(){
		List<Course> list = new ArrayList<Course>();
		String dir = "courses.txt";
		DataMgmt dm = new DataMgmt();
		
		try{
			list = dm.readSerialObj(dir);
			
			if(list == null){
				throw new Exception();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Get a Course object
	 * @param courseID courseID
	 * @return getCourse 
	 */
	public Course getCourseObj(String courseID){
		Course getCourse = null;
		Course course = new Course();
		
		try{		
			List<Course> list = course.getAllCourseObj();
			
			int i = 0;
			while(i < list.size()){
				
				if(list.get(i).getCourseID().equals(courseID))
				{
					getCourse = (Course) list.get(i);
					break;
				}
				
				i++;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return getCourse;
	}

	/**
	 * Print the course information
	 */
	public void printCourseInfo() {
		System.out.println("Course ID: " + this.courseID);
		System.out.println("School: " + this.school);
		printIndexList();	
		System.out.println("\n========================================");
	}

	
	/**
	 * Print list of indexes of course
	 */
	public void printIndexList() {
		System.out.print("Indexes of course: ");
	
		int i = 0;
		while(i<indexGroupList.size())
		{
			System.out.print(indexGroupList.get(i));
			if (i != indexGroupList.size() - 1) {
				System.out.print(", ");
			}
			i++;
		}
	}
	
	/**
	 * Update the course object by writing back to txt file
	 * @param c1 Course object
	 */
	public void updateCourseObj(Course c1){
		String dir = "courses.txt";
		Course course = new Course();
		DataMgmt dm = new DataMgmt();
		
		try{
			List<Course> list = course.getAllCourseObj();
			
			int i = 0;
			while(i < list.size()){
				if(list.get(i).getCourseID().equals(c1.getCourseID()))
				{
					list.set(i, c1);
					break;
				}
				i++;
			}
			
			dm.writeSerialObj(list,dir);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get course object index
	 * @param indexGroupID indexGroupID
	 * @return getCourse course object
	 */
	public Course getCourseObjIndex(Integer indexGroupID){
		List<Course> list = new ArrayList<Course>();
		List<Integer> indexList = new ArrayList<Integer>();
		Course getCourse = null;
		Course course = new Course();
		
		try{
			list = course.getAllCourseObj();
			
			int i = 0;
			while(i < list.size()){
				indexList = list.get(i).getIndexGroupList();
				
				for(int j = 0; j < indexList.size(); j++){
					if(indexList.get(j).equals(indexGroupID)){
						getCourse = list.get(i);
						break;
					}
				}
				if(getCourse != null){
					break;
				}
				i++;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return getCourse;
	}
	
	
}