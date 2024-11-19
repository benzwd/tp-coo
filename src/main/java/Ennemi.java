import java.util.logging.Logger;

public class Ennemi extends Personnage {
    private final Type TYPE;
    private static final Logger logger = Logger.getLogger(Ennemi.class.getName());

    public Ennemi(String name, int pv, int forceAttaque, Type type){
        super(name, pv, forceAttaque, 1);
        this.TYPE = type;
    }

    public Type getType() {
        return this.TYPE;
    }
}
