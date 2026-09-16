package mx.desarrollo.ui;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import mx.desarrollo.entity.Profesor;
import mx.desarrollo.helper.ProfesorHelper;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named("profesorUI")
@SessionScoped
public class ProfesorBean implements Serializable{
    private ProfesorHelper profesorHelper;
    private Profesor profesor;

    public ProfesorBean() {
        profesorHelper = new ProfesorHelper();
    }
    @PostConstruct
    public void init(){
        profesor = new Profesor();
    }

    public void registrar(){
        boolean registrado = profesorHelper.registrar(profesor.getNombre(), profesor.getApellidoPat(), profesor.getApellidoMat(), profesor.getRfc());
        if(registrado){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Profesor registrado:", "El profesor se agrego al catalogo."));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de registro:", "Hubo un error al hacer el registro en la BD."));
        }
    }
    public Profesor buscar(int id){
        return profesorHelper.buscar(id);
    }
    public List<Profesor> consultar(){
        return profesorHelper.consultar();
    }
    
    /* getters y setters*/
    public Profesor getProfesor() {
        return profesor;
    }
}
