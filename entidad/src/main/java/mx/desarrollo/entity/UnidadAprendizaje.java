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
    private int horasClase;

    @Column(name = "horas_taller", nullable = false)
    private int horasTaller;

    @Column(name = "horas_lab", nullable = false)
    private int horasLaboratorio;

    @OneToMany(mappedBy = "unidadAprendizaje")
    private List<Asignacion> asignaciones;

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public int getHorasClase() {return horasClase;}
    public void setHorasClase(int horasClase){this.horasClase = horasClase;}

    public int getHorasTaller() {return horasTaller;}
    public void setHorasTaller(int horasTaller){this.horasTaller = horasTaller;}

    public int getHorasLaboratorio() {return horasLaboratorio;}
    public void setHorasLaboratorio(int horasLaboratorio){this.horasLaboratorio = horasLaboratorio;}

    public List<Asignacion> getAsignaciones() {return asignaciones;}
    public void setAsignaciones(List<Asignacion> asignaciones) {this.asignaciones = asignaciones;}
}