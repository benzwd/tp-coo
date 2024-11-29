public class Main {
    private static void init(){
        System.out.println("Bienvenue sur Beat them all ! \nDéveloppé par Matys LEPRETRE et Benjamin ZAWODA.");
    }

    public static void main(String[] args) {
        init();
        Jeu jeu = new Jeu();
        jeu.demarrageJeu();
        while (true){
            jeu.jouerTour();
        }
    }
}