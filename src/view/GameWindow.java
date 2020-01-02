package view;

import model.Automaton;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    /**
     * Méthode permettant de créer la fenêtre de jeu
     * @param size
     */
    public GameWindow(int size, String[] players) {
        //Game window
        setTitle("Game Window");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //The panel containing everything
        JPanel windowsContents = (JPanel) getContentPane();
        windowsContents.setLayout(new BorderLayout());

        //Header of the window
        windowsContents.add(createHeader(players), BorderLayout.NORTH);

        //Grid of the window
        windowsContents.add(createGridGame(size), BorderLayout.CENTER);

        //Footer of the window
        windowsContents.add(createFooter(), BorderLayout.SOUTH);

        //Settings the content pane of the game window
        setContentPane(windowsContents);
    }

    /**
     * Méthode permettant de créer le titre de la fenêtre
     * @return le panel contenant tous les élements de cette partie de la fenêtre
     */
    private JPanel createHeader(String[] players) {
        JPanel header = new JPanel(new BorderLayout());
        JPanel title = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel playersPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel lbl = new JLabel("Automaton's game");
        JLabel playerOne = new JLabel("Player n°1: " + players[0]);
        JLabel playerTwo = new JLabel("Player n°2: " + players[1]);

        title.add(lbl);
        playersPanel.add(playerOne);
        playersPanel.add(playerTwo);

        header.add(title, BorderLayout.NORTH);
        header.add(playersPanel, BorderLayout.CENTER);

        return header;
    }

    /**
     * Méthode permettant de créer la grille de jeu
     * @param gridSize la taille de la grille
     * @return la panel contenant la grille de jeu
     */
    private JPanel createGridGame(int gridSize) {
        JPanel gridGame = new JPanel(new GridLayout(gridSize, gridSize));
        for(int i = 0; i < gridSize * gridSize; i++) {
            gridGame.add(new JCheckBox(), false);
        }
        return gridGame;
    }

    /**
     * Méthode permettant de créer les boutons du bas de la fenêtre
     * @return le panel contenant bouton/curseur
     */
    private JPanel createFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel cSpeedLabel = new JLabel("Speed's cursor : ");
        JSlider cSpeedSlider = new JSlider();
        cSpeedSlider.setPreferredSize(new Dimension(280, 20));


        JButton button = new JButton("Start");
        button.setPreferredSize(new Dimension(80, 30));

        footer.add(cSpeedLabel);
        footer.add(cSpeedSlider);
        footer.add(button);

        return footer;
    }
}
