import java.util.logging.Logger;

public class Ennemi extends Personnage {
    private final Type TYPE;
    private static final Logger logger = Logger.getLogger(Ennemi.class.getName());

    public Ennemi(String name, int pv, int forceAttaque, Type type){
        super(name, pv, forceAttaque, 1);
        this.TYPE = type;
        ajoutAttributType();
    }

    public Type getType() {
        return this.TYPE;
    }

    public Type getTYPE() {
        return this.TYPE;
    }

    private void ajoutAttributType(){
        if(this.TYPE == Type.CATCHEUR){
            this.setPv((int) (this.getPv() * 1.5));
        }else if(this.TYPE == Type.GANGSTER){
            this.setPorterAttaque(true);
        }
    }

}
