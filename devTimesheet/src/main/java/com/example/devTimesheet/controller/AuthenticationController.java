package com.example.devTimesheet.controller;

import com.example.devTimesheet.config.JwtUtils;
import com.example.devTimesheet.dto.request.JwtRequest;
import com.example.devTimesheet.dto.request.LogOutRequest;
import com.example.devTimesheet.dto.respon.ApiRespon;
import com.example.devTimesheet.dto.respon.AuthenticationRespon;
import com.example.devTimesheet.service.LogOutService;
import com.example.devTimesheet.service.impl.UserDetailServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    UserDetailServiceImpl userDetailService;
    JwtUtils jwtUtils;
    LogOutService logOut;
    AuthenticationManager authenticationManager;

    @PreAuthorize("permitAll()")
    @PostMapping("/token")
    ApiRespon<AuthenticationRespon> authentication(@RequestBody @Valid JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        System.out.println("ok");

        UserDetails userDetails = userDetailService
                .loadUserByUsername(authenticationRequest.getUsername());

        String token = jwtUtils.generateToken(userDetails);
        String tokenRefresh = jwtUtils.generateTokenRefresh(userDetails);
        AuthenticationRespon authenticationRespon = AuthenticationRespon.builder()
                .token(token)
                .tokenRefresh(tokenRefresh)
                .authenticated(true)
                .build();
        return ApiRespon.<AuthenticationRespon>builder()
                .result(authenticationRespon)
                .build();
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    @PreAuthorize("permitAll()")
    @PostMapping("/refreshToken")
    ApiRespon<AuthenticationRespon> refreshToken(HttpServletRequest request) throws Exception {

        String username = null;
        String jwtToken = null;

        final String requestTokenHeader = request.getHeader("Authorization");

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtUtils.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        }
        UserDetails userDetails = userDetailService.loadUserByUsername(username);

        String token = jwtUtils.generateToken(userDetails);

        AuthenticationRespon authenticationRespon = AuthenticationRespon.builder()
                .token(token)
                .tokenRefresh(jwtToken)
                .authenticated(true)
                .build();
        return ApiRespon.<AuthenticationRespon>builder()
                .result(authenticationRespon)
                .build();
    }


    @PreAuthorize("permitAll()")
    @PostMapping("/logOut")
    ApiRespon<Void> logOut(@RequestBody @Valid LogOutRequest request){

        logOut.logOut(request);
        return ApiRespon.<Void>builder()
                .build();
    }
}

