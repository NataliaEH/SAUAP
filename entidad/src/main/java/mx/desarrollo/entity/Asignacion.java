package mx.desarrollo.entity;

package mx.desarrollo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "asignacion")
public class Asignacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAsignacion", nullable = false)
    private Integer id;

    private Profesor profesor;

    private UnidadAprendizaje unidadAprendizaje;

    @OneToMany(mappedBy = "unidadAprendizaje")
    private List<Horario> horarios;

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public Profesor getProfesor() {return profesor;}

    public Profesor setProfesor(Profesor profesor){this.profesor = profesor;}

    public UnidadAprendizaje getUnidadAprendizaje(){return unidadAprendizaje;}

    public UnidadAprendizaje setUnidadAprendizaje(UnidadAprendizaje unidadAprendizaje) {this.unidadAprendizaje = unidadAprendizaje;}

    public List<Horario> getHorarios() {return horarios;}

    public List<Horario> setHorarios(List<Horario> horarios) {this.horarios = horarios;}
}
