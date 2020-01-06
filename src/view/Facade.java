package view;

import controler.Game;
import controler.GridController;
import controler.ViewController;
import model.Automaton;
import model.Expansion;
import utils.ViewUtilities;

import java.util.ArrayList;

public class Facade {
    /**
     * Méthode permettant d'initialiser une fenêtre de jeu
     * @param numericParameters les paramètres numérriques du jeu
     * @param textParameters les paramètres textuels du jeu
     */
    public static void initGameWindow(ArrayList<Integer> numericParameters, ArrayList<String> textParameters) {
        Automaton[] aPlayers = {ViewUtilities.toAutomaton(textParameters.get(1)), ViewUtilities.toAutomaton((textParameters.get(2)))};
        String[] sPlayers = {textParameters.get(1), textParameters.get(2)};

        Game.getInstance().createGame(numericParameters.get(0), numericParameters.get(1), numericParameters.get(2),  numericParameters.get(3), ViewUtilities.toExpansion(textParameters.get(0)), aPlayers);
        ViewController.getInstance().createGameWindow(numericParameters.get(0), sPlayers,numericParameters.get(3),numericParameters.get(1));

        System.out.println("Facade.java: initGameWindow()");
        System.out.println("Les paramètres numériques entrés: " + numericParameters);
        System.out.println("Méthode d'extension de la grille choisi: " + ViewUtilities.toExpansion(textParameters.get(0)));
        System.out.println("Méthode d'évolution joueur n°1: " + ViewUtilities.toAutomaton(textParameters.get(1)));
        System.out.println("Méthode d'évolution joueur n°2: " + ViewUtilities.toAutomaton(textParameters.get(2)));
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

    public static void resetGame() {
        GridController.getInstance().getGrids().clear();
    }

    public static void main(String[] args){
        initSettingsWindow();
    }
}
