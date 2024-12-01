import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.List;
import net.datafaker.Faker;

public class Jeu {
    private Heros hero;
    private Carte carte;
    private List<Combat> combats = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(Jeu.class.getName());
    Faker faker = new Faker();

    public void demarrageJeu(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez votre nom : ");
        String heroName = scanner.nextLine();
        Jeu.attendre(500);
        System.out.println("Cartes des différents héros : ");
        Jeu.attendre(500);
        CapaciteSpeciale.afficherPossibilites();
        Jeu.attendre(500);
        System.out.println("Fais ton choix : (1,2,...)");
        int choixCapacite = scanner.nextInt();
        Jeu.attendre(500);
        CapaciteSpeciale capacite = switch (choixCapacite){
            case 2 -> CapaciteSpeciale.MAGE;
            case 3 -> CapaciteSpeciale.SOIGNEUR;
            case 4 -> CapaciteSpeciale.ASSASSIN;
            default -> CapaciteSpeciale.BARBARE;
        };
        hero = new Heros(heroName, 150, 1, capacite);
        logger.info("Hero ajouté (" + hero.getName() + ") = PV / Puissance / Capacité : " + hero.getPv() + " / " + hero.getForceAttaque() + " / " + hero.getCapaciteSpeciale());

        System.out.println("Choississez un niveau de difficulté : ");
        System.out.println("1. Facile\n2. Moyen\n3. Difficile");
        int choixNiveau = scanner.nextInt();
        int longueurCarte = switch(choixNiveau){
            case 2 -> faker.number().numberBetween(10, 20);
            case 3 -> faker.number().numberBetween(20, 40);
            default -> faker.number().numberBetween(5, 10);
        };
        carte = new Carte(faker.streetFighter().stages(), longueurCarte);
        System.err.println("Longueur carte : " + longueurCarte);
        logger.info("Carte ajouté (" + carte.getNom() + ") = Longueur : " + carte.getLongueur());

        this.genererListeCombats(longueurCarte);

        System.out.println("\nDébut de la partie !");

        carte.placerHero(hero);
        carte.placerCombat(combats);
        statusBar(hero);
        carte.afficherCarte();
    }

    private void statusBar(Heros h){
        System.out.println(h.statsBar());
    }

    private void genererListeCombats(int longueurCarte){
        int nbCombat = faker.number().numberBetween(1, longueurCarte);
        System.err.println("Nb combat : " + nbCombat);
        for(int i = 0; i < nbCombat; i++){
            combats.add(new Combat(hero));
        }
    }

    public void jouerTour(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Que voulez-vous faire ?");
        System.out.println("1. Avancer\n2. Quitter");
        int choix = scanner.nextInt();
        switch (choix){
            case 1 -> {
                if(carte.getCase(hero.getPosition() + 1).equals("[!]")){
                    System.out.println("Vous avez rencontré un groupe d'ennemis !");
                    Jeu.attendre(250);
                    System.out.println("Voulez-vous combattre ou abandonner ?");
                    Jeu.attendre(250);
                    System.out.println("1. Combattre\n2. Abandonner");
                    int choixCombat = scanner.nextInt();
                    Jeu.attendre(250);
                    if(choixCombat == 1){
                        carte.getPositionsCombats().get(hero.getPosition() + 1).derouleCombat();
                    }else {
                        hero.setPv(0);
                        finJeu();
                    }
                }else{
                    hero.avance(carte);
                }
            }
            case 2 -> {
                hero.setPv(0);
                finJeu();
            }
            default -> System.out.println("Action invalide.");
        }
        carte.afficherCarte();
        if(hero.getPosition() == carte.getPositionArrivee() - 1){
            finJeu();
        }
    }

    public void finJeu(){
        if(hero.estMort()){
            System.out.println("Défaite. Le héro est mort !");
            logger.info("Héro mort. Défaite.");
        }else{
            System.out.println("Victoire. Les ennemis ont été vaincus !");
            logger.info("Ennemis vaincus. Victoire.");
        }
        System.out.println("Merci d'avoir joué !");
        System.exit(0);
    }

    public static void attendre(int millisecondes) {
        try {
            Thread.sleep(millisecondes);
        } catch (InterruptedException e) {
            System.err.println("Le thread a été interrompu : " + e.getMessage());
        }
    }
}
