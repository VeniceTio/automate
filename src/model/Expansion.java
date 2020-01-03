package model;

/**
 * Cette énumération présente les cinq méthodes d'extension de la grille de jeu
 */
public enum Expansion {
    REPETITION("Repetition"),
    PERIODICITY("Periodicity"),
    SYMETRY1("Symetry n°1"),
    SYMETRY2("Symetry n°2"),
    CONSTANT("Constant");

    private String _abreviation;

    Expansion(String abreviation){
        this._abreviation = abreviation;
    }

    public String getAbreviation(){return _abreviation;}
}
