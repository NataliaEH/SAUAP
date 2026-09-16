package mx.desarrollo.delegate;

import mx.desarrollo.persistence.integration.ServiceLocator;

public class DelegateProfesor {
    public boolean registrar(String nombre, String apellidoPat, String apellidoMat, String rfc) {

        Profesor profesor = new Profesor();
        profesor.setNombre(nombre);
        profesor.setApellidoPat(apellidoPat);
        profesor.setApellidoMat(apellidoMat);
        profesor.setRfc(rfc);

        return ServiceLocator.getInstanceProfesorDAO().save(profesor);
    }

    public Profesor buscar(int id) {
        return ServiceLocator.getInstanceProfesorDAO().find(id);
    }

    public List<Profesor> consultar() {
        return ServiceLocator.getInstanceProfesorDAO().findAll();
    }
}

