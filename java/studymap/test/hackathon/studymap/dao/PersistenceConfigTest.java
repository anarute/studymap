package hackathon.studymap.dao;

import hackathon.studymap.model.StudyArea;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author murilodemoraestuvani
 */
public class PersistenceConfigTest {

    public PersistenceConfigTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void hello() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("studymapPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("StudyArea.findAll");
        List<StudyArea> sas = query.getResultList();
        for (StudyArea sa : sas) {
            System.out.println(sa.toString());
        }
        em.close();
        emf.close();
    }
}
