package model;

import java.util.ArrayList;

public interface ExpansionStrategy<Expansion extends Enum<?>> {

    ArrayList<State> _neighbors = new ArrayList<>();

    default ArrayList<State> getNeighborsState(int x,int y, ArrayList<State> grid){
        int size = (int)(Math.sqrt(grid.size()));
        if (x==0 && (y==0 || y==size-1)) {         //coin gauche
            if (y==0){
                leftUpCorner(x,y,grid,size);
            }else {
                leftDownCorner(x,y,grid,size);
            }
        } else if(x==size-1 && (y==0 || y==size-1)){  // coin droit   juste
            if (y==0){
                rightUpCorner(x,y,grid,size);
            } else {
                rightDownCorner(x,y,grid,size);
            }
        } else if(x==0 || x==size-1){   // a verifier
            if(x==0){
                leftSide(x,y,grid,size);
            } else {
                rightSide(x,y,grid,size);
            }
        } else if (y==0 || y==size-1){   // a verifier
            if (y==0){
                upSide(x,y,grid,size);
            } else {
                downSide(x,y,grid,size);
            }
        } else {     // millieu     juste
            middle(x,y,grid,size);
        }
        return _neighbors;
    }

    void leftUpCorner(int x,int y, ArrayList<State> grid,int size);
    void leftDownCorner(int x,int y, ArrayList<State> grid,int size);
    void rightUpCorner(int x,int y, ArrayList<State> grid,int size);
    void rightDownCorner(int x,int y, ArrayList<State> grid,int size);
    void rightSide(int x,int y, ArrayList<State> grid,int size);
    void leftSide(int x,int y, ArrayList<State> grid,int size);
    void upSide(int x,int y, ArrayList<State> grid,int size);
    void downSide(int x,int y, ArrayList<State> grid,int size);
    void middle(int x,int y, ArrayList<State> grid,int size);

}
