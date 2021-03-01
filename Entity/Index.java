/**
 * Entity class for Index
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

public class Index implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * Index ID of this index
	 */
	private Integer indexID;
	/**
	 * Number of vacancy of this index
	 */
	private Integer vacancy;
	/**
	 * List of lessons of this index
	 */
	private ArrayList<Lesson> schedule;
	/**
	 * List of students in wait list of this index
	 */
	private ArrayList<Student> waitList;
	/**
	 * List of students of this index
	 */
	private ArrayList<Student> studentList;

	/**
	 * Create an index without attributes
	 */
	public Index(){

	}

	/**
	 * Create an index with the following attributes
	 * @param indexID Index ID
	 * @param vacancy Number of vacancy
	 * @param schedule List of lessons
	 * @param waitlist List of students in wait list
	 * @param studentList List of students
	 */
	public Index(Integer indexID, Integer vacancy, ArrayList<Lesson> schedule, ArrayList<Student> waitlist, ArrayList<Student> studentList){
		this.indexID = indexID;
		this.vacancy = vacancy;
		this.schedule = schedule;
		this.waitList = waitlist;
		this.studentList = studentList;
	}

	/**
	 * Get Index ID of this index
	 * @return this Index's indexID
	 */
	public Integer getIndexID(){
		return this.indexID;
	}

	/**
	 * Change the index ID of this index
	 * @param indexID new indexID
	 */
	public void setIndexID(Integer indexID){
		this.indexID= indexID;
	}

	/**
	 * Get the number of vacancy of this index
	 * @return this Index's vacancy
	 */
	public Integer getVacancy(){
		return this.vacancy;
	}

	/**
	 * Change the number of vacancy of this index
	 * @param vacancy new vacancy
	 */
	public void setVacancy(Integer vacancy){
		this.vacancy = vacancy;
	}

	/**
	 * Get list of lessons of this index
	 * @return this Index's schedule
	 */
	public ArrayList<Lesson> getSchedule() {
		return schedule;
	}

	/**
	 * Change the list of lessons of this index
	 * @param schedule new schedule
	 */
	public void setSchedule(ArrayList<Lesson> schedule){
		this.schedule = schedule;
	}

	/**
	 * Get the list of students in wait list of this index
	 * @return this waitList
	 */
	public ArrayList<Student> getWaitList() {
		return waitList;
	}

	/**
	 * Change the list of students in wait list of this index
	 * @param waitList new waitList
	 */
	public void setWaitList(ArrayList<Student> waitList) {
		this.waitList = waitList;
	}

	/**
	 * Get list of students of this index
	 * @return this student list
	 */
	public ArrayList<Student> getStudentList() {
		return studentList;
	}

	/**
	 * Change the list of students of this index
	 * @param studentList new studentList
	 */
	public void setStudentList(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}

	/**
	 * Get all the Index object
	 * @return list of Index object
	 */
	@SuppressWarnings({"unused", "unchecked"})
	public List<Index> getAllIndexObj(){
		List<Index> indexList = new ArrayList<Index>();
		DataMgmt dm = new DataMgmt();
		String dir = "indexes.txt";
		try{
			indexList = dm.readSerialObj(dir);

			if(indexList == null){
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return indexList;
	}

	/**
	 * Get an Index object
	 * @param indexID Index ID
	 * @return ig Index object
	 */
	@SuppressWarnings({"unused", "unchecked"})
	public Index getIndexObj(Integer indexID){
		Index ig = null;
		Index indexGroup = new Index();

		try{
			List<Index> indexList = indexGroup.getAllIndexObj();

			int i = 0;
			while(i < indexList.size()){
				if(indexList.get(i).getIndexID().equals(indexID))
				{
					ig = indexList.get(i);
					break;
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ig;
	}

	/**
	 * Update the index and write back to txt file
	 * @param ig Index object
	 */
	public void updateIndex(Index ig){
		Index indexGroup = new Index();
		DataMgmt dm = new DataMgmt();
		String dir = "indexes.txt";
		
		try{
			List<Index> indexList = indexGroup.getAllIndexObj();
			
			int i = 0;
			while(i < indexList.size()){
				if(indexList.get(i).getIndexID().equals(ig.getIndexID()))
				{
					indexList.set(i, ig);
					break;
				}
				i++;
			}
			
			dm.writeSerialObj(indexList,dir);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check if an index exists
	 * @param indexID Index ID
	 * @return True/False depending if the index exists
	 */
	public boolean lookUpIndex(int indexID) { //check if theres such index
		boolean flag = false;
		Index ig = null;
		Index indexGroup = new Index();
		
		try {
			ig = indexGroup.getIndexObj(indexID);
			if(ig != null)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}