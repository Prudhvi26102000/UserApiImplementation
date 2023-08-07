package com.DPDzero.controllers;

import com.DPDzero.config.AppConstants;
import com.DPDzero.exception.ErrorsException;
import com.DPDzero.repositories.UserRepository;
import com.DPDzero.requests.JwtAuthRequest;
import com.DPDzero.requests.RegisterRequest;
import com.DPDzero.requests.StoreDataRequest;
import com.DPDzero.requests.UpdateRequest;
import com.DPDzero.responses.*;
import com.DPDzero.security.JwtTokenHelper;
import com.DPDzero.services.StoreDataService;
import com.DPDzero.services.UserService;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private StoreDataService storeDataService;

    //User Registration
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest registerRequest){
        RegisterResponse registerResponse=this.userService.registerUser(registerRequest);
        return new ResponseEntity<>(registerResponse,HttpStatus.CREATED);
    }

    //Generate Token
    @PostMapping("/token")
    public ResponseEntity<LoginResponse> createToken(@RequestBody JwtAuthRequest request)throws Exception{

        this.authenticate(request.getUsername(),request.getPassword());
        UserDetails userDetails=this.userDetailsService.loadUserByUsername(request.getUsername());
        String token=this.jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse response=new JwtAuthResponse();
        response.setToken(token);
        response.setExpire_in(this.jwtTokenHelper.getTokenValidityInMilliseconds()/1000);

        LoginResponse loginResponse=new LoginResponse("success", "Access token generated successfully.",response);
        return new ResponseEntity<LoginResponse>(loginResponse,HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            this.authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            throw new ErrorsException(AppConstants.status, "INVALID_CREDENTIALS", AppConstants.INVALID_CREDENTIALS);
        }
    }

    //Store Data
    @PostMapping("/data")
    public ResponseEntity<StoreDataResponse> storeData(@Valid @RequestBody StoreDataRequest request){
        StoreDataResponse response=this.storeDataService.storeData(request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    //Retrieve Data
    @GetMapping("/data/{key}")
    public ResponseEntity<RetrieveDataResponse> retrieveData(@PathVariable String key){
       RetrieveDataResponse response=this.storeDataService.retrivedata(key);
       return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //Update Data
    @PutMapping("/data/{key}")
    public  ResponseEntity<StoreDataResponse> updateData(@RequestBody UpdateRequest request, @PathVariable String key){
        StoreDataResponse response=this.storeDataService.updateData(request,key);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    //Delete Data
    @DeleteMapping("/data/{key}")
    public ResponseEntity<StoreDataResponse> deleteData(@PathVariable String key){
        StoreDataResponse response=this.storeDataService.deleteData(key);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
