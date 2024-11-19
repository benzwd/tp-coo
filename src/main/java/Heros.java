import java.util.Scanner;
import java.util.logging.Logger;

public class Heros extends Personnage {
    private static final Logger logger = Logger.getLogger(Heros.class.getName());
    private final int ID;
    private static int increment = 0;
    private CapaciteSpeciale capaciteSpeciale;
    private boolean aUtiliseSaCapaciteSpeciale;



    public Heros(String name, int pv, int forceAttaque, CapaciteSpeciale capaciteSpeciale) {
        super(name, pv, forceAttaque, 5);
        this.ID = increment ++;
        this.capaciteSpeciale = capaciteSpeciale;
        this.aUtiliseSaCapaciteSpeciale = false;
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

    public boolean choixJoueur(){
        Scanner entreJoueur = new Scanner(System.in);

        System.out.println("Souhaites-tu utiliser la capacité spéciale de ton héros ? (Oui ou Non)");

        String reponse = entreJoueur.nextLine();
        entreJoueur.close();
        return reponse.charAt(0) == 'O' || reponse.charAt(0) == 'o';
    }

    public void utilisationCapaciteSpeciale(){
        // proposition : faire une méthode dans l'enum CapaciteSpeciale qui boucle en fonction des capacité, cad si il esquive pendant 2 tours les balles, il doit attaquer deux fois, si il se soigne on augmente ses pv, si il oneshot ses ennemis on fait un while il est pas mort
    }

    public void attaque(Personnage e) {
        if(this.aUtiliseSaCapaciteSpeciale && choixJoueur()){
            this.aUtiliseSaCapaciteSpeciale = true;
            // utilisation de la capacité spéciale
        }else{
            super.attaque(e);
        }
    }
}
