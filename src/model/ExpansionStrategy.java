package model;

import java.util.ArrayList;

public interface ExpansionStrategy<Expansion extends Enum<?>> {

    ArrayList<State> getNeighborsState(int x,int y, ArrayList<State> grid);
}
