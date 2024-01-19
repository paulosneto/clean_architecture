package br.com.emailsender.infrastructure.ses;

import br.com.emailsender.adapters.EmailSenderGateway;
import br.com.emailsender.core.exceptions.EmailSenderException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SesEmailSender implements EmailSenderGateway {

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    @Autowired // Injetado automaticamente pelo Spring
    public SesEmailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        SendEmailRequest request = new SendEmailRequest()
                .withSource("contatosuporte@gmail.com")
                .withDestination(new Destination().withToAddresses(to))
                .withMessage(
                        new Message().withSubject(new Content(subject))
                                .withBody(
                                        new Body().withText(new Content(body))
                                )
                );

        try {
            this.amazonSimpleEmailService.sendEmail(request);
        } catch (AmazonServiceException e) {
            throw new EmailSenderException("Failure while sending email", e);
        }
    }
}
