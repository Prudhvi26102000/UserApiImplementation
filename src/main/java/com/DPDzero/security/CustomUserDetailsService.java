package com.DPDzero.security;

import com.DPDzero.exception.ErrorsException;
import com.DPDzero.model.UserData;
import com.DPDzero.repositories.UserRepository;
import com.DPDzero.config.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData userData=this.userRepository.findByUsername(username);
        if(userData==null){
            throw new ErrorsException(AppConstants.status,"MISSING_FIELDS",AppConstants.MISSING_FIELDS);
        }
        return userData;
    }
}
