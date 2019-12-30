package view;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    public GameWindow() {
        //Game window
        setTitle("Game Window");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Getting the pane of the window
        JPanel windowsContents = (JPanel) getContentPane();
        windowsContents.setLayout(null);

        //The cursor of the game's speed
        JLabel cSpeedLabel = new JLabel("Speed's cursor : ");
        JSlider cSpeedSlider = new JSlider();

        //Adding the elements to the pane
        windowsContents.add(cSpeedLabel);
        windowsContents.add(cSpeedSlider);
    }

    private JPanel createGridGame(int gridSize) {
        JPanel gameGrid = new JPanel(new GridLayout(gridSize, gridSize));
        for(int i = 0; i < gridSize; i++) {
            gameGrid.add(new JButton());
        }
        return gameGrid;
    }

    public static void main(String[] args) {
        new GameWindow().setVisible(true);
    }
}
