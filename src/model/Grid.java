package model;

import java.util.ArrayList;

public class Grid {

    /**
     * La grille de jeu contenant les états des cellules
     */
    ArrayList<State> _grid;
    final Rule<State> _strategy;
    /**
     * La méthode d'extension de la grille
     */
    final ExpansionStrategy<Expansion> _expansion;
    /**
     * La taille de la grille de jeu
     */
    final int _size;

    /**
     * Méthode permettant d'initialiser les paramètres de jeu
     * @param size la taille de la grille de jeu
     * @param strategy
     * @param expansion la méthode d'extension de la grille
     */
    public Grid(int size, Rule<State> strategy, ExpansionStrategy<Expansion> expansion){
        _strategy = strategy;
        _expansion = expansion;
        _size = size;
        _grid = initGrid();
    }

    /**
     * Méthode permettant d'initialiser la grille de jeu
     * @return la grille initialisée
     */
    public ArrayList<State> initGrid(){
        ArrayList<State> grid = new ArrayList<>();
        for(int i=0; i<_size;i++){
            for(int k=0;i<_size;i++){
                grid.add(State.DEAD);
            }
        }
        return grid;
    }

    /**
     * Méthode permettant de changer l'état d'une cellule
     * @param index la position de la cellule
     * @param state le nouvel état de la cellule
     */
    public void setState(int index, State state){
        _grid.set(index, state);
    }

    public State getState(int index){return _grid.get(index);}

    /**
     * Méthode permettant de récupérer les voisins d'une cellule
     * @param x la position x d'une cellule
     * @param y la position y d'une cellule
     * @return la liste des voisins de la cellule
     */
    public ArrayList<State> getNeighbors(int x, int y){
        return _expansion.getNeighborsState(x,y,_grid);
    }

    /**
     * Méthode permettant de récupérer le nouvel état de la cellule
     * @param neighbors les voisins de cette cellule
     * @param actualState l'état actuel de la cellule
     * @return le nouvel état
     */
    public State getNewState(ArrayList<State> neighbors, State actualState){
        return _strategy.getNewState(neighbors,actualState);

    }

    /**
     * Méthode permettant de compter le nombre de cellule vivante
     * @return le nombre de cellule vivante
     */
    public int countAlive(){
        int nbCelAlive = 0;
        for (State state:_grid) {
            if(state != State.DEAD){
                nbCelAlive++;
            }
        }
        return nbCelAlive;
    }

    /**
     * Méthode permettant d'avancer dans le jeu
     */
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
