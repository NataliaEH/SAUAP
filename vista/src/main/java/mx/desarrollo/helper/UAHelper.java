/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.desarrollo.helper;

import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.integration.ServiceFacadeLocator;
import java.io.Serializable;

public class UAHelper implements Serializable {
    public boolean registrar(String nombre, int horasClase, int horasTaller, int horasLab){
        return ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().registrar(nombre, horasClase, horasTaller, horasLab);
    }
    public boolean modificar(int id, String nombre, int horasClase, int horasTaller, int horasLab){
        return ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().modificar(id, nombre, horasClase, horasTaller, horasLab);
    }
    public boolean eliminar(int id){
        return ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().eliminar(id);
    }
    public UnidadAprendizaje buscar(int id){
        return ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().buscar(id);
    }
    public List<UnidadAprendizaje> consultar(){
        return ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().consultar();
    }