package com.DPDzero.services;

import com.DPDzero.model.UserData;
import com.DPDzero.requests.RegisterRequest;
import com.DPDzero.responses.RegisterResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    RegisterResponse registerUser(RegisterRequest registerRequest);
    List<UserData> fetchAllData();
}
