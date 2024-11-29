public class Main {
    private static void init(){
        System.out.println("Bienvenue sur Beat them all !");
        Jeu.attendre(500);
        System.out.println("Développé par Matys LEPRETRE et Benjamin ZAWODA.");
    }

    public static void main(String[] args) {
        init();
        Jeu.attendre(500);
        Jeu jeu = new Jeu();
        jeu.demarrageJeu();
        while (true){
            Jeu.attendre(1000);
            jeu.jouerTour();
        }
    }
}