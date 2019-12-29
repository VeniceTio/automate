package model;

import java.util.ArrayList;

public interface Rule<State> {
    State getNewState(ArrayList<State> neighbors, State actualState);
}
