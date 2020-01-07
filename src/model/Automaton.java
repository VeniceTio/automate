package model;

/**
 * Cette énumération présente les trois méthodes d'évolution que le joueur peut choisir
 */
public enum Automaton {
    GAMEOFLIFE("Game of life"),
    FREDKIN1("Fredkin n°1"),
    FREDKIN2("Fredkin n°2");

    /**
     * L'abréviation représentant l'automate
     */
    private final String _abbreviation;

    /**
     * Méthode permettant d'initialiser une instance de la classe Automaton
     * @param abbreviation l'abréviation de l'automate
     */
    Automaton(String abbreviation){
        this._abbreviation = abbreviation;
    }

    /**
     * Méthode permettant de renvoyer l'abréviation de l'automate
     * @return l'abréviation de l'automate
     */
    public String getAbbreviation(){
        return _abbreviation;
    }
}
