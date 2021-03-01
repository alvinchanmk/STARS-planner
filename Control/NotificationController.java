/**
 * Control class to send out email notification
 * @author Ang Wei Jun
 * @author Alvin Chan
 * @author Choar Choong Leong
 * @author Tan Zhi Yong
 * @version 1.1
 * @since 2020-10-29
 */
package Control;

import java.util.*;
import javax.mail.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*This email notification uses javaxmail API*/
public class NotificationController {
	/**
	 * Sender's email
	 */
	private final static String EMAIL_SENDER = "ce2004test@gmail.com";
	/**
	 * Sender's password
	 */
	private final static String PW_SENDER = "123Test321";
	/**
	 * SMTP server
	 */
	private final static String EMAIL_SERVER = "smtp.gmail.com";
	/**
	 * SMTP server port number
	 */
	private final static String SERVER_PORT = "465";
	
	/**
	 * Send an email to the recipient
	 * @param receiver The recipient's email
	 * @param indexID Course index number
	 */
	  public void sendEmail(String receiver, Integer indexID){
	   
	  Properties props = new Properties();
	  props.put("mail.smtp.host", EMAIL_SERVER);
	  props.put("mail.smtp.port", SERVER_PORT);
	  props.put("mail.smtp.starttls.enable", "true");
	  props.put("mail.smtp.auth", "true");
	  props.put("mail.smtp.socketFactory.port", SERVER_PORT);
	  props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	  props.put("mail.smtp.socketFactory.fallback", "false");
	  
	  Session sess = Session.getInstance(props,
			  new javax.mail.Authenticator() {
		  		protected PasswordAuthentication getPasswordAuthentication() {
		  			return new PasswordAuthentication(EMAIL_SENDER, PW_SENDER);
		  		}
	  	});
	  
	  try{  
	  Message msg = new MimeMessage(sess);
	  msg.setFrom(new InternetAddress(EMAIL_SENDER));
	  msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(receiver));
	  msg.setSubject("Notification regarding waitlist");
	  
	  Transport.send(msg);
	  System.out.println("Email has been send successfully!"); 
	  }
	  
	  catch (MessagingException e){
		  throw new RuntimeException(e);
	  }
	  
	  
	  }
}
