/**
 * Interface class for IO operation of file
 * @author Ang Wei Jun
 * @author Alvin Chan
 * @author Choar Choong Leong
 * @author Tan Zhi Yong
 * @version 1.1
 * @since 2020-10-29
 */
package Interface;

import java.util.List;

public interface FileIOInterface {

	/**
	 * Read data from txt file
	 * @param dir Directory of file
	 * @return List of objects from file
	 */
	@SuppressWarnings("rawtypes")
	public abstract List readSerialObj(String dir);

	/**
	 * Write data to txt file
	 * @param list List of objects to write to file
	 * @param dir Directory of file
	 */
	@SuppressWarnings("rawtypes")
	public abstract void writeSerialObj(List list, String dir);
}