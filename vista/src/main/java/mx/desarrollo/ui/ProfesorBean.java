package mx.desarrollo.ui;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import mx.desarrollo.entity.Profesor;
import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.helper.ProfesorHelper;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

@Named("profesorUI")
@ViewScoped
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
        if (!Pattern.matches("[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+",profesor.getNombre()) || !Pattern.matches("[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+",profesor.getApellidoPat()) || !Pattern.matches("[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+",profesor.getApellidoMat())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de formato:", "No se permiten números ni carácteres especiales."));
            return;
        }

        for(Profesor p: consultar()){
            if(p.getRfc().equalsIgnoreCase(profesor.getRfc()) || (p.getNombre().equalsIgnoreCase(profesor.getNombre()) && p.getApellidoPat().equalsIgnoreCase(profesor.getApellidoPat()) && p.getApellidoMat().equalsIgnoreCase(profesor.getApellidoMat()))){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de registro:", "Profesor ya existe en el catálogo."));
                return;
            }
        }

        boolean validado = profesorHelper.validarRFC(profesor.getRfc());
        if(!validado){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de formato:", "Formato RFC incorrecto."));
            return;
        }
        boolean registrado = profesorHelper.registrar(profesor.getNombre(), profesor.getApellidoPat(), profesor.getApellidoMat(), profesor.getRfc());
        if(registrado){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Profesor registrado:", "El profesor se agregó al catálogo."));
            profesor = new Profesor();
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
