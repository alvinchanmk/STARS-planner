/**
 * Control class to manage the reading and writing of data
 * @author Ang Wei Jun
 * @author Alvin Chan
 * @author Choar Choong Leong
 * @author Tan Zhi Yong
 * @version 1.1
 * @since 2020-10-29
 */
package Control;

import java.util.*;

import Interface.FileIOInterface;

import java.io.*;

public class DataMgmt implements Serializable, FileIOInterface
{
	private static final long serialVersionUID = 1L;

	/**
	 * Read objects from txt files
	 * @param dir String of directory
	 * @return list of object
	 */
	@SuppressWarnings("rawtypes")
	public List readSerialObj(String dir) {
		List info = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"\\"+dir);
			ois = new ObjectInputStream(fis);
			info = (ArrayList) ois.readObject();
			ois.close();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return info;
	}

	/**
	 * Write objects to txt files
	 * @param list List of object
	 * @param dir String of directory
	 */
	@SuppressWarnings("rawtypes")
	public void writeSerialObj(List list, String dir) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(System.getProperty("user.dir") + "\\"+dir);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
			oos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}