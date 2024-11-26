import java.util.Random;

public enum Type {
    BRIGAND,
    CATCHEUR,
    GANGSTER;

    // Méthode pour renvoyer un élément aléatoire
    public static Type aleatoire() {
        Random random = new Random();
        int index = random.nextInt(values().length);  // Génère un indice aléatoire
        return values()[index];  // Retourne l'élément correspondant à cet indice
    }
}
