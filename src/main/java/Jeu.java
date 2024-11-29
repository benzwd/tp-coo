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
