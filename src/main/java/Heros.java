import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Heros extends Personnage {
    private static final Logger logger = Logger.getLogger(Heros.class.getName());
    private final int ID;
    private static int increment = 0;
    private CapaciteSpeciale capaciteSpeciale;
    private boolean aUtiliseSaCapaciteSpeciale = false;
    private int position = 1;

    public Heros(String name, int pv, int forceAttaque, CapaciteSpeciale capaciteSpeciale) {
        super(name, pv, forceAttaque, 5);
        this.ID = increment ++;
        this.capaciteSpeciale = capaciteSpeciale;
    }


    public int getID() {
        return this.ID;
    }

    public CapaciteSpeciale getCapaciteSpeciale() {
        return this.capaciteSpeciale;
    }

    public void setCapaciteSpeciale(CapaciteSpeciale capaciteSpeciale) {
        this.capaciteSpeciale = capaciteSpeciale;
    }

    public boolean isAUtiliseSaCapaciteSpeciale() {
        return this.aUtiliseSaCapaciteSpeciale;
    }

    public boolean getAUtiliseSaCapaciteSpeciale() {
        return this.aUtiliseSaCapaciteSpeciale;
    }

    public void setAUtiliseSaCapaciteSpeciale(boolean aUtiliseSaCapaciteSpeciale) {
        this.aUtiliseSaCapaciteSpeciale = aUtiliseSaCapaciteSpeciale;
    }

    private boolean choixJoueur(Scanner joueurInput){
        System.out.println("Souhaites-tu utiliser la capacité spéciale de ton héros ? (Oui ou Non)");

        String reponse = joueurInput.nextLine();

        if(reponse.isEmpty()) return false;

        return reponse.charAt(0) == 'O' || reponse.charAt(0) == 'o';
    }

    

    public void attaque(List<Ennemi> ennemis) {
        if(!this.aUtiliseSaCapaciteSpeciale && choixJoueur(new Scanner(System.in))){
            this.aUtiliseSaCapaciteSpeciale = true;
            CapaciteSpeciale.utilisationCapaciteSpeciale(this,ennemis);
        }else{
            super.attaque(ennemis.get(0));
        }
    }

    public void avance(Carte carte){
        if(position < carte.getLongueur() - 1){
            carte.updatePosition(position, position + 1, getName());
            position++;
            logger.info(getName() + " avance à la position " + position + ".");
        }else {
            logger.warning(getName() + " est déjà à la fin de la carte !");
        }
    }

    public int getPosition() {
        return position;
    }
}
