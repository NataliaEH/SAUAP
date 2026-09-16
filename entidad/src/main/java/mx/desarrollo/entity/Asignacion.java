package mx.desarrollo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "asignacion")
public class Asignacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignacion", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;

    @ManyToOne
    @JoinColumn(name = "id_ua")
    private UnidadAprendizaje unidadAprendizaje;

    @OneToMany(mappedBy = "asignacion")
    private List<Horario> horarios;

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public Profesor getProfesor() {return profesor;}

    public void setProfesor(Profesor profesor){this.profesor = profesor;}

    public UnidadAprendizaje getUnidadAprendizaje(){return unidadAprendizaje;}

    public void setUnidadAprendizaje(UnidadAprendizaje unidadAprendizaje) {this.unidadAprendizaje = unidadAprendizaje;}

    public List<Horario> getHorarios() {return horarios;}

    public void setHorarios(List<Horario> horarios) {this.horarios = horarios;}
}
