package model;

import java.util.ArrayList;

public class GameOfLife implements Rule<State> {
    /**
     * Méthode permettant d'avoir le nouvel état d'une cellule en fonction du voisinage
     * @param neighbors les voisins de cette cellule
     * @param actualState l'état actuel de la cellule
     * @return le nouvel état que doit prendre la cellule
     */
    @Override
    public State getNewState(ArrayList<State> neighbors, State actualState) {
        int nbLivingCel = 0;
        State nextState = actualState;
        if(actualState == State.ALIVE){
            nbLivingCel--;
        }
        for (int i = 0; i < 9; i++){
            if(neighbors.get(i) == State.ALIVE){
                nbLivingCel++;
            }
        }
        if (nbLivingCel < 2 || nbLivingCel > 3){
            nextState = State.DEAD;
        } else if (nbLivingCel == 3){
            nextState = State.ALIVE;
        }
        return nextState;
    }
}
