package co.edu.uniandes.dse.parcial1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.repositories.MercanciaRepository;
import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;

import java.util.List;
import java.util.ArrayList;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MercanciaService {
    private static final String NOMBRE_ENTIDAD = "mercancia";

    @Autowired
    MercanciaRepository mercanciaRepository;

    @Transactional
    private List<String> getMercanciasBarras() {
        List<MercanciaEntity> mercancias = mercanciaRepository.findAll();
        List<String> barras = new ArrayList<>();
        for (MercanciaEntity me : mercancias) {
            barras.add(me.getCodigoDeBarras());
        }
        return barras;
    }

    @Transactional
    public MercanciaEntity createMercancia(MercanciaEntity mercancia) throws IllegalOperationException {
        if (mercancia.getCodigoDeBarras().isEmpty()) {
            throw new IllegalOperationException("Código de barras vacío");
        }
        if (getMercanciasBarras().contains(mercancia.getCodigoDeBarras())) {
            throw new IllegalOperationException("Código de barras duplicado");
        }
        if (mercancia.getNombre().isEmpty()) {
            throw new IllegalOperationException("Nombre vacío");
        }
        if (mercancia.getFechaDeRecepcion() == null) {
            throw new IllegalOperationException("Código de barras vacío");
        }
        return mercanciaRepository.save(mercancia);
    }
}
