package co.edu.uniandes.dse.parcial1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.UbicacionBodegaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.UbicacionBodegaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UbicacionBodegaService {
    private static final String NOMBRE_ENTIDAD = "ubicacion";

    @Autowired
    UbicacionBodegaRepository ubicacionBodegaRepository;

    @Transactional
    public List<UbicacionBodegaEntity> getUbicaciones() {
        return ubicacionBodegaRepository.findAll();
    }

    @Transactional
    public UbicacionBodegaEntity createUbicacion(UbicacionBodegaEntity ubicacion) throws IllegalOperationException {
        if (ubicacion.getNumeroDeEstante() <= 0) {
            throw new IllegalOperationException("Código de barras vacío");
        }
        return ubicacionBodegaRepository.save(ubicacion);
    }
}
