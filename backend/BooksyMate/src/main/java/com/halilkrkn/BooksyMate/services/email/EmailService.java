package com.halilkrkn.BooksyMate.services.email;

import com.halilkrkn.BooksyMate.core.enums.EmailTemplateName;
import jakarta.mail.MessagingException;

public interface EmailService {
    void sendEmail(String to,
                   String username,
                   String confirmationUrl,
                   String activationCode,
                   EmailTemplateName emailTemplate,
                   String subject) throws MessagingException;

}
