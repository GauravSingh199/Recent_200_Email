package com.email.email.Service;

import java.io.IOException;
import java.util.List;

import com.email.email.Entity.Email;

public interface Email_Fetching_Service {
	
	
	List<Email> listAndSaveLast200Emails() throws IOException;
}
