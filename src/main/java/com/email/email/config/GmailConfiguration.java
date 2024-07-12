package com.email.email.config;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.gmail.Gmail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.client.json.gson.GsonFactory;

@Configuration
public class GmailConfiguration {
	
	 private static final String APPLICATION_NAME = "Gmail API Java Quickstart";
	 private static final JsonFactory JSON_FACTORY = 
	           GsonFactory.getDefaultInstance();
	 
	    private static HttpTransport httpTransport;

	    @Bean
	    public Gmail gmailService_bean() throws IOException, GeneralSecurityException {
	        httpTransport = GoogleNetHttpTransport.newTrustedTransport();
	        return new Gmail.Builder(httpTransport, JSON_FACTORY, null)
	                .setApplicationName(APPLICATION_NAME)
	                .build();
	    }

}
