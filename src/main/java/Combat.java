import java.util.List;

public class Combat {
    private List<Ennemi> ennemis;
    private Heros heros;

    public Combat(List<Ennemi> ennemis, Heros heros) {
        this.ennemis = ennemis;
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
    
    private void derouleUnCombat(Ennemi e){
        while(!(heros.estMort() || e.estMort())){
            if(herosAttaqueEnPremier(e)){
                heros.attaque(e);
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
        }
    }

    public void derouleLesCombats(){
        while (!this.estTerminer()) {
            derouleUnCombat(ennemis.get(0));
            ennemis.remove(0);
        }
        if(!heros.estMort()){
            System.out.println("Vous avez gagné le combat");
        }
    }
}