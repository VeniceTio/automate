package model;

import java.util.ArrayList;

public class Grid {

    ArrayList<State> _grid;
    Rule<State> _strategy;
    ExpansionStrategy<Expansion> _expansion;
    int _size;

    public Grid(int taille, Rule strategie, ExpansionStrategy expansion){
        _grid = initGrid(taille);
        _strategy = strategie;
        _expansion = expansion;
        _size = taille;
    }

    public ArrayList<State> initGrid(int size){
        ArrayList<State> grid = new ArrayList<State>();
        for(int i=0; i<size;i++){
            for(int k=0;i<size;i++){
                grid.add(State.MORT);
            }
        }
        return grid;
    }
    public void setState(int x, int y, State state){
        _grid.set((x*_size)+y, state);
    }
    public ArrayList getNeighbors(int x, int y){
        return _expansion.getNeighborsState(x,y,_grid);
    }
    public State getNewState(ArrayList<State> neighbors, State actualState){
        return _strategy.getNewState(neighbors,actualState);

    }

    public void clockForward(){
        State previousState;
        State newState;
        for(int i = 0; i< _size; i++){
            for(int k = 0; i< _size; i++){
                previousState = _grid.get((i*_size)+k);
                newState = getNewState(getNeighbors(i,k),previousState);
                if(newState!=previousState){
                    setState(i,k,newState);
                }
            }
        }
    }
}
