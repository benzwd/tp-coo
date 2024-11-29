import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class Combat {
    private final int NOMBRE_ENNEMI_MAX = 5;
    private static final Logger logger = Logger.getLogger(Combat.class.getName());
    private List<Ennemi> ennemis;
    private Heros heros;

    public Combat(Heros heros) {
        int nbrEnnemis = 1 + (int)(Math.random() * NOMBRE_ENNEMI_MAX + 1);
        ennemis = new ArrayList<Ennemi>();
        for(int i = 0; i < nbrEnnemis; i++){
            ennemis.add(new Ennemi("En",(50 + (int)(Math.random() * ((100 - 50) + 1))), (int)(Math.random() * 3), Type.aleatoire()));
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
    
    private void derouleCombat(List<Ennemi> ennemis){
        Ennemi e;
        while (!this.estTerminer()) {
            e = ennemis.get(0);
            while(!(heros.estMort() || e.estMort())){
                heros.afficherPvRestant();
                e.afficherPvRestant();
                if(herosAttaqueEnPremier(e)){
                    heros.attaque(e, ennemis);
                    if(!e.estMort()){
                        e.attaque(heros);
                    }
                }else{
                    e.attaque(heros);
                    if(!heros.estMort()){
                        heros.attaque(e);
                    }
                }
            }
            if(heros.estMort()){
                heros.afficherPvRestant();
            }else{
                e.afficherPvRestant();
                ennemis.remove(e);
            }
        }if(!heros.estMort()){
            System.out.println("Vous avez gagné le combat");
        }
    }
}