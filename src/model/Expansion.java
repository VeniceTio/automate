package model;

/**
 * Cette énumération présente les cinq méthodes d'extension de la grille de jeu
 */
public enum Expansion {
    REPETITION("Repetition"),
//    PERIODICITY("Periodicity"),
//    SYMETRY1("Symetry n°1"),
//    SYMETRY2("Symetry n°2"),
    CONSTANT("Constant");

    /**
     * L'abréviation représentant la méthode d'extension
     */
    private final String _abbreviation;

    /**
     * Méthode permettant d'initialiser une instance de la classe Expansion
     * @param abbreviation l'abréviation de la méthode d'extension
     */
    Expansion(String abbreviation){
        this._abbreviation = abbreviation;
    }

    /**
     * Méthode renvoyant la version string de l'enumeration
     * @return version string de l'enumeration
     */
    public String getAbbreviation(){
        return _abbreviation;
    }
}
