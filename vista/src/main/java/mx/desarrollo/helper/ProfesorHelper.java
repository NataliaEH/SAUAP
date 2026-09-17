package mx.desarrollo.helper;

import mx.desarrollo.entity.Profesor;
import mx.desarrollo.integration.ServiceFacadeLocator;
import java.io.Serializable;
import java.util.List;

public class ProfesorHelper implements Serializable {
    public boolean registrar(String nombre, String apellidoPat, String apellidoMat, String rfc){
        return ServiceFacadeLocator.getInstanceFacadeProfesor().registrar(nombre, apellidoPat, apellidoMat, rfc);
    }
    public boolean validarRFC(String rfc){
        return ServiceFacadeLocator.getInstanceFacadeProfesor().validarRFC(rfc);
    }
    public Profesor buscar(int id){
        return ServiceFacadeLocator.getInstanceFacadeProfesor().buscar(id);
    }
    public List<Profesor> consultar(){
        return ServiceFacadeLocator.getInstanceFacadeProfesor().consultar();
    }
}
