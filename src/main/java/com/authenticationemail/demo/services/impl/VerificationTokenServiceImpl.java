package com.authenticationemail.demo.services.impl;

import com.authenticationemail.demo.exception.ApplicationRuntimeException;
import com.authenticationemail.demo.mapper.UserMapper;
import com.authenticationemail.demo.mapper.VerificationTokenMapper;
import com.authenticationemail.demo.models.User;
import com.authenticationemail.demo.models.UserDTO;
import com.authenticationemail.demo.models.VerificationToken;
import com.authenticationemail.demo.models.VerificationTokenDTO;
import com.authenticationemail.demo.repositories.VerificationTokenRepository;
import com.authenticationemail.demo.services.MailService;
import com.authenticationemail.demo.services.UserService;
import com.authenticationemail.demo.services.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;
    @Autowired
    private VerificationTokenMapper verificationTokenMapper;
    @Autowired
    private UserMapper userMapper;

    private VerificationToken getVerificationToken(User user){
        VerificationToken token = new VerificationToken(1440,user);

        return verificationTokenRepository.save(token);
    }
    @Override
    public void sendVerificationToken(UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        VerificationToken token = getVerificationToken(user);
        VerificationTokenDTO tokenDTO = verificationTokenMapper.toVerificationTokenDTO(token);
        mailService.sendEmailVerificationMail(tokenDTO);
    }
    @Override
    public void verifyToken(String token) {
        Optional<VerificationToken> verificationTokenOptional = verificationTokenRepository.findByToken(token);

        //noinspection SimplifyOptionalCallChains
        if (!verificationTokenOptional.isPresent()) {
            throw new ApplicationRuntimeException("exception.token.notFound");
        }

        VerificationToken verificationToken = verificationTokenOptional.get();

        UserDetails userDetails = userService.getLoggedInUser();

        if(!verificationToken.getUser().getUsername().equals(userDetails.getUsername()))
            throw new ApplicationRuntimeException("exception.token.notMatched");

        if (verificationToken.getExpiredDateTime().isBefore(LocalDateTime.now()))
            throw new ApplicationRuntimeException("exception.token.expired");

        if(verificationToken.getStatus().equals(VerificationToken.STATUS_VERIFIED)) return;

        verificationToken.setConfirmedDateTime(LocalDateTime.now());
        verificationToken.setStatus(VerificationToken.STATUS_VERIFIED);
        verificationTokenRepository.save(verificationToken);
    }
}
