package model;

import java.util.ArrayList;

public interface Rule<State> {
    /**
     * Méthode permettant d'avoir le nouvel état d'une cellule
     * @param neighbors les voisins de cette cellule
     * @param actualState l'état actuel de la cellule
     * @return le nouvel état que doit prendre la cellule
     */
    State getNewState(ArrayList<State> neighbors, State actualState);
}
