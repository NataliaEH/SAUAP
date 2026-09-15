package mx.desarrollo.integration;

import mx.desarrollo.facade.FacadeAlumno;
import mx.desarrollo.facade.FacadeUsuario;

public class ServiceFacadeLocator {

    private static FacadeProfesor facadeProfesor;
    private static FacadeHorario facadeHorario;
    private static FacadeAsignacion facadeAsignacion;
    private static FacadeUnidadAprendizaje facadeUnidadAprendizaje;
    private static FacadeUsuario facadeUsuario;

    public static FacadeProfesor getInstanceFacadeProfesor() {
        if (facadeProfesor == null) {
            facadeProfesor = new FacadeProfesor();
            return facadeProfesor;
        } else {
            return facadeProfesor;
        }
    }

    public static FacadeHorario getInstanceFacadeHorario() {
        if (facadeHorario == null) {
            facadeHorario = new FacadeHoraio();
            return facadeHorario;
        } else {
            return facadeHorario;
        }
    }

    public static FacadeAsignacion getInstanceFacadeAsignacion() {
        if (facadeAsignacion == null) {
            facadeAsignacion = new FacadeAsignacion();
            return facadeAsignacion;
        } else {
            return facadeAsignacion;
        }
    }

    public static FacadeUnidadAprendizaje getInstanceFacadeUnidadAprendizaje() {
        if (facadeUnidadAprendizaje == null) {
            facadeUnidadAprendizaje = new FacadeUnidadAprendizaje();
            return facadeUnidadAprendizaje;
        } else {
            return facadeUnidadAprendizaje ;
        }
    }

    public static FacadeUsuario getInstanceFacadeUsuario() {
        if (facadeUsuario == null) {
            facadeUsuario = new FacadeUsuario();
            return facadeUsuario;
        } else {
            return facadeUsuario;
        }
    }
}


