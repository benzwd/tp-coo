import java.util.List;

public enum CapaciteSpeciale {
    BARBARE("Barbare", "Multiplication de sa force d'attaque x3"),
    MAGE("Mage", "Soigner ses PV à 25% de sa barre multiplier sa force d'attaque x2"),
    SOIGNEUR("Soigneur", "Soigner ses PV à 50%"),
    ASSASSIN("Assassin", "One shot tous ses ennemis");

    private String name;
    private String description;

    // Constructeur
    private CapaciteSpeciale(String name, String description) {
        this.name = name;
        this.description = description;
    }


    // Méthode pour afficher toutes les capacités spéciales sous forme de "cartes"
    public static void afficherPossibilites() {
        int i = 1;
        for (CapaciteSpeciale capacite : CapaciteSpeciale.values()) {
            System.out.println((i ++) + ".");
            capacite.afficherCarte();
        }
    }

    public void afficherCarte() {
        int largeurCarte = 45; // Nouvelle largeur de la carte
        int largeurTexte = largeurCarte - 2; // Largeur interne pour le texte (sans bordures)
    
        // Découper la description en respectant les mots
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
    
        // Affichage de la carte
        System.out.println("┌" + "─".repeat(largeurCarte) + "┐");
        System.out.printf("│ %-43s │%n", this.name); // Centrer le titre
        System.out.println("├" + "─".repeat(largeurCarte) + "┤");
    
        // Afficher la description découpée
        for (String ligneTexte : descriptionDecoupee.toString().split("\n")) {
            System.out.printf("│ %-43s │%n", ligneTexte);
        }
    
        System.out.println("└" + "─".repeat(largeurCarte) + "┘");
    }
    
    
    

    public static void utilisationCapaciteSpeciale(Heros heros,List<Ennemi> ennemis){
        if (heros.getCapaciteSpeciale() == BARBARE){
            ennemis.get(0).setPv(ennemis.get(0).getPv() - (3 * heros.getForceAttaque()));
        }else if(heros.getCapaciteSpeciale() == MAGE){
            heros.setPv((int)(heros.getPv() * 1.25));
            ennemis.get(0).setPv(ennemis.get(0).getPv() - (2 * heros.getForceAttaque()));
        }else if(heros.getCapaciteSpeciale() == SOIGNEUR){
            ennemis.get(0).setPv((int)(ennemis.get(0).getPv() * 1.5));
        }else{
            while (ennemis.isEmpty()) {
                ennemis.get(0).setPv(0);;
            }
        }
    }
}
