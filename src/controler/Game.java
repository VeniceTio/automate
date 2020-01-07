package controler;

import model.*;

import utils.EnumUtils;

import static java.lang.Thread.sleep;

public class Game {
    /**
     * Le nombre de tours maximale de jeu
     */
    private int _maxTurn;
    /**
     * la vitesse de jeu
     */
    private int _gameSpeed;
    /**
     * L'instance de la classe Game
     */
    private static Game _instance = null;

    /**
     * Méthode permettant de récupérer l'instance de la classe
     * @return l'instance de la classe
     */
    public static Game getInstance(){
        if(_instance == null){
            _instance = new Game();
        }
        return _instance;
    }

    /**
     * Méthode permettant d'initialiser les paramètres du jeu
     * @param gridSize la taille de la grille
     * @param gameSpeed la vitesse de jeu
     * @param turnNum le nombre de tours de jeu
     * @param cellNum le nombre de cellule par joueur
     * @param expansion la méthode d'extension de la grille
     * @param players les choix d'évolution d'automates des deux joueurs
     */
    public void createGame(int gridSize, int gameSpeed, int turnNum, int cellNum,
                           Expansion expansion, Automaton[] players){
        _gameSpeed = gameSpeed*1000;
        _maxTurn = turnNum;
        GridController GC = GridController.getInstance();
        ExpansionStrategy<Expansion> expansionType = EnumUtils.getExpansionType(expansion);
        Rule<State> autoType;
        for (Automaton auto:players) {
            autoType = EnumUtils.getAutomaton(auto);
            GC.initGrid(gridSize,autoType,expansionType);
        }
        GC.add(ViewController.getInstance().getGameWin());
    }

    /**
     * Méthode permettant de faire tourner le jeu
     */
    public void automatonGame(){
        int turn = 0;
        boolean alive = true;
        GridController GC = GridController.getInstance();
        while(alive && turn< _maxTurn){
            System.out.println("## turn : "+turn+"##");
                GC.clockForward();
                alive = GC.allAlive();
                turn++;
                try {
                    sleep(_gameSpeed);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
        }
        Facade.initEndWindow(turn);
    }

    /**
     * Méthode permettant de changer la vitesse de jeu
     * @param gameSpeed la nouvelle vitesse de jeu
     */
    public void setGameSpeed(int gameSpeed){
        _gameSpeed = gameSpeed;
    }
}
