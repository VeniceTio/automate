package model;

import java.util.ArrayList;

public class GameOfLife implements Rule<State> {
    @Override
    public State getNewState(ArrayList<State> neighbors, State actualState) {
        int nbLivingCel = 0;
        State nextState = actualState;
        if(actualState == State.VIVANT){
            nbLivingCel--;
        }
        for (int i=0;i<9;i++){
            if(neighbors.get(i)==State.VIVANT){
                nbLivingCel++;
            }
        }
        if (nbLivingCel<2 || nbLivingCel>3){
            nextState = State.MORT;
        } else if (nbLivingCel==3){
            nextState = State.VIVANT;
        }
        return nextState;
    }

    @Override
    public State next(State actualState) {
        State next = State.MORT;
        if (actualState == State.MORT){
            next = State.VIVANT;
        }
        return next;
    }
}
