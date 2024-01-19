package br.com.emailsender.core;

public interface EmailSenderUseCase {
    void sendEmail(String to, String subject, String body);
}
