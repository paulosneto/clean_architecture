package br.com.emailsender.adapters;

public interface EmailSenderGateway {

    void sendEmail(String to, String subject, String body);
}
