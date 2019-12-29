package model;

import java.util.ArrayList;

public class Grid {

    ArrayList<State> _grid;
    final Rule<State> _strategy;
    final ExpansionStrategy<Expansion> _expansion;
    final int _size;

    public Grid(int size, Rule<State> strategy, ExpansionStrategy<Expansion> expansion){
        _strategy = strategy;
        _expansion = expansion;
        _size = size;
        _grid = initGrid();
    }

    public ArrayList<State> initGrid(){
        ArrayList<State> grid = new ArrayList<>();
        for(int i=0; i<_size;i++){
            for(int k=0;i<_size;i++){
                grid.add(State.DEAD);
            }
        }
        return grid;
    }
    public void setState(int x, int y, State state){
        _grid.set((x*_size)+y, state);
    }
    public ArrayList<State> getNeighbors(int x, int y){
        return _expansion.getNeighborsState(x,y,_grid);
    }
    public State getNewState(ArrayList<State> neighbors, State actualState){
        return _strategy.getNewState(neighbors,actualState);

    }

    public void clockForward(){
        State previousState;
        State newState;
        ArrayList<State> nextGrid = initGrid();
        for(int i = 0; i< _size; i++){
            for(int k = 0; i< _size; i++){
                previousState = _grid.get((i*_size)+k);
                newState = getNewState(getNeighbors(i,k),previousState);
                nextGrid.set((i*_size+k),newState);
            }
        }
        _grid = nextGrid;
    }
}
