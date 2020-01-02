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

    /**
     * Méthode permettant de récupérer l'instance de la classe
     * @return
     */
    public static GridController getInstance(){
        if(_instance == null){
            _instance = new GridController();
        }
        return _instance;
    }

    /**
     * Méthode permettant de récupérer toutes les grilles de jeu
     * @return
     */
    public ArrayList<Grid> getGrids(){
        return _grids;
    }

    /**
     * Méthode permettant de changer l'état d'une cellule
     * @param index la grille sur laquelle on doit l'état de la cellule
     * @param x la position x de la cellule
     * @param y la position y de la cellule
     * @param state le nouvelle état de la cellule
     */
    public void setStateGrid(int index, int x, int y, State state){
        _grids.get(index).setState(x,y,state);
    }

    /**
     * Méthode permettant d'initialiser une grille de jeu
     * @param size la taille de la grille
     * @param strategy
     * @param expansion la stratégie d'extension que doit utiliser la grille
     */
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

    public int count(int index){
        return _grids.get(index).countAlive();
    }
    
    public void clockForward(){
        for (Grid grid:_grids) {
            grid.clockForward();
        }
        notifyObservers();
    }
}
