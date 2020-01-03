package controler;

import model.Automaton;
import model.Expansion;
import view.GameWindow;
import view.SettingsWindow;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class ViewController {
    //private ...
    private SettingsWindow _settingsWin;
    private GameWindow _gameWin;
    private ArrayList<String> _expansions = new ArrayList<>();
    private ArrayList<String> _automatons = new ArrayList<>();

    public ViewController(){
        for (Expansion exp:Expansion.values()){
            _expansions.add(exp.getAbreviation());
        }
        for (Automaton auto:Automaton.values()){
            _automatons.add(auto.getAbreviation());
        }
    }

    /**
     * L'instance de la classe ViewController (Singleton)
     */
    private static ViewController _instance = null;

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

    public void createSettingWindow(){
        _settingsWin = new SettingsWindow(_automatons,_expansions);
        _settingsWin.setVisible(true);
    }

    public void createGameWindow(int size, String[] players, int cellNum) {
        _settingsWin.setVisible(false);
        _gameWin = new GameWindow(size, players,cellNum);
        _gameWin.setVisible(true);
    }
    public void clockForward() {
        synchronized (this) {
            _gameWin.update();
//            try {
//                sleep(3000);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
            notify();
        }
    }




























    /**
     * Méthode permettant de convertir un type d'évolution (string) en type d'énumération
     * @param text le type d'évolution choisi par les joueurs
     * @return le type d'évolution en type énuméré
     */
    private static Automaton toAutomaton(String text) {
        Automaton automatonChosen;
        switch(text) {
            case "Game of life":
                automatonChosen = Automaton.GAMEOFLIFE;
                break;
            case "Fredkin n°1":
                automatonChosen = Automaton.FREDKIN1;
                break;
            case "Fredkin n°2":
                automatonChosen = Automaton.FREDKIN2;
                break;
            default:
                automatonChosen = null;
        }

        return automatonChosen;
    }

    /**
     * Méthode permettant de convertir une méthode d'extension(string) en type d'énumération
     * @param text la méthode d'extension de la grille
     * @return la méthode d'évolution de la grille  en type énuméré
     */
    private static Expansion toExpansion(String text) {
        Expansion expansionChosen;
        switch(text) {
            case "Repetition":
                expansionChosen = Expansion.REPETITION;
                break;
            case "Periodicity":
                expansionChosen = Expansion.PERIODICITY;
                break;
            case "Symetry n°1":
                expansionChosen = Expansion.SYMETRY1;
                break;
            case "Symetry n°2":
                expansionChosen = Expansion.SYMETRY2;
                break;
            case "Constant":
                expansionChosen = Expansion.CONSTANT;
                break;
            default:
                expansionChosen = null;
        }

        return expansionChosen;
    }
}
