import java.util.logging.Logger;

public abstract class Personnage implements IPersonnage {
    private static final Logger logger = Logger.getLogger(Personnage.class.getName());
    private final int NOMBRE_ATTAQUE;
    private String name;
    private int pv;
    private int forceAttaque;
    private int pvActuels;
    private boolean porterAttaque;

    public Personnage(String name, int pv, int forceAttaque, int nombreAttaque) {
        this.NOMBRE_ATTAQUE = nombreAttaque;
        this.name = name;
        this.pv = pv;
        this.forceAttaque = forceAttaque;
        this.pvActuels = pv;
        this.porterAttaque = false;
    }

    public int getNombreAttaque(){
        return this.NOMBRE_ATTAQUE;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPv() {
        return this.pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getForceAttaque() {
        return this.forceAttaque;
    }

    public void setForceAttaque(int forceAttaque) {
        this.forceAttaque = forceAttaque;
    }

    public boolean getPorterAttaque() {
        return this.porterAttaque;
    }

    public void setPorterAttaque(boolean porterAttaque) {
        this.porterAttaque = porterAttaque;
    }

    public boolean estMort(){
        return getPv() <= 0;
    }

    // Méthodes liées à la fonctionnalité Attaque
    public int nombreAttaque(){
        return (int) (Math.random() * this.NOMBRE_ATTAQUE + 1);
    }

    public void afficherPvRestant(){
        if(this.estMort()){
            System.out.println(this.name + " a perdu tous ses pv, il est donc mort...");
        }else{
            System.out.println(this.name + " a " + this.pv + " pv restant");
        }
    }

    public void attaque(Personnage e) {
        for(int i = 0; i <= nombreAttaque(); i ++){
            e.setPv(e.pv - this.forceAttaque);
        }
    }

    public String statsBar(){
        return name + " (♥ " + pv + " | ⚔ " + forceAttaque + ")";
    }
}
