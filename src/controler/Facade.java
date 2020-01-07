package controler;

import model.Automaton;

import utils.EnumUtils;

import java.awt.*;
import java.util.ArrayList;

public class Facade {
    /**
     * Méthode permettant d'initialiser une fenêtre de jeu
     * @param numericParameters les paramètres numérriques du jeu
     * @param textParameters les paramètres textuels du jeu
     */
    public static void initGameWindow(ArrayList<Integer> numericParameters, ArrayList<String> textParameters) {
        Automaton[] aPlayers = {EnumUtils.toAutomaton(textParameters.get(1)), EnumUtils.toAutomaton((textParameters.get(2)))};
        String[] sPlayers = {textParameters.get(1), textParameters.get(2)};
        Color[] colorPlayer = new Color[]{Color.BLUE,Color.RED};

        ViewController.getInstance().createGameWindow(numericParameters.get(0), sPlayers,numericParameters.get(3),numericParameters.get(1),colorPlayer);
        Game.getInstance().createGame(numericParameters.get(0), numericParameters.get(1), numericParameters.get(2),
                numericParameters.get(3), EnumUtils.toExpansion(textParameters.get(0)), aPlayers);
    }

    /**
     * Méthode permettant de créer la fenêtre de paramètrage
     */
    public static void initSettingsWindow(){
        ViewController.getInstance().createSettingWindow();
    }

    /**
     * Méthode permettant de créer la fenêtre de fin de jeu
     */
    public static void initEndWindow(int turnNumber) {
        ViewController.getInstance().createEndWindow(turnNumber);
    }

    /**
     * Méthode permettant de réinitialiser la liste contenant les grilles de jeu
     */
    public static void resetGame() {
        GridController.getInstance().reset();
    }
}
