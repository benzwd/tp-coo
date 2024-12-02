import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.List;
import net.datafaker.Faker;

/**
 * Classe représentant le jeu "Beat them all".
 * Cette classe contient la logique principale pour le démarrage, le déroulement et la fin de la partie.
 * Elle gère également la création de la carte, des combats, et les interactions du joueur.
 */
public class Jeu {
    private Heros hero;
    private Carte carte;
    private List<Combat> combats = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(Jeu.class.getName());
    Faker faker = new Faker();

    /**
     * Démarre le jeu en initialisant le héros, la carte, et les combats.
     * Cette méthode guide le joueur dans la sélection d'un héros et du niveau de difficulté,
     * puis génère la carte et place les combats avant de commencer la partie.
     */
    public void demarrageJeu() {
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

        CapaciteSpeciale capacite = switch (choixCapacite) {
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
        int longueurCarte = switch (choixNiveau) {
            case 2 -> faker.number().numberBetween(10, 20);
            case 3 -> faker.number().numberBetween(20, 40);
            default -> faker.number().numberBetween(5, 10);
        };

        carte = new Carte(faker.streetFighter().stages(), longueurCarte);
        System.err.println("Longueur carte : " + longueurCarte);
        logger.info("Carte ajoutée (" + carte.getNom() + ") = Longueur : " + carte.getLongueur());

        this.genererListeCombats(longueurCarte);

        System.out.println("\nDébut de la partie !");

        carte.placerHero(hero);
        carte.placerCombat(combats);
        statusBar(hero);
        carte.afficherCarte();
    }

    /**
     * Affiche une barre de statistiques du héros.
     * 
     * @param h Le héros dont les statistiques doivent être affichées.
     */
    private void statusBar(Heros h) {
        System.out.println(h.statsBar());
    }

    /**
     * Génère une liste de combats en fonction de la longueur de la carte.
     * 
     * @param longueurCarte Longueur de la carte utilisée pour déterminer le nombre de combats.
     */
    private void genererListeCombats(int longueurCarte) {
        int nbCombat = faker.number().numberBetween(1, longueurCarte);
        System.err.println("Nb combat : " + nbCombat);
        for (int i = 0; i < nbCombat; i++) {
            combats.add(new Combat(hero));
        }
    }

    /**
     * Exécute un tour de jeu.
     * Le joueur peut choisir d'avancer ou de quitter le jeu. Si le joueur rencontre des ennemis,
     * il peut choisir de combattre ou de fuir (si il fuit la partie est terminée).
     */
    public void jouerTour() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Que voulez-vous faire ?");
        System.out.println("1. Avancer\n2. Quitter");
        int choix = scanner.nextInt();

        switch (choix) {
            case 1 -> {
                if (carte.getCase(hero.getPosition() + 1).equals("[!]")) {
                    System.out.println("Vous avez rencontré un groupe d'ennemis !");
                    Jeu.attendre(250);
                    System.out.println("Voulez-vous combattre ou abandonner ?");
                    Jeu.attendre(250);
                    System.out.println("1. Combattre\n2. Abandonner");
                    int choixCombat = scanner.nextInt();
                    Jeu.attendre(250);
                    if (choixCombat == 1) {
                        carte.getPositionsCombats().get(hero.getPosition() + 1).derouleCombat();
                    } else {
                        hero.setPv(0);
                        finJeu();
                    }
                } else {
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

        if (hero.getPosition() == carte.getPositionArrivee() - 1) {
            finJeu();
        }
    }

    /**
     * Gère la fin de la partie.
     * Affiche un message de victoire si les ennemis ont été vaincus ou de défaite si le héros est mort.
     * Le programme se termine après l'affichage.
     */
    public void finJeu() {
        if (hero.estMort()) {
            System.out.println("Défaite. Le héros est mort !");
            logger.info("Héros mort. Défaite.");
        } else {
            System.out.println("Victoire. Les ennemis ont été vaincus !");
            logger.info("Ennemis vaincus. Victoire.");
        }
        System.out.println("Merci d'avoir joué !");
        System.exit(0);
    }

    /**
     * Met en pause l'exécution du jeu pour un nombre donné de millisecondes.
     * 
     * @param millisecondes Durée de la pause en millisecondes.
     */
    public static void attendre(int millisecondes) {
        try {
            Thread.sleep(millisecondes);
        } catch (InterruptedException e) {
            System.err.println("Le thread a été interrompu : " + e.getMessage());
        }
    }
}
