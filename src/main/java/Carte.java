import java.util.logging.*;
public class Carte {
    private static final Logger logger = Logger.getLogger(Carte.class.getName());
    private final String nom;
    private final int longueur;
    private final int positionDepart;
    private final int positionArrivee;
    private int positionHero = 1;
    private final String[] env;

    public Carte(String nom, int longueur) {
        this.nom = nom;
        this.longueur = longueur;
        this.positionDepart = 0;
        this.positionArrivee = longueur - 1;
        this.env = new String[longueur];
        initCarte();
    }

    public String getNom() {
        return nom;
    }

    public int getLongueur() {
        return longueur;
    }

    public int getPositionDepart() {
        return positionDepart;
    }

    public int getPositionArrivee() {
        return positionArrivee;
    }

    private void initCarte(){
        for(int i=0; i<longueur; i++){
            env[i] = "_";
        }
        env[positionDepart] = "[DÉBUT]";
        env[positionArrivee] = "[FIN]";
        logger.info("Initialisation de la carte " + nom + " de " + longueur + ".");
    }

    public void afficherCarte() {
        for(String caseCarte: env){
            System.out.print(caseCarte);
        }
        System.out.println();
        logger.info("Affichage de la carte " + nom + " de " + longueur + ".");
    }

    public void placeHero(Heros h){
        if(positionHero >= 0 && positionHero < longueur){
            env[positionHero] = "[" + h.getName() + "]";
        }
    }
}