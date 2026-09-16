package mx.desarrollo.facade;


import mx.desarrollo.delegate.DelegateUA;


public class FacadeUA {

    private final DelegateUA delegateUA;

    public FacadeUA() {
        this.delegateUA = new DelegateUA();
    }

    public void registrar(String nombre, int horasClase, int horasTaller, int horasLab) {
        delegateUA.registrar(nombre, horasClase, horasTaller, horasLab);
    }

    public void modificar(int id) {
        delegateUA.modificar(id);
    }

    public void eliminar(int id) {
        delegateUA.eliminar(id);
    }

    public UnidadAprendizaje buscar(int id) {
        return delegateUA.buscar(id);
    }

    public List<UnidadAprendizaje> consultar() {
        return delegateUA.consultar();
    }
}