//package edu.vanderbilt.cs.cyberbull.services.auth.email;
//
//import lombok.AllArgsConstructor;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class EmailSenderService {
//
//    private JavaMailSender javaMailSender;
//    public EmailSenderService(){
//
//    }
//    @Async
//    public void sendEmail(SimpleMailMessage email) {
//        javaMailSender.send(email);
//    }
//}