/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.desarrollo.persistence.integration;

import jakarta.persistence.EntityManager;
import mx.desarrollo.persistence.dao.AlumnoDAO;
import mx.desarrollo.persistence.dao.UsuarioDAO;
import mx.desarrollo.persistence.persistence.HibernateUtil;

/**
 *
 * @author total
 */
public class ServiceLocator {

    private static AlumnoDAO alumnoDAO;
    private static UsuarioDAO usuarioDAO;

    private static EntityManager getEntityManager(){
        return HibernateUtil.getEntityManager();
    }


    /**
     * se crea la instancia de profesorDAO si esta no existe
     */
    public static ProfesorDAO getInstanceProfesorDAO(){
        if(profesorDAO == null){
            profesorDAO = new ProfesorDAO(getEntityManager());
            return profesorDAO;
        } else{
            return profesorDAO;
        }
    }

    /**
     * se crea la instancia de horarioDAO si esta no existe
     */
    public static HorarioDAO getInstanceHorarioDAO(){
        if(horarioDAO == null){
            horarioDAO = new HorarioDAO(getEntityManager());
            return horarioDAO;
        } else{
            return horarioDAO;
        }
    }

    /**
     * se crea la instancia de unidad_de_aprendizajeDAO si esta no existe
     */
    public static Unidad_De_AprendizajeDAO getInstanceHorarioDAO() {
        if (unidad_de_aprendizajeDAO == null) {
            unidad_de_aprendizajeDAO = new Unidad_De_aprendizajeDAO(getEntityManager());
            return unidad_de_aprendizajeDAO;
        } else {
            return unidad_de_aprendizajeDAO;
        }
    }

    /**
     * se crea la instancia para asignacionDAO si esta no existe
     */
    public static AsignacionDAO getInstanceAsignacionDAO () {
        if (asignacionDAO == null) {
            asignacionDAO = new AsignacionDAO(getEntityManager());
            return asignacionDAO;
        } else {
            return asignacionDAO;
        }
    }

    /**
     * se crea la instancia para usuarioDAO si esta no existe
     */
    public static UsuarioDAO getInstanceUsuarioDAO () {
        if (usuarioDAO == null) {
            usuarioDAO = new UsuarioDAO(getEntityManager());
            return usuarioDAO;
        } else {
            return usuarioDAO;
        }
    }
}
