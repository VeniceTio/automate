package controler;

import model.Automaton;
import model.Expansion;
import view.EndWindow;
import view.GameWindow;
import view.SettingsWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class ViewController {
    //private ...
    private SettingsWindow _settingsWin;
    private GameWindow _gameWin;
    private EndWindow _endWin;
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

    public void createSettingWindow() {
        _settingsWin = new SettingsWindow(_automatons,_expansions);
        _settingsWin.setVisible(true);
    }

    public void createGameWindow(int size, String[] players, int cellNum,int initValue) {
        _settingsWin.dispose();
        _gameWin = new GameWindow(size, players,cellNum,initValue);
        _gameWin.setVisible(true);
    }

    public void createEndWindow(int turnNumber) {
        _gameWin.dispose();
        _endWin = new EndWindow(turnNumber);
        _endWin.setVisible(true);
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
}
