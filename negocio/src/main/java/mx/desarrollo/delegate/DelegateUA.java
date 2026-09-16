package mx.desarrollo.delegate;

import mx.desarrollo.persistence.integration.ServiceLocator;

public class DelegateUA {

    public void registrar(String nombre, int horasClase, int horasTaller, int horasLab) {
        UnidadAprendizaje ua = new UnidadAprendizaje();

        ua.setNombre(nombre);
        ua.setHorasClase(horasClase);
        ua.setHorasTaller(horasTaller);
        ua.setHorasLab(horasLab);

        ServiceLocator.getInstanceUnidadAprendizajeDAO().save(ua);
    }

    public void modificar(int id) {
        ServiceLocator.getInstanceUnidadAprendizajeDAO().find(id);
    }

    public void eliminar(int id) {
        ServiceLocator.getInstanceUnidadAprendizajeDAO().delete(id);
    }

    public UnidadAprendizaje buscar(int id) {
        return ServiceLocator.getInstanceUnidadAprendizajeDAO().find(id);
    }

    public List<UnidadAprendizaje> consultar() {
        return ServiceLocator.getInstanceUnidadAprendizajeDAO().obtenerTodos();
    }
}
