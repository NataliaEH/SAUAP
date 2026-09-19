package mx.desarrollo.facade;

import mx.desarrollo.delegate.DelegateAsignacion;
import mx.desarrollo.entity.Asignacion;
import mx.desarrollo.entity.Horario;
import mx.desarrollo.entity.Profesor;
import mx.desarrollo.entity.UnidadAprendizaje;

import java.util.List;

public class FacadeAsignacion {

    private final DelegateAsignacion delegateAsignacion;

    public FacadeAsignacion() {
        this.delegateAsignacion = new DelegateAsignacion();
    }

    public boolean asignar(int idUA, int idProfesor, int grupo, List<Horario> horarios) {
        return delegateAsignacion.asignar(idProfesor, idUA, grupo, horarios);
    }

    public boolean modificar(int id, UnidadAprendizaje ua, Profesor profesor, int grupo, List<Horario> horarios) {
        return delegateAsignacion.modificar(id, profesor, ua, grupo, horarios);
    }

    public boolean eliminar(int id) {
        return delegateAsignacion.eliminar(id);
    }

    public Asignacion buscar(int id) {
        return delegateAsignacion.buscar(id);
    }

    public List<Asignacion> listar() {
        return delegateAsignacion.listar();
    }

    public boolean agregarHorario(Horario horario) {
        return delegateAsignacion.agregarHorario(horario);
    }

    public List<Horario> listarHorarios(int id) {
        return delegateAsignacion.listarHorarios(id);
    }
    public List<Horario> listarHorarios(){return delegateAsignacion.listarHorarios();}
}
