package creation;

import java.util.*;

import Entity.Index;
import Entity.Lesson;
import Entity.Student;

import java.io.*;

public class createindex implements Serializable
{
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) 
	{
		 
		ArrayList<Lesson> schedule = new ArrayList<Lesson>();
		ArrayList<Student> waitList = new ArrayList<Student>();
		ArrayList<Student> studentList = new ArrayList<Student>();

		Index newStud = new Index(00001, 10, schedule, waitList, studentList);

		try {
			FileOutputStream f = new FileOutputStream(new File("indexes.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(newStud);

			o.close();
			f.close();

			FileInputStream fi = new FileInputStream(new File("indexes.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			
			Index adm1 = (Index) oi.readObject();

			System.out.println(adm1.getIndexID()+" "+adm1.getVacancy() +" "+ adm1.getSchedule()+ 				adm1.getWaitList()+adm1.getStudentList());

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