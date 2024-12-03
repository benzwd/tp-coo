import java.util.Random;

/**
 * Enumération représentant les différents types d'ennemis dans le jeu.
 * Chaque type possède un nom associé. Cette enum fournit également des méthodes
 * pour afficher les types disponibles et en sélectionner un aléatoirement.
 */
public enum TypeEnnemi {
    /**
     * Type représentant un brigand.
     */
    BRIGAND("Brigand"),
    
    /**
     * Type représentant un catcheur.
     */
    CATCHEUR("Catcheur"),
    
    /**
     * Type représentant un gangster.
     */
    GANGSTER("Gangster");

    /**
     * Nom associé au type d'ennemi.
     */
    private String name;

    /**
     * Constructeur privé de l'énumération `Type`.
     * 
     * @param name Nom du type d'ennemi.
     */
    private TypeEnnemi(String name) {
        this.name = name;
    }

    /**
     * Récupère une représentation textuelle du type d'ennemi.
     * 
     * @return name Nom du type d'ennemi.
     */
    public String getName(){
        return name;
    }

    /**
     * Affiche les types disponibles avec leurs numéros correspondants.
     * Cette méthode parcourt toutes les valeurs de l'énumération et affiche
     * chaque type avec un numéro incrémental pour faciliter la sélection.
     */
    public static void afficherPossibilite() {
        for (int i = 0; i < TypeEnnemi.values().length; i++) {
            System.out.println((i + 1) + " : " + TypeEnnemi.values()[i]);
        }
    }

    /**
     * Sélectionne aléatoirement un type parmi les valeurs disponibles.
     * 
     * @return Un type aléatoire de l'énumération `Type`.
     */
    public static TypeEnnemi aleatoire() {
        Random random = new Random();
        int index = random.nextInt(values().length);
        return values()[index];
    }
}
