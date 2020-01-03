package controler;

import model.*;

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
    public void createGameWindow(int gridSize, int gameSpeed, int turnNum, int cellNum,
                                 Expansion expansion, Automaton[] players){
        _gameSpeed = gameSpeed*1000;
        _maxturn = turnNum;
        GridController GC = GridController.getInstance();
        ExpansionStrategy<Expansion> expansionType = getExpansionType(expansion);
        Rule<State> autoType;
        System.out.println("size : " + gridSize);
        System.out.println("gameSpeed : " + gameSpeed);
        System.out.println("turnNum : " + turnNum);
        System.out.println("cellNum : " + cellNum);
        System.out.println("expansion : " + expansion);
        for (Automaton auto:players) {
            autoType = getAutomaton(auto);
            GC.initGrid(gridSize,autoType,expansionType);
            System.out.println("player : " + auto);
        }
    }

    /**
     * Méthode permettant de faire tourner le jeu
     * @throws InterruptedException
     */
    public void automatonGame() throws InterruptedException {
        int turn = 0;
        boolean alive = true;
        GridController GC = GridController.getInstance();
        while(alive || turn<_maxturn){
            System.out.println("## turn : "+turn+"##");
            GC.clockForward();
            alive = GC.allAlive();
            turn++;
            sleep(_gameSpeed);
        }
        //TODO : fin analyse du perdant et lancement de la fenetre de fin
    }

    /**
     * Méthode permettant de créer l'instance de l'automate choisi
     * @param automaton le mode d'évolution choisi
     * @return l'instance de l'automate choisi
     */
    public Rule<State> getAutomaton(Automaton automaton){
        Rule<State> autoType;
        switch (automaton){
            case FREDKIN1:
                autoType = new Fredkin1();
                System.out.println("automa rnetré fred1");
                break;
            case FREDKIN2:
                autoType = new Fredkin2();
                System.out.println("automa rnetré fred2");
                break;
            case GAMEOFLIFE:
                autoType = new GameOfLife();
                System.out.println("automa rnetré gameoflife");
                break;
            default:
                autoType = null;
                System.out.println("automa pas trouvé");
                break;
        }
        return autoType;
    }

    /**
     * Méthode permettant de créer l'intance de l'extension choisi
     * @param expansion la méthode d'extension de grille choisi
     * @return l'instance de l'extension de la grille choisi
     */
    public ExpansionStrategy<Expansion> getExpansionType(Expansion expansion){
        ExpansionStrategy<Expansion> expansionType;
        switch (expansion){
            case CONSTANT:
                expansionType = new Constant();
                break;
            case REPETITION:
                expansionType = new Repetition();
                break;
            default:
                expansionType = null;
                break;
        }
        return expansionType;
    }
}
