package mx.desarrollo.ui;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import mx.desarrollo.entity.Asignacion;
import mx.desarrollo.entity.Horario;
import mx.desarrollo.helper.AsignacionHelper;

import java.io.Serializable;

@Named("asignacionUI")
@SessionScoped
public class AsignacionBean implements Serializable{
    private AsignacionHelper asignacionHelper;
    private Asignacion asignacion;
    private List<Horario> horarios;

    public AsignacionBean() {
        asignacionHelper = new AsignacionHelper();
    }
    @PostConstruct
    public void init(){
        asignacion = new Asignacion();
    }

    public void asignar(){
        boolean asignado = asignacionHelper.asignar(asignacion.getUA(), asignacion.getProfesor(), asignacion.getHorarios());
        if(asignado){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Asignacion registrada:", "La asignacion se agrego al catalogo."));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de registro:", "Hubo un error al hacer el registro en la BD."));
        }
    }
    public void modificar(int id){
        boolean modificado = asignacionHelper.asignar(id, asignacion.getUA(), asignacion.getProfesor(), asignacion.getHorarios());
        if(modificado){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Asignacion modificada:", "La asignacion se modifico en el catalogo."));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de modificacion:", "Hubo un error al hacer la modificacion en la BD."));
        }
    }
    public void eliminar(int id){
        boolean eliminado = asignacionHelper.eliminar(id);
        if(eliminado){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Asignacion eliminada:", "La asignacion se elimino del catalogo."));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de eliminacion:", "Hubo un error al hacer la eliminacion en la BD."));
        }
    }
    public Asignacion buscar(int id){
        return asignacionHelper.buscar(id);
    }
    public List<Asignacion> consultar(){
        return asignacionHelper.consultar();
    }
    public List<Horario> consultarHorarios(int id){ return asignacionHelper.consultarHorarios(id);}
    public void agregarHorario(int idAsig){
        //
    }

    /* getters y setters*/
    public Asignacion getAsignacion() {
        return asignacion;
    }
}
