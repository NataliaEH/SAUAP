/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.desarrollo.ui;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import mx.desarrollo.entity.Usuario;
import mx.desarrollo.helper.LoginHelper;

import java.io.IOException;
import java.io.Serializable;

@Named("loginUI")
@SessionScoped
public class LoginBean implements Serializable{
    private LoginHelper loginHelper;
    private Usuario usuario;
    
    public LoginBean() {
        loginHelper = new LoginHelper();
    }
    @PostConstruct
    public void init(){
        usuario = new Usuario();
    }

    public void iniciarSesion() throws IOException{
        String appURL = "/index.xhtml";
        Usuario us= new Usuario();
        us.setId(0);
        us = loginHelper.iniciarSesion(usuario.getUsuario(), usuario.getContrasena());
        if(us != null && us.getId()!=null){
            usuario = us;
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + appURL);
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario o contraseña incorrecta:", "Intente de nuevo."));
        }
    }
    
    /* getters y setters*/
    public Usuario getUsuario() {
        return usuario;
    }
}
