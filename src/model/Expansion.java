package model;

/**
 * Cette énumération présente les cinq méthodes d'extension de la grille de jeu
 */
public enum Expansion {
    REPETITION("Repetition"),
    CONSTANT("Constant");
//    PERIODICITY("Periodicity"),
//    SYMETRY1("Symetry n°1"),
//    SYMETRY2("Symetry n°2"),


    /**
     * version string de l'enumeration
     */
    private final String _abreviation;

    /**
     * Constructeur d'enumeration
     * @param abreviation version string de l'enumeration
     */
    Expansion(String abreviation){
        this._abreviation = abreviation;
    }

    /**
     * Méthode renvoyant la version string de l'enumeration
     * @return version string de l'enumeration
     */
    public String getAbreviation(){return _abreviation;}
}
