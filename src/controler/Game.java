package controler;

import model.*;

import static java.lang.Thread.sleep;

public class Game {

    private int _maxturn;
    private int _gameSpeed;
    private static Game _instance = null;

    public static Game getInstance(){
        if(_instance == null){
            _instance = new Game();
        }
        return _instance;
    }
    public void createGameWindow(int gridSize, Expansion expansion, int turnNum,
                                 Automaton[] players,int cellNum,int gameSpeed){
        _gameSpeed = gameSpeed;
        _maxturn = turnNum;
        GridController GC = GridController.getInstance();
        ExpansionStrategy<Expansion> expansionType = getExpansionType(expansion);
        Rule<State> autoType;
        for (Automaton auto:players) {
            autoType = getAutomaton(auto);
            GC.initGrid(gridSize,autoType,expansionType);
        }
    }

    public void automatonGame() throws InterruptedException {
        int turn = 0;
        Boolean alive = true;
        GridController GC = GridController.getInstance();
        while(alive || turn<_maxturn){
            GC.clockForward();
            sleep(_gameSpeed);
            alive = GC.allAlive();
            turn++;
        }
        //TODO : fin analyse du perdant et lancement de la fenetre de fin
    }

    public Rule<State> getAutomaton(Automaton automaton){
        Rule<State> autoType;
        switch (automaton){
            case FREDKIN:
                autoType = new Fredkin();
            case FREDKIN2:
                autoType = new Fredkin2();
            case GAMEOFLIFE:
                autoType = new GameOfLife();
            default:
                autoType = null;
        }
        return autoType;
    }

    public ExpansionStrategy<Expansion> getExpansionType(Expansion expansion){
        ExpansionStrategy<Expansion> expansionType;
        switch (expansion){
            case CONSTANT:
                expansionType = new Constant();
            case REPETITION:
                expansionType = new Repetition();
            default:
                expansionType = null;
        }
        return expansionType;
    }
}
