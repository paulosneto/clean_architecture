package br.com.emailsender.core.exceptions;

public class EmailSenderException extends  RuntimeException{
    public  EmailSenderException(String message){
        super(message);
    }

    public EmailSenderException(String message, Throwable throwable){
        super(message, throwable);
    }


}
