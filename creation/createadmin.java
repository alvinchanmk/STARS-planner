package creation;

import java.util.*;

import Control.DataMgmt;
import Control.HashPwController;
import Entity.Admin;
import Entity.Lesson;
import Entity.Student;

import java.io.*;

public class createadmin
{
	public static void main(String[] args) 
	{
		

		Scanner sc = new Scanner(System.in);
		DataMgmt dm = new DataMgmt();
		String dir = "admin.txt";

		System.out.println("Enter new student's ID: ");
		String adminID = sc.nextLine();
		System.out.println("Enter new student's password: ");
		String password = sc.nextLine();
		password = HashPwController.hash(password);
		
		Admin adm = new Admin(adminID, password);
		List<Admin> list = new ArrayList<Admin>();
		Admin admin = new Admin();
		try {
			list = admin.getAllAdmObj();
			list.add(adm);
			dm.writeSerialObj(list, dir);

		} catch (Exception e) 
		{
			System.out.println("Exception: " + e.getMessage());
		}
		System.out.println("New admin added");
	}
}