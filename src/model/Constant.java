package model;

import java.util.ArrayList;

public class Constant implements ExpansionStrategy<Expansion> { //TODO: commentaire à faire
    @Override
    public void leftUpCorner(int x, int y, ArrayList<State> grid, int size) {
        _neighbors.add(State.DEAD);
        for (int g=x;g<x+2;g++){
            _neighbors.add(State.DEAD);
        }
        for (int i=y;i<y+2;i++){
            _neighbors.add(State.DEAD);
            for(int k=x;k<x+2;k++){
                _neighbors.add(grid.get((i*size)+k));
            }
        }
    }

    @Override
    public void leftDownCorner(int x, int y, ArrayList<State> grid, int size) {
        for (int i=y-1;i<y+1;i++){
            _neighbors.add(State.DEAD);
            for(int k=x;k<x+2;k++){
                _neighbors.add(grid.get((i*size)+k));
            }
        }
        for (int g=3;g<6;g++){
            _neighbors.add(State.DEAD);
        }
    }

    @Override
    public void rightUpCorner(int x, int y, ArrayList<State> grid, int size) {
        for (int g=x-1;g<x+1;g++){
            _neighbors.add(State.DEAD);
        }
        _neighbors.add(_neighbors.get(1));
        for (int i=y;i<y+2;i++){
            for(int k=x-1;k<x+1;k++){
                _neighbors.add(grid.get((i*size)+k));
            }
            _neighbors.add(State.DEAD);
        }
    }

    @Override
    public void rightDownCorner(int x, int y, ArrayList<State> grid, int size) {
        for (int i=y-1;i<y+1;i++){
            for(int k=x-1;k<x+1;k++){
                _neighbors.add(grid.get((i*size)+k));
            }
            _neighbors.add(State.DEAD);
        }
        for (int g=3;g<6;g++){
            _neighbors.add(State.DEAD);
        }
    }

    @Override
    public void rightSide(int x, int y, ArrayList<State> grid, int size) {
        for (int i=y-1;i<y+2;i++){
            for(int k=x-1;k<x+1;k++){
                _neighbors.add(grid.get((i*size)+k));
            }
            _neighbors.add(State.DEAD);
        }
    }

    @Override
    public void leftSide(int x, int y, ArrayList<State> grid, int size) {
        for (int i=y-1;i<y+2;i++){
            _neighbors.add(State.DEAD);
            for(int k=x;k<x+2;k++){
                _neighbors.add(grid.get((i*size)+k));
            }
        }
    }

    @Override
    public void upSide(int x, int y, ArrayList<State> grid, int size) {
        for (int g=x-1;g<x+2;g++){
            _neighbors.add(State.DEAD);
        }
        for (int i=y;i<y+2;i++){
            for(int k=x-1;k<x+2;k++){
                _neighbors.add(grid.get((i*size)+k));
            }
        }
    }

    @Override
    public void downSide(int x, int y, ArrayList<State> grid, int size) {
        for (int i=y-1;i<y+1;i++){
            for(int k=x-1;k<x+2;k++){
                _neighbors.add(grid.get((i*size)+k));
            }
        }
        for (int g=3;g<6;g++){
            _neighbors.add(State.DEAD);
        }
    }
}
