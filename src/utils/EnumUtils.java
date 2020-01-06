package utils;

import model.*;

public class EnumUtils {
    /**
     * Méthode permettant de créer l'intance de l'extension choisi
     * @param expansion la méthode d'extension de grille choisi
     * @return l'instance de l'extension de la grille choisi
     */
    public static ExpansionStrategy<Expansion> getExpansionType(Expansion expansion){
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
    /**
     * Méthode permettant de créer l'instance de l'automate choisi
     * @param automaton le mode d'évolution choisi
     * @return l'instance de l'automate choisi
     */
    public static Rule<State> getAutomaton(Automaton automaton){
        System.out.println("Game.java: getAutomaton()");
        Rule<State> autoType;
        switch (automaton){
            case FREDKIN1:
                autoType = new Fredkin1();
                //System.out.println("automate fred1");
                break;
            case FREDKIN2:
                autoType = new Fredkin2();
                //System.out.println("automate fred2");
                break;
            case GAMEOFLIFE:
                autoType = new GameOfLife();
                //System.out.println("automate gameoflife");
                break;
            default:
                autoType = null;
                //System.out.println("automate non trouvé");
                break;
        }
        return autoType;
    }

    /**
     * Méthode permettant de convertir une méthode d'extension(string) en type d'énumération
     * @param text la méthode d'extension de la grille
     * @return la méthode d'évolution de la grille  en type énuméré
     */
    public static Expansion toExpansion(String text) {
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

    /**
     * Méthode permettant de convertir un type d'évolution (string) en type d'énumération
     * @param text le type d'évolution choisi par les joueurs
     * @return le type d'évolution en type énuméré
     */
    public static Automaton toAutomaton(String text) {
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
}
