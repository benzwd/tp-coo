import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;
import net.datafaker.Faker;

public class Combat {
    Faker faker = new Faker();
    private static final Logger logger = Logger.getLogger(Combat.class.getName());

    private final int NOMBRE_ENNEMI_MAX = 5;
    private List<Ennemi> ennemis = new ArrayList<>();
    private Heros heros;

    public Combat(Heros heros) {
        int nbrEnnemis = faker.number().numberBetween(1, NOMBRE_ENNEMI_MAX);
        System.out.println("Nbr enn : " + nbrEnnemis);
        for(int i = 0; i < nbrEnnemis; i++){
            int pv = faker.number().numberBetween(50, 100);
            int forceAtt = faker.number().numberBetween(1, 3);
            ennemis.add(new Ennemi(faker.streetFighter().characters(), pv, forceAtt, Type.aleatoire()));
        }
        this.heros = heros;
    }

    public List<Ennemi> getEnnemis() {
        return this.ennemis;
    }

    public void setEnnemis(List<Ennemi> ennemis) {
        this.ennemis = ennemis;
    }

    public Heros getHeros() {
        return this.heros;
    }

    public void setHeros(Heros heros) {
        this.heros = heros;
    }

    private boolean estTerminer(){
        if(ennemis.isEmpty() || heros.estMort()){
            return true;
        }else{
            return false;
        }
    }

    private boolean herosAttaqueEnPremier(Ennemi e){
        return e.getType() != Type.GANGSTER;
    }

    private String statsBar(){
        StringBuilder sb = new StringBuilder();
        sb.append(heros.statsBar()).append("\n");
        sb.append("Ennemi(s) :\n");
        for(Ennemi e : ennemis){
            sb.append("• ").append(e.statsBar()).append("\n");
        }
        return sb.toString();
    }
    
    public void derouleCombat(){
        Ennemi e;
        while (!this.estTerminer()) {
            e = ennemis.get(0);
            while(!(heros.estMort() || e.estMort())){
                System.out.println(statsBar());
                if(herosAttaqueEnPremier(e)){
                    heros.attaque(ennemis);
                    Jeu.attendre(500);
                    if(!e.estMort()){
                        e.attaque(heros);
                        Jeu.attendre(500);
                    }
                }else{
                    e.attaque(heros);
                    Jeu.attendre(500);
                    if(!heros.estMort()){
                        heros.attaque(ennemis);
                        Jeu.attendre(500);
                    }
                }
            }
            if(heros.estMort()){
                System.out.println(statsBar());
            }else{
                System.out.println(statsBar());
                ennemis.remove(e);
            }
        }if(!heros.estMort()){
            System.out.println("Vous avez gagné le combat");
        }
    }
}