package co.edu.uniandes.dse.parcial1.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import jakarta.transaction.Transactional;

import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.exceptions.*;

@DataJpaTest
@Transactional
@Import(MercanciaService.class)
public class MercanciaServiceTest {
    @Autowired
    MercanciaService mercanciaService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    @BeforeEach
    public void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from MercanciaEntity").executeUpdate();
    }

    private void insertData() {
        MercanciaEntity entity = factory.manufacturePojo(MercanciaEntity.class);
        entityManager.persist(entity);
    }

    @Test
    public void testCreateMercancia() throws IllegalOperationException {
        MercanciaEntity newEntity = factory.manufacturePojo(MercanciaEntity.class);
        newEntity.setNombre("mercancia");
        newEntity.setCodigoDeBarras("11111111");
        newEntity.setFechaDeRecepcion("11112024");
        newEntity.setCantidadDisponible(10);
        MercanciaEntity createdEntity = mercanciaService.createMercancia(newEntity);
        Assertions.assertNotNull(createdEntity);
        Assertions.assertEquals(newEntity.getNombre(), createdEntity.getNombre());
        Assertions.assertEquals(newEntity.getCodigoDeBarras(), createdEntity.getCodigoDeBarras());
        Assertions.assertEquals(newEntity.getFechaDeRecepcion(), createdEntity.getFechaDeRecepcion());
        Assertions.assertEquals(newEntity.getCantidadDisponible(), createdEntity.getCantidadDisponible());
    }

    @Test
    public void testCreateMercanciaWithNullName() {
        MercanciaEntity newEntity = factory.manufacturePojo(MercanciaEntity.class);

        entityManager.persist(newEntity);
        Assertions.assertThrows(IllegalOperationException.class, () -> {
            newEntity.setNombre(null);
            mercanciaService.createMercancia(newEntity);
        });
    }
}
