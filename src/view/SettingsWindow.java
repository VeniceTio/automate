package view;

import controler.Facade;
import utils.ViewUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SettingsWindow extends JFrame {
    //All the options for the game
    private ArrayList<String> _gameOptions;
    private ArrayList<String> _expansionOptions;
    private static JLabel _userMessage = ViewUtils.createLabel("");

    /**
     * Méthode permettant de créer la fenêtre de paramètrage du jeu
     */
    public SettingsWindow(ArrayList<String> automatons,ArrayList<String> expansions) {
        _expansionOptions = expansions;
        _gameOptions = automatons;

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

        //Setting the font for the window
        ViewUtils.changeFont(settingsWindow);

        //Settings the content pane of the settings window
        setContentPane(settingsWindow);
    }

    /**
     * Méthode permettant de renvoyer la label contenant le message affiché à l'utilisateur
     * @return la label contenant le message
     */
    public static JLabel getUserMessage() {
        return _userMessage;
    }

    /**
     * Méthode permettant de créer le titre de la fenêtre
     * @return le panel contenant tous les élements de cette partie de la fenêtre
     */
    private JPanel createHeader() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER));
        header.add(ViewUtils.createLabel("Choose the settings for your game !"));
        return header;
    }

    /**
     * Méthode permettant de créer tous les paramètres nécéssaires au jeu que le joueur pourra modifier
     * @return le panel contenant tous ces paramètres
     */
    private JPanel createSettingsContents() {
        JPanel settingsContents = new JPanel(new GridBagLayout());
        settingsContents.setBorder(new EmptyBorder(0, 0, 0, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1;
        gbc.weighty = 1;

        //Adding the components to the pane
        gbc.gridx = 0;
        gbc.gridy = 0;
        settingsContents.add(ViewUtils.createLabel("Grid's size : "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        settingsContents.add(ViewUtils.createTextField(10, 2, 20, "Enter a value between 2 and 20"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        settingsContents.add(ViewUtils.createLabel("Games's extension : "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        settingsContents.add(ViewUtils.createComboBox(_expansionOptions.toArray(), null), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        settingsContents.add(ViewUtils.createLabel("Games's speed : "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        settingsContents.add(ViewUtils.createTextField(10, 1, 100, "Enter a value between 1 and 100"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        settingsContents.add(ViewUtils.createLabel("Number of turns : "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        settingsContents.add(ViewUtils.createTextField(10, 1, 100,"Enter a value between 1 and 100"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        settingsContents.add(createGamesOption(_gameOptions, 2), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        settingsContents.add(ViewUtils.createLabel("Number of cells : "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        settingsContents.add(ViewUtils.createTextField(10, 1, 200, "Enter a value between 1 and 200"), gbc);

        return settingsContents;
    }

    /**
     * Méthode permettant de créer les boutons du bas de la fenêtre
     * @return le panel contenant les boutons
     */
    private JPanel createFooter() {
        _userMessage.setForeground(Color.RED);
        _userMessage.setBorder(new EmptyBorder(0, 5, 0, 0));

        JPanel footer = new JPanel(new BorderLayout());
        footer.setBorder(new EmptyBorder(0, 0, 0, 0));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(ViewUtils.createButton("Exit", 90, 30, actionEvent -> System.exit(0)));
        buttons.add(ViewUtils.createButton("Validate", 90, 30, actionEvent -> validateButton()));

        footer.add(_userMessage, BorderLayout.WEST);
        footer.add(buttons, BorderLayout.EAST);

        return footer;
    }

    /**
     * Méthode permettant de créer les combobox des options de jeu
     * @param gameOptions les options disponibles dans le jeu
     * @param numPlayer le nombre de joueurs
     * @return la panel contenant toutes les combobox contentant les options de jeu
     */
    private JPanel createGamesOption(ArrayList<String> gameOptions, int numPlayer) { //TODO: change the size of the cells
        JPanel gamesOptionPanel = new JPanel(new GridLayout(numPlayer, 2, 14,10));
        gamesOptionPanel.setBorder(new EmptyBorder(0, 0,0, 0));

        for(int i = 0; i < numPlayer; i++) {
            gamesOptionPanel.add(ViewUtils.createLabel("Player n°" + (i + 1) + " : "));
            gamesOptionPanel.add(ViewUtils.createComboBox(gameOptions.toArray(), this::updateGameOptions));
        }

        return gamesOptionPanel;
    }

    /**
     * Méthode permettant de récupérer les informations dans les combobox
     * @param c le composant qu'on doit cast en combobox
     * @return l'information récupérer de la combobox
     */
    private String cboDataRetrieving(Component c) {
        JComboBox<String> cbo = (JComboBox) c;
        return String.valueOf(cbo.getSelectedItem());
    }

    /**
     * Méthode permettant de mettre à jour les options de jeu restantes
     * @param e l'action event
     */
    private void updateGameOptions(ActionEvent e) {
        JComboBox cboSelected = (JComboBox) e.getSource();
        JPanel contents = (JPanel) cboSelected.getParent();
        String gameOptionSelected = String.valueOf(cboSelected.getSelectedItem());

        if(gameOptionSelected != null) {
            for(Component comp: contents.getComponents()) {
                if((comp instanceof JComboBox) && (!comp.equals(cboSelected))) {
                    JComboBox<String> cboTemp = (JComboBox) comp;
                    String optionTemp = String.valueOf(cboTemp.getSelectedItem());
                    cboTemp.removeAllItems();

                    for(String option: _gameOptions) {
                        if(!option.equals(gameOptionSelected)) {
                            cboTemp.addItem(option);
                        }
                    }

                    cboTemp.setSelectedItem(optionTemp);
                    cboSelected.setSelectedItem(gameOptionSelected);
                }
            }
        }
    }

    /**
     * Méthode permettant de tester si la liste est vide
     * @param numericList la liste qu'on doit tester
     * @return vrai si la liste continent une valeur null faux sinon
     */
    private boolean isNull(ArrayList<Integer> numericList) {
        boolean res = false;
        int i = 0;

        if(numericList.size() == 4) {
            while(i < numericList.size() && !res) {
                String value = String.valueOf(numericList.get(i));
                if(value.isEmpty()) res = true;
                i++;
            }
        }
        else {
            res = true;
        }
        return res;
    }

    /**
     * Méthode permettant de savoir si une valeur est dupliqué
     * @param textList la liste qu'on doit tester
     * @return vrai si la valeur est dupliqué faux sinon
     */
    private boolean isDuplicated(ArrayList<String> textList) {
        boolean res = false;
        int i = 0;

        if(!textList.isEmpty()) {
            while(i < textList.size() && !res) {
                String text = textList.get(i);
                if(Collections.frequency(textList, text) > 1) res = true;
                i++;
            }
        }
        return res;
    }

    /**
     * Méthode permettant de vérifier les paramètres
     * @param nullValue le booléen représentant si une valeur est nulle
     * @param alreadySelected le booléen représentant si une valeur est déjà sélectionné
     * @return le booleen contenant le resultat de des deux autres booleen
     */
    private boolean checkParameters(boolean nullValue, boolean alreadySelected) {
        ArrayList<String> messages = new ArrayList<>(Arrays.asList("Incorrect value(s) - Same options chosen", "Incorrect value(s)", "Same options chosen"));
        String message = "";

        if(nullValue && alreadySelected) {
            message = messages.get(0);
        }
        else if(nullValue) {
            message = messages.get(1);
        }
        else if(alreadySelected) {
            message = messages.get(2);
        }

        ViewUtils.incorrectValue(message);
        return (!nullValue && !alreadySelected);
    }

    /**
     * Méthode permettant de récupérer les différents paramètres des composants
     * @param component le composant sur lequel les paramètres doivent être récupérer
     * @param numericList la liste des paramètres numérique
     * @param textList la liste des paramètres textuels
     */
    private void retrievingParameters(Component component, ArrayList<Integer> numericList, ArrayList<String> textList) {
        if(component instanceof JTextField) {
            JTextField text = (JTextField) component;
            if(!text.getText().isEmpty())
                numericList.add(Integer.parseInt(text.getText()));
        }
        else if(component instanceof JComboBox) {
            textList.add(cboDataRetrieving(component));
        }
        else if(component instanceof JPanel) {
            for(Component c: ((JPanel) component).getComponents()) {
                retrievingParameters(c, numericList, textList);
            }
        }
    }

    /**
     * Méthode qui est lancée par l'appui du bouton "validate"
     */
    private void validateButton() {
        JPanel settingsContents = (JPanel) getContentPane().getComponent(1);
        ArrayList<Integer> numericParameters = new ArrayList<>();
        ArrayList<String> textParameters = new ArrayList<>();
        boolean alreadySelected;
        boolean nullValue;

        retrievingParameters(settingsContents, numericParameters, textParameters);

        nullValue = isNull(numericParameters);
        alreadySelected = isDuplicated(textParameters);

        if (checkParameters(nullValue, alreadySelected)) {
            ViewUtils.correctValue();

            if (checkParameters(nullValue, alreadySelected)) {
                ViewUtils.correctValue();
                Facade.initGameWindow(numericParameters, textParameters);
            }
        }
    }
}