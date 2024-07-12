package com.email.email.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.email.Entity.Email;

import com.email.email.Service.Email_Fetching_Service;

@RestController
@RequestMapping("/emails")
public class Fetching_Controller {

	private final Email_Fetching_Service emailService;

    @Autowired
    public Fetching_Controller(Email_Fetching_Service emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/recent200")
    public List<Email> getLast200Emails() throws IOException {
        return emailService.listAndSaveLast200Emails();
    }
}
