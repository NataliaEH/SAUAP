package mx.desarrollo.helper;

import mx.desarrollo.entity.Asignacion;
import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.integration.ServiceFacadeLocator;
import java.io.Serializable;
import java.util.List;

public class UAHelper implements Serializable {
    public boolean registrar(String nombre, int horasClase, int horasTaller, int horasLab) {
        return ServiceFacadeLocator.getInstanceFacadeUA().registrar(nombre, horasClase, horasTaller, horasLab);
    }

    public boolean modificar(int id, String nombre, int horasClase, int horasTaller, int horasLab) {
        return ServiceFacadeLocator.getInstanceFacadeUA().modificar(id, nombre, horasClase, horasTaller, horasLab);
    }

    public boolean eliminar(int id) {
        for(Asignacion a:ServiceFacadeLocator.getInstanceFacadeAsignacion().listar()){
            if(a.getUnidadAprendizaje().getId()==id){
                ServiceFacadeLocator.getInstanceFacadeAsignacion().eliminar(a.getId());
            }
        }
        return ServiceFacadeLocator.getInstanceFacadeUA().eliminar(id);
    }

    public UnidadAprendizaje buscar(int id) {
        return ServiceFacadeLocator.getInstanceFacadeUA().buscar(id);
    }

    public List<UnidadAprendizaje> consultar() {
        return ServiceFacadeLocator.getInstanceFacadeUA().consultar();
    }
}