package view;

import controler.GridController;
import controler.ViewController;
import utils.ViewUtilities;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

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
        ViewUtilities.changeFont(endWindow);

        //Settings the content pane of the end window
        setContentPane(endWindow);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER));

        header.add(ViewUtilities.createLabel("The result of the game"));

        return header;
    }

    private JPanel createMainContents(int turnNumber) {
        JPanel contents = new JPanel(new GridLayout(4, 1, 0, 0));
        //lbl.setHorizontalAlignment(JLabel.CENTER);

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
            cellNumber = GridController.getInstance().count(winnerIndex);
            winningAutomaton = GridController.getInstance().getGrids().get(winnerIndex).getStringStrategy();
        }

        contents.add(ViewUtilities.createLabel("The winner: " + winningPlayer));
        contents.add(ViewUtilities.createLabel("Automaton: " + winningAutomaton.toLowerCase()));
        contents.add(ViewUtilities.createLabel("Number of turn: " + turnNumber));
        contents.add(ViewUtilities.createLabel("Number of cells: " + cellNumber));

        return contents;
    }

    private JPanel createFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));

        footer.add(ViewUtilities.createButton("Play again", 100, 30, actionEvent -> playAgain()));
        footer.add(ViewUtilities.createButton("Exit", 100, 30, actionEvent -> System.exit(0)));

        return footer;
    }

    private void playAgain() {
        this.dispose();
        Facade.resetGame();
        Facade.initSettingsWindow();
    }
}
