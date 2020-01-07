package model;

import java.util.ArrayList;

public class Repetition implements ExpansionStrategy<Expansion> {
    /**
     * Méthode permettant de récupérer l'état des voisins d'une cellule dans le coin haut gauche vers _neighbors
     * @param x la position x de la cellule
     * @param y la position y de la cellule
     * @param grid la grille de jeu
     * @param size taille de la grille (size*size)
     */
    @Override
    public void leftUpCorner(int x, int y, ArrayList<State> grid, int size) {
        _neighbors.add(grid.get(0));
        for (int g = x; g < x+2; g++){
            _neighbors.add(_neighbors.get((y*size) + g));
        }
        for (int i=y; i < y+2; i++){
            _neighbors.add(grid.get((i * size) + x));
            for(int k = x; k < x+2; k++){
                _neighbors.add(grid.get((i * size) + k));
            }
        }
    }

    /**
     * Méthode permettant de récupérer l'état des voisins d'une cellule dans le coin bas gauche vers _neighbors
     * @param x la position x de la cellule
     * @param y la position y de la cellule
     * @param grid la grille de jeu
     * @param size taille de la grille (size*size)
     */
    @Override
    public void leftDownCorner(int x, int y, ArrayList<State> grid, int size) {
        for (int i = y-1; i < y+1; i++){
            _neighbors.add(grid.get((i * size) + x));
            for(int k = x; k < x+2; k++){
                _neighbors.add(grid.get((i * size) + k));
            }
        }
        for (int g=3; g<6; g++){
            _neighbors.add(_neighbors.get(g));
        }
    }

    /**
     * Méthode permettant de récupérer l'état des voisins d'une cellule dans le coin haut droit vers _neighbors
     * @param x la position x de la cellule
     * @param y la position y de la cellule
     * @param grid la grille de jeu
     * @param size taille de la grille (size*size)
     */
    @Override
    public void rightUpCorner(int x, int y, ArrayList<State> grid, int size) {
        for (int g = x-1; g < x+1; g++){
            _neighbors.add(grid.get((y * size) + g));
        }
        _neighbors.add(_neighbors.get(1));
        for (int i = y; i<y+2; i++){
            for(int k = x-1; k < x+1; k++){
                _neighbors.add(grid.get((i * size) + k));
            }
            _neighbors.add(grid.get((i * size) + x));
        }
    }

    /**
     * Méthode permettant de récupérer l'état des voisins d'une cellule dans le coin bas droit vers _neighbors
     * @param x la position x de la cellule
     * @param y la position y de la cellule
     * @param grid la grille de jeu
     * @param size taille de la grille (size*size)
     */
    @Override
    public void rightDownCorner(int x, int y, ArrayList<State> grid,int size) {
        for (int i = y-1; i < y+1; i++){
            for(int k = x-1; k < x+1; k++){
                _neighbors.add(grid.get((i * size) + k));
            }
            _neighbors.add(grid.get((i * size) + x));
        }
        for (int g = 3; g < 6; g++){
            _neighbors.add(_neighbors.get(g));
        }
    }

    /**
     * Méthode permettant de récupérer l'état des voisins d'une cellule sur le coté droit de la grille vers _neighbors
     * @param x la position x de la cellule
     * @param y la position y de la cellule
     * @param grid la grille de jeu
     * @param size taille de la grille (size*size)
     */
    @Override
    public void rightSide(int x, int y, ArrayList<State> grid, int size) {
        for (int i = y-1; i < y+2; i++){
            for(int k = x-1; k < x+1; k++){
                _neighbors.add(grid.get((i * size) + k));
            }
            _neighbors.add(grid.get((i * size) + x));
        }
    }

    /**
     * Méthode permettant de récupérer l'état des voisins d'une cellule sur le coté gauche de la grille vers _neighbors
     * @param x la position x de la cellule
     * @param y la position y de la cellule
     * @param grid la grille de jeu
     * @param size taille de la grille (size*size)
     */
    @Override
    public void leftSide(int x, int y, ArrayList<State> grid, int size) {
        for (int i = y-1;i < y+2; i++){
            _neighbors.add(grid.get((i * size) + x));
            for(int k = x; k < x+2; k++){
                _neighbors.add(grid.get((i * size) + k));
            }
        }
    }

    /**
     * Méthode permettant de récupérer l'état des voisins d'une cellule sur le coté haut de la grille vers _neighbors
     * @param x la position x de la cellule
     * @param y la position y de la cellule
     * @param grid la grille de jeu
     * @param size taille de la grille (size*size)
     */
    @Override
    public void upSide(int x, int y, ArrayList<State> grid, int size) {
        for (int g = x-1; g < x+2; g++){
            _neighbors.add(grid.get((y * size) + g));
        }
        for (int i = y; i < y+2; i++){
            for(int k = x-1; k < x+2; k++){
                _neighbors.add(grid.get((i * size) + k));
            }
        }
    }

    /**
     * Méthode permettant de récupérer l'état des voisins d'une cellule sur le coté bas de la grille vers _neighbors
     * @param x la position x de la cellule
     * @param y la position y de la cellule
     * @param grid la grille de jeu
     * @param size taille de la grille (size*size)
     */
    @Override
    public void downSide(int x, int y, ArrayList<State> grid, int size) {
        for (int i = y-1; i < y+1; i++){
            for(int k = x-1; k < x+2; k++){
                _neighbors.add(grid.get((i * size) + k));
            }
        }
        for (int g = 3; g < 6; g++){
            _neighbors.add(_neighbors.get(g));
        }
    }
}
