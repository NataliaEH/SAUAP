package mx.desarrollo.persistence.dao;

import jakarta.persistence.EntityManager;
import mx.desarrollo.entity.Horario;
import mx.desarrollo.persistence.persistence.AbstractDAO;

import java.util.List;

public class HorarioDAO extends AbstractDAO<Horario> {
    private final EntityManager entityManager;

    public HorarioDAO(EntityManager em) {
        super(Horario.class);
        this.entityManager = em;
    }

    public List<Horario> obtenerTodos(){
        return entityManager
                .createQuery("SELECT u FROM Horario u", Horario.class)
                .getResultList();
    }

    //pUBLIC INT BUSCAR POR ID

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
