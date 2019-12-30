package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;


public class SettingsWindow extends JFrame {

    //All the options for the game
    private ArrayList<String> gameOptions = new ArrayList<>(Arrays.asList("Game of life", "Fredkin 1", "Fredkin 2"));
    private ArrayList<String> extensionOptions = new ArrayList<>(Arrays.asList("Repetition", "Periodicity", "Symetry 1", "Symetry 2", "Constant"));

    public SettingsWindow() {
        //Settings window
        setTitle("Settings window");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        //Getting the panel of the window
        JPanel windowsContents = (JPanel) getContentPane();
        windowsContents.setLayout(new GridLayout( 6, 2, 10, 10));

        //The size of the grid
        JLabel gSizeLabel = new JLabel("Grid's size : ");
        JTextField gSizeText = new JTextField(10);

        //The extension of the game
        JLabel extensionLabel = new JLabel("Game's extension : ");
        JComboBox<String> cboExtensions = new JComboBox(extensionOptions.toArray());

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

        for(Component c : getContentPane().getComponents()) {
            System.out.println(c);
        }
    }

    private JPanel createPlayerOption(ArrayList<String> gameOptions) {
        JPanel playerPanel = new JPanel(new GridLayout());

        JLabel playerLabel = new JLabel("Player n°1 : ");
        JComboBox<String> cboGameOptions  = new JComboBox(gameOptions.toArray());

        //Adding the action listener for each elements
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

    private void selectedIndexChanged(ActionEvent actionEvent) {
        JPanel pane = (JPanel) getContentPane().getComponent(6);
        JComboBox cboSelected = (JComboBox) actionEvent.getSource();

        String gameOptionSelected = (String) cboSelected.getSelectedItem();
        ArrayList<String> gameOptionsRemaining = new ArrayList<>();

        for(String s : gameOptions) {
            if(!s.equals(gameOptionSelected)) {
                gameOptionsRemaining.add(s);
            }
        }

        for(Component c : pane.getComponents()) {
            if((c instanceof JComboBox) && (!c.equals(cboSelected)) ) {
                JComboBox cbo = (JComboBox) c;
                cbo.removeAllItems();
                for(String s : gameOptionsRemaining) {
                    cbo.addItem(s);
                }
            }
        }
    }

    public static void main(String[] args) {
        new SettingsWindow().setVisible(true);
    }
}
