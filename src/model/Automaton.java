package model;

/**
 * Cette énumération présente les trois méthodes d'évolution que le joueur peut choisir
 */
public enum Automaton {
    GAMEOFLIFE("Game of life"),
    FREDKIN1("Fredkin n°1"),
    FREDKIN2("Fredkin n°2");

    private final String _abreviation;

    Automaton(String abreviation){
        this._abreviation = abreviation;
    }

    public String getAbreviation(){return _abreviation;}
}
