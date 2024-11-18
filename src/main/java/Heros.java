public class Heros extends Personnage {
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
        return true; // fonction qui va afficher : "Veux tu utiliser la capacité spéciale de ton heros" => O (oui) ~ N (non) et va etre implémenté dans la surcharge de la méthode attaque
    }

    public void attaque(Personnage e) {
        if(choixJoueur()){
            // utilisation de sa capacité spéciale
        }else{
            super.attaque(e);
        }
    }
}
