import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.List;

public class Jeu {
    private Heros hero;
    private Carte carte;
    private List<Combat> combats;
    private static final Logger logger = Logger.getLogger(Jeu.class.getName());

    public void demarrageJeu(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez votre nom : ");
        String heroName = scanner.nextLine();

        System.out.println("Choississez une classe : ");
        System.out.println("1. Barbare \n2. Mage\n3. Soigneur\n4. Assassin");
        int choixCapacite = scanner.nextInt();
        CapaciteSpeciale capacite = switch (choixCapacite){
            case 2 -> CapaciteSpeciale.MAGE;
            case 3 -> CapaciteSpeciale.SOIGNEUR;
            case 4 -> CapaciteSpeciale.ASSASSIN;
            default -> CapaciteSpeciale.BARBARE;
        };
        hero = new Heros(heroName, 150, (int)(Math.random() * 5), capacite);
        logger.info("Hero ajouté (" + hero.getName() + ") = PV / Puissance / Capacité : " + hero.getPv() + " / " + hero.getForceAttaque() + " / " + hero.getCapaciteSpeciale());

        System.out.println("Choississez un niveau de difficulté : ");
        System.out.println("1. Facile\n2. Moyen\n3. Difficile");
        int choixNiveau = scanner.nextInt();
        int longueurCarte = switch(choixNiveau){
            case 2 -> 10 + (int)(Math.random() * ((20 - 10) + 1));
            case 3 -> 20 + (int)(Math.random() * ((40 - 20) + 1));
            default -> 5 + (int)(Math.random() * ((10 - 5) + 1));
        };
        carte = new Carte("MAP", longueurCarte);
        logger.info("Carte ajouté (" + carte.getNom() + ") = Longueur : " + carte.getLongueur());

        this.genererListeCombats(longueurCarte);

        System.out.println("\nDébut de la partie !");

        carte.placerHero(hero);
        carte.placerCombat(this.combats);
        statusBar(hero);
        carte.afficherCarte();
    }

    private void statusBar(Heros h){
        System.out.println(h.barreVie());
    }

    private void genererListeCombats(int longueurCarte){
        this.combats = new ArrayList<Combat>();
        int nbCombat = (int) (Math.random() * ((longueurCarte - 1) + 1));
        for(int i = 0; i < nbCombat; i++){
            this.combats.add(new Combat(hero));
        }
    }
}
