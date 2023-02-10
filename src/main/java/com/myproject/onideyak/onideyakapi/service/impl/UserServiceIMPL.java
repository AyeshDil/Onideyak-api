package com.myproject.onideyak.onideyakapi.service.impl;

import com.myproject.onideyak.onideyakapi.dto.request.UserRequestDTO;
import com.myproject.onideyak.onideyakapi.dto.response.CommonResponseDTO;
import com.myproject.onideyak.onideyakapi.repo.UserRepo;
import com.myproject.onideyak.onideyakapi.service.UserService;
import com.myproject.onideyak.onideyakapi.service.process.EmailService;
import com.myproject.onideyak.onideyakapi.util.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;


@Service
public class UserServiceIMPL implements UserService {
    private final Generator generator;
    private final EmailService emailService;
    private final UserRepo userRepo;


    @Autowired
    public UserServiceIMPL(Generator generator, EmailService emailService, UserRepo userRepo) {
        this.generator = generator;
        this.emailService = emailService;
        this.userRepo = userRepo;
    }


    @Override
    public CommonResponseDTO createUser(UserRequestDTO userRequestDTO) throws MessagingException {
        // Generate a prefix
        String generatedPrefix = generator.generatePrefix(5);

        if(userRepo.findByPrefix(generatedPrefix).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        // Generate primary number
        String generatedPrimaryKey = generator.generatedPrimaryKey(generatedPrefix, "U", 10);

        // send email
        // Generate verification code
        String verificationCode = generator.generateVerificationCode();

        String body= "<h1>Verification Code = "+ verificationCode +"</h1>";

        boolean isVerified = emailService.sendEmail(
                userRequestDTO.getEmail(),
                "Regarding Logging",
                body
                );

        if (isVerified){
            // save user

            return new CommonResponseDTO(200,"Saved",null);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
