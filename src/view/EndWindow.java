package view;

import controler.Facade;
import controler.GridController;
import utils.ViewUtils;

import javax.swing.*;
import java.awt.*;

public class EndWindow extends JFrame {
    public EndWindow(int turnNumber) {
        //EndWindow
        setTitle("End Window");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //The panel containing everything
        JPanel endWindow = (JPanel) getContentPane();
        endWindow.setLayout(new BorderLayout());

        //The header of the window
        endWindow.add(createHeader(), BorderLayout.NORTH);

        //The contents of the window
        endWindow.add(createMainContents(turnNumber), BorderLayout.CENTER);

        //The footer of the window
        endWindow.add(createFooter(), BorderLayout.SOUTH);

        //Setting the font for the window
        ViewUtils.changeFont(endWindow);

        //Settings the content pane of the end window
        setContentPane(endWindow);
    }

    /**
     * Méthode permettant de créer le titre de la fenêtre
     * @return le panel contenant tous les élements de cette partie de la fenêtre
     */
    private JPanel createHeader() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER));

        header.add(ViewUtils.createLabel("The result of the game"));

        return header;
    }

    /**
     * Méthode permettant de créer la fenêtre contenant toutes les informations à propos du gagnant
     * @param turnNumber le nombre de tours avec laquelle le joueur gagnant a gagné
     * @return le panel contenants tous les éléments sur la victoire
     */
    private JPanel createMainContents(int turnNumber) {
        JPanel contents = new JPanel(new GridLayout(4, 1, 0, 0));

        String winningPlayer;
        String winningAutomaton;

        int winnerIndex = GridController.getInstance().getWinner();
        int cellNumber = 0;

        if(winnerIndex == -1) {
            winningPlayer = "Draw";
            winningAutomaton = "None";
        }
        else {
            winningPlayer = "player n°" + (winnerIndex + 1);
            cellNumber = GridController.getInstance().cellCount(winnerIndex);
            winningAutomaton = GridController.getInstance().getGrids().get(winnerIndex).getStringStrategy();
        }

        contents.add(ViewUtils.createLabel("The winner: " + winningPlayer));
        contents.add(ViewUtils.createLabel("Automaton: " + winningAutomaton.toLowerCase()));
        contents.add(ViewUtils.createLabel("Number of turn: " + turnNumber));
        contents.add(ViewUtils.createLabel("Number of cells: " + cellNumber));

        return contents;
    }

    /**
     * Méthode permettant de créer les boutons du bas de la fenêtre
     * @return le panel contenant bouton/curseur
     */
    private JPanel createFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));

        footer.add(ViewUtils.createButton("Play again", 100, 30, actionEvent -> playAgain()));
        footer.add(ViewUtils.createButton("Exit", 100, 30, actionEvent -> System.exit(0)));

        return footer;
    }

    /**
     * Méthode permettat de rejouer au jeu
     */
    private void playAgain() {
        this.dispose();
        Facade.resetGame();
        Facade.initSettingsWindow();
    }
}
