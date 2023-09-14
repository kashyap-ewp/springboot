package com.authenticationemail.demo.services.impl;

import com.authenticationemail.demo.exception.ApplicationRuntimeException;
import com.authenticationemail.demo.mapper.VerificationTokenMapper;
import com.authenticationemail.demo.models.ResetPasswordDTO;
import com.authenticationemail.demo.models.User;
import com.authenticationemail.demo.models.VerificationToken;
import com.authenticationemail.demo.repositories.UserRepository;
import com.authenticationemail.demo.repositories.VerificationTokenRepository;
import com.authenticationemail.demo.services.MailService;
import com.authenticationemail.demo.services.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PasswordServiceImpl implements PasswordService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailService mailService;
    @Autowired
    private VerificationTokenMapper verificationTokenMapper;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void sendResetPasswordToken(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        //noinspection SimplifyOptionalCallChains
        if(!userOptional.isPresent()) throw new ApplicationRuntimeException("exception.user.usernameNotFound");

        VerificationToken verificationToken = new VerificationToken(5,userOptional.get());

        verificationTokenRepository.save(verificationToken);

        mailService.sendPasswordVerificationMail(verificationTokenMapper.toVerificationTokenDTO(verificationToken));
    }

    @Override
    public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
        if(!isTokenValid(resetPasswordDTO)) return;

        Optional<User> userOptional = userRepository.findByUsername(resetPasswordDTO.getUsername());

        //noinspection SimplifyOptionalCallChains
        if(!userOptional.isPresent()) throw new ApplicationRuntimeException("exception.user.notFound");

        User user = userOptional.get();

        String password = bCryptPasswordEncoder.encode(resetPasswordDTO.getPassword());

        user.setPassword(password);

        userRepository.save(user);
    }

    private boolean isTokenValid(ResetPasswordDTO resetPasswordDTO) {
        Optional<VerificationToken> verificationTokenOptional =
                verificationTokenRepository.findByToken(resetPasswordDTO.getToken());

        //noinspection SimplifyOptionalCallChains
        if(!verificationTokenOptional.isPresent())
            throw new ApplicationRuntimeException("exception.token.notFound");

        VerificationToken verificationToken = verificationTokenOptional.get();

        if(!verificationToken.getUser().getUsername().equals(resetPasswordDTO.getUsername()))
            throw new ApplicationRuntimeException("exception.token.notMatched");

        if (verificationToken.getExpiredDateTime().isBefore(LocalDateTime.now()))
            throw new ApplicationRuntimeException("exception.token.expired");

        if(verificationToken.getStatus().equals(VerificationToken.STATUS_VERIFIED)) return false;

        verificationToken.setConfirmedDateTime(LocalDateTime.now());
        verificationToken.setStatus(VerificationToken.STATUS_VERIFIED);
        verificationTokenRepository.save(verificationToken);

        return true;
    }
}
