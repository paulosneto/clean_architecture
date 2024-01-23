package br.com.emailsender.controllers;

import br.com.emailsender.application.EmailSenderUseCaseImpl;
import br.com.emailsender.core.EmailRequest;
import br.com.emailsender.core.exceptions.EmailSenderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailSenderController {

    private final EmailSenderUseCaseImpl emailSenderUseCaseImp;

    @Autowired
    public EmailSenderController(EmailSenderUseCaseImpl emailSenderUseCaseImp){
        this.emailSenderUseCaseImp = emailSenderUseCaseImp;
    }

    @PostMapping()
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request){

        try{
            this.emailSenderUseCaseImp.sendEmail(request.to(), request.subject(), request.body());
            return ResponseEntity.ok("email sent successfully");
        }catch (EmailSenderException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro while sending emaill...");
        }
    }

}
