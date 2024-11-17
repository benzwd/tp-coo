public class Main {
    public static void main(String[] args) {
        Heros h = new Heros("Benjamin", 10,5,null);
        Ennemi e = new Ennemi("Mechant", 5, 1, null);

        for(int i = 0; i < 100; i++){
            System.out.println(h.nombreAttaque());
        }

        System.out.println(e.getName() + " : " + e.getPv() + " pv restant");
        h.attaque(e);
        System.out.println(e.getName() + " : " + e.getPv() + " pv restant");
    }
}