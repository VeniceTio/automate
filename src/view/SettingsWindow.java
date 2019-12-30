package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Arrays;

public class SettingsWindow extends JFrame {

    //All the options for the game
    private ArrayList<String> _gameOptions = new ArrayList<>(Arrays.asList("Game of life", "Fredkin n°1", "Fredkin n°2"));
    private ArrayList<String> _extensionOptions = new ArrayList<>(Arrays.asList("Repetition", "Periodicity", "Symetry n°1", "Symetry n°2", "Constant"));

    //The panel containing everything
    private JPanel _settingsWindow = (JPanel) getContentPane();

    public SettingsWindow() {
        //Settings of the settings window
        setTitle("Settings window");
        setSize(420, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Set the layout for the settings window panel
        _settingsWindow.setLayout(new BorderLayout());

        //The panel containing the contents of the settings windows
        JPanel contents = new JPanel(new GridLayout( 6, 2, 10, 10));
        contents.setBorder(new EmptyBorder(0, 10, 0, 10));

        //The size of the grid
        JLabel gSizeLabel = new JLabel("Grid's size : ");
        JTextField gSizeText = new JTextField(10);
        gSizeText.setPreferredSize(new Dimension(0, 10));

        //The extension of the game
        JLabel extensionLabel = new JLabel("Game's extension : ");
        JComboBox<String> cboExtensions = new JComboBox(_extensionOptions.toArray());

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
        contents.add(gSizeLabel);
        contents.add(gSizeText);

        contents.add(extensionLabel);
        contents.add(cboExtensions);

        contents.add(turnNumLabel);
        contents.add(turnNumText);

        contents.add(createPlayerOption(_gameOptions));
        contents.add(createPlayerOption(_gameOptions));

        contents.add(cNumberLabel);
        contents.add(cNumberText);

        contents.add(gSpeedLabel);
        contents.add(gSpeedText);

        //Header of the settings window
        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Choose the settings for your game ! ");
        header.add(titleLabel);
        _settingsWindow.add(header, BorderLayout.NORTH);

        //Contents of the settings window
        _settingsWindow.add(contents, BorderLayout.CENTER);

        //Footer of the settings windows
        JButton quitButton = new JButton("Quit");
        JButton validateButton = new  JButton("Validate");
        quitButton.setPreferredSize(new Dimension(90, 30));
        validateButton.setPreferredSize(new Dimension(90, 30));

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setBorder(new EmptyBorder(0, 0, 0, 5));
        footer.add(quitButton);
        footer.add(validateButton);
        _settingsWindow.add(footer, BorderLayout.SOUTH);

        //Adding the action event for the elements
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                validateGame();
            }
        });
        //Settings the content pane of the settings window
        setContentPane(_settingsWindow);
    }

    private JPanel createPlayerOption(ArrayList<String> gameOptions) {
        JPanel playerPanel = new JPanel(new GridLayout());

        JLabel playerLabel = new JLabel("Player n°1 : ");
        JComboBox<String> cboGameOptions  = new JComboBox(gameOptions.toArray());

        //Adding the event selectedIndexChanged for the combobox
        cboGameOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                selectedIndexChanged(actionEvent);
            }
        });

        playerPanel.add(playerLabel);
        playerPanel.add(cboGameOptions);

        return playerPanel;
    }

    private void selectedIndexChanged(ActionEvent actionEvent) { //TODO
        JPanel pane = (JPanel) getContentPane().getComponent(6);
        JComboBox<String> cboSelected = (JComboBox) actionEvent.getSource();

        String gameOptionSelected = (String) cboSelected.getSelectedItem();
        ArrayList<String> gameOptionsRemaining = new ArrayList<>();

        for(String s : _gameOptions) {
            if(!s.equals(gameOptionSelected)) {
                gameOptionsRemaining.add(s);
            }
        }

        for(Component c : pane.getComponents()) {
            if((c instanceof JComboBox) && (!c.equals(cboSelected)) ) {
                JComboBox<String> cbo = (JComboBox) c;
                cbo.removeAllItems();
                for(String s : gameOptionsRemaining) {
                    cbo.addItem(s);
                }
            }
        }
    }

    private void validateGame() { //TODO
    }

    public static void main(String[] args) {
        new SettingsWindow().setVisible(true);
    }
}
