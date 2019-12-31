package controler;

import model.*;
import utils.Observable;

import java.util.ArrayList;

public class GridController extends Observable {

    private ArrayList<Grid> _grids = new ArrayList<>();
    /**
     * L'instance de la classe GridController (Singleton)
     */
    private static GridController _instance = null;

    public static GridController getInstance(){
        if(_instance == null){
            _instance = new GridController();
        }
        return _instance;
    }

    public ArrayList<Grid> getGrids(){return _grids;}
    public void setStateGrid(Grid grid, int x, int y, State state){
        grid.setState(x,y,state);
    }

    public void initGrid(int size, Rule<State> strategy, ExpansionStrategy<Expansion> expansion){
        _grids.add(new Grid(size,strategy,expansion));
    }
    public boolean allAlive(){
        boolean alive = true;
        for (Grid grid:_grids) {
            if(grid.countAlive()==0){
                alive = false;
            }
        }
        return alive;
    }
    public void clockForward(){
        for (Grid grid:_grids) {
            grid.clockForward();
        }
        notifyObservers();
    }
}
