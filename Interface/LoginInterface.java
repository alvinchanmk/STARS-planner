/**
 * Interface class for Admin/Students to implement
 * @author Ang Wei Jun
 * @author Alvin Chan
 * @author Choar Choong Leong
 * @author Tan Zhi Yong
 * @version 1.1
 * @since 2020-10-29
 */
package Interface;

public interface LoginInterface {
	/**
	 * Login method to compare username and password with database
	 * @param username User's username
	 * @param passwordHash User's passwordHash
	 * @return boolean success/failure
	 */
	public abstract boolean login(String username, String passwordHash);

}