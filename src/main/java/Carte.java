import java.util.Arrays;
import java.util.logging.*;
import java.util.HashMap;
import java.util.List;
import net.datafaker.Faker;

public class Carte {
    private static final Logger logger = Logger.getLogger(Carte.class.getName());
    Faker faker = new Faker();

    private final String nom;
    private final int longueur;
    private final int positionDepart;
    private final int positionArrivee;
    private final String[] env;
    private HashMap<Integer, Combat> positionsCombats = new HashMap<>();

    public Carte(String nom, int longueur) {
        this.nom = nom;
        this.longueur = longueur + 2;
        this.positionDepart = 0;
        this.positionArrivee = this.longueur - 1;
        this.env = new String[this.longueur];
        initCarte();
    }

    public String getNom() { return nom;}
    public int getLongueur() { return longueur;}
    public int getPositionArrivee() { return positionArrivee;}
    public HashMap<Integer, Combat> getPositionsCombats(){ return positionsCombats;}

    private void initCarte(){
        Arrays.fill(env, "_");
        env[positionDepart] = "[DEBUT]";
        env[positionArrivee] = "[FIN]";
        logger.info("Initialisation de la carte " + nom + " de longueur" + longueur + ".");
    }

    public void afficherCarte() {
        for(String caseCarte: env){
            System.out.print(caseCarte);
        }
        System.out.println();
        logger.info("Affichage de la carte " + nom + " de longueur " + longueur + ".");
    }

    public void placerHero(Heros h){
        int positionHero = h.getPosition();
        if(positionHero < longueur){
            env[positionHero] = "[" + h.getName() + "]";
        }
        logger.info("Hero " + h.getName() + " placé sur la carte à la position " + positionHero + ".");
    }

    public void placerCombat(List<Combat> combats){
        for(Combat c : combats){
            boolean placed = false;
            for (int tentative = 0; tentative < 10; tentative++) {
                int position = faker.number().numberBetween(positionDepart + 2, positionArrivee - 1);
                if (env[position].equals("_")) {
                    positionsCombats.put(position, c);
                    env[position] = "[!]";
                    logger.info("Groupe d'ennemis placé sur la carte à la position " + position + ".");
                    placed = true;
                    break;
                }
            }
        }
    }

    public void updatePosition(int oldPos, int newPos, String name){
        if(oldPos >= 0 && oldPos < longueur){
            env[oldPos] = "_";
        }
        if(newPos >= 0 && newPos < longueur){
            env[newPos] = "[" + name + "]";
        }
    }

    public String getCase(int position){
        if(position >= 0 && position < longueur){
            return env[position];
        }
        return "_";
    }
}