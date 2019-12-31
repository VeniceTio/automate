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
    private ArrayList<String> _expansionOptions = new ArrayList<>(Arrays.asList("Repetition", "Periodicity", "Symetry n°1", "Symetry n°2", "Constant"));

    public SettingsWindow() {
        //Settings of the settings window
        setTitle("Settings window");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //The panel containing everything
        JPanel settingsWindow = (JPanel) getContentPane();
        settingsWindow.setLayout(new BorderLayout());

        //Header of the settings window
        settingsWindow.add(createHeader(), BorderLayout.NORTH);

        //Contents of the settings window
        settingsWindow.add(createSettingsContents(), BorderLayout.CENTER);

        //Footer of the settings windows
        settingsWindow.add(createFooter(), BorderLayout.SOUTH);

        //Settings the content pane of the settings window
        setContentPane(settingsWindow);
    }

    private JLabel createLabel(String text) {
        return new JLabel(text);
    }

    private JTextField createTextField(int size) {
        return new JTextField(size);
    }

    private JComboBox<Object> createComboBox(Object[] values, ActionListener al) {
        JComboBox<Object> cbo = new JComboBox<>(values);
        cbo.setPreferredSize(new Dimension(20, 10));
        cbo.addActionListener(al);
        return cbo;
    }

    private JButton createButton(String text, int width, int height, ActionListener al) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));
        button.addActionListener(al);
        return button;
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER));
        header.add(createLabel("Choose the settings for your game !"));
        return header;
    }

    private JPanel createSettingsContents() {
        JPanel settingsContents = new JPanel(new GridBagLayout());
        settingsContents.setBorder(new EmptyBorder(0, 0, 0, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.weightx = 5;
        gbc.weighty = 5;

        //Adding the elements to the pane
        gbc.gridx = 0;
        gbc.gridy = 0;
        settingsContents.add(createLabel("Grid's size : "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        settingsContents.add(createTextField(10), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        settingsContents.add(createLabel("Games's extension : "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        settingsContents.add(createComboBox(_expansionOptions.toArray(), null), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        settingsContents.add(createLabel("Games's speed : "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        settingsContents.add(createTextField(10), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        settingsContents.add(createLabel("Number of turns : "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        settingsContents.add(createTextField(10), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        settingsContents.add(createGamesOption(_gameOptions, 2), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        settingsContents.add(createLabel("Number of cells : "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        settingsContents.add(createTextField(10), gbc);


        return settingsContents;
    }

    private JPanel createFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setBorder(new EmptyBorder(0, 0, 0, 5));
        footer.add(createButton("Quit", 90, 30, actionEvent -> System.exit(0)));
        footer.add(createButton("Validate", 90, 30, actionEvent -> confirmSettings()));
        return footer;
    }

    private JPanel createGamesOption(ArrayList<String> gameOptions, int numPlayer) { //TODO: change the size of the cells
        JPanel gamesOptionPanel = new JPanel(new GridLayout(numPlayer, 2, 14,10));
        gamesOptionPanel.setBorder(new EmptyBorder(0, 0,0, 0));

        for(int i = 1; i <= numPlayer; i++) {
            gamesOptionPanel.add(createLabel("Player n°" + i + " : "));
            gamesOptionPanel.add(createComboBox(gameOptions.toArray(), actionEvent -> updateGameOptions(actionEvent)));
        }

        return gamesOptionPanel;
    }

    private void updateGameOptions(ActionEvent e) { //TODO: problem here
        JComboBox<String> cboSelected = (JComboBox) e.getSource();
        JPanel contents = (JPanel) cboSelected.getParent();

        String gameOptionSelected = (String) cboSelected.getSelectedItem();
        ArrayList<String> gameOptionsRemaining = new ArrayList<>();

        for(String s: _gameOptions) {
            if(!s.equals(gameOptionSelected)) {
                gameOptionsRemaining.add(s);
            }
        }

        System.out.println(gameOptionsRemaining);

        for(Component c : contents.getComponents()) {
            if((c instanceof JComboBox) && (!c.equals(cboSelected))) {
                JComboBox<String> cbo = (JComboBox) c;
                cbo.removeAllItems();
                for(String s: gameOptionsRemaining) {
                    cbo.addItem(s);
                }
            }
        }
    }

    private void confirmSettings() { //TODO
    }

    public static void main(String[] args) {
        new SettingsWindow().setVisible(true);
    }
}