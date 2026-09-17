package mx.desarrollo.delegate;

import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.persistence.integration.ServiceLocator;

import java.util.List;

public class DelegateUA {

    public boolean registrar(String nombre, int horasClase, int horasTaller, int horasLab) {
        UnidadAprendizaje ua = new UnidadAprendizaje();

        ua.setNombre(nombre);
        ua.setHorasClase(horasClase);
        ua.setHorasTaller(horasTaller);
        ua.setHorasLaboratorio(horasLab);

        try {
            ServiceLocator.getInstanceUnidadAprendizajeDAO().save(ua);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean modificar(int id, String nombre, int horasClase, int horasTaller, int horasLab) {
        if (nombre == null || nombre.trim().isEmpty() || nombre.length() > 50) {
            return false;
        }

        UnidadAprendizaje ua = new UnidadAprendizaje();
        ua.setId(id);
        ua.setNombre(nombre);
        ua.setHorasClase(horasClase);
        ua.setHorasTaller(horasTaller);
        ua.setHorasLaboratorio(horasLab);

        try {
            ServiceLocator.getInstanceUnidadAprendizajeDAO().update(ua);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        UnidadAprendizaje ua = buscar(id);

        if(ua == null){
            return false;
        }
        try {
            ServiceLocator.getInstanceUnidadAprendizajeDAO().delete(ua);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public UnidadAprendizaje buscar(int id) {
        return ServiceLocator.getInstanceUnidadAprendizajeDAO().find(id).orElse(null);
    }

    public List<UnidadAprendizaje> consultar() {
        return ServiceLocator.getInstanceUnidadAprendizajeDAO().findAll();
    }
}
