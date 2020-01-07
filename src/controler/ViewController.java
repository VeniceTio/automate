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
    private SettingsWindow _settingsWindow;
    /**
     * La fenêtre de je
     */
    private GameWindow _gameWindow;
    /**
     * La fenêtre de fin de jeu
     */
    private EndWindow _endWindow;
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
        for (Expansion exp:Expansion.values()) {
            _expansions.add(exp.getAbbreviation());
        }
        for (Automaton auto:Automaton.values()) {
            _automatons.add(auto.getAbbreviation());
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
        _settingsWindow = new SettingsWindow(_automatons, _expansions);
        _settingsWindow.setVisible(true);
    }

    /**
     * Méthode permettant de créer la fenêtre de jeu
     * @param size la taille de la grille
     * @param players les méthodes d'évolutions des joueurs
     * @param cellNum le nombre de cellule de chaque joeu
     * @param initSpeedValue la vitesse de base du jeu
     * @param playersColor les couleurs des deux joueurs
     */
    public void createGameWindow(int size, String[] players, int cellNum, int initSpeedValue,Color[] playersColor) {
        _settingsWindow.dispose();
        _gameWindow = new GameWindow(size, players, cellNum, initSpeedValue, playersColor);
        _gameWindow.setVisible(true);
    }

    /**
     * Méthode permettant de créer la fenêtre de fin de jeu
     * @param turnNumber le nombre avec lequel le jeu s'est fini
     */
    public void createEndWindow(int turnNumber) {
        _gameWindow.dispose();
        _endWindow = new EndWindow(turnNumber);
        _endWindow.setVisible(true);
    }

    /**
     * Méthode permettant de récupérer l'instace de la classe GameWindow
     * @return l'instance de classe de la classe GameWindow
     */
    public GameWindow getGameWin(){
        return _gameWindow;
    }
}
