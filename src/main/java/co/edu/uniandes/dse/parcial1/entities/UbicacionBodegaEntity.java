package co.edu.uniandes.dse.parcial1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;

import java.util.List;
import java.util.ArrayList;

@Data
@Entity
public class UbicacionBodegaEntity extends BaseEntity{
    private Integer numeroDeEstante;
    private List<MercanciaEntity> CanastaMercancia;
    private Integer pesoMaximo;

    @PodamExclude
    @OneToMany(mappedBy = "ubicacion", cascade = jakarta.persistence.CascadeType.PERSIST, orphanRemoval = true)
    private List<MercanciaEntity> mercancias = new ArrayList<>();
}
