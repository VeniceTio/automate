package model;

import java.util.ArrayList;

public interface Rule<State> {
    State getNewState(ArrayList<State> neightbors, State actualState);
}
