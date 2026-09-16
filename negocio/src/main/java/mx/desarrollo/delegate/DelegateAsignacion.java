package mx.desarrollo.delegate;

import mx.desarrollo.entity.Asignacion;
import mx.desarrollo.entity.Horario;
import mx.desarrollo.entity.Profesor;
import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.persistence.integration.ServiceLocator;

import java.util.List;

public class DelegateAsignacion {

    public boolean asignar(Profesor profesor, UnidadAprendizaje ua, List<Horario> horarios) {

        if (profesor == null || ua == null) {
            return false;
        }

        Asignacion asignacion = new Asignacion();
        asignacion.setProfesor(profesor);
        asignacion.setUnidadAprendizaje(ua);
        asignacion.setHorarios(horarios);

        try {
            ServiceLocator.getInstanceAsignacionDAO().save(asignacion);

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
            ServiceLocator.getInstanceAsignacionDAO().delete(asignacion);
            return true;
        } catch (Exception e) {
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

    public boolean validarTraslape() {
        List<Horario> horarios = ServiceLocator.getInstanceHorarioDAO().findAll();

        for (int i = 0; i < horarios.size(); i++) {

            Horario horario1 = horarios.get(i);

            for (int j = i + 1; j < horarios.size(); j++) {

                Horario horario2 = horarios.get(j);

                if (horario1.getDia().equalsIgnoreCase(horario2.getDia())
                        && horario1.getHoraInicio() < horario2.getHoraFinal()
                        && horario1.getHoraFinal() > horario2.getHoraInicio()) {

                    return true;
                }
            }
        }

        return false;
    }

    public List<Horario> listarHorarios(int id) {

        Asignacion asignacion = buscar(id);

        if (asignacion == null) {
            return null;
        }

        return asignacion.getHorarios();
    }
}