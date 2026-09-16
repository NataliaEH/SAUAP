package mx.desarrollo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "unidad_de_aprendizaje")
public class UnidadAprendizaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ua", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "nombre_ua", nullable = false, length = 45)
    private String nombre;

    @Column(name = "horas_clase", nullable = false)
    private Integer horasClase;

    @Column(name = "horas_taller", nullable = false)
    private Integer horasTaller;

    @Column(name = "horas_lab", nullable = false)
    private Integer horasLaboratorio;

    @OneToMany(mappedBy = "unidadAprendizaje")
    private List<Asignacion> asignaciones;

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public Integer getHorasClase() {return horasClase;}

    public void setHorasClase(Integer horasClase){this.horasClase = horasClase;}

    public Integer getHorasTaller() {return horasTaller;}

    public void setHorasTaller(Integer horasTaller){this.horasTaller = horasTaller;}

    public Integer getHorasLaboratorio() {return horasLaboratorio;}

    public void setHorasLaboratorio(Integer horasLaboratorio){this.horasLaboratorio = horasLaboratorio;}

    public List<Asignacion> getAsignaciones() {return asignaciones;}

    public void setAsignaciones(List<Asignacion> asignaciones) {this.asignaciones = asignaciones;}
}