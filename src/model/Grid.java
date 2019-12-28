package model;

import java.util.ArrayList;

public class Grid {

    Etat[][] _grid;
    CellStrategy _strategie;
    ExpansionStrategy _expansion;
    int _taille;

    public Grid(int taille, CellStrategy strategie, ExpansionStrategy expansion){
        _grid = initGrid(taille);
        _strategie = strategie;
        _expansion = expansion;
        _taille = taille;
    }

    public Etat[][] initGrid(int taille){
        Etat[][] grid = new Etat[taille][taille];
        for(int i=0; i<taille;i++){
            for(int k=0;i<taille;i++){
                grid[i][k] = Etat.MORT;
            }
        }
        return grid;
    }
    public void setState(int x, int y,Etat etat){
        _grid[x][y] = etat;
    }
    public ArrayList<Etat> getNeighbors(int x, int y){
        ArrayList<Etat> Neighbors = new ArrayList<Etat>();
        //TODO obtenir le nombre de voisin
        return Neighbors;
    }
    public Etat getNewState(ArrayList<Etat> neighbors,Etat actualState){
        return _strategie.getNewState(neighbors,actualState);
    }

    public void clockForward(){
        Etat previousState;
        Etat newState;
        for(int i=0; i<_taille;i++){
            for(int k=0;i<_taille;i++){
                previousState = _grid[i][k];
                newState = getNewState(getNeighbors(i,k),previousState);
                if(newState!=previousState){
                    setState(i,k,newState);
                }
            }
        }
    }
}
