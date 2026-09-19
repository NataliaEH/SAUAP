/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.desarrollo.helper;

import mx.desarrollo.entity.Asignacion;
import mx.desarrollo.entity.Horario;
import mx.desarrollo.entity.Profesor;
import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.integration.ServiceFacadeLocator;
import java.io.Serializable;
import java.util.List;

public class AsignacionHelper implements Serializable {
    public boolean asignar(int idUA, int idProfesor, int grupo, List<Horario> horarios){
        return ServiceFacadeLocator.getInstanceFacadeAsignacion().asignar(idUA, idProfesor, grupo, horarios);
    }
    public boolean modificar(int id, UnidadAprendizaje ua, Profesor profesor, int grupo, List<Horario> horarios){
        return ServiceFacadeLocator.getInstanceFacadeAsignacion().modificar(id, ua, profesor, grupo, horarios);
    }
    public boolean eliminar(int id){
        return ServiceFacadeLocator.getInstanceFacadeAsignacion().eliminar(id);
    }
    public Asignacion buscar(int id){
        return ServiceFacadeLocator.getInstanceFacadeAsignacion().buscar(id);
    }
    public List<Asignacion> consultar(){
        return ServiceFacadeLocator.getInstanceFacadeAsignacion().listar();
    }
    public List<Horario> consultarHorarios(int id){
        return ServiceFacadeLocator.getInstanceFacadeAsignacion().listarHorarios(id);
    }
    public List<Horario> consultarHorarios(){return ServiceFacadeLocator.getInstanceFacadeAsignacion().listarHorarios();}
    public UnidadAprendizaje getUA(int id){return ServiceFacadeLocator.getInstanceFacadeUA().buscar(id);}

    public Profesor getProfesor(int id) {return ServiceFacadeLocator.getInstanceFacadeProfesor().buscar(id);    }
}
