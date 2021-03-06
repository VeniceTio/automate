package model;

import java.util.ArrayList;

public interface ExpansionStrategy<Expansion> {
    /**
     * Liste de voisins de la cellule
     */
    ArrayList<State> _neighbors = new ArrayList<>();

    /**
     * Méthode permettant de récupérer l'état des voisins d'une cellule
     * @param x la position x de la cellule
     * @param y la position y de la cellule
     * @param grid la grille de jeu
     * @return la liste contenant l'état des voisins de la cellule
     */
    default ArrayList<State> getNeighborsState(int x,int y, ArrayList<State> grid){
        _neighbors.clear();
        int size = (int)(Math.sqrt(grid.size()));
        if (x==0 && (y==0 || y==size-1)) {
            if (y==0){
                leftUpCorner(x, y, grid, size);
            }else {
                leftDownCorner(x, y, grid, size);
            }
        } else if(x==size-1 && (y==0 || y==size-1)){
            if (y==0){
                rightUpCorner(x, y, grid, size);
            } else {
                rightDownCorner(x, y, grid, size);
            }
        } else if(x==0 || x==size-1){
            if(x==0){
                leftSide(x, y, grid, size);
            } else {
                rightSide(x, y, grid, size);
            }
        } else if (y==0 || y==size-1){
            if (y==0){
                upSide(x, y, grid, size);
            } else {
                downSide(x, y, grid, size);
            }
        } else {     // millieu     juste
            middle(x, y, grid, size);
        }
        return _neighbors;
    }

    /**
     * Méthode permettant de récupérer l'état des voisins d'une cellule dans le coin haut gauche vers _neighbors
     * @param x la position x de la cellule
     * @param y la position y de la cellule
     * @param grid la grille de jeu
     * @param size taille de la grille (size*size)
     */
    void leftUpCorner(int x, int y, ArrayList<State> grid, int size);
    /**
     * Méthode permettant de récupérer l'état des voisins d'une cellule dans le coin bas gauche vers _neighbors
     * @param x la position x de la cellule
     * @param y la position y de la cellule
     * @param grid la grille de jeu
     * @param size taille de la grille (size*size)
     */
    void leftDownCorner(int x, int y, ArrayList<State> grid, int size);
    /**
     * Méthode permettant de récupérer l'état des voisins d'une cellule dans le coin haut droit vers _neighbors
     * @param x la position x de la cellule
     * @param y la position y de la cellule
     * @param grid la grille de jeu
     * @param size taille de la grille (size*size)
     */
    void rightUpCorner(int x, int y, ArrayList<State> grid, int size);
    /**
     * Méthode permettant de récupérer l'état des voisins d'une cellule dans le coin bas droit vers _neighbors
     * @param x la position x de la cellule
     * @param y la position y de la cellule
     * @param grid la grille de jeu
     * @param size taille de la grille (size*size)
     */
    void rightDownCorner(int x, int y, ArrayList<State> grid, int size);
    /**
     * Méthode permettant de récupérer l'état des voisins d'une cellule sur le coté droit de la grille vers _neighbors
     * @param x la position x de la cellule
     * @param y la position y de la cellule
     * @param grid la grille de jeu
     * @param size taille de la grille (size*size)
     */
    void rightSide(int x, int y, ArrayList<State> grid, int size);
    /**
     * Méthode permettant de récupérer l'état des voisins d'une cellule sur le coté gauche de la grille vers _neighbors
     * @param x la position x de la cellule
     * @param y la position y de la cellule
     * @param grid la grille de jeu
     * @param size taille de la grille (size*size)
     */
    void leftSide(int x, int y, ArrayList<State> grid, int size);
    /**
     * Méthode permettant de récupérer l'état des voisins d'une cellule sur le coté haut de la grille vers _neighbors
     * @param x la position x de la cellule
     * @param y la position y de la cellule
     * @param grid la grille de jeu
     * @param size taille de la grille (size*size)
     */
    void upSide(int x, int y, ArrayList<State> grid, int size);
    /**
     * Méthode permettant de récupérer l'état des voisins d'une cellule sur le coté bas de la grille vers _neighbors
     * @param x la position x de la cellule
     * @param y la position y de la cellule
     * @param grid la grille de jeu
     * @param size taille de la grille (size*size)
     */
    void downSide(int x, int y, ArrayList<State> grid, int size);
    /**
     * Méthode permettant de récupérer l'état des voisins d'une cellule au milieu de la grille vers _neighbors
     * @param x la position x de la cellule
     * @param y la position y de la cellule
     * @param grid la grille de jeu
     * @param size taille de la grille (size*size)
     */
    default void middle(int x, int y, ArrayList<State> grid, int size) {
        for (int i = y-1; i < y+2; i++){
            for(int k = x-1; k < x+2; k++){
                _neighbors.add(grid.get((i * size) + k));
            }
        }
    }
}
