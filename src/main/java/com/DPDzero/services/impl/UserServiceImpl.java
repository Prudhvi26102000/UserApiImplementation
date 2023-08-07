package com.DPDzero.services.impl;

import com.DPDzero.exception.ErrorsException;
import com.DPDzero.model.UserData;
import com.DPDzero.repositories.UserRepository;
import com.DPDzero.requests.RegisterRequest;
import com.DPDzero.responses.RegisterResponse;
import com.DPDzero.services.UserService;
import com.DPDzero.config.AppConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@_#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    @Override
    public RegisterResponse registerUser(RegisterRequest registerRequest) {

        if(isNullOrEmpty(registerRequest))
        {
            throw new ErrorsException(AppConstants.status,"INVALID_REQUEST",AppConstants.INVALID_REQUEST);
        }

        List<UserData> users=fetchAllData();

        for(UserData user:users) {
            if (user.getUsername().equals(registerRequest.getUsername())) {
                throw new ErrorsException(AppConstants.status, "USERNAME_EXISTS", AppConstants.USERNAME_EXISTS);
            } else if (user.getEmail().equals(registerRequest.getEmail())) {
                throw new ErrorsException(AppConstants.status, "EMAIL_EXISTS", AppConstants.EMAIL_EXISTS);
            }
        }

        if(!isValid(registerRequest.getPassword())){
            throw new ErrorsException(AppConstants.status, "INVALID_PASSWORD", AppConstants.INVALID_PASSWORD);
        }
        else if(registerRequest.getAge()<=0){
            throw new ErrorsException(AppConstants.status, "INVALID_AGE", AppConstants.INVALID_AGE);
        }
        else if(registerRequest.getGender()==null || registerRequest.getGender().isEmpty()){
            throw new ErrorsException(AppConstants.status, "GENDER_REQUIRED", AppConstants.GENDER_REQUIRED);
        }
        else if(!isValidEmail(registerRequest.getEmail())){
            throw new ErrorsException(AppConstants.status,"INVALID_EMAIL",AppConstants.INVALID_EMAIL);
        }


        UserData userData=this.modelMapper.map(registerRequest,UserData.class);
        userData.setPassword(this.passwordEncoder.encode(userData.getPassword()));

        UserData newUser=this.userRepository.save(userData);
        RegisterResponse registerResponse=this.modelMapper.map(newUser,RegisterResponse.class);
        registerResponse.setStatus("success");
        registerResponse.setMessage("User successfully registered!");

        return registerResponse;
    }

    public Boolean isValidEmail(String email){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public Boolean isNullOrEmpty(RegisterRequest registerRequest){
        if(registerRequest.getUsername()==null || registerRequest.getUsername().isEmpty() ||
                registerRequest.getEmail()==null || registerRequest.getEmail().isEmpty()||
                registerRequest.getPassword()==null || registerRequest.getPassword().isEmpty()||
                registerRequest.getFull_name()==null||registerRequest.getFull_name().isEmpty()){
            return true;
        }
        return false;
    }
    public static boolean isValid(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    @Override
    public List<UserData> fetchAllData() {
        List<UserData> users=this.userRepository.findAll();
        return users;
    }
}
