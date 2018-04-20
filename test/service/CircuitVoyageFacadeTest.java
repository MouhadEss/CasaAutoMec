/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.CircuitVoyage;
import bean.Ville;
import bean.Voyage;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author IlyassElfouih
 */
public class CircuitVoyageFacadeTest {
    
    public CircuitVoyageFacadeTest() {
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

    /**
     * Test of getUniqueResult method, of class CircuitVoyageFacade.
     */
//    @Test
//    public void testGetUniqueResult() throws Exception {
//        System.out.println("getUniqueResult");
//        String query = "";
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        CircuitVoyageFacade instance = (CircuitVoyageFacade)container.getContext().lookup("java:global/classes/CircuitVoyageFacade");
//        CircuitVoyage expResult = null;
//        CircuitVoyage result = instance.getUniqueResult(query);
//        assertEquals(expResult, result);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMultipleResult method, of class CircuitVoyageFacade.
//     */
//    @Test
//    public void testGetMultipleResult() throws Exception {
//        System.out.println("getMultipleResult");
//        String query = "";
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        CircuitVoyageFacade instance = (CircuitVoyageFacade)container.getContext().lookup("java:global/classes/CircuitVoyageFacade");
//        List<CircuitVoyage> expResult = null;
//        List<CircuitVoyage> result = instance.getMultipleResult(query);
//        assertEquals(expResult, result);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of create method, of class CircuitVoyageFacade.
//     */
//    @Test
//    public void testCreate() throws Exception {
//        System.out.println("create");
//        CircuitVoyage entity = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        CircuitVoyageFacade instance = (CircuitVoyageFacade)container.getContext().lookup("java:global/classes/CircuitVoyageFacade");
//        instance.create(entity);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of edit method, of class CircuitVoyageFacade.
//     */
//    @Test
//    public void testEdit() throws Exception {
//        System.out.println("edit");
//        CircuitVoyage entity = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        CircuitVoyageFacade instance = (CircuitVoyageFacade)container.getContext().lookup("java:global/classes/CircuitVoyageFacade");
//        instance.edit(entity);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of remove method, of class CircuitVoyageFacade.
//     */
//    @Test
//    public void testRemove() throws Exception {
//        System.out.println("remove");
//        CircuitVoyage entity = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        CircuitVoyageFacade instance = (CircuitVoyageFacade)container.getContext().lookup("java:global/classes/CircuitVoyageFacade");
//        instance.remove(entity);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of find method, of class CircuitVoyageFacade.
//     */
//    @Test
//    public void testFind() throws Exception {
//        System.out.println("find");
//        Object id = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        CircuitVoyageFacade instance = (CircuitVoyageFacade)container.getContext().lookup("java:global/classes/CircuitVoyageFacade");
//        CircuitVoyage expResult = null;
//        CircuitVoyage result = instance.find(id);
//        assertEquals(expResult, result);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findAll method, of class CircuitVoyageFacade.
//     */
//    @Test
//    public void testFindAll() throws Exception {
//        System.out.println("findAll");
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        CircuitVoyageFacade instance = (CircuitVoyageFacade)container.getContext().lookup("java:global/classes/CircuitVoyageFacade");
//        List<CircuitVoyage> expResult = null;
//        List<CircuitVoyage> result = instance.findAll();
//        assertEquals(expResult, result);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findRange method, of class CircuitVoyageFacade.
//     */
//    @Test
//    public void testFindRange() throws Exception {
//        System.out.println("findRange");
//        int[] range = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        CircuitVoyageFacade instance = (CircuitVoyageFacade)container.getContext().lookup("java:global/classes/CircuitVoyageFacade");
//        List<CircuitVoyage> expResult = null;
//        List<CircuitVoyage> result = instance.findRange(range);
//        assertEquals(expResult, result);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of count method, of class CircuitVoyageFacade.
//     */
//    @Test
//    public void testCount() throws Exception {
//        System.out.println("count");
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        CircuitVoyageFacade instance = (CircuitVoyageFacade)container.getContext().lookup("java:global/classes/CircuitVoyageFacade");
//        int expResult = 0;
//        int result = instance.count();
//        assertEquals(expResult, result);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateId method, of class CircuitVoyageFacade.
//     */
//    @Test
//    public void testGenerateId() throws Exception {
//        System.out.println("generateId");
//        String beanName = "";
//        String idName = "";
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        CircuitVoyageFacade instance = (CircuitVoyageFacade)container.getContext().lookup("java:global/classes/CircuitVoyageFacade");
//        Long expResult = null;
//        Long result = instance.generateId(beanName, idName);
//        assertEquals(expResult, result);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getVilleCircuitByVoyage method, of class CircuitVoyageFacade.
     */
    @Test
    public void testGetVilleCircuitByVoyage() throws Exception {
        System.out.println("getVilleCircuitByVoyage");
        Voyage voyage = new Voyage(new Long(1));
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CircuitVoyageFacade instance = (CircuitVoyageFacade)container.getContext().lookup("java:global/classes/CircuitVoyageFacade");
        List<Ville> expResult = null;
        List<Ville> result = instance.getVilleCircuitByVoyage(voyage);
        assertEquals(expResult, result);
        System.out.println(result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
