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
import java.util.List;

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
        boolean asignado = asignacionHelper.asignar(asignacion.getUnidadAprendizaje(), asignacion.getProfesor(), asignacion.getHorarios());
        if(asignado){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Asignación registrada:", "La asignación se agregó al catálogo."));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de registro:", "Hubo un error al hacer el registro en la BD."));
        }
    }
    public void modificar(int id){
        boolean modificado = asignacionHelper.modificar(id, asignacion.getUnidadAprendizaje(), asignacion.getProfesor(), asignacion.getHorarios());
        if(modificado){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Asignación modificada:", "La asignación se modificó en el catálogo."));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de modificación:", "Hubo un error al hacer la modificación en la BD."));
        }
    }
    public void eliminar(int id){
        boolean eliminado = asignacionHelper.eliminar(id);
        if(eliminado){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Asignación eliminada:", "La asignación se eliminó del catálogo."));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de eliminación:", "Hubo un error al hacer la eliminación en la BD."));
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
