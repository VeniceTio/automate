package view;

import controler.GridController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EndWindow extends JFrame {
    public EndWindow(int turnNumber) {
        //EndWindow
        setTitle("End Window");
        setSize(400, 300);
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

        //Settings the content pane of the end window
        setContentPane(endWindow);
    }

    /**
     * Méthode permettant de créer un bouton
     * @param text le texte qu'affichera le bouton
     * @param width la largeur du bouton
     * @param height la hauteur du bouton
     * @param al l'événement du bouton
     * @return le bouton
     */
    private JButton createButton(String text, int width, int height, ActionListener al) {
        JButton button = new JButton(text);

        button.setPreferredSize(new Dimension(width, height));
        button.addActionListener(al);
        return button;
    }

    /**
     * Méthode permettant de créer un label
     * @param text le texte qu'affichera le label
     * @return le label
     */
    private JLabel createLabel(String text) {
        return new JLabel(text);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER));

        header.add(createLabel("The result of the game"));

        return header;
    }

    private JPanel createMainContents(int turnNumber) {
        JPanel contents = new JPanel(new GridLayout(3, 1, 0, 0));

        String automatonWinner;
        int winnerIndex = GridController.getInstance().getWinner();
        int cellNumber = 0;

        if(winnerIndex == -1) {
            automatonWinner = "Draw";
        }
        else {
            cellNumber = GridController.getInstance().count(winnerIndex);
            automatonWinner = GridController.getInstance().getGrids().get(winnerIndex).getStringStrategy();
        }

        contents.add(createLabel("The winnner: " + automatonWinner));
        contents.add(createLabel("Number of turn: " + turnNumber));
        contents.add(createLabel("Number of cells: " + cellNumber));

        return contents;
    }

    private JPanel createFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));

        footer.add(createButton("Play again", 100, 30, actionEvent -> playAgain()));
        footer.add(createButton("Exit", 100, 30, actionEvent -> System.exit(0)));

        return footer;
    }

    private void playAgain() {
        this.dispose();
        Facade.resetGame();
        Facade.initSettingsWindow();
    }
}
