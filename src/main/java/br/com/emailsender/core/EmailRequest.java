package br.com.emailsender.core;

public record EmailRequest(String to, String subject, String body) {
}
