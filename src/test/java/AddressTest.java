import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ogm.model.Address;
import org.ogm.model.State;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.fest.assertions.Assertions.assertThat;

public class AddressTest {
    private static EntityManagerFactory entityManagerFactory;

    @BeforeClass
    public static void setUpEntityManagerFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("hikePu");
    }

    @AfterClass
    public static void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }

    @Test
    public void testThatObjectRelationIsTakenCareOf() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // add and persist a state
        State rajasthan = new State("Rajasthan");
        rajasthan.getAttractions().add("Jaipur");
        rajasthan.getAttractions().add("Jodhpur");
        entityManager.persist(rajasthan);

        // add multiple addresses
        Address address1 = null;
        for (int i = 0; i < 100; i++) {
            address1 = new Address("firstLine", "secondLine", "122004");
            address1.setState(rajasthan);
            entityManager.persist(address1);
        }


        entityManager.getTransaction().commit();
        entityManager.close();


        // get a new EM to make sure data is actually retrieved from the store and not Hibernate's internal cache
        entityManager = entityManagerFactory.createEntityManager();


        // load it back
        entityManager.getTransaction().begin();
        Address loadedAddress = entityManager.find(Address.class, address1.getId());
        assertThat(loadedAddress).isNotNull();
        assertThat(loadedAddress.getState()).isNotNull();
        assertThat(loadedAddress.getState().getName()).isEqualToIgnoringCase("rajasthan");

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}