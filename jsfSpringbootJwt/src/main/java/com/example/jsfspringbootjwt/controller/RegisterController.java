package com.example.jsfspringbootjwt.controller;
import com.example.jsfspringbootjwt.model.User;
import com.example.jsfspringbootjwt.service.RegisterService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named("registerController")
@SessionScoped
public class RegisterController implements Serializable {

    private User user = new User();

    private final RegisterService registerService;


    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    public String registerUser(){

        if (user.getUsername().equals("") || user.getEmail().equals("") || user.getGitHubAcc().equals("")){
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please fill out all filed"));
            return "";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passworsd=passwordEncoder.encode(user.getPassword());

        user.setPassword(passworsd);
       registerService.registerNewUser(user);

       return "login?faces-redirect=true";
    }

    public String backButton(){
        user= new User();
        return "index.xhtml";
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
