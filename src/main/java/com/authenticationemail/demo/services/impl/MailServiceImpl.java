package com.authenticationemail.demo.services.impl;

import com.authenticationemail.demo.exception.ApplicationRuntimeException;
import com.authenticationemail.demo.mapper.VerificationTokenMapper;
import com.authenticationemail.demo.models.VerificationToken;
import com.authenticationemail.demo.models.VerificationTokenDTO;
import com.authenticationemail.demo.services.MailService;
import com.authenticationemail.demo.utils.UriUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.mail.username}") private String sender;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UriUtils uriUtils;
    @Autowired
    private VerificationTokenMapper verificationTokenMapper;

    private void sendMail(String text,String subject,String toEmail){
        try{
            SimpleMailMessage mail = new SimpleMailMessage();

            mail.setFrom(sender);
            mail.setTo(toEmail);
            mail.setSubject(subject);
            mail.setText(text);

            mailSender.send(mail);
        }
        catch (Exception e){
            log.error(e.toString());
            throw new ApplicationRuntimeException("exception.mail.notSent");
        }
    }
    @Override
    public void sendEmailVerificationMail(VerificationTokenDTO tokenDTO) {
        VerificationToken token = verificationTokenMapper.toVerificationToken(tokenDTO);

        String text = uriUtils.getBaseUri() + "/verify/token/" + token.getToken();
        String subject = "Email verification";
        String toEmail = token.getUser().getEmail();

        sendMail(text,subject,toEmail);
    }

    @Override
    public void sendPasswordVerificationMail(VerificationTokenDTO resetPasswordTokenDTO) {
        VerificationToken token = verificationTokenMapper.toVerificationToken(resetPasswordTokenDTO);

        String text = token.getToken();
        String subject = "Token to reset password";
        String toEmail = token.getUser().getEmail();

        sendMail(text,subject,toEmail);
    }
}
