import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;
import net.datafaker.Faker;

/**
 * Classe représentant un combat entre un héros et un groupe d'ennemis.
 * Elle gère la logique du combat, y compris l'ordre des attaques,
 * l'état des participants, et l'affichage des statistiques.
 */
public class Combat {
    /**
     * Instance de la bibliothèque Faker pour générer des données aléatoires.
     */
    private final Faker faker = new Faker();

    /**
     * Logger pour suivre les événements et les actions pendant le combat.
     */
    private static final Logger logger = Logger.getLogger(Combat.class.getName());

    /**
     * Nombre maximum d'ennemis dans un combat.
     */
    private final int NOMBRE_ENNEMI_MAX = 5;

    /**
     * Liste des ennemis participant au combat.
     */
    private List<Ennemi> ennemis = new ArrayList<>();

    /**
     * Héros participant au combat.
     */
    private Heros heros;

    /**
     * Constructeur qui initialise un combat avec un héros donné.
     * Le nombre d'ennemis est généré aléatoirement, et chaque ennemi est
     * initialisé avec des points de vie, une force d'attaque, et un type.
     * 
     * @param heros Héros participant au combat.
     */
    public Combat(Heros heros) {
        int nbrEnnemis = faker.number().numberBetween(1, NOMBRE_ENNEMI_MAX);
        System.out.println("Nbr enn : " + nbrEnnemis);
        for (int i = 0; i < nbrEnnemis; i++) {
            int pv = faker.number().numberBetween(50, 100);
            int forceAtt = faker.number().numberBetween(1, 3);
            ennemis.add(new Ennemi(faker.streetFighter().characters(), pv, forceAtt, Type.aleatoire()));
        }
        this.heros = heros;
    }

    /**
     * Récupère la liste des ennemis participant au combat.
     * 
     * @return Liste des ennemis.
     */
    public List<Ennemi> getEnnemis() {
        return this.ennemis;
    }

    /**
     * Définit une nouvelle liste d'ennemis pour le combat.
     * 
     * @param ennemis Nouvelle liste d'ennemis.
     */
    public void setEnnemis(List<Ennemi> ennemis) {
        this.ennemis = ennemis;
    }

    /**
     * Récupère le héros participant au combat.
     * 
     * @return Le héros.
     */
    public Heros getHeros() {
        return this.heros;
    }

    /**
     * Définit un nouveau héros pour le combat.
     * 
     * @param heros Nouveau héros.
     */
    public void setHeros(Heros heros) {
        this.heros = heros;
    }

    /**
     * Vérifie si le combat est terminé.
     * Le combat se termine si la liste des ennemis est vide ou si le héros est mort.
     * 
     * @return `true` si le combat est terminé, sinon `false`.
     */
    private boolean estTerminer() {
        return ennemis.isEmpty() || heros.estMort();
    }

    /**
     * Détermine si le héros attaque en premier.
     * Le héros attaque en premier sauf si l'ennemi est de type {@code GANGSTER}.
     * 
     * @param e Ennemi à vérifier.
     * @return `true` si le héros attaque en premier, sinon `false`.
     */
    private boolean herosAttaqueEnPremier(Ennemi e) {
        return e.getType() != Type.GANGSTER;
    }

    /**
     * Génère une barre de statistiques pour le combat, comprenant
     * les statistiques du héros et la liste des ennemis.
     * 
     * @return Barre de statistiques sous forme de chaîne de caractères.
     */
    private String statsBar() {
        StringBuilder sb = new StringBuilder();
        sb.append(heros.statsBar()).append("\n");
        sb.append("Ennemi(s) :\n");
        for (Ennemi e : ennemis) {
            sb.append("• ").append(e.statsBar()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Déroule le combat entre le héros et les ennemis.
     * Le combat continue jusqu'à ce qu'il soit terminé (tous les ennemis sont morts ou le héros est mort).
     * Le déroulement inclut :
     * <ul>
     * <li>L'ordre des attaques (le héros ou l'ennemi attaque en premier).</li>
     * <li>L'affichage des statistiques après chaque action.</li>
     * <li>La gestion de la mort des participants.</li>
     * </ul>
     */
    public void derouleCombat() {
        Ennemi e;
        while (!this.estTerminer()) {
            e = ennemis.get(0);
            while (!(heros.estMort() || e.estMort())) {
                System.out.println(statsBar());
                if (herosAttaqueEnPremier(e)) {
                    heros.attaque(ennemis);
                    Jeu.attendre(500);
                    if (!e.estMort()) {
                        e.attaque(heros);
                        Jeu.attendre(500);
                    }
                } else {
                    e.attaque(heros);
                    Jeu.attendre(500);
                    if (!heros.estMort()) {
                        heros.attaque(ennemis);
                        Jeu.attendre(500);
                    }
                }
            }
            if (heros.estMort()) {
                System.out.println(statsBar());
            } else {
                System.out.println(statsBar());
                ennemis.remove(e);
            }
        }
        if (!heros.estMort()) {
            System.out.println("Vous avez gagné le combat");
        }
    }
}
