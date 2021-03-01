/**
 * Boundary class for users to access the program
 * @author Ang Wei Jun
 * @author Alvin Chan
 * @author Choar Choong Leong
 * @author Tan Zhi Yong
 * @version 1.1
 * @since 2020-10-29
 */
package Boundary;

import java.io.Console;
import java.util.*;

import Control.AdminController;
import Control.HashPwController;
import Control.StudentController;
import Entity.Admin;
import Entity.Student;

import java.io.*;
import java.time.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainUI
{
	/**
	 * Main menu for user to use.
	 * @param args The argument to invoke
	 */
	public static void main(String[] args) 
	{
		boolean login = false;
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("(1) Login as admin\n"
					+"(2) Login as student");
					char loginChoice = sc.next().charAt(0);	
					while(loginChoice != '1' && loginChoice != '2'){
						System.out.println("Please enter 1 or 2 only.");
						loginChoice = sc.next().charAt(0);	
					}
					
						if (loginChoice == '1'){
							AdminController a = new AdminController();
							login = adminLogin();
							while(login){
								System.out.println("(1) Edit access period\n"
									+"(2) Print index lessons\n"
									+"(3) Add new student\n"
									+"(4) Add a new course\n"
									+"(5) Add a new lesson\n"
									+"(6) Check vacancy for course\n"
									+"(7) Print student list by index\n"
									+"(8) Print student list by course\n"
									+"(9) Print all courses\n"
									+"(10) Print all students\n"
									+"(11) Print index info\n"
									+"(12) Logout");
								
								int admChoice = sc.nextInt();
									switch(admChoice)
									{
									case 1:
										a.editAccess();
										break;
								  	case 2:
										a.printIndexLessons();
										//AdminController.editAccess();
										break;
								  	case 3:
										a.addStudent();
										break;
								  	case 4:
										a.adminAddCourse();
										break;
								  	case 5:
										a.addIndex();
										break;
								  	case 6:
										a.checkVacancy();
										break;
								  	case 7:
										a.printStudByIndex();
										break;
								  	case 8:
										a.printStudByCourse();
										break;
								  	case 9:																
								  		a.printAllCourse();
										break;
									case 10:
										a.printAllStudents();
										break;
									case 11:
										a.printIndexInfo();
										break;
									case 12:
										System.out.println("Thank you");
										login = false;
								}
							}
							
						}
						else if (loginChoice == '2'){
							StudentController s = new StudentController();
							if(StudentController.checkAccess()){
								Scanner sc1 = new Scanner(System.in);
								Console cons = System.console();
								Student student = new Student();
								Student stud = new Student();
								boolean loop = true;
								String studentID=null;
								

								while (loop) {
									System.out.println("Welcome to MyStars");

									System.out.println("Please enter your user ID: ");
									studentID = sc1.nextLine();
									char[] password = cons.readPassword("Please enter your password: ");
									String pw = new String(password);
									
									String hashedpw = HashPwController.hash(pw);
									
									if(stud.login(studentID,hashedpw)){
										System.out.println("Logged in!");
										login = true;
										loop = false;
									}
									else{
										System.out.println("Invalid ID/password");	
									}
							
								}

								stud = student.getStudObj(studentID);
								
								while(login){
									System.out.println("(1) Add course\n"
										+"(2) Drop course\n"
										+"(3) Check course registered\n"
										+"(4) Check vacancy for index\n"
										+"(5) Change index\n"
										+"(6) Swap index\n"
										+"(7) Logout");
								
									int admChoice = sc.nextInt();
										switch(admChoice)
										{
									  	case 1:
											s.addCourse(stud);
											break;
									  	case 2:
											s.dropCourse(stud);
											break;
									  	case 3:
											s.printCourseReg(studentID);
											break;
									  	case 4:
											s.checkVacancy();
											break;
									  	case 5:
											s.changeIndex(stud);
											break;
									  	case 6:
											s.swapIndex(stud);
											break;
									  	case 7:
											System.out.println("Thank you");
											login = false;
									}
								}
							}
							else {
								System.out.println("Access period over. Please contact administrator.");					
							}	
						
					}
		}
		
		
		

	}
	
	
	/**
	 * To check for admin login credentials
	 * @return boolean result
	 */
	@SuppressWarnings({"unused", "unchecked"})
	public static boolean adminLogin() {
		boolean login = false;
		Console cons = System.console();
		Admin admin = new Admin();
		Scanner sc = new Scanner(System.in);
		boolean loop = true;

		while (loop) {
			System.out.println("Welcome to MyStars");
			System.out.println("Please enter your user ID: ");
			String id = sc.nextLine();

			char[] password = cons.readPassword("Please enter your password: ");
			String pw = new String(password);
			
			String hashedpw = HashPwController.hash(pw);
			
			if(admin.login(id,hashedpw)){
				System.out.println("Logged in!");
				login = true;
				loop = false;
			}
			else{
				System.out.println("Invalid ID/password");	
			}
			
		}
		return login;
	}

	
}