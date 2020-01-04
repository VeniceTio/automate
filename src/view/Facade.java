package view;

import controler.Game;
import controler.ViewController;
import model.Automaton;
import model.Expansion;

import java.util.ArrayList;

public class Facade {
    /**
     * Méthode permettant d'initialiser une fenêtre de jeu
     * @param numericParameters les paramètres numérriques du jeu
     * @param textParameters les paramètres textuels du jeu
     */
    public static void initGameWindow(ArrayList<Integer> numericParameters, ArrayList<String> textParameters) {
        Automaton[] aPlayers = {toAutomaton(textParameters.get(1)), toAutomaton((textParameters.get(2)))};
        String[] sPlayers = {textParameters.get(1), textParameters.get(2)};

        Game.getInstance().createGame(numericParameters.get(0), numericParameters.get(1), numericParameters.get(2),  numericParameters.get(3), toExpansion(textParameters.get(0)), aPlayers);
        ViewController.getInstance().createGameWindow(numericParameters.get(0), sPlayers,numericParameters.get(3),numericParameters.get(1));

        System.out.println("Facade.java: initGameWindow()");
        System.out.println("Les paramètres numériques entrés: " + numericParameters);
        System.out.println("Méthode d'extension de la grille choisi: " + toExpansion(textParameters.get(0)));
        System.out.println("Méthode d'évolution joueur n°1: " + toAutomaton(textParameters.get(1)));
        System.out.println("Méthode d'évolution joueur n°2: " + toAutomaton(textParameters.get(2)));
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

    public static void main(String[] args){
        initSettingsWindow();
    }
}
