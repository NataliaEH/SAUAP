package mx.desarrollo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;

@Entity
@Table(name = "horario")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_asignacion")
    private Asignacion asignacion;

    @Size(max = 45)
    @NotNull
    @Column(name = "dia", nullable = false, length = 45)
    private String dia;

    @NotNull
    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @NotNull
    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFinal;

    @NotNull
    @Column(name = "tipo_de_clase", nullable = false, length = 45)
    private String tipoClase;

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public Asignacion getAsignacion() {return asignacion;}
    public void setAsignacion(Asignacion asignacion) {this.asignacion = asignacion;}

    public String getDia() {return dia;}
    public void setDia(String dia) {this.dia = dia;}

    public LocalTime getHoraInicio() {return horaInicio;}
    public void setHoraInicio(LocalTime horaInicio) {this.horaInicio = horaInicio;}

    public LocalTime getHoraFinal() {return horaFinal;}
    public void setHoraFinal(LocalTime horaFinal){this.horaFinal = horaFinal;}

    public String getTipoClase() {return tipoClase;}
    public void setTipoClase(String tipoClase) {this.tipoClase = tipoClase;}
}
