package mx.desarrollo.facade;


import mx.desarrollo.delegate.DelegateUA;
import mx.desarrollo.entity.UnidadAprendizaje;

import java.util.List;


public class FacadeUA {

    private final DelegateUA delegateUA;

    public FacadeUA() {
        this.delegateUA = new DelegateUA();
    }

    public boolean registrar(String nombre, int horasClase, int horasTaller, int horasLab) {
        return delegateUA.registrar(nombre, horasClase, horasTaller, horasLab);
    }

    public boolean modificar(int id, String nombre, int horasClase, int horasTaller, int horasLab) {
        return delegateUA.modificar(id, nombre, horasClase, horasTaller, horasLab);
    }

    public boolean eliminar(int id) {
        return delegateUA.eliminar(id);
    }

    public UnidadAprendizaje buscar(int id) {
        return delegateUA.buscar(id);
    }

    public List<UnidadAprendizaje> consultar() {
        return delegateUA.consultar();
    }
}