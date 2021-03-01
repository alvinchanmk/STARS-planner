/**
 * Entity class for admin
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
import Interface.LoginInterface;


public class Admin implements Serializable, LoginInterface
{
	private static final long serialVersionUID = 1L;
	/**
	 * Admin's login ID
	 */
	private String adminID;
	/**
	 * Admin's login password
	 */
	private String password;

	/**
	 * Create a new admin without attributes
	 */
	public Admin() {

	}
	
	/**
	 * Create admin with attributes
	 * @param adminID Admin's ID
	 * @param password Admin's password
	 */
	public Admin(String adminID, String password) 
	{
		this.adminID = adminID;
		this.password = password;
	}

	/**
	 * Get admin ID of this admin
	 * @return Admin's ID
	 */
	public String getAdminID() 
	{
		return adminID;
	}
	
	/**
	 * Change the adminID of this admin
	 * @param adminID Admin's new ID
	 */
	public void setAdminID(String adminID) 
	{
		this.adminID = adminID;
	}
	
	/**
	 * Get the password of this admin
	 * @return admin's password
	 */
	public String getPassword() 
	{
		return password;
	}
	
	/**
	 * Change this admin password
	 * @param password admin's new password
	 */
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	/**
	 * Get all the Admin objects
	 * @return List of Admin objects
	 */
	@SuppressWarnings("unchecked")
	public List<Admin> getAllAdmObj() {	
		DataMgmt dm = new DataMgmt();
		List<Admin> list = new ArrayList<Admin>();
		String dir = "admin.txt";

		try {
			list = dm.readSerialObj(dir);

			if (list == null) {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Check login access for admin
	 * @param adminID admin's ID
	 * @param password admin's password
	 * @return access:True/False
	 */
	@SuppressWarnings({"unused", "unchecked"})
	public boolean login(String adminID, String password) {
		boolean access = false;
		List<Admin> adminList = new ArrayList<Admin>();
		DataMgmt dm = new DataMgmt();
		String dir = "admin.txt";

		try {
			adminList = dm.readSerialObj(dir);
			
			for(int i = 0; i < adminList.size(); i++) 
			{
				Admin admin = (Admin)adminList.get(i);
								
				if (admin != null) 
				{
					if (admin.getAdminID().equals(adminID)) 
					{
						if (admin.getPassword().equals(password)) 
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