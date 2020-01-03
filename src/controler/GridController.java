package controler;

import model.*;
import utils.Observable;

import java.awt.*;
import java.util.ArrayList;

public class GridController extends Observable {

    /**
     * Liste contenant toutes les grilles de jeu
     */
    private ArrayList<Grid> _grids = new ArrayList<>();
    /**
     * L'instance de la classe GridController
     */
    private static GridController _instance = null;

    /**
     * Méthode permettant de récupérer l'instance de la classe
     * @return l'instance de la classe
     */
    public static GridController getInstance(){
        if(_instance == null){
            _instance = new GridController();
        }
        return _instance;
    }

    /**
     * Méthode permettant de récupérer toutes les grilles de jeu
     * @return une liste contenant toutes les grilles
     */
    public ArrayList<Grid> getGrids(){
        return _grids;
    }

    /**
     *
     * @param index
     * @param position
     * @param state
     */
    public void setStateGrid(int index, int position, State state){ //TODO: commentaire à faire ici
        _grids.get(index).setState(position,state);
    }

    /**
     * Méthode permettant d'initialiser une grille de jeu
     * @param size la taille de la grille
     * @param strategy //TODO: commentaire à faire
     * @param expansion la stratégie d'extension que doit utiliser la grille
     */
    public void initGrid(int size, Rule<State> strategy, ExpansionStrategy<Expansion> expansion){
        _grids.add(new Grid(size,strategy,expansion));
    }

    /**
     * Méthode permettant de savoir si toutes les cellules sont vivantes ou pas
     * @return un booléen vrai si toutes les cellules sont vivantes sinon faux
     */
    public boolean allAlive(){
        boolean alive = true;
        for (Grid grid:_grids) {
            if(grid.countAlive()==0){
                alive = false;
            }
        }
        return alive;
    }

    /**
     * Méthode permettant de compter le nombre de cellule vivante
     * @param index la grille sur laquelle on veut compter le nombre de cellule vivante //TODO: index correspond bien à ça ?
     * @return le nombre de cellule vivante
     */
    public int count(int index){
        return _grids.get(index).countAlive();
    }

    /**
     * Méthode permettant de récupérer l'état d'une cellule
     * @param index la grille sur laquelle se situe la cellule //TODO: index correspond bien à ça ?
     * @param cell la cellule en question
     * @return l'état de la cellule
     */
    public State getState(int index, int cell){
        return _grids.get(index).getState(cell);
    }

    /**
     * Méthode permettant de faire évoluer les grilles
     */
    public void clockForward(){
        for (Grid grid:_grids) {
            //System.out.println(grid);
            grid.clockForward();
        }
        System.out.println("turn finish");
        //new Thread(this::notifyObservers).start();
    }
}
