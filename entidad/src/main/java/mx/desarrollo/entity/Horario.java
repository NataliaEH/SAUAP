package mx.desarrollo.entity;

package mx.desarrollo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "horario")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario", nullable = false)
    private Integer id;

    private Asignacion asignacion;

    @Size(max = 45)
    @NotNull
    @Column(name = "dia", nullable = false, length = 45)
    private String dia;

    @Size(max = 45)
    @NotNull
    @Column(name = "horaInicio", nullable = false, length = 45)
    private String horaInicio;

    @Size(max = 45)
    @NotNull
    @Column(name = "horaFinal", nullable = false, length = 45)
    private String horaFinal;

    @Size(max = 45)
    @NotNull
    @Column(name = "tipoClase", nullable = false, length = 45)
    private String tipoClase;

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public Asignacion getAsignacion() {return asignacion;}
    public Asignacion setAsignacion(Asignacion asignacion) {this.asignacion = asignacion;}

    public String getDia() {return dia;}
    public void setDia(String dia) {this.dia = dia;}

    public String getHoraInicio() {return horaInicio;}
    public String setHoraInicio(String horaInicio) {this.horaInicio = horaInicio;}

    public String getHoraFinal() {return horaFinal;}
    public String setHoraFinal(String horaFinal){this.horaFinal = horaFinal;}

    public String getTipoClase() {return tipoClase;}
    public void setTipoClase(String tipoClase) {this.tipoClase = tipoClase;}
}
