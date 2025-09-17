package co.edu.uniandes.dse.parcial1.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcial1.entities.UbicacionBodegaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(UbicacionBodegaService.class)
public class UbicacionBodegaServiceTest {
    @Autowired
    UbicacionBodegaService ubicacionService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    @BeforeEach
    public void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from UbicacionBodegaEntity").executeUpdate();
    }

    private void insertData() {
        UbicacionBodegaEntity entity = factory.manufacturePojo(UbicacionBodegaEntity.class);
        entityManager.persist(entity);
    }

    @Test
    public void testCreateUbicacion() throws IllegalOperationException {
        UbicacionBodegaEntity newEntity = factory.manufacturePojo(UbicacionBodegaEntity.class);
        newEntity.getNumeroDeEstante()
        UbicacionBodegaEntity createdEntity = ubicacionService.createUbicacion(newEntity);
        Assertions.assertNotNull(createdEntity);
        Assertions.assertEquals(newEntity.getNumeroDeEstante(), createdEntity.getNumeroDeEstante());
    }

    @Test
    public void testCreateUbicacionWithZeroEstante() {
        UbicacionBodegaEntity newEntity = factory.manufacturePojo(UbicacionBodegaEntity.class);

        entityManager.persist(newEntity);
        Assertions.assertThrows(IllegalOperationException.class, () -> {
            newEntity.setNumeroDeEstante(0);
            ubicacionService.createUbicacion(newEntity);
        });
    }
}
