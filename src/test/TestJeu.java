import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.beans.Transient;
import java.util.ArrayList;

import net.datafaker.providers.videogame.HeroesOfTheStorm;

public class TestJeu {

    @Test 
    void testHerosMort(){
        Heros h = new Heros("Walid", 5, 10, CapaciteSpeciale.BARBARE);
        Ennemi e = new Ennemi("Mechant", 10, 1, Type.BRIGAND);

        for(int i = 0; i < 5; i++){
            assertTrue(!h.estMort());
            e.attaque(h);
        }
        assertTrue(h.estMort());
    }
    
    @Test 
    void testHerosSoigneurUtiliseCapaciteSpeciale(){
        Heros h = new Heros("Benjamin", 5, 10, CapaciteSpeciale.SOIGNEUR);
        CapaciteSpeciale.utilisationCapaciteSpeciale(h,null);
        assertEquals(15, h.getPv());
    }
}
