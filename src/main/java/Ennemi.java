import java.util.logging.Logger;

/**
 * Classe représentant un ennemi dans le jeu.
 * Hérite de la classe abstraite {@link Personnage} et ajoute des fonctionnalités spécifiques aux ennemis,
 * notamment la gestion du type d'ennemi et ses attributs associés.
 */
public class Ennemi extends Personnage {
    /**
     * Type de l'ennemi (Brigand, Catcheur, Gangster).
     */
    private final Type TYPE;

    /**
     * Logger pour suivre les actions et événements liés aux ennemis.
     */
    private static final Logger logger = Logger.getLogger(Ennemi.class.getName());

    /**
     * Constructeur pour créer un ennemi avec un nom, des points de vie, une force d'attaque, et un type.
     * Chaque type peut modifier les caractéristiques de l'ennemi via {@link #ajoutAttributType()}.
     * 
     * @param name          Nom de l'ennemi.
     * @param pv            Points de vie de l'ennemi.
     * @param forceAttaque  Force d'attaque de l'ennemi.
     * @param type          Type de l'ennemi (détermine ses caractéristiques spécifiques).
     */
    public Ennemi(String name, int pv, int forceAttaque, Type type) {
        super(name, pv, forceAttaque, 1);
        this.TYPE = type;
        ajoutAttributType();
    }

    /**
     * Récupère le type de l'ennemi.
     * 
     * @return Le type de l'ennemi (Brigand, Catcheur, Gangster).
     */
    public Type getType() {
        return this.TYPE;
    }

    /**
     * Alias pour récupérer le type de l'ennemi.
     * 
     * @return Le type de l'ennemi (Brigand, Catcheur, Gangster).
     * @see #getType()
     */
    public Type getTYPE() {
        return this.TYPE;
    }

    /**
     * Modifie les caractéristiques de l'ennemi en fonction de son type.
     * <ul>
     * <li>Si le type est {@code Type.CATCHEUR}, les points de vie sont augmentés de 50%.</li>
     * <li>Si le type est {@code Type.GANGSTER}, l'ennemi est prêt à porter une attaque immédiatement.</li>
     * </ul>
     */
    private void ajoutAttributType() {
        if (this.TYPE == Type.CATCHEUR) {
            this.setPv((int) (this.getPv() * 1.5));
        } else if (this.TYPE == Type.GANGSTER) {
            this.setPorterAttaque(true);
        }
    }
}
