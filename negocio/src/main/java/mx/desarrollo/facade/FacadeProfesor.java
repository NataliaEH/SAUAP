package mx.desarrollo.facade;


import mx.desarrollo.delegate.DelegateProfesor;
import mx.desarrollo.entity.Profesor;

import java.util.List;

public class FacadeProfesor {

    private final DelegateProfesor delegateProfesor;

    public FacadeProfesor() {
        this.delegateProfesor = new DelegateProfesor();
    }

    public boolean registrar(String nombre, String apellidoPat, String apellidoMat, String rfc) {
        return delegateProfesor.registrar(nombre, apellidoPat, apellidoMat, rfc);
    }

    public Profesor buscar(int id) {
        return delegateProfesor.buscar(id);
    }

    public List<Profesor> consultar() {
        return delegateProfesor.consultar();
    }
}
