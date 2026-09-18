package mx.desarrollo.delegate;

import mx.desarrollo.entity.Asignacion;
import mx.desarrollo.entity.Horario;
import mx.desarrollo.entity.Profesor;
import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.persistence.integration.ServiceLocator;

import java.util.List;

public class DelegateAsignacion {

    public boolean asignar(int idProfesor, int idUA, int grupo, List<Horario> horarios) {
        if (idProfesor == 0 || idUA == 0) {
            return false;
        }

        try {
            Profesor p = ServiceLocator.getInstanceProfesorDAO().find(idProfesor).orElse(null);
            UnidadAprendizaje ua = ServiceLocator.getInstanceUnidadAprendizajeDAO().find(idUA).orElse(null);
            if(p==null || ua==null){
                return false;
            }
            Asignacion asignacion = new Asignacion();
            asignacion.setProfesor(p);
            asignacion.setUnidadAprendizaje(ua);
            asignacion.setGrupo(grupo);

            ServiceLocator.getInstanceAsignacionDAO().save(asignacion);

            if (horarios != null) {
                for (Horario horario : horarios) {
                    horario.setAsignacion(asignacion);
                    ServiceLocator.getInstanceHorarioDAO().save(horario);
                }
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modificar(int id, Profesor profesor, UnidadAprendizaje ua, List<Horario> horarios) {

        if (profesor == null || ua == null) {
            return false;
        }

        Asignacion asignacion = buscar(id);

        if (asignacion == null) {
            return false;
        }

        asignacion.setProfesor(profesor);
        asignacion.setUnidadAprendizaje(ua);
        asignacion.setHorarios(horarios);

        try {
            ServiceLocator.getInstanceAsignacionDAO().update(asignacion);

            if (horarios != null) {
                for (Horario horario : horarios) {
                    horario.setAsignacion(asignacion);
                    ServiceLocator.getInstanceHorarioDAO().save(horario);
                }
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        Asignacion asignacion = buscar(id);

        if (asignacion == null) {
            return false;
        }

        try {
            List<Horario> horarios = ServiceLocator.getInstanceHorarioDAO().findByOneParameter(id, "asignacion.id");
            for(Horario h:horarios){
                ServiceLocator.getInstanceHorarioDAO().delete(h);
            }
            ServiceLocator.getInstanceAsignacionDAO().delete(asignacion);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Asignacion buscar(int id) {
        return ServiceLocator.getInstanceAsignacionDAO().find(id).orElse(null);
    }

    public List<Asignacion> listar() {
        return ServiceLocator.getInstanceAsignacionDAO().findAll();
    }

    public boolean agregarHorario(Horario horario) {

        if (horario == null) {
            return false;
        }

        try {
            ServiceLocator.getInstanceHorarioDAO().save(horario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Horario> listarHorarios(int id) {
        Asignacion asignacion = buscar(id);

        if (asignacion == null) {
            return null;
        }
        return ServiceLocator.getInstanceHorarioDAO().findByOneParameter(id, "asignacion.id");
    }
    public List<Horario> listarHorarios(){
        return ServiceLocator.getInstanceHorarioDAO().findAll();
    }
}