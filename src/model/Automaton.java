package model;

/**
 * Cette énumération présente les trois méthodes d'évolution que le joueur peut choisir
 */
public enum Automaton {
    GAMEOFLIFE("Game of life"),
    FREDKIN1("Fredkin n°1"),
    FREDKIN2("Fredkin n°2");

    /**
     * version string de l'enumeration
     */
    private final String _abreviation;

    /**
     * Constructeur d'enumeration
     * @param abreviation version string de l'enumeration
     */
    Automaton(String abreviation){
        this._abreviation = abreviation;
    }

    /**
     * Méthode renvoyant la version string de l'enumeration
     * @return version string de l'enumeration
     */
    public String getAbreviation(){return _abreviation;}
}
