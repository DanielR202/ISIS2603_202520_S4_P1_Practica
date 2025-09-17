package co.edu.uniandes.dse.parcial1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

import java.util.Date;

@Data
@Entity
public class MercanciaEntity extends BaseEntity{
    private String nombre;
    private String codigoDeBarras;
    private Date fechaDeRecepcion;
    private Integer cantidadDisponible;

    @PodamExclude
    @ManyToOne
    private UbicacionBodegaEntity ubicacion;
}
