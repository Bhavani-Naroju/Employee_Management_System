
package miniproject.springboot.emailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EmailService {
	@Autowired
	private JavaMailSender mailSender;
	private static final String SENDER_NAME = "RGUKT Company";
	private static final String SENDER_EMAIL = "bhavaninaroju4@gmail.com";
	public void sendWelcomeEmail(String toEmail, String employeeName) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(SENDER_NAME + " <" + SENDER_EMAIL + ">");
		message.setTo(toEmail);
		message.setSubject("Welcome to the RGUKT Company!");
		message.setText("Dear " + employeeName + ",\n\n" +
		"Welcome to the RGUKT company! We are excited to have you on board.\n\n" +
		"Best Regards,\n" +
		"The RGUKT Company Team");
		mailSender.send(message);
	}
	public void sendFarewellEmail(String toEmail, String employeeName) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(SENDER_NAME + " <" + SENDER_EMAIL + ">");
		message.setTo(toEmail);
		message.setSubject("Thank You for Your Services");
		message.setText("Dear " + employeeName + ",\n\n" +
		"Thank you for your services to the RGUKT company. We wish you all the best in your future endeavors.\n\n" +
		"Best Regards,\n" +
		"The RGUKT Company Team");
		mailSender.send(message);
	}
}