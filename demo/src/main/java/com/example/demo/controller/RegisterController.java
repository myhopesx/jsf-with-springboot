package com.example.demo.controller;
import com.example.demo.model.User;
import com.example.demo.service.RegisterService;
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
       registerService.registerNewUser(user);
       return "list?faces-redirect=true";
    }

    public String backButton(){
        user=new User();
        return "index?faces-redirect=true";
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
