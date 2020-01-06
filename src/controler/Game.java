package controler;

import model.*;
import utils.EnumUtils;

import static java.lang.Thread.sleep;

public class Game {
    /**
     * Le nombre de tours maximale de jeu
     */
    private int _maxturn;
    /**
     * la vitesse de jeu
     */
    private int _gameSpeed;
    /**
     * L'instance de la classe
     */
    private static Game _instance = null;

    /**
     * Méthode permettant de récupérer l'instance de la classe
     * @return
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
        _maxturn = turnNum;
        GridController GC = GridController.getInstance();
        ExpansionStrategy<Expansion> expansionType = EnumUtils.getExpansionType(expansion);
        Rule<State> autoType;
        System.out.println("Game.java: createGameWindow()");
        System.out.println("size : " + gridSize);
        System.out.println("gameSpeed : " + gameSpeed);
        System.out.println("turnNum : " + turnNum);
        System.out.println("cellNum : " + cellNum);
        System.out.println("expansion : " + expansion);
        for (Automaton auto:players) {
            autoType = EnumUtils.getAutomaton(auto);
            GC.initGrid(gridSize,autoType,expansionType);
            System.out.println("player : " + auto);
        }
        GC.add(ViewController.getInstance());
    }

    /**
     * Méthode permettant de faire tourner le jeu
     * @throws InterruptedException
     */
    public void automatonGame() throws InterruptedException {
        int turn = 0;
        boolean alive = true;
        GridController GC = GridController.getInstance();
        ViewController VC = ViewController.getInstance();
        Thread MajView = new Thread(VC:: clockForward);
        Thread MajGrids = new Thread(GC::clockForward);
        while(alive && turn<_maxturn){
            System.out.println("## turn : "+turn+"##");
            //MajGrids.start();
            //MajView.start();
            //VC.clockForward();
            //new Thread(GC::clockForward).start();
            synchronized (VC){
                GC.clockForward();
                //new Thread(VC:: clockForward).start();
                //VC.wait();
                alive = GC.allAlive();
                turn++;
                try {
                    sleep(_gameSpeed);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        Facade.initEndWindow(turn);
        //TODO : fin analyse du perdant et lancement de la fenetre de fin
    }

    public void setGameSpeed(int gameSpeed){
        _gameSpeed = gameSpeed;
        System.out.println("game speed : "+gameSpeed);
    }
}
