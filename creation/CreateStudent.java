package creation;

import java.util.*;

import Entity.Lesson;
import Entity.Student;

import java.io.*;

public class CreateStudent
{
	public static void main(String[] args) 
	{
		ArrayList<Lesson> schedule = new ArrayList<Lesson>();
		ArrayList<String> courseList = new ArrayList<String>();
		ArrayList<Integer> indexGroupList = new ArrayList<Integer>();
		ArrayList<Integer> waitList = new ArrayList<Integer>();

		Student newStud = new Student("angw0091", "123", "ang wei jun", "u1922925a", 'M', 
				"Singaporean", "scse", schedule, courseList, indexGroupList, waitList, "test@gmail.com");

		try {
			FileOutputStream f = new FileOutputStream(new File("student.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(newStud);

			o.close();
			f.close();

			FileInputStream fi = new FileInputStream(new File("student.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			
			Student adm1 = (Student) oi.readObject();

			System.out.println(adm1.getName()+adm1.getStudentID()+adm1.getPassword()+
			adm1.getMatric()+ adm1.getGender()+ adm1.getNationality()+ adm1.getSchool()+ adm1.getSchedule()+adm1.getCourseList()+ adm1.getIndexGroupList()+ adm1.getWaitList()+adm1.getEmail());

			oi.close();
			fi.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}