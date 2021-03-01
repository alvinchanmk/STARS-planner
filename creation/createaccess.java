package creation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public class createaccess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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

}
