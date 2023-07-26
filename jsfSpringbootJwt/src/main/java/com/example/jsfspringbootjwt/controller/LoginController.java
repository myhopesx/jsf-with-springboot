package com.example.jsfspringbootjwt.controller;
import com.example.jsfspringbootjwt.model.AuthReq;
import com.example.jsfspringbootjwt.security.JwtTokenUtil;
import com.example.jsfspringbootjwt.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

@Named("loginController")
@SessionScoped
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private AuthReq authReq = new AuthReq();

    public String login() throws Exception {
        Authentication auth;
        try {
            auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authReq.getUsername(),
                            authReq.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (BadCredentialsException e) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Bad Credentials"));
            return "";
        }

        final UserDetails user = loginService.loadUserByUsername(authReq.getUsername());
        final String jwtToken = jwtTokenUtil.generateToken(user);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setHeader("Authorization",jwtToken);

        return "/list";
    }

    public AuthReq getAuthReq() {
        return authReq;
    }

    public void setAuthReq(AuthReq authReq) {
        this.authReq = authReq;
    }
}
