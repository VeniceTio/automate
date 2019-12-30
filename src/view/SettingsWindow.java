package view;

import javax.swing.*;
import java.awt.*;


public class SettingsWindow extends JFrame {
    public SettingsWindow() {
        //Settings window
        setTitle("Settings window");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //All the options for the game
        String[] gameOptions = {"Game of life", "Fredkin 1", "Fredkin 2"};
        String[] extensionOptions = {"Repetition", "Periodicity", "Symetry 1", "Symetry 2", "Constant"};

        //Getting the panel of the window
        JPanel windowsContents = (JPanel) getContentPane();
        windowsContents.setLayout(new GridLayout( 6, 2, 10, 10));

        //The size of the grid
        JLabel gSizeLabel = new JLabel("Grid's size : ");
        JTextField gSizeText = new JTextField(10);

        //The extension of the game
        JLabel extensionLabel = new JLabel("Game's extension : ");
        JComboBox<String> cboExtensions = new JComboBox<>(extensionOptions);

        //The number of turns in the game
        JLabel turnNumLabel = new JLabel("Number of turns : ");
        JTextField turnNumText = new JTextField(10);

        //The speed of the game
        JLabel gSpeedLabel = new JLabel("Game's speed : ");
        JTextField gSpeedText = new JTextField(10);

        //The number of cells for each player
        JLabel cNumberLabel = new JLabel("Number of cells : ");
        JTextField cNumberText = new JTextField(10);

        //Adding the elements to the pane
        windowsContents.add(gSizeLabel);
        windowsContents.add(gSizeText);

        windowsContents.add(extensionLabel);
        windowsContents.add(cboExtensions);

        windowsContents.add(turnNumLabel);
        windowsContents.add(turnNumText);

        windowsContents.add(createPlayerOption(gameOptions));
        windowsContents.add(createPlayerOption(gameOptions));

        windowsContents.add(cNumberLabel);
        windowsContents.add(cNumberText);

        windowsContents.add(gSpeedLabel);
        windowsContents.add(gSpeedText);

        //Settings the contents to the JFrame
        setContentPane(windowsContents);
    }

    private JPanel createPlayerOption(String[] gameOptions) {
        JPanel playerPanel = new JPanel(new BorderLayout());

        JLabel playerLabel = new JLabel("Player n°1 : ");
        JComboBox<String> cboPlayerOne = new JComboBox<>(gameOptions);

        playerPanel.add(playerLabel, BorderLayout.WEST);
        playerPanel.add(cboPlayerOne);

        return playerPanel;
    }

    public static void main(String[] args) {
        new SettingsWindow().setVisible(true);
    }
}
