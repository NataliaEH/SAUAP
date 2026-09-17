package mx.desarrollo.ui;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.helper.UAHelper;

import java.io.Serializable;
import java.util.List;

@Named("uaUI")
@SessionScoped
public class UABean implements Serializable{
    private UAHelper uaHelper;
    private UnidadAprendizaje ua;

    public UABean() {
        uaHelper = new UAHelper();
    }
    @PostConstruct
    public void init(){
        ua = new UnidadAprendizaje();
    }

    public void registrar(){
        boolean registrado = uaHelper.registrar(ua.getNombre(), ua.getHorasClase(), ua.getHorasTaller(), ua.getHorasLaboratorio());
        if(registrado){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "UA registrada:", "La UA se agregó al catálogo."));
            ua = new UnidadAprendizaje();
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de registro:", "Hubo un error al hacer el registro en la BD."));
        }
    }
    public void modificar(int id){
        System.out.println("MODIFICANDO");
        boolean modificado = uaHelper.modificar(id, ua.getNombre(), ua.getHorasClase(), ua.getHorasTaller(), ua.getHorasLaboratorio());
        if(modificado){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "UA modificada:", "La UA se modificó en el catálogo."));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de modificación:", "Hubo un error al hacer la modificación en la BD."));
        }
    }
    public void eliminar(int id){
        boolean eliminado = uaHelper.eliminar(id);
        if(eliminado){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "UA eliminada:", "La UA se eliminó en el catálogo."));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de eliminación:", "Hubo un error al hacer la eliminación en la BD."));
        }
    }
    public UnidadAprendizaje buscar(int id){
        return uaHelper.buscar(id);
    }
    public List<UnidadAprendizaje> consultar(){
        return uaHelper.consultar();
    }
    
    /* getters y setters*/
    public UnidadAprendizaje getUA() {
        return ua;
    }
}
