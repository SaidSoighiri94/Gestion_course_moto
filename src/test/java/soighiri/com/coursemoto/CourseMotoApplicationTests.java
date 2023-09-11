package soighiri.com.coursemoto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import soighiri.com.coursemoto.model.Circuit;
import soighiri.com.coursemoto.repository.CircuitRepository;

import java.util.List;

@SpringBootTest
class CourseMotoApplicationTests {

    @Autowired
    private CircuitRepository circuitRepository;
    // puis on injecte la @Test

    // Teste de la methode createCircuit
    @Test
    public void testCreateCircuit(){
        Circuit circuit = new Circuit("Circuit de CSFX ","Ambatomena, Fianarantsoa",5.400);
        Circuit savedCircuit=circuitRepository.save(circuit);
        Assertions.assertEquals("Circuit de CSFX ",savedCircuit.getNomCircuit());
    }
    // Test de la methode findCircuitById(idCircuit)
    @Test
    public void testFindCircuit(){
        Circuit circuit = circuitRepository.findById(1L).get();
        System.out.println(circuit);
    }

    //Test pour la methode UpdateCircuit permettant de mettre a jour un circuit
    @Test
    public void testUpdateCircuit(){
        Circuit circuit = circuitRepository.findById(1L).get();
        circuit.setAdresseCircuit("Imertsiatosika, Antananarivo");
        circuit.setNomCircuit("Circuit Imerka ");
        circuitRepository.save(circuit);
    }

    // Test permettant de supprimer un circuit avec la methode testDeleteCircuit()
    @Test
    public void testDeleteCircuit(){
        circuitRepository.deleteById(7L);
    }

    //Test de la methode permettand de lister tous les circuits avec findAllCircuits
    @Test
    public void testListerTousCircuits(){

        // on utilise la generité avec un list
        List<Circuit> circuits = circuitRepository.findAll();

            // Puis on fait un foreach sur le circuit
        for (Circuit circuit : circuits
             ) {
            System.out.println(circuit);
        }
    }

    void contextLoads() {
    }


}