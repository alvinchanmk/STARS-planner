package creation;

import java.util.*;

import Entity.Course;
import Entity.Student;

import java.io.*;

public class createcourse
{
	public static void main(String[] args) 
	{
		
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> indexGroupList = new ArrayList<Integer>();
		ArrayList<Student> studentList = new ArrayList<Student>();
		boolean flag = false;
		System.out.println("Enter list of index(20011,20012,20013...): ");
		Scanner sc1 = null;
		String list;
		do {
			list = sc.nextLine();
			sc1 = new Scanner(list).useDelimiter(",");
			while (sc1.hasNext()) {
				try {
					int index = sc1.nextInt();
					if (index >= 0) {
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
		
		Course newCourse = new Course("CZ2001", "SCSE", indexGroupList, studentList);

		try {
			FileOutputStream f = new FileOutputStream(new File("courses.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(newCourse);

			o.close();
			f.close();

			FileInputStream fi = new FileInputStream(new File("courses.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			
			Course adm1 = (Course) oi.readObject();

			System.out.println(adm1.getCourseID()+ adm1.getSchool()+ adm1.getIndexGroupList());

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