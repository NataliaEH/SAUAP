package mx.desarrollo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "profesor")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Size(max = 45)
    @NotNull
    @Column(name = "apellido_pat", nullable = false, length = 45)
    private String apellidoPat;

    @Size(max = 45)
    @NotNull
    @Column(name = "apellido_mat", nullable = false, length = 45)
    private String apellidoMat;

    @Size(max = 45)
    @NotNull
    @Column(name = "rfc", nullable = false, length = 45)
    private String rfc;

    @OneToMany(mappedBy = "profesor")
    private List<Asignacion> asignaciones;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellidoPat() {return apellidoPat;}

    public void setApellidoPat(String apellidoPat){this.apellidoPat = apellidoPat;}

    public String getApellidoMat() {return apellidoMat;}

    public void setApellidoMat(String apellidoMat){this.apellidoMat = apellidoMat;}

    public String getRfc() {return rfc;}

    public void setRfc(String rfc) {this.rfc = rfc;}

    public List<Asignacion> getAsignaciones() {return asignaciones;}

    public void setAsignaciones(List<Asignacion> asignaciones) {this.asignaciones = asignaciones;}
}