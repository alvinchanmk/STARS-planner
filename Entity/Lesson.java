/**
 * Entity class for Lesson
 * @author Ang Wei Jun
 * @author Alvin Chan
 * @author Choar Choong Leong
 * @author Tan Zhi Yong
 * @version 1.1
 * @since 2020-10-29
 */
package Entity;

import java.util.*;
import java.io.*;
import java.time.*;
import java.text.*;
import java.time.temporal.ChronoUnit;

public class Lesson implements Serializable{	
	private static final long serialVersionUID = 1L;
	/**
	 * Course ID of this lesson
	 */
	private String course;
	/**
	 * Index number of this lesson
	 */
	private Integer index;
	/**
	 * Professor name of this lesson
	 */
	private String profName;
	/**
	 * Day of lesson of this lesson
	 */
	private String day;
	/**
	 * Lesson's venue of this lesson
	 */
	private String venue;
	/**
	 * Type of lesson of this lesson
	 */
	private String type;
	/**
	 * Lesson start time of this lesson
	 */
	private LocalTime lessonStart;
	/**
	 * Lesson end time of this lesson
	 */
	private LocalTime lessonEnd;
	/**
	 * Week of this lesson
	 */
	private char oddEven; 

	/**
	 * Create lesson without attributes
	 */
	public Lesson(){
		
	}
	
	/**
	 * Create lesson with the following attributes
	 * @param course Course ID
	 * @param index Index number
	 * @param profName Professor name
	 * @param day Day of lesson
	 * @param venue Lesson's venue
	 * @param type Type of lesson
	 * @param lessonStart Lesson start time
	 * @param lessonEnd Lesson end time
	 * @param oddEven Odd or Even
	 */
	public Lesson(String course, Integer index, String profName, String day, String venue, String type, LocalTime lessonStart, LocalTime lessonEnd, char oddEven){
		this.course = course;
		this.index = index;
		this.profName = profName;
		this.day = day;
		this.venue = venue;
		this.type = type;
		this.lessonStart = lessonStart;
		this.lessonEnd = lessonEnd;
		this.oddEven = oddEven;
	}

	/**
	 * Get course ID of this lesson
	 * @return this course
	 */
	public String getCourse(){
		return this.course;
	}

	/**
	 * Change the course ID of this lesson
	 * @param course new course
	 */
	public void setCourse(String course){
		this.course = course;
	}
	
	/**
	 * Get the index number of this lesson
	 * @return this index
	 */
	public Integer getIndex(){
		return this.index;
	}	

	/**
	 * Change the index number of this lesson
	 * @param index new index
	 */
	public void setIndex(Integer index){
		this.index = index;
	}

	/**
	 * Get the professor name of this lesson
	 * @return this professor
	 */
	public String getProfName(){
		return this.profName;
	}

	/**
	 * Change professor name of this lesson
	 * @param profName new profName
	 */
	public void setProfName(String profName){
		this.profName = profName;
	}

	/**
	 * Get the day of lesson of this lesson
	 * @return this day
	 */
	public String getDay(){
		return this.day;
	}

	/**
	 * Change the day of lesson of this lesson
	 * @param day new day
	 */
	public void setDay(String day){
		this.day = day;
	}

	/**
	 * Get the venue of lesson of this lesson
	 * @return this venue
	 */
	public String getVenue(){
		return this.venue;
	}

	/**
	 * Change the venue of lesson of this lesson
	 * @param venue new venue
	 */
	public void setVenue(String venue){
		this.venue = venue;
	}

	/**
	 * Get the type of lesson of this lesson
	 * @return this type
	 */
	public String getType(){
		return this.type;
	}
	
	/**
	 * Change the type of lesson of this lesson
	 * @param type new type
	 */
	public void setType(String type){
		this.type = type;
	}
	
	/**
	 * Get the lesson start time of this lesson
	 * @return this lessonStart
	 */
	public LocalTime getLessonStart(){
		return this.lessonStart;
	}

	/**
	 * Change the lesson start time of this lesson
	 * @param lessonStart new lessonStart
	 */
	public void setLessonStart(LocalTime lessonStart){
		this.lessonStart = lessonStart;
	}

	/**
	 * Get the lesson end time of this lesson
	 * @return this lessonEnd
	 */
	public LocalTime getLessonEnd(){
		return this.lessonEnd;
	}

	/**
	 * Change the lesson end time of this lesson
	 * @param lessonEnd new lessonEnd
	 */
	public void setLessonEnd(LocalTime lessonEnd){
		this.lessonEnd = lessonEnd;
	}
	
	/**
	 * Get the week of this lesson
	 * @return oddEven
	 */
	public char getOddEven() {
		return oddEven;
	}
	
	/**
	 * Change the week of this lesson
	 * @param oddEven new oddEven
	 */
	public void setOddEven(char oddEven) {
		this.oddEven = oddEven;
	}
	
	/**
	 * Print the lesson information
	 */
	public void printLessonInfo() {
		System.out.println("Lesson of course: " + this.getCourse());
		System.out.println("Lesson of index: " + this.getIndex());
		System.out.println("Prof: " + this.getProfName());
		System.out.println("Day: " + this.getDay());
		System.out.println("Venue: " + this.getVenue());
		System.out.println("Lesson Type: " + this.getType());
		System.out.println("Start time: " + this.getLessonStart());
		System.out.println("End time: " + this.getLessonEnd());
		if(this.getOddEven() == '1')
			System.out.println("Week: Odd weeks only");
		else if(this.getOddEven() == '2')
			System.out.println("Week: Even weeks only");
		else if(this.getOddEven() == '0')
			System.out.println("Week: Every week");		

	}

	/**
	 * Change integer input to String
	 * @param day The day of lesson
	 * @return dayString
	 */
	public static String intToString(Integer day){
		String dayString = null;
		if(day == 1){
			dayString = "MONDAY";
		}
		else if(day == 2){
			dayString = "TUESDAY";
		}
		else if(day == 3){
			dayString = "WEDNESDAY";
		}
		else if(day == 4){
			dayString = "THURSDAY";
		}
		else if(day == 5){
			dayString = "FRIDAY";
		}
		else if(day == 6){
			dayString = "SATURDAY";
		}
		else if(day == 7){
			dayString = "SUNDAY";
		}
		return dayString;
	}
	
	/**
	 * Change string input to integer
	 * @param day The day of lesson
	 * @return d
	 */
	public static int dayStringToInt(String day){
		int d = 0;
		
		if(day.equalsIgnoreCase("MONDAY"))
			d = 1;
		else if(day.equalsIgnoreCase("TUESDAY"))
			d = 2;
		else if(day.equalsIgnoreCase("WEDNESDAY"))
			d = 3;
		else if(day.equalsIgnoreCase("THURSDAY"))
			d = 4;
		else if(day.equalsIgnoreCase("FRIDAY"))
			d = 5;
		
		return d;
		
	}

	/**
	 * Check if there is clash
	 * @param l Lesson object
	 * @return boolean
	 */
	public boolean checkConflict(Lesson l){
		if(!checkDayClash(l)){
			return false;
			}
		if(!checkWeekClash(l)) {
			return false;
		}
		return !checkTimeClash(l);

	}

	/**
	 * check if the day of lessons for clashes
	 * @param l Lesson object
	 * @return boolean
	 */
	public boolean checkDayClash(Lesson l){
	return day.equals(l.getDay());
	}

	/**
	 * Check if the time clashes
	 * @param l Lesson object
	 * @return boolean
	 */
	public boolean checkTimeClash(Lesson l) {
		return ( lessonStart.isAfter( l.getLessonEnd().minus(1, ChronoUnit.NANOS) ) || lessonEnd.isBefore( l.getLessonStart().plus(1, ChronoUnit.NANOS) ) );
	}

	/**
	 * Check if the odd/even week clashes
	 * @param l Lesson object
	 * @return boolean
	 */
	public boolean checkWeekClash(Lesson l) {
		if(oddEven==l.getOddEven()) { // check if the same
			return true;
		}
		if(oddEven == '0' || l.getOddEven() == '0' ) { // check if either is both
			return true;
		}
		return false; // if different and both not both, no clash
	}
	
	
}