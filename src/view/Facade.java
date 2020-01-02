package view;

import controler.Game;
import model.Automaton;
import model.Expansion;

import java.util.ArrayList;

public class Facade {
    public static void initGameWindow(ArrayList<Integer> numericParameters, ArrayList<String> textParameters) {
        Automaton[] aPlayers = {toAutomaton(textParameters.get(1)), toAutomaton((textParameters.get(2)))};
        String[] sPlayers = {textParameters.get(1), textParameters.get(2)};
        Game.getInstance().createGameWindow(numericParameters.get(0), numericParameters.get(1), numericParameters.get(2),  numericParameters.get(3), toExpansion(textParameters.get(0)), aPlayers);
        new GameWindow(numericParameters.get(0), sPlayers,numericParameters.get(3)).setVisible(true);

        System.out.println(numericParameters);
        System.out.println(toExpansion(textParameters.get(0)));
        System.out.println(toAutomaton(textParameters.get(1)));
        System.out.println(toAutomaton(textParameters.get(2)));
    }

    public static void createSettingsWindow(){
        new SettingsWindow().setVisible(true);
    }

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
        createSettingsWindow();
    }
}
