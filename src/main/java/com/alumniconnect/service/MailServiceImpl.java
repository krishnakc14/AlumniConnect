package com.alumniconnect.service;

import static com.alumniconnect.constants.NotificationConstants.FROM_EMAIL_DISPLAY;

import java.nio.charset.StandardCharsets;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.alumniconnect.model.Mail;

@Service
public class MailServiceImpl implements MailService {

	@Autowired private JavaMailSender emailSender;
	@Autowired private FileService fileService;

	@Value("${spring.mail.from}")
	private String fromEmail;

	public void sendEmail(Mail mail) {

		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper;
		
		try {
			
			helper = new MimeMessageHelper(message, 
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			
			// Set the content for HTML
			Context context = new Context();
			context.setVariables(mail.getModel());
			
			String[] mailIds = fileService.getMailIds(mail.getImportFileName());
			System.out.println("Mail Impl: ----->");
			for(String addr: mailIds) {
				System.out.println(addr);
			}

			// Set the Mail details from the input
			helper.setTo(mailIds);
			helper.setText(mail.getMessageBody());
			helper.setSubject(mail.getSubject());
			helper.setFrom(new InternetAddress(fromEmail, FROM_EMAIL_DISPLAY));

			emailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
