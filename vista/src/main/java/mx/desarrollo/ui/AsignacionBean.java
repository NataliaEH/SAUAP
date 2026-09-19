package mx.desarrollo.ui;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import mx.desarrollo.entity.Asignacion;
import mx.desarrollo.entity.Horario;
import mx.desarrollo.entity.Profesor;
import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.helper.AsignacionHelper;

import java.io.Serializable;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Named("asignacionUI")
@ViewScoped
public class AsignacionBean implements Serializable{
    private AsignacionHelper asignacionHelper;
    private Asignacion asignacion;
    private Asignacion select;
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
        select = new Asignacion();
    }

    public void asignar(){
        //VALIDAR HORAS
        for(Horario h: horarios){
            long horas = ChronoUnit.HOURS.between(h.getHoraInicio(), h.getHoraFinal());
            if(horas<=0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de horas:", "La hora de inicio debe ser antes que la hora final."));
                return;
            }

            switch(h.getTipoClase()){
                case "Clase":   if(horas>getUA(idUA).getHorasClase()){
                                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de horas:", "El intervalo de horas es mayor del establecido en la unidad de aprendizaje."));
                                    return;
                                }
                    break;
                case "Taller":  if(horas>getUA(idUA).getHorasTaller()){
                                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de horas:", "El intervalo de horas es mayor del establecido en la unidad de aprendizaje."));
                                    return;
                                }
                    break;
                case "Laboratorio": if(horas>getUA(idUA).getHorasLaboratorio()){
                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de horas:", "El intervalo de horas es mayor del establecido en la unidad de aprendizaje."));
                                        return;
                                    }
                    break;
                default: return;
            }
        }
        //VALIDAR TRASLAPE CON MISMA ASIGNACION
        for(int i=0;i<horarios.toArray().length;i++){
            for(int j=0;j<horarios.toArray().length;j++){
                if(j!=i && (horarios.get(i).getDia().equalsIgnoreCase(horarios.get(j).getDia()) && (horarios.get(i).getHoraInicio().isBefore(horarios.get(j).getHoraFinal())
                        && horarios.get(i).getHoraFinal().isAfter(horarios.get(j).getHoraInicio())))){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de registro:", "Horarios repetidos."));
                    return;
                }
            }
        }
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
            asignacion = new Asignacion();
            horarios.clear();
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de registro:", "Hubo un error al hacer el registro en la BD."));
        }
    }
    public void modificar(){
        select.setUnidadAprendizaje(getUA(idUA));
        select.setProfesor(getProfesor(idProfesor));

        //VALIDAR HORAS
        for(Horario h: horarios){
            long horas = ChronoUnit.HOURS.between(h.getHoraInicio(), h.getHoraFinal());
            if(horas<=0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de horas:", "La hora de inicio debe ser antes que la hora final."));
                return;
            }

            switch(h.getTipoClase()){
                case "Clase":   if(horas>select.getUnidadAprendizaje().getHorasClase()){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de horas:", "El intervalo de horas es mayor del establecido en la unidad de aprendizaje."));
                    return;
                }
                    break;
                case "Taller":  if(horas>select.getUnidadAprendizaje().getHorasTaller()){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de horas:", "El intervalo de horas es mayor del establecido en la unidad de aprendizaje."));
                    return;
                }
                    break;
                case "Laboratorio": if(horas>select.getUnidadAprendizaje().getHorasLaboratorio()){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de horas:", "El intervalo de horas es mayor del establecido en la unidad de aprendizaje."));
                    return;
                }
                    break;
                default: return;
            }
        }

        //VALIDAR HORARIO
        if(getAsignaciones()!=null){
            for(Asignacion a:getAsignaciones()){
                if(!Objects.equals(select.getId(), a.getId()) && Objects.equals(a.getProfesor().getId(), a.getProfesor().getId()) && Objects.equals(a.getUnidadAprendizaje().getId(), select.getUnidadAprendizaje().getId()) && Objects.equals(a.getGrupo(), select.getGrupo())){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de registro:", "Ya existe una asignación de este profesor a esta UA del mismo grupo."));
                    return;
                }
                if(!Objects.equals(select.getId(), a.getId()) && Objects.equals(a.getGrupo(), select.getGrupo())){
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
                if(!Objects.equals(select.getId(), a.getId()) && Objects.equals(a.getProfesor().getId(), select.getProfesor().getId())){
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

        boolean modificado = asignacionHelper.modificar(select.getId(), select.getUnidadAprendizaje(), select.getProfesor(), select.getGrupo(), select.getHorarios());
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

    public UnidadAprendizaje getUA(int id){return asignacionHelper.getUA(id);}
    public Profesor getProfesor(int id){return asignacionHelper.getProfesor(id);}
    public void seleccionar(Asignacion a){
        this.select=a;
        this.idProfesor = a.getProfesor().getId();
        this.idUA = a.getUnidadAprendizaje().getId();}

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

    public Asignacion getSelect(){return select;}
    public void setSelect(Asignacion select){this.select = select;}
}
