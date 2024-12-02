import java.util.List;

/**
 * Enumération représentant les différentes capacités spéciales disponibles pour les héros.
 * Chaque capacité spéciale possède un nom et une description, et peut être utilisée par un héros
 * pour affecter les ennemis ou modifier ses propres statistiques.
 */
public enum CapaciteSpeciale {
    /**
     * Capacité "Barbare" : Multiplie la force d'attaque par 3.
     */
    BARBARE("Barbare", "Multiplication de sa force d'attaque x3"),

    /**
     * Capacité "Mage" : Soigne les PV à 25% de leur valeur de base et multiplie la force d'attaque par 2 pendant un tour.
     */
    MAGE("Mage", "Soigner ses PV à 25% de sa barre multiplier sa force d'attaque x2"),

    /**
     * Capacité "Soigneur" : Soigne les PV à 50% de leur valeur actuelle.
     */
    SOIGNEUR("Soigneur", "Soigner ses PV à 50%"),

    /**
     * Capacité "Assassin" : Élimine tous les ennemis en un coup.
     */
    ASSASSIN("Assassin", "One shot tous ses ennemis");

    /**
     * Nom de la capacité spéciale.
     */
    private final String name;

    /**
     * Description de la capacité spéciale.
     */
    private final String description;

    /**
     * Constructeur privé pour initialiser une capacité spéciale avec un nom et une description.
     * 
     * @param name        Nom de la capacité spéciale.
     * @param description Description de la capacité spéciale.
     */
    private CapaciteSpeciale(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Affiche toutes les capacités spéciales sous forme de cartes.
     * Chaque capacité est affichée avec un numéro incrémental et sa carte associée.
     */
    public static void afficherPossibilites() {
        int i = 1;
        for (CapaciteSpeciale capacite : CapaciteSpeciale.values()) {
            System.out.println((i++) + ".");
            capacite.afficherCarte();
        }
    }

    /**
     * Affiche une carte visuelle représentant la capacité spéciale.
     * La carte inclut le nom de la capacité et une description.
     */
    public void afficherCarte() {
        int largeurCarte = 45; // Largeur fixe de la carte
        int largeurTexte = largeurCarte - 2; // Largeur interne (sans bordures)

        // Découper la description pour respecter la largeur
        StringBuilder descriptionDecoupee = new StringBuilder();
        String[] mots = description.split(" ");
        StringBuilder ligne = new StringBuilder();

        for (String mot : mots) {
            if (ligne.length() + mot.length() + 1 > largeurTexte) { // +1 pour l'espace
                descriptionDecoupee.append(ligne).append("\n");
                ligne = new StringBuilder();
            }
            if (ligne.length() > 0) {
                ligne.append(" ");
            }
            ligne.append(mot);
        }
        descriptionDecoupee.append(ligne); // Ajouter la dernière ligne

        // Afficher la carte
        System.out.println("┌" + "─".repeat(largeurCarte) + "┐");
        System.out.printf("│ %-43s │%n", this.name);
        System.out.println("├" + "─".repeat(largeurCarte) + "┤");

        // Afficher la description formatée
        for (String ligneTexte : descriptionDecoupee.toString().split("\n")) {
            System.out.printf("│ %-43s │%n", ligneTexte);
        }

        System.out.println("└" + "─".repeat(largeurCarte) + "┘");
    }

    /**
     * Utilise la capacité spéciale d'un héros sur un ou plusieurs ennemis.
     * Les effets dépendent de la capacité spéciale sélectionnée :
     * <ul>
     * <li><b>Barbare :</b> Multiplie la force d'attaque par 3 et inflige des dégâts à un ennemi.</li>
     * <li><b>Mage :</b> Soigne les PV du héros à 25% et double sa force d'attaque.</li>
     * <li><b>Soigneur :</b> Soigne les PV de l'ennemi à 50% (erreur possible dans la logique).</li>
     * <li><b>Assassin :</b> Élimine tous les ennemis d'un seul coup.</li>
     * </ul>
     * 
     * @param heros   Le héros utilisant la capacité spéciale.
     * @param ennemis Liste des ennemis affectés par la capacité.
     */
    public static void utilisationCapaciteSpeciale(Heros heros, List<Ennemi> ennemis) {
        if (heros.getCapaciteSpeciale() == BARBARE) {
            ennemis.get(0).setPv(ennemis.get(0).getPv() - (3 * heros.getForceAttaque()));
        } else if (heros.getCapaciteSpeciale() == MAGE) {
            heros.setPv((int) (heros.getPv() * 1.25));
            ennemis.get(0).setPv(ennemis.get(0).getPv() - (2 * heros.getForceAttaque()));
        } else if (heros.getCapaciteSpeciale() == SOIGNEUR) {
            ennemis.get(0).setPv((int) (ennemis.get(0).getPv() * 1.5)); // Semble incohérent (erreur possible).
        } else { // Assassin
            while (!ennemis.isEmpty()) {
                ennemis.get(0).setPv(0);
                ennemis.remove(0);
            }
        }
    }
}
