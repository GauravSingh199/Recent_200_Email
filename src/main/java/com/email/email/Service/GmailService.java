package com.email.email.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.email.email.Entity.Email;

import com.email.email.Repository.Email_Fetching_Repository;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePartHeader;

@Service
public class GmailService implements Email_Fetching_Service {
	
	private final Gmail gmail;
    private final Email_Fetching_Repository emailRepository;

    @Autowired
    public GmailService(Gmail gmail, Email_Fetching_Repository emailRepository) {
        this.gmail = gmail;
        this.emailRepository = emailRepository;
    }

	@Override
	public List<Email> listAndSaveLast200Emails() throws IOException {
		List<Email> emails = new ArrayList<>();
        Gmail.Users.Messages.List request = gmail.users().messages().list("me")
                .setMaxResults(200L);
        List<Message> messages = request.execute().getMessages();
        if (messages != null) {
            for (Message message : messages) {
                Message fullMessage = gmail.users().messages().get("me", message.getId()).execute();
                List<MessagePartHeader> headers = fullMessage.getPayload().getHeaders();
                String sender = "";
                String subject = "";
                for (MessagePartHeader header : headers) {
                    if (header.getName().equals("From")) {
                        sender = header.getValue();
                    } else if (header.getName().equals("Subject")) {
                        subject = header.getValue();
                    }
                }
                Email email = new Email(sender, subject);
                emailRepository.save(email);
                emails.add(email);
            }
        }
        return emails;
	}

}
