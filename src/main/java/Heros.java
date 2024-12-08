import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import net.datafaker.Faker;

/**
 * Classe représentant un héros dans le jeu.
 * Hérite de la classe abstraite {@link Personnage} et ajoute des fonctionnalités spécifiques aux héros,
 * notamment la gestion d'une capacité spéciale et des interactions avec la carte et les ennemis.
 */
public class Heros extends Personnage {
    private static final Logger logger = Logger.getLogger(Heros.class.getName());
    private static final Faker faker = new Faker();
    private static final int MULTIPLICATION_ATTAQUE = 5;
    private static final boolean activateQCM = false;

    /**
     * Capacité spéciale du héros.
     */
    private final TypeHeros typeHeros;

    /**
     * Indique si le héros a déjà utilisé sa capacité spéciale.
     */
    private boolean aUtiliseSaCapaciteSpeciale = false;

    /**
     * Position actuelle du héros sur la carte.
     */
    private int position = 1;

    /**
     * Constructeur pour créer un héros avec un nom, des points de vie, une force d'attaque, et une capacité spéciale.
     * 
     * @param name              Nom du héros.
     * @param typeHeros  Capacité spéciale du héros.
     */
    public Heros(String name, TypeHeros typeHeros) {
        super(name, typeHeros.getPv(), typeHeros.getForceAttaque(), faker.number().numberBetween(1, 5));
        this.typeHeros = typeHeros;
        
        logger.info("Héros ajouté (" + getName() + ") = PV / Puissance / Nombre d'attaques / Capacité : " +
                getPv() + " / " + getForceAttaque() + " / " + getNombreAttaque() + " / " + getTypeHeros());
    }

    /**
     * Récupère la capacité spéciale du héros.
     * 
     * @return La capacité spéciale du héros.
     */
    public TypeHeros getTypeHeros() {
        return this.typeHeros;
    }

    /**
     * Récupère l'état d'utilisation de la capacité spéciale.
     * 
     * @return `true` si la capacité spéciale a été utilisée, sinon `false`.
     */
    public boolean getAUtiliseSaCapaciteSpeciale() {
        return this.aUtiliseSaCapaciteSpeciale;
    }

    /**
     * Définit l'état d'utilisation de la capacité spéciale.
     * 
     * @param aUtiliseSaCapaciteSpeciale `true` pour indiquer que la capacité spéciale a été utilisée.
     */
    public void setAUtiliseSaCapaciteSpeciale(boolean aUtiliseSaCapaciteSpeciale) {
        this.aUtiliseSaCapaciteSpeciale = aUtiliseSaCapaciteSpeciale;
    }

    /**
     * Demande au joueur s'il souhaite utiliser la capacité spéciale de son héros.
     * 
     * @param joueurInput Scanner pour lire la réponse du joueur.
     * @return `true` si le joueur souhaite utiliser la capacité spéciale, sinon `false`.
     */
    private boolean choixJoueur(Scanner joueurInput) {
        System.out.println("Souhaites-tu utiliser ta capacité spéciale de ton héros ?");
        String reponse = joueurInput.nextLine();
        if (reponse.isEmpty()) return false;
        return reponse.charAt(0) == 'O' || reponse.charAt(0) == 'o';
    }

    /**
     * Réalise une attaque sur les ennemis.
     * Si la capacité spéciale du héros n'a pas encore été utilisée, demande au joueur s'il souhaite l'activer.
     * Sinon, attaque normalement le premier ennemi.
     * 
     * @param ennemis Liste des ennemis présents.
     */
    public void attaque(List<Ennemi> ennemis, ListeQuestions listeQuestions) {
        if (!aUtiliseSaCapaciteSpeciale && choixJoueur(new Scanner(System.in))) {
            setAUtiliseSaCapaciteSpeciale(true);
            TypeHeros.utilisationCapaciteSpeciale(this, ennemis);
        } else {
            if(!listeQuestions.estVide() && activateQCM){
                if(listeQuestions.getQuestionAleatoire().poserQuestion()){
                    for(int i = 0; i < MULTIPLICATION_ATTAQUE; i++) super.attaque(ennemis.getFirst());
                }
            }else{
                super.attaque(ennemis.getFirst());
            }           
        }
    }

    /**
     * Fait avancer le héros sur la carte.
     * Si le héros est déjà à la fin de la carte, un message d'avertissement est affiché.
     * 
     * @param carte Carte sur laquelle le héros avance.
     */
    public void avance(Carte carte) {
        if (position < carte.getLongueur() - 1) {
            carte.updatePosition(position, position + 1, getName());
            position++;
            logger.info(getName() + " avance à la position " + position + ".");
        } else {
            logger.warning(getName() + " est déjà à la fin de la carte !");
        }
    }

    /**
     * Récupère la position actuelle du héros sur la carte.
     * 
     * @return Position actuelle du héros.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Génère une représentation textuelle des statistiques du héros.
     * Affiche les points de vie, la force d'attaque et la disponibilité de la capacité spéciale.
     * 
     * @return Barre de statistiques sous forme de chaîne de caractères.
     */

    public String statsBar() {
        return super.statsBar().substring(0, super.statsBar().length() - 1) + " | CS " + (getAUtiliseSaCapaciteSpeciale() ? "indisponible" : "disponible") + ")";
    }
}
