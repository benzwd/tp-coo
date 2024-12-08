import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestJeu {

    @Test
    void testHeroMort(){
        Jeu jeu = new Jeu();
        Heros h = new Heros("Walid", TypeHeros.BARBARE);
        h.setPv(10);
        jeu.hero = h;
        h.setPv(0);
        assertTrue(h.estMort());
        jeu.finJeu();
    }

    @Test
    void testHeroAttaqueEnnemiAvecPVExactes() {
        Heros hero = new Heros("Arthur", TypeHeros.BARBARE);
        Ennemi ennemi = new Ennemi("Gobelin", (hero.getForceAttaque() * hero.getNombreAttaque()), 0, TypeEnnemi.BRIGAND);

        Combat combat = new Combat(hero);
        combat.addEnnemis(ennemi);
        hero.attaque(ennemi);

        assertEquals(0, ennemi.getPv());
        assertTrue(ennemi.estMort());

        Carte carte = new Carte("test", 5);
        carte.placerHero(hero);
        HashMap<Integer, Combat> positionsCombats = carte.getPositionsCombats();
        int positionCombat = 2;
        positionsCombats.put(positionCombat, combat);

        assertEquals(0, ennemi.getPv(), "Les PV de l'ennemi doivent être 0 après l'attaque.");
        assertTrue(ennemi.estMort(), "L'ennemi doit être mort.");

        carte.supprimerCombat(positionCombat);
        assertFalse(positionsCombats.containsKey(positionCombat), "Le combat doit être supprimé de la carte.");
    }

//    @Test
//    void testHeroSoigneurUtiliseCapaciteSpeciale(){
//        Heros h = new Heros("Benjamin", TypeHeros.SOIGNEUR);
//        TypeHeros.utilisationCapaciteSpeciale(h,null);
//        assertEquals(15, h.getPv());
//    }


}
