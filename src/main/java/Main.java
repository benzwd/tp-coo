import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

import java.util.Scanner;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static String heroName;

    private static void init(){
        System.out.println("Bienvenue sur Beat them all ! \nDéveloppé par Matys LEPRETRE et Benjamin ZAWODA.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez votre nom : ");
        heroName = scanner.nextLine();
    }

    public static void main(String[] args) {
        init();
        Heros h = new Heros(heroName, 10,1,null);
        Ennemi e = new Ennemi("Mechant", 5, 1, Type.BRIGAND);
        Ennemi e1 = new Ennemi("Mechant 2", 10, 2, Type.GANGSTER);

        List<Ennemi> l = new ArrayList<>(); 
        l.add(e);
        l.add(e1);

        Carte carte = new Carte("MapMonde", 40);
        carte.placeHero(h);
        carte.afficherCarte();


        Combat combat = new Combat(l, h);
        combat.derouleLesCombats();
    }
}