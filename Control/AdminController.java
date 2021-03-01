/**
 * Control class to manage the Admin tasks
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

import java.io.*;
import java.time.*;
import java.text.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AdminController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Print the index info consisting of vacancy,students enrolled in the index and wait list
	 * @return result of printing index info
	 */
	public static boolean printIndexInfo(){
		Scanner sc = new Scanner(System.in);
		Index ind = new Index();
		System.out.println("Enter index of info you want to print");
		int indexID = 0;

		try {
			indexID = sc.nextInt();

		} catch (InputMismatchException e) {
			System.out.println("Invalid input.");
			return false;
		}
		
		if(ind.getIndexObj(indexID) == null){
			System.out.println("No such index");
			return false;
		}
		ind = ind.getIndexObj(indexID);
		ArrayList<Student> studList = ind.getStudentList();
		ArrayList<Student> waitList = ind.getWaitList();
		System.out.println("index ID: "+ind.getIndexID());
		System.out.println("index vacancy: "+ind.getVacancy());
		System.out.println("Name:");
		for(int i=0; i<studList.size(); i++){
			System.out.print(studList.get(i).getStudentID() +",");
		}
		System.out.println("");
		System.out.println("Wait list size:" +waitList.size());
		for(int i=0; i<waitList.size(); i++){
			System.out.print(waitList.get(i).getStudentID() +",");
		}
		System.out.println("");
		
		return true;
	}

	/**
	 * Print the lessons of an index
	 * @return result of printing index
	 */
	public static boolean printIndexLessons(){
		Scanner sc = new Scanner(System.in);
		DataMgmt dm = new DataMgmt();
		String dir = "indexes.txt";
		Index ind = new Index();
		Index index = new Index();
		ArrayList<Lesson> schedule = new ArrayList<Lesson>();

		System.out.println("Enter index of lessons you want to print");
		int indexID = 0;
		try {
			indexID = sc.nextInt();

		} catch (InputMismatchException e) {
			System.out.println("Invalid input.");
			return false;
		}
		
		try{
			if(index.getIndexObj(indexID) == null){
				System.out.println("Index does not exist.\n");
				return false;
			}
			ind = index.getIndexObj(indexID);
			schedule = ind.getSchedule();
	
			for(int i=0; i<schedule.size(); i++){
				Lesson lesson = (Lesson) schedule.get(i);
				lesson.printLessonInfo();
				System.out.println("-----------------------------");
			}
		}catch (Exception e) {
            		e.printStackTrace();
        	}
		
		return true;
	}

	/**
	 * Print the list of all courses
	 */
	public static void printAllCourse() {
		Course course = new Course();
		System.out.println("Course List: ");
		List<Course> courseList = course.getAllCourseObj();
		for (int i = 0; i < courseList.size(); i++) {
			courseList.get(i).printCourseInfo();
		}
	}

	/**
	 * To add lesson to an course index
	 * @return result of adding index
	 */
	public static boolean addIndex(){
		Scanner sc = new Scanner(System.in);
		DataMgmt dm = new DataMgmt();
		String dir = "indexes.txt";
		Index ind = new Index();
		Index index = new Index();
		ArrayList<Lesson> schedule = new ArrayList<Lesson>();
		boolean flag = false;
		int count = 0;
		Course course = new Course();
		Lesson lesson = new Lesson();

		System.out.println("Enter the course of index you want to add");
		String courseID = sc.nextLine().toUpperCase();

		Course courseToAdd = course.getCourseObj(courseID);
		if(courseToAdd == null){
			System.out.println("Course does not exist.");
			return false;
		}

		System.out.println("Enter Index you want to add");
		int indexID = 0;
		do {
			try {
				indexID = sc.nextInt();
				sc.nextLine();
				if (indexID > 0) {
					flag = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input.");
				sc.nextLine();
			}
		} while (!flag);
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

		if(index.getIndexObj(indexID) != null){
			System.out.println("Index already exist");
			return false;
		}

		System.out.println("Enter number of lessons to add: ");
		do {
			try {
				count = sc.nextInt();
				sc.nextLine();
				if (count > 0) {
					flag = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input.");
				System.out.println("Please try again.");
				sc.nextLine();
			}
		} while (!flag);
		flag = false;
		
		for(int i=0; i<count; i++){

			System.out.println("\nEnter details for lesson " + (i + 1));

			System.out.println("Enter prof's name: ");
			String profName = sc.nextLine();
			System.out.println("Enter day of lesson\n1) Monday\n2) Tuesday\n3) Wednesday\n4) Thursday\n5) Friday: ");
			int dayInt = 0;
			do {
				try {
					dayInt = sc.nextInt();
					sc.nextLine();
					if (dayInt > 0 && dayInt < 6) {
						flag = true;
					}
					else{
						System.out.println("Invalid input.");
						System.out.println("Please try again.");
					}
				} catch (InputMismatchException e) {
					System.out.println("Invalid input.");
					System.out.println("Please try again.");

				}
			} while (!flag);
			flag = false;

			System.out.println("Enter venue of lesson: ");
			String venue = sc.nextLine();
			System.out.println("Enter lesson type: ");
			String lessonType = sc.nextLine();
			System.out.println("Enter start hour of lesson (HH:MM) in 24-hour format: ");
			String lessonStartStr = sc.nextLine();
			System.out.println("Enter end hours of lesson (HH:MM) in 24-hour format: ");
			String lessonEndStr = sc.nextLine();
			LocalTime lessonStart = null;
			LocalTime lessonEnd = null;
			try{
				
				lessonStart = LocalTime.parse(lessonStartStr,DateTimeFormatter.ISO_TIME);
				lessonEnd = LocalTime.parse(lessonEndStr,DateTimeFormatter.ISO_TIME);
			}
			catch (DateTimeParseException e) {
            			System.out.println("Invalid time input.");
            			System.out.println("Returning to main menu.");
				return false;
        		}
			System.out.println("Select lesson week: ");
			System.out.println("0) Every week");
			System.out.println("1) Odd weeks only");
			System.out.println("2) Even weeks only");
			char oddEven = sc.nextLine().charAt(0);
			while(oddEven != '0' && oddEven != '1' && oddEven != '2'){
				System.out.println("Enter 0, 1, or 2 only.");
				oddEven = sc.nextLine().charAt(0);
			}
			String lessonDay = lesson.intToString(dayInt);
			Lesson lessonToAdd = new Lesson(courseID, indexID, profName, lessonDay, venue, lessonType, 			lessonStart, lessonEnd, oddEven);

			schedule.add(lessonToAdd);
		}

		for(int i=0; i<schedule.size(); i++){
			Lesson l1 = (Lesson) schedule.get(i);
			for(int j=0; j<schedule.size(); j++){
				Lesson l2 = (Lesson) schedule.get(j);
				if(i != j){
					if(l1.checkConflict(l2) == true){
						System.out.println("Lesson clash.");
						return false;
					}
				}
			}			
		}

		ArrayList<Student> waitList = new ArrayList<Student>();
		ArrayList<Student> studentList = new ArrayList<Student>();

		ind = new Index(indexID, 10, schedule, waitList, studentList);

		System.out.println("Enter 1 to confirm, 2 to cancel");
		char cfm = sc.next().charAt(0);

		if(cfm == '1'){
			if(index.getIndexObj(indexID) == null){
				List<Index> indexList = new ArrayList<Index>();
				try {
					indexList = index.getAllIndexObj();
					indexList.add(ind);
					dm.writeSerialObj(indexList, dir);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else{
				System.out.println("Index already exist.");
			}
		}
		else if (cfm == '2'){
			System.out.println("Cancelled.");
		}
		else{
			while (cfm != '1' || cfm != '2') {
				System.out.println("Enter 1 or 2 only");
				cfm = sc.next().charAt(0);
				if(cfm == '1'){
					if(index.getIndexObj(indexID) == null){
						List<Index> indexList = new ArrayList<Index>();
						try {
							indexList = index.getAllIndexObj();
							indexList.add(ind);
							dm.writeSerialObj(indexList, dir);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					else{
						System.out.println("Index already exist.");
					}
				}
				else if (cfm == '2'){
					System.out.println("Cancelled.");
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Print the list of students by course
	 * @return result of printing
	 */
	public static boolean printStudByCourse(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter course you want to print: ");
		String courseString = sc.nextLine();
		int studCount = 0;
		Course c = new Course();
		Course course = new Course();

		try{
			if(c.getCourseObj(courseString) == null){
				System.out.println("Course does not exist.\n");
				return false;
			}
			course = c.getCourseObj(courseString);
			List<Student> studList = course.getStudentList();
	
			System.out.println("List of students enrolled in course "+courseString);
			System.out.println("----------------------------------------------------------");
        	    	System.out.println("|         Name         |      Gender    |   Nationality  |");
            	    	System.out.println("----------------------------------------------------------");

			for (int i = 0; i < studList.size(); i++) {
                    		Student student1 = (Student) studList.get(i);
                    		System.out.format("| %-21s| %-15s| %-15s|\n",student1.getName(),student1.getGender(), student1.getNationality());
		    
                    		studCount++;
                    	}
            	    	System.out.println("----------------------------------------------------------");
            
            	    	if (studCount == 0) {
                    		System.out.println("There are no students!");
	            	} else {
        	        	System.out.println("\nNumber of students: " + studCount);
            		}
		
		}catch (Exception e) {
            		e.printStackTrace();
        	}

		return true;
		
	}

	/**
	 * Print list of students by index number
	 * @return result of printing
	 */
	public static boolean printStudByIndex(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter index you want to print: ");
		int index = 0;
		try {
			index = sc.nextInt();

		} catch (InputMismatchException e) {
			System.out.println("Invalid input.");
			return false;
		}
		int studCount = 0;
		Index ind = new Index();
		Index indGroup = new Index();

		try{
			if(indGroup.getIndexObj(index) == null){
				System.out.println("Index does not exist.\n");
				return false;
			}
			ind = indGroup.getIndexObj(index);
			List<Student> studList = ind.getStudentList();
	
			System.out.println("List of students enrolled in index "+index);
			System.out.println("----------------------------------------------------------");
        	    	System.out.println("|         Name         |      Gender    |   Nationality  |");
            	    	System.out.println("----------------------------------------------------------");

			for (int i = 0; i < studList.size(); i++) {
                    		Student student1 = (Student) studList.get(i);
                    		System.out.format("| %-21s| %-15s| %-15s|\n",student1.getName(),student1.getGender(), student1.getNationality());
		    
                    		studCount++;
                    	}
            	    	System.out.println("----------------------------------------------------------");
            
            	    	if (studCount == 0) {
                    		System.out.println("There are no students!");
	            	} else {
        	        	System.out.println("\nNumber of students: " + studCount);
            		}

		}catch (Exception e) {
            		e.printStackTrace();
        	}
		
		return true;

	}

	/**
	 * Print out the vacancy of an index
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
	 * Allows admin to add new course and its indexes
	 */
	public static void adminAddCourse()
	{
		boolean flag = false;
		Course course = new Course();
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> indexGroupList = new ArrayList<Integer>();
		ArrayList<Student> studentList = new ArrayList<Student>();
		String dir = "courses.txt";
		DataMgmt dm = new DataMgmt();
		List<Course> allCourseObj = course.getAllCourseObj();

		System.out.println("Enter course code: ");
		String courseID = sc.nextLine();
		System.out.println("Enter school of course: ");
		String school = sc.nextLine();
		System.out.println("Enter list of index(20011,20012,20013...): ");
		Scanner sc1 = null;
		String list;
		do {
			list = sc.nextLine();
			sc1 = new Scanner(list).useDelimiter(",");
			while (sc1.hasNext()) {
				try {
					int index = sc1.nextInt();
					for(int i=0; i<allCourseObj.size(); i++){
						Course tempCourse = (Course) allCourseObj.get(i);
						ArrayList<Integer> tempIndexList = tempCourse.getIndexGroupList();
						for(int j=0; j<tempIndexList.size(); j++){
							if(tempIndexList.get(j).equals(index)){
								System.out.println("Index already exist");
								return;	
							}
						}
					}
					if (index >= 0 && !indexGroupList.contains(index)) {
						flag = true;
						indexGroupList.add(index);
					}
					

				} catch (InputMismatchException e) {
					flag = false;
					indexGroupList.clear();
					System.out.println("Invalid input. Integers only.");
					break;
				}
			}
		} while (!flag);
		sc1.close();
		
		Course newCourse = new Course(courseID.toUpperCase(), school, indexGroupList, studentList);
		
		System.out.println("1) Confirm to add course ");
		System.out.println("2) Cancel ");
		char input = sc.nextLine().charAt(0);

		if(input == '1')
		{
			if(course.getCourseObj(courseID) == null)
			{
				List<Course> courseList = new ArrayList<Course>();
				try
				{
					courseList = course.getAllCourseObj();
					courseList.add(newCourse);
					dm.writeSerialObj(courseList,dir);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				
				System.out.println("Course added. \n");
				printAllCourse();
			}
			else
			{
				System.out.println("Course already exists.");
			}
		}
		else if(input == '2')
		{
			System.out.println("Cancelled.");
		}
		else
		{
			while(input != '1' || input != '2')
			{
				System.out.println("Enter 1 or 2 only.");
				input = sc.nextLine().charAt(0);
				if(input == '1')
				{
					if(course.getCourseObj(courseID) == null)
					{
						List<Course> courseList = new ArrayList<Course>();
						try
						{
							courseList = course.getAllCourseObj();
							courseList.add(newCourse);
							dm.writeSerialObj(courseList,dir);
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
				
						System.out.println("Course added. \n");
						printAllCourse();
						break;
					}
					else
					{
						System.out.println("Course already exists.");
						break;
					}
				}
				else if(input == '2')
				{
					System.out.println("Cancelled.");
					break;
				}
			}
		}
	}

	/**
	 * Allows admin to edit the access period for students to add/drop
	 */
	public static void editAccess() 
	{	
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss", Locale.ENGLISH);
		Calendar start = Calendar.getInstance(Locale.ENGLISH);
		Calendar end = Calendar.getInstance(Locale.ENGLISH);

		System.out.println("Enter the start of the access period: (dd/MM/yy HH:mm:ss)");
		String startStr = sc.nextLine();
		System.out.println("Enter the end of the access period: (dd/MM/yy HH:mm:ss)");
		String endStr = sc.nextLine();

		try {
			start.setTime(sdf.parse(startStr));
			end.setTime(sdf.parse(endStr));
			
			if(end.before(start)){
				System.out.println("End date/time is before start.");
				return;
			}

			File f = new File("accessTime.txt");
	        	FileOutputStream fos = new FileOutputStream(f);
	        	ObjectOutputStream oos = new ObjectOutputStream(fos);
	        	oos.writeObject(start);
	        	oos.writeObject(end);
			
			System.out.println("Access time changed to:\nstart: "+start.getTime().toString()+"\nend: "+end.getTime().toString());
		
		} catch (Exception e) {
			System.out.println("You entered the wrong format!");
		}

		
	}

	/**
	 * Check if student exist
	 * @param studentID Student's ID
	 * @param matric Student's matric number
	 * @param email Student's email
	 * @return result of checking
	 */
	public static boolean checkStud(String studentID, String matric, String email)
	{
		boolean flag = false;
		Student student = new Student();
		List<Student> studList = new ArrayList<Student>();
		try {
			studList = student.getAllStudObj();

			for (int i = 0; i < studList.size(); i++) {
				Student stud = (Student)studList.get(i);
				if (stud.getStudentID().equals(studentID) || stud.getMatric().equals(matric) || stud.getEmail().equals(email)) {
					flag = true;
					break;
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return flag;
	}

	/**
	 * Print the list of all the students
	 * @return result of printing
	 */
	public static boolean printAllStudents()
	{
		boolean success = false;
        	List<Student> studList = new ArrayList<Student>();
        	int studCount = 0;
	        Student student = new Student();
 
	        try {
	            studList = student.getAllStudObj();
 
	            System.out.println("List of students: \n");
 
 	            System.out.println("------------------------------------------------------------------------------------------------------");
        	    System.out.println("|         Name         |     Student ID    |      School    |      Matric    |         Email         |");
            	    System.out.println("------------------------------------------------------------------------------------------------------");

            
            	    for (int i = 0; i < studList.size(); i++) {
                    	Student student1 = (Student) studList.get(i);
                    	System.out.format("| %-21s| %-18s| %-15s| %-15s| %-22s|\n",student1.getName(),student1.getStudentID(),
				student1.getSchool(), student1.getMatric(), student1.getEmail());
		    
                    	studCount++;
                    }
             
            	    System.out.println("------------------------------------------------------------------------------------------------------");
            
            	    if (studCount == 0) {
                    System.out.println("There are no students!");
            	} else {
                	System.out.println("\nNumber of students: " + studCount);
            	}
        	} catch (Exception e) {
            		e.printStackTrace();
        	}
        	return success;
	}

	/**
	 * Allows admin to add a new student
	 * @return result of adding student
	 */
	public static boolean addStudent()
	{
		Scanner sc = new Scanner(System.in);
		DataMgmt dm = new DataMgmt();
		String dir = "student.txt";

		System.out.println("Enter new student's ID: ");
		String studentID = sc.nextLine();
		System.out.println("Enter new student's password: ");
		String password = sc.nextLine();
		password = HashPwController.hash(password);
		System.out.println("Enter new student's name: ");
		String name = sc.nextLine();
		System.out.println("Enter new student's matriculation number: ");
		String matric = sc.nextLine();
		System.out.println("Enter new student's gender: ");
		char gender = sc.nextLine().charAt(0);
		while (gender !='M' && gender != 'F') 
		{
				System.out.println("Please enter M or F only.");
				gender = sc.nextLine().charAt(0);
		}
		System.out.println("Enter new student's nationality: ");
		String nationality = sc.nextLine();
		System.out.println("Enter new student's school: ");
		String school = sc.nextLine();
		System.out.println("Enter new student's email: ");
		String email = sc.nextLine();
		ArrayList<Lesson> schedule = new ArrayList<Lesson>();
		ArrayList<String> courseList = new ArrayList<String>();
		ArrayList<Integer> waitList = new ArrayList<Integer>();
		ArrayList<Integer> indexGroupList = new ArrayList<Integer>();

		Student newStud = new Student(studentID, password, name, matric, gender, nationality, 
				school, schedule, courseList, indexGroupList, waitList, email);

		System.out.println("Enter 1 to confirm, 2 to cancel");
		int cfm = 0;

		try {
			cfm = sc.nextInt();

		} catch (InputMismatchException e) {
			System.out.println("Invalid input.");
			return false;
		}

		if (cfm == 1) 
		{
			if (checkStud(studentID, matric, email) == false) 					// add new student
			{ 			
				List<Student> list = new ArrayList<Student>();
				Student student = new Student();
				try {
					list = student.getAllStudObj();
					list.add(newStud);
					dm.writeSerialObj(list, dir);

				} 
				catch (Exception e) 
				{
					System.out.println("Exception: " + e.getMessage());
				}
				System.out.println("New student added");
				printAllStudents();
			} 
			else 
			{
				System.out.println("There already exist a student with the same ID, matric number or email in database.");
			}
			
		} 
		else if (cfm == 2) 
		{
			System.out.println("Cancelled.");
		} 
		else 
		{
			while (cfm != 1 || cfm != 2) {
				System.out.println("Invalid input. Enter 1 or 2 only.");
				cfm = sc.nextInt();

				if (cfm == 1) 
				{
					if (checkStud(studentID, matric, email) == false) 			// add new student
					{ 			
						List<Student> list = new ArrayList<Student>();
						Student student = new Student();
						try {
							list = student.getAllStudObj();
							list.add(newStud);
							dm.writeSerialObj(list, dir);
	
						} 
						catch (Exception e) 
						{
							System.out.println(e.getMessage());
						}
						System.out.println("New student added");
						printAllStudents();
						break;
					} 
					else 
					{
						System.out.println("There already exist a student with the same ID, matric number or email in database.");
						break;
					}
					
				} 
				else if (cfm == 2) 
				{
					System.out.println("Cancelled.");
					break;
				}
			}
		}
		return true;
	}
}