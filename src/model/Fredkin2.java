package model;

import java.util.ArrayList;

public class Fredkin2 implements Rule<State> {
    /**
     * Méthode permettant d'avoir le nouvel état d'une cellule
     * @param neighbors les voisins de cette cellule
     * @param actualState l'état actuel de la cellule
     * @return le nouvel état que doit prendre la cellule
     */
    @Override
    public State getNewState(ArrayList<State> neighbors, State actualState) {
        int nbLivingCel = 0;
        State nextState = actualState;
        for(int i = 0;i<3;i+=2){
            if(neighbors.get(i)==State.ALIVE){
                nbLivingCel++;
            }
        }
        for(int i = 6;i<9;i+=2){
            if(neighbors.get(i)==State.ALIVE){
                nbLivingCel++;
            }
        }
        if(nbLivingCel==1 || nbLivingCel==3){
            nextState = State.ALIVE;
        }else{
            nextState = State.DEAD;
        }
        return nextState;
    }
}
