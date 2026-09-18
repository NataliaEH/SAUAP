package mx.desarrollo.ui;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import mx.desarrollo.entity.Asignacion;
import mx.desarrollo.entity.Horario;
import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.helper.AsignacionHelper;

import java.io.Serializable;
import java.util.*;

@Named("asignacionUI")
@ViewScoped
public class AsignacionBean implements Serializable{
    private AsignacionHelper asignacionHelper;
    private Asignacion asignacion;
    private List<Asignacion> asignaciones;
    private List<Horario> horarios = new ArrayList<Horario>();

    //para guardar los ids de ua y profesor
    private int idProfesor;
    private int idUA;

    public AsignacionBean() {
        asignacionHelper = new AsignacionHelper();
    }
    @PostConstruct
    public void init(){
        asignacion = new Asignacion();
    }

    public void asignar(){
        //VALIDAR HORARIO
        if(getAsignaciones()!=null && !getAsignaciones().isEmpty()){
            for(Asignacion a:getAsignaciones()){
                if(a.getProfesor().getId()==idProfesor && a.getUnidadAprendizaje().getId()==idUA && Objects.equals(a.getGrupo(), asignacion.getGrupo())){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de registro:", "Ya existe una asignación de este profesor a esta UA del mismo grupo."));
                    return;
                }
                if(Objects.equals(a.getGrupo(), asignacion.getGrupo())){
                    for(Horario h:consultarHorarios(a.getId())){
                        for(Horario h2:horarios){
                            if(h2.getDia().equalsIgnoreCase(h.getDia()) && (h2.getHoraInicio().isBefore(h.getHoraFinal())
                                    && h2.getHoraFinal().isAfter(h.getHoraInicio()))){
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de registro:", "Este grupo tiene un traslape de horario con otra asignación."));
                                return;
                            }
                        }
                    }
                }
                if(a.getProfesor().getId()==idProfesor){
                    for(Horario h:consultarHorarios(a.getId())){
                        for(Horario h2:horarios){
                            if(h2.getDia().equalsIgnoreCase(h.getDia()) && (h2.getHoraInicio().isBefore(h.getHoraFinal())
                                    && h2.getHoraFinal().isAfter(h.getHoraInicio()))){
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de registro:", "Este profesor tiene un traslape de horario con otra asignación."));
                                return;
                            }
                        }
                    }
                }
            }
        }

        boolean asignado = asignacionHelper.asignar(idUA, idProfesor, asignacion.getGrupo(), horarios);
        if(asignado){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Asignación registrada:", "La asignación se agregó al catálogo."));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de registro:", "Hubo un error al hacer el registro en la BD."));
        }
    }
    public void modificar(Asignacion asignacion){
        //VALIDAR HORARIO
        if(getAsignaciones()!=null){
            for(Asignacion a:getAsignaciones()){
                if(!Objects.equals(asignacion.getId(), a.getId()) && a.getProfesor().getId()==idProfesor && a.getUnidadAprendizaje().getId()==idUA && Objects.equals(a.getGrupo(), asignacion.getGrupo())){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de registro:", "Ya existe una asignación de este profesor a esta UA del mismo grupo."));
                    return;
                }
                if(!Objects.equals(asignacion.getId(), a.getId()) && Objects.equals(a.getGrupo(), asignacion.getGrupo())){
                    for(Horario h:consultarHorarios(a.getId())){
                        for(Horario h2:horarios){
                            if(h2.getDia().equalsIgnoreCase(h.getDia()) && (h2.getHoraInicio().isBefore(h.getHoraFinal())
                                    && h2.getHoraFinal().isAfter(h.getHoraInicio()))){
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de registro:", "Este grupo tiene un traslape de horario con otra asignación."));
                                return;
                            }
                        }
                    }
                }
                if(!Objects.equals(asignacion.getId(), a.getId()) && a.getProfesor().getId()==idProfesor){
                    for(Horario h:consultarHorarios(a.getId())){
                        for(Horario h2:horarios){
                            if(h2.getDia().equalsIgnoreCase(h.getDia()) && (h2.getHoraInicio().isBefore(h.getHoraFinal())
                                    && h2.getHoraFinal().isAfter(h.getHoraInicio()))){
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de registro:", "Este profesor tiene un traslape de horario con otra asignación."));
                                return;
                            }
                        }
                    }
                }
            }
        }


        boolean modificado = asignacionHelper.modificar(asignacion.getId(), asignacion.getUnidadAprendizaje(), asignacion.getProfesor(), asignacion.getHorarios());
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
    public List<Horario> consultarHorarios(int idAsig){ return asignacionHelper.consultarHorarios(idAsig);}
    public List<Horario> consultarHorarios(){ return asignacionHelper.consultarHorarios();}
    public void agregarHorario(){horarios.add(new Horario());}
    public void eliminarHorario(Horario horario){horarios.remove(horario);}

    /* getters y setters*/
    public Asignacion getAsignacion() {
        return asignacion;
    }

    public List<Asignacion> getAsignaciones(){
        if(asignaciones==null){
            asignaciones = consultar();
        }
        return asignaciones;
    }
    public List<Horario> getHorarios(){
        return horarios;
    }
    public int getIdProfesor(){return idProfesor;}
    public void setIdProfesor(int idProfesor){this.idProfesor = idProfesor;}

    public int getIdUA(){return idUA;}
    public void setIdUA(int idUA){this.idUA = idUA;}
}
