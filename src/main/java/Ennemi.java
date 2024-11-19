public class Ennemi extends Personnage {
    private final Type TYPE;

    public Ennemi(String name, int pv, int forceAttaque, Type type){
        super(name, pv, forceAttaque, 1);
        this.TYPE = type;
    }

    public Type getType() {
        return this.TYPE;
    }
}
