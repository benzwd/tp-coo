import java.util.List;
import java.util.Random;

public enum Type {
    BRIGAND("Brigand"),
    CATCHEUR("Catcheur"),
    GANGSTER("Gangster");

    private String name;

    private Type(String name){
            this.name = name;
    }

    public static void afficherPossibilite(){
        for (int i = 0; i < Type.values().length; i ++) {
            System.out.println((i+1) + " : " + Type.values()[i]);
        }
    }
    
    public static Type aleatoire() {
        Random random = new Random();
        int index = random.nextInt(values().length);
        return values()[index];
    }
}
