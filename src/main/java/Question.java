import java.util.Scanner;

/**
 * Classe représentant une question de type QCM avec plusieurs choix et une bonne réponse.
 */
public class Question {
    // Attributs de la classe
    private final String question;
    private final String choix1;
    private final String choix2;
    private final String choix3;
    private final String choix4;
    private final String bonneReponse;
    /**
     * Constructeur pour initialiser une question avec ses choix et la bonne réponse.
     *
     * @param question     La question à poser.
     * @param choix1      La première choix.
     * @param choix2      La deuxième choix.
     * @param choix3      La troisième choix.
     * @param choix4      La quatrième choix.
     * @param bonneReponse Le numéro de la bonne réponse (1 à 4).
     */
    public Question(String question, String choix1, String choix2, String choix3, String choix4, String bonneReponse) {
        this.question = question;
        this.choix1 = choix1;
        this.choix2 = choix2;
        this.choix3 = choix3;
        this.choix4 = choix4;
        this.bonneReponse = bonneReponse;
    }

    /**
     * Affiche la question et les choix dans la console.
     */
    public void afficherQuestion() {
        System.out.println("^^ " + question + " ^^");
        System.out.println("-  1. " + choix1);
        System.out.println("-  2. " + choix2);
        System.out.println("-  3. " + choix3);
        System.out.println("-  4. " + choix4);
        System.out.print("Choisissez le numéro correspondant à la réponse correcte : ");
    }

    /**
     * Vérifie si le choix de l'utilisateur correspond à la bonne réponse.
     *
     * @param choix Le numéro du choix choisi par l'utilisateur.
     * @return true si le choix correspond à la bonne réponse, sinon false.
     */
    public boolean choixJoueurEstBonneReponse(int choix) {
        String reponseJoueur;
        switch (choix) {
            case 1:
                reponseJoueur = this.choix1;
            case 2:
                reponseJoueur = this.choix2;
            case 3:
                reponseJoueur = this.choix3;
            default:
                reponseJoueur = this.choix4;
        }
        if(reponseJoueur.equals(reponseJoueur)) System.out.println("BONNE REPONSE");
        else System.out.println("La bonne réponse est : " + bonneReponse);
        return reponseJoueur.equals(reponseJoueur) ;
    }

    /**
     * Méthode globale pour afficher la question, gérer l'entrée de l'utilisateur
     * et vérifier si la réponse est correcte.
     *
     * @return true si l'utilisateur a donné la bonne réponse, sinon false.
     */
    public boolean poserQuestion(Scanner scanner) {
        int choix;
        try {
            choix = -1;
            afficherQuestion();
            while (choix < 1 || choix > 4) {
                try {
                    choix = scanner.nextInt();
                    if (choix < 1 || choix > 4) {
                        System.out.print("Entrée invalide. Veuillez saisir un numéro entre 1 et 4 : ");
                    }
                } catch (Exception e) {
                    System.out.print("Entrée invalide. Veuillez saisir un numéro entre 1 et 4 : ");
                    scanner.nextLine();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return choixJoueurEstBonneReponse(choix);
    }
}