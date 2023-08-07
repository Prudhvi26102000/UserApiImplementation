package com.DPDzero.security;

import java.io.IOException;


import com.DPDzero.config.AppConstants;
import com.DPDzero.exception.ErrorsException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //getToken
        String requestToken=request.getHeader("Authorization");

        //Bearer 212a156d3aa
        //System.out.println(requestToken);

        String username=null;
        String token=null;

        if(requestToken!=null && requestToken.startsWith("Bearer"))
        {
            token=requestToken.substring(7);
            try {
                username = this.jwtTokenHelper.getUsernameFromToken(token);
            }
            catch(IllegalArgumentException e)
            {
                System.out.println("Unable to get Jwt Token");
            }
            catch(ExpiredJwtException e)
            {
                System.out.println("Jwt token has expired");
            }
            catch(MalformedJwtException e)
            {
                System.out.println("Invalid JWT");
            }
        }
        else {
            System.out.println("Jwt Token doesnot Begin with Bearer");
        }

        // once we get the token , now validate

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {

            UserDetails userdetails= this.userDetailsService.loadUserByUsername(username);

            if(this.jwtTokenHelper.validateToken(token, userdetails))
            {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userdetails,null,userdetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else {
                throw new ErrorsException(AppConstants.status,"INVALID_TOKEN",AppConstants.INVALID_TOKEN);
            }

        }
        else {
            System.out.println("Username is null or context is not null");
        }


        filterChain.doFilter(request, response);

    }
}
