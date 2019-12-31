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
        setSize(420, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //The panel containing everything
        JPanel _settingsWindow = (JPanel) getContentPane();
        _settingsWindow.setLayout(new BorderLayout());

        //The panel containing the contents of the settings window
        JPanel contents = new JPanel(new GridLayout(6, 1, 10, 10));
        contents.setBorder(new EmptyBorder(0, 10, 0, 10));

        //Adding the elements to the pane
        contents.add(createLabel("Grid's size : "));
        contents.add(createTextField(10));

        contents.add(createLabel("Games's extension : "));
        contents.add(createComboBox(_expansionOptions.toArray()));

        contents.add(createLabel("Games's speed : "));
        contents.add(createTextField(10));
        
        //contents.add(createGamesOption(_gameOptions, 2));

        contents.add(createLabel("Number of turns"));
        contents.add(createTextField(10));

        contents.add(createLabel("Number of cells"));
        contents.add(createTextField(10));

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

    private JLabel createLabel(String text) { //TODO
        return new JLabel(text);
    }

    private JTextField createTextField(int size) { //TODO
        return new JTextField(size);
    }

    private JComboBox<?> createComboBox(Object[] values) { //TODO
        return new JComboBox<>(values);
    }

    private JPanel createGamesOption(ArrayList<String> gameOptions, int numPlayer) {
        JPanel playerPanel = new JPanel(new GridLayout(numPlayer, 2));

        for(int i = 1; i < numPlayer; i++) {
            playerPanel.setBorder(new EmptyBorder(0, 0,0, 0));
            JLabel playerLabel = new JLabel("Player n°" + numPlayer + " : ");
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
        }

        return playerPanel;
    }

    private void selectedIndexChanged(ActionEvent e) { //TODO
        JPanel contents = (JPanel) getContentPane().getComponent(1);
        JComboBox<String> cboSelected = (JComboBox) e.getSource();
        JPanel t = (JPanel) cboSelected.getParent();

        for(Component c : t.getComponents()) {
            System.out.println(c);
        }

        String gameOptionSelected = (String) cboSelected.getSelectedItem();
        ArrayList<String> gameOptionsRemaining = new ArrayList<>();

        for(String s : _gameOptions) {
            if(!s.equals(gameOptionSelected)) {
                gameOptionsRemaining.add(s);
            }
        }

        for(Component c : contents.getComponents()) {
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
