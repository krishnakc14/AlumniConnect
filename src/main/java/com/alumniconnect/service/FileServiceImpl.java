package com.alumniconnect.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {
	
	@Autowired ResourceLoader loader;
	
	public String[] getMailIds(String fileName) {
		
		List<String> mailIds = new ArrayList<>();
		String[] ret = null;
		String mailId = "";
		BufferedReader reader;
		
		try {
		
		Resource resource = loader.getResource("classpath:files/"+fileName+".txt");
		
		reader = new BufferedReader
				(new InputStreamReader(resource.getInputStream()));
		
		while((mailId = reader.readLine()) != null) {
			mailIds.add(mailId);
		}
		
		ret = new String[mailIds.size()];
		int i = 0;
		
		for(String mail: mailIds) {
			ret[i++] = mail;
		}
		
		reader.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		
		return ret;
		
	}

}
