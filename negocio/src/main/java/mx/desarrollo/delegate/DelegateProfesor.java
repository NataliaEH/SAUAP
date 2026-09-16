package mx.desarrollo.delegate;

import mx.desarrollo.entity.Profesor;
import mx.desarrollo.persistence.integration.ServiceLocator;

import java.util.List;

public class DelegateProfesor {
    public boolean registrar(String nombre, String apellidoPat, String apellidoMat, String rfc) {

        if (nombre == null || nombre.trim().isEmpty() || nombre.length() > 45) {
            return false;
        }

        if (apellidoPat == null || apellidoPat.trim().isEmpty() || apellidoPat.length() > 45) {
            return false;
        }

        if (apellidoMat == null || apellidoMat.trim().isEmpty() || apellidoMat.length() > 45) {
            return false;
        }

        if (rfc == null || rfc.trim().isEmpty() || rfc.length() > 45) {
            return false;
        }

        Profesor profesor = new Profesor();
        profesor.setNombre(nombre);
        profesor.setApellidoPat(apellidoPat);
        profesor.setApellidoMat(apellidoMat);
        profesor.setRfc(rfc);

        try {
            ServiceLocator.getInstanceProfesorDAO().save(profesor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Profesor buscar(int id) {
        return ServiceLocator.getInstanceProfesorDAO().find(id).orElse(null);
    }

    public List<Profesor> consultar() {
        return ServiceLocator.getInstanceProfesorDAO().findAll();
    }
}

