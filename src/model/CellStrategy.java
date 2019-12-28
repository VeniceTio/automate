package model;

import java.util.ArrayList;

public interface CellStrategy{
    Etat getNewState(ArrayList<Etat> neightbors, Etat actualState);
}
