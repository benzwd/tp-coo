import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Heros h = new Heros("Benjamin", 10,1,null);
        Ennemi e = new Ennemi("Mechant", 5, 1, Type.BRIGAND);
        Ennemi e1 = new Ennemi("Mechant 2", 10, 2, Type.GANGSTER);

        List<Ennemi> l = new ArrayList<>(); 
        l.add(e);
        l.add(e1);


        Combat combat = new Combat(l, h);
        combat.derouleLesCombats();
    }
}