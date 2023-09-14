package com.authenticationemail.demo.mapper;

import com.authenticationemail.demo.models.VerificationToken;
import com.authenticationemail.demo.models.VerificationTokenDTO;
import org.mapstruct.Mapper;

@Mapper
public interface VerificationTokenMapper {
    VerificationTokenDTO toVerificationTokenDTO(VerificationToken verificationToken);

    VerificationToken toVerificationToken(VerificationTokenDTO verificationTokenDTO);
}
