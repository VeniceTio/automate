package controler;

import model.*;

import utils.Observable;

import java.util.ArrayList;

public class GridController extends Observable {

    /**
     * Liste contenant toutes les grilles de jeu
     */
    private final ArrayList<Grid> _grids = new ArrayList<>();
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
     * Méthode permettant de supprimer toute les grilles du controller
     */
    public void reset(){
        _grids.clear();
    }

    /**
     * Méthode permettant de mettre à jour une cellule d'une grille donnée
     * @param index référence la grille à modifier
     * @param position référence la cellule à modifier
     * @param state l'etat vers lequel la cellule doit évoluer
     */
    public void setStateGrid(int index, int position, State state){
        _grids.get(index).setState(position, state);
    }

    /**
     * Méthode permettant d'initialiser une grille de jeu
     * @param size la taille de la grille
     * @param strategy la méthode d'évolution de l'automate
     * @param expansion la méthode d'extension de la grille
     */
    public void initGrid(int size, Rule<State> strategy, ExpansionStrategy<Expansion> expansion){
        _grids.add(new Grid(size, strategy, expansion));
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
     * @param index la grille sur laquelle on veut compter le nombre de cellule vivante
     * @return le nombre de cellule vivante
     */
    public int cellCount(int index){
        return _grids.get(index).countAlive();
    }

    /**
     * Méthode permettant de récupérer l'état d'une cellule
     * @param index la grille sur laquelle se situe la cellule
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
            grid.clockForward();
        }
        notifyObservers();
    }

    /**
     * Méthode permettant de savoir quel automate est le gagnant de la partie
     * @return winner l'index de l'automate gagnant
     */
    public int getWinner(){
        int winner = 0;
        int cell = _grids.get(0).countAlive();
        for (int i=1;i<_grids.size();i++) {
            if(_grids.get(i).countAlive()>cell){
                winner = i;
                cell = _grids.get(i).countAlive();
            }else if (_grids.get(i).countAlive() == cell){
                winner = -1;
            }
        }
        return winner;
    }
}
