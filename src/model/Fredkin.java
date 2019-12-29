package model;

import java.util.ArrayList;

public class Fredkin implements Rule<State>{
    @Override
    public State getNewState(ArrayList<State> neighbors, State actualState) {
        int nbLivingCel = 0;
        State nextState = actualState;
        for(int i = 1;i<8;i+=2){
            if(neighbors.get(i)==State.VIVANT){
                nbLivingCel++;
            }
        }
        if(nbLivingCel==1 || nbLivingCel==3){
            nextState = State.VIVANT;
        }else{
            nextState = State.MORT;
        }
        return nextState;
    }
}
