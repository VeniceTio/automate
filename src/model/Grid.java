package model;

import java.util.ArrayList;

public class Grid {

    /**
     * La grille de jeu contenant les états des cellules
     */
    private ArrayList<State> _grid;
    /**
     * strategie d'extention de la grille
     */
    final private  Rule<State> _strategy;
    /**
     * La méthode d'extension de la grille
     */
    final private  ExpansionStrategy<Expansion> _expansion;
    /**
     * La taille de la grille de jeu
     */
    final private int _size;

    /**
     * Méthode permettant d'initialiser les paramètres de la grille
     * @param size la taille de la grille de jeu
     * @param strategy type d'automate
     * @param expansion la méthode d'extension de la grille
     */
    public Grid(int size, Rule<State> strategy, ExpansionStrategy<Expansion> expansion){
        _strategy = strategy;
        _expansion = expansion;
        _size = size;
        _grid = initGrid();
    }

    /**
     * Méthode permettant d'initialiser une grille de jeu à l'etat mort
     * @return la grille initialisée
     */
    public ArrayList<State> initGrid(){
        ArrayList<State> grid = new ArrayList<>();
        for(int i = 0; i < _size; i++){
            for(int k = 0; k < _size; k++){
                grid.add(State.DEAD);
            }
        }
        return grid;
    }

    /**
     * Méthode permettant de mettre à jour l'etat d'une cellule
     * @param index cellule à mettre à jour
     * @param state nouvelle etat de la cellule
     */
    public void setState(int index, State state){
        _grid.set(index, state);
    }

    /**
     * Renvoie l'état d'une cellule de la grid
     * @param index position de la cellule questionné
     * @return etat de la cellule
     */
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
     * Méthode permettant de récupérer le nouvel état de la cellule TODO: bonne description?
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
        int aliveCellCount = 0;
        for (State state:_grid) {
            if(state != State.DEAD){
                aliveCellCount++;
            }
        }
        return aliveCellCount;
    }

    /**
     * Méthode permettant d'avancer dans le jeu
     */
    public void clockForward(){
        State previousState;
        State newState;
        ArrayList<State> nextGrid = initGrid();
        for(int i = 0; i < _size; i++){
            for(int k = 0; k < _size; k++){
                previousState = _grid.get((i * _size) + k);
                newState = getNewState(getNeighbors(k, i), previousState);
                nextGrid.set(((i * _size) + k), newState);
            }
        }
        _grid = nextGrid;
    }

    /**
     * Renvoie le type d'automate controllant la grille
     * @return le nom de l'automate
     */
    public String getStringStrategy() {
        String automaton = "";

        if(_strategy instanceof Fredkin1) {
            automaton = Automaton.FREDKIN1.getAbbreviation();
        }
        else if(_strategy instanceof Fredkin2) {
            automaton = Automaton.FREDKIN2.getAbbreviation();
        }
        else if(_strategy instanceof GameOfLife) {
            automaton = Automaton.GAMEOFLIFE.getAbbreviation();
        }

        return automaton;
    }

    /**
     * Renvoie la Grid formaté pour un affichage console
     * @return chaine de caractere contenant la grid
     */
    @Override
    public String toString(){
        StringBuilder chaine = new StringBuilder("grid :\n");
        for(int i = 0; i < _size; i++) {
            for (int k = 0; k < _size; k++) {
                chaine.append(_grid.get(i * _size + k)).append(", ");
            }
            chaine.append("\n");
        }
        return chaine.toString();
    }
}
