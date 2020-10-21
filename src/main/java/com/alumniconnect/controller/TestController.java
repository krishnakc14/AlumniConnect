package com.alumniconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alumniconnect.service.FileService;

@Controller
@RequestMapping("\test")
public class TestController {
	
	@Autowired FileService fileService;
	
	@GetMapping("\testFileFetch")
	public void testFileFetch() {
		fileService.getMailIds("CSEBatch_2019");
	}

}
