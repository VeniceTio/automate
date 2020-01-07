package controler;

import model.Automaton;
import model.Expansion;

import view.EndWindow;
import view.GameWindow;
import view.SettingsWindow;

import java.awt.*;
import java.util.ArrayList;

public class ViewController {
    /**
     * La fenêtre de paramétrage
     */
    private SettingsWindow _settingsWin;
    /**
     * La fenêtre de je
     */
    private GameWindow _gameWin;
    /**
     * La fenêtre de fin de jeu
     */
    private EndWindow _endWin;
    /**
     * liste contenant les méthodes d'extensions de la grille
     */
    private ArrayList<String> _expansions = new ArrayList<>();
    /**
     * Liste contenant les méthode d'évolution des automates
     */
    private ArrayList<String> _automatons = new ArrayList<>();
    /**
     * L'instance de la classe ViewController
     */
    private static ViewController _instance = null;

    /**
     * Méthode permettant d'initialiser une instance de classe ViewController
     */
    public ViewController(){
        for (Expansion exp:Expansion.values()){
            _expansions.add(exp.getAbreviation());
        }
        for (Automaton auto:Automaton.values()){
            _automatons.add(auto.getAbreviation());
        }
    }

    /**
     * Méthode permettant de récupérer l'instance de la classe
     * @return l'instance de la classe
     */
    public static ViewController getInstance(){
        if(_instance == null){
            _instance = new ViewController();
        }
        return _instance;
    }

    /**
     * Méthode permettant de créer la fenêtre de paramètrage
     */
    public void createSettingWindow() {
        _settingsWin = new SettingsWindow(_automatons,_expansions);
        _settingsWin.setVisible(true);
    }

    /**
     * Méthode permettant de créer la fenêtre de jeu
     * @param size la taille de la grille
     * @param players les méthodes d'évolutions des joueurs
     * @param cellNum le nombre de cellule de chaque joeu
     * @param initValue la vitesse de base du jeu
     * @param playersColor les couleurs des deux joueurs
     */
    public void createGameWindow(int size, String[] players, int cellNum,int initValue,Color[] playersColor) {
        _settingsWin.dispose();
        _gameWin = new GameWindow(size, players,cellNum,initValue,playersColor);
        _gameWin.setVisible(true);
    }

    /**
     * Méthode permettant de créer la fenêtre de fin de jeu
     * @param turnNumber le nombre avec lequel le jeu s'est fini
     */
    public void createEndWindow(int turnNumber) {
        _gameWin.dispose();
        _endWin = new EndWindow(turnNumber);
        _endWin.setVisible(true);
    }
    public GameWindow getGameWin(){return _gameWin;}
}
