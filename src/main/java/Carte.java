import java.util.Arrays;
import java.util.logging.*;
import java.util.HashMap;
import java.util.List;
import net.datafaker.Faker;

/**
 * Classe représentant une carte dans le jeu.
 * La carte contient des informations sur l'environnement, les positions des combats,
 * et les positions de départ et d'arrivée. Elle gère également l'affichage et les interactions
 * avec les personnages et les combats.
 */
public class Carte {
    /**
     * Logger pour enregistrer les événements liés à la carte.
     */
    private static final Logger logger = Logger.getLogger(Carte.class.getName());

    /**
     * Instance de la bibliothèque Faker pour générer des données aléatoires.
     */
    private final Faker faker = new Faker();

    /**
     * Nom de la carte.
     */
    private final String nom;

    /**
     * Longueur totale de la carte, incluant les cases de départ et d'arrivée.
     */
    private final int longueur;

    /**
     * Position de départ sur la carte.
     */
    private final int positionDepart;

    /**
     * Position d'arrivée sur la carte.
     */
    private final int positionArrivee;

    /**
     * Tableau représentant les cases de la carte.
     */
    private final String[] env;

    /**
     * Positions des combats sur la carte, associées à des instances de {@link Combat}.
     */
    private final HashMap<Integer, Combat> positionsCombats = new HashMap<>();

    /**
     * Constructeur pour initialiser une carte avec un nom et une longueur donnée.
     * La carte est automatiquement initialisée avec des cases vides, une position de départ,
     * et une position d'arrivée.
     * 
     * @param nom      Nom de la carte.
     * @param longueur Longueur de la carte (hors cases de départ et d'arrivée).
     */
    public Carte(String nom, int longueur) {
        this.nom = nom;
        this.longueur = longueur + 2; // Ajout des cases de départ et d'arrivée
        this.positionDepart = 0;
        this.positionArrivee = this.longueur - 1;
        this.env = new String[this.longueur];
        initCarte();
    }

    /**
     * Récupère le nom de la carte.
     * 
     * @return Le nom de la carte.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Récupère la longueur totale de la carte.
     * 
     * @return La longueur totale de la carte.
     */
    public int getLongueur() {
        return longueur;
    }

    /**
     * Récupère la position d'arrivée sur la carte.
     * 
     * @return La position d'arrivée.
     */
    public int getPositionArrivee() {
        return positionArrivee;
    }

    /**
     * Récupère les positions des combats sur la carte.
     * 
     * @return Une map associant les positions à des instances de {@link Combat}.
     */
    public HashMap<Integer, Combat> getPositionsCombats() {
        return positionsCombats;
    }

    /**
     * Initialise la carte en remplissant les cases avec des valeurs par défaut,
     * et en définissant les cases de départ et d'arrivée.
     */
    private void initCarte() {
        Arrays.fill(env, "_");
        env[positionDepart] = "[DEBUT]";
        env[positionArrivee] = "[FIN]";
        logger.info("Initialisation de la carte " + nom + " de longueur " + longueur + ".");
    }

    /**
     * Affiche la carte dans la console, montrant toutes les cases et leur contenu.
     */
    public void afficherCarte() {
        for (String caseCarte : env) {
            System.out.print(caseCarte);
        }
        System.out.println();
        logger.info("Affichage de la carte " + nom + " de longueur " + longueur + ".");
    }

    /**
     * Place un héros sur la carte à sa position actuelle.
     * 
     * @param h Le héros à placer.
     */
    public void placerHero(Heros h) {
        int positionHero = h.getPosition();
        if (positionHero < longueur) {
            env[positionHero] = "[" + h.getName() + "]";
        }
        logger.info("Hero " + h.getName() + " placé sur la carte à la position " + positionHero + ".");
    }

    /**
     * Place les combats sur des positions aléatoires de la carte.
     * Les combats ne peuvent être placés que sur des cases vides, et jusqu'à 10 tentatives
     * sont effectuées pour chaque combat.
     * 
     * @param combats Liste des combats à placer sur la carte.
     */
    public void placerCombat(List<Combat> combats) {
        for (Combat c : combats) {
            boolean placed = false;
            for (int tentative = 0; tentative < 10; tentative++) {
                int position = faker.number().numberBetween(positionDepart + 2, positionArrivee - 1);
                if (env[position].equals("_")) {
                    positionsCombats.put(position, c);
                    env[position] = "[!]";
                    logger.info("Groupe d'ennemis placé sur la carte à la position " + position + ".");
                    placed = true;
                    break;
                }
            }
        }
    }

    /**
     * Met à jour la position d'un élément sur la carte.
     * 
     * @param oldPos Ancienne position de l'élément.
     * @param newPos Nouvelle position de l'élément.
     * @param name   Nom de l'élément (affiché sur la carte).
     */
    public void updatePosition(int oldPos, int newPos, String name) {
        if (oldPos >= 0 && oldPos < longueur) {
            env[oldPos] = "_";
        }
        if (newPos >= 0 && newPos < longueur) {
            env[newPos] = "[" + name + "]";
        }
    }

    /**
     * Récupère le contenu d'une case à une position donnée.
     * 
     * @param position Position de la case.
     * @return Le contenu de la case (ou "_" si la position est invalide).
     */
    public String getCase(int position) {
        if (position >= 0 && position < longueur) {
            return env[position];
        }
        return "_";
    }
}
