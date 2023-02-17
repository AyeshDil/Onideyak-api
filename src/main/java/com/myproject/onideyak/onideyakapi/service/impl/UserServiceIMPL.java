package com.myproject.onideyak.onideyakapi.service.impl;

import com.myproject.onideyak.onideyakapi.dto.request.UserRequestDTO;
import com.myproject.onideyak.onideyakapi.dto.request.UserUpdateRequestDTO;
import com.myproject.onideyak.onideyakapi.dto.response.CommonResponseDTO;
import com.myproject.onideyak.onideyakapi.entity.User;
import com.myproject.onideyak.onideyakapi.entity.UserRole;
import com.myproject.onideyak.onideyakapi.entity.share.UserNameResource;
import com.myproject.onideyak.onideyakapi.exception.ConflictException;
import com.myproject.onideyak.onideyakapi.exception.NotFoundException;
import com.myproject.onideyak.onideyakapi.exception.ServerErrorException;
import com.myproject.onideyak.onideyakapi.exception.UnauthorizedException;
import com.myproject.onideyak.onideyakapi.repo.UserRepo;
import com.myproject.onideyak.onideyakapi.repo.UserRoleRepo;
import com.myproject.onideyak.onideyakapi.service.UserService;
import com.myproject.onideyak.onideyakapi.service.process.EmailService;
import com.myproject.onideyak.onideyakapi.util.Generator;
import com.myproject.onideyak.onideyakapi.util.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import java.util.Optional;


@Service
public class UserServiceIMPL implements UserService {
    private final Generator generator;
    private final EmailService emailService;
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final UserRoleRepo userRoleRepo;


    @Autowired
    public UserServiceIMPL(Generator generator, EmailService emailService, UserRepo userRepo, UserMapper userMapper, UserRoleRepo userRoleRepo) {
        this.generator = generator;
        this.emailService = emailService;
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.userRoleRepo = userRoleRepo;
    }


    @Override
    public CommonResponseDTO createUser(UserRequestDTO userRequestDTO) throws MessagingException {
        // Generate a prefix
        String generatedPrefix = generator.generatePrefix(5);

        if (userRepo.findByPrefix(generatedPrefix).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        // Generate primary number
        String generatedPrimaryKey = generator.generatedPrimaryKey(generatedPrefix, "U", 10);

        // Send email
        // Generate verification code
        String verificationCode = generator.generateVerificationCode();

        // Email body
        String body = "<h1>Verification Code = " + verificationCode + "</h1>";

        boolean isSendEmail = emailService.sendEmail(
                userRequestDTO.getEmail(),
                "Regarding Logging",
                body
        );

        if (isSendEmail) {
            // save user

            Optional<UserRole> selectedUserRole = userRoleRepo.findByRoleNameEquals("USER");

            if (selectedUserRole.isEmpty()) {
                throw new ConflictException("User Roles not present in database");
            }

            User user = new User(
                    generatedPrimaryKey,
                    userRequestDTO.getContactNumber(),
                    new UserNameResource(userRequestDTO.getFirstName(), userRequestDTO.getLastName()),
                    userRequestDTO.getEmail(),
                    userRequestDTO.getPassword(),
                    true,
                    true,
                    true,
                    false,
                    generatedPrefix,
                    verificationCode,
                    null,
                    null,
                    null,
                    selectedUserRole.get()
            );
            userRepo.save(user);

           /* User user = userMapper.dtoToEntity(userRequestDTO);
            user.setOtp(verificationCode);
            user.setPropertyId(generatedPrimaryKey);
            userRepo.save(user);*/
            return new CommonResponseDTO(200, userRequestDTO.getEmail() + " send Email.", user.getPropertyId());
        } else {
            throw new ServerErrorException(userRequestDTO.getEmail() + " not saved! Internal Server Error");
        }
    }

    @Override
    public CommonResponseDTO verifyUser(String otp, String email) {
        // find user by email
        Optional<User> selectedUser = userRepo.findByEmailEquals(email);
        // if the user in db
        if (selectedUser.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // if enabled is true
        if (selectedUser.get().isEnabled()) throw new ResponseStatusException(HttpStatus.OK);
        // update user enable to true
        if (selectedUser.get().getOtp().equals(otp)) {
            selectedUser.get().setEnabled(true);
            selectedUser.get().setOtp(null);
            userRepo.save(selectedUser.get());
            return new CommonResponseDTO(201, "Activated", null);
        } else {
            throw new UnauthorizedException("OTP not match");
        }

    }

    @Override
    public CommonResponseDTO updateUserDetails(UserUpdateRequestDTO userUpdateRequestDTO, String userId) {

        if (userRepo.existsById(userId)) {
            userRepo.updateUserDetails(
                    userUpdateRequestDTO.getContactNumber(),
                    userUpdateRequestDTO.getFirstName(),
                    userUpdateRequestDTO.getLastName(),
                    userUpdateRequestDTO.getPassword(),
                    userId
            );
            return new CommonResponseDTO(201, "Updated", null);
        } else {
            throw new NotFoundException("Wrong user id");
        }
    }

    @Override
    public CommonResponseDTO forgotPassword(String email) throws MessagingException {
        // find user email in db
        Optional<User> selectedUser = userRepo.findByEmailEquals(email);

        if (selectedUser.isEmpty()) {
            throw new NotFoundException("Wrong Email");
        }

        String verificationCode = generator.generateVerificationCode();

        // Email body
        String body = "<h1>Verification Code = " + verificationCode + "</h1>";

        boolean isSendEmail = emailService.sendEmail(
                email,
                "Regarding Logging",
                body
        );
        if (isSendEmail){
            // save new otp
            selectedUser.get().setOtp(verificationCode);
            userRepo.save(selectedUser.get());
            return new CommonResponseDTO(307, "otp sent", "localhost:8000/api/v1/users/visitor/restart-password/{otp}");
        }
        throw new ServerErrorException(email + " not saved! Internal Server Error");

    }

    @Override
    public CommonResponseDTO restartPassword(String email, String otp, String newPassword) {
        // find user by email
        Optional<User> selectedUser = userRepo.findByEmailEquals(email);
        // check whether otp same?
        if (selectedUser.get().getOtp().equals(otp)) {
            // change password
            selectedUser.get().setPassword(newPassword);
            selectedUser.get().setOtp(null);
            userRepo.save(selectedUser.get());
            return new CommonResponseDTO(308, "Logging to account by using new password", null);
        }
        throw new NotFoundException("Wrong Email");
    }


}
