package com.alumniconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alumniconnect.model.Mail;
import com.alumniconnect.service.MailService;

@Controller
@RequestMapping("/")
public class AlumniConnectController {

	@Autowired
	MailService mailService;

	@PostMapping(
			path = {"/sendEmail"}, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public void sendEmail(@RequestBody Mail mail) {
		mailService.sendEmail(mail);
	}

}
