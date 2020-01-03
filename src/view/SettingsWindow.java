package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.Arrays;

public class SettingsWindow extends JFrame {
    //All the options for the game
    private ArrayList<String> _gameOptions;//= new ArrayList<>(Arrays.asList("Game of life", "Fredkin n°1", "Fredkin n°2"));
    private ArrayList<String> _expansionOptions;//= new ArrayList<>(Arrays.asList("Repetition", "Periodicty", "Symetry n°1", "Symetry n°2","Constant"));
    private JLabel _userMessage = createLabel("");

    class IntFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.insert(offset, string);

            if (isInt(sb.toString())) {
                correctValue();
                super.insertString(fb, offset, string, attr);
            }
            else {
                incorrectValue();
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.replace(offset, offset + length, text);

            if (isInt(sb.toString())) {
                correctValue();
                super.replace(fb, offset, length, text, attrs);
            }
            else {
                incorrectValue();
            }

        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.delete(offset, offset + length);

            if(sb.toString().isEmpty()) {
                super.replace(fb, offset, length, "", null);
            }
            else {
                if (isInt(sb.toString())) {
                    correctValue();
                    super.remove(fb, offset, length);
                }
                else {
                    incorrectValue();
                }
            }
        }

        private void correctValue() {
            _userMessage.setText("");
            _userMessage.setVisible(false);
        }

        private void incorrectValue() {
            _userMessage.setText("Invalid value !");
            _userMessage.setForeground(Color.RED);
            _userMessage.setVisible(true);
        }

        private boolean isInt(String text) {
            try {
                Integer.parseInt(text);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

    }

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

        //Settings the content pane of the settings window
        setContentPane(settingsWindow);
    }

    /**
     * Méthode permettant de créer un label
     * @param text le texte qu'affichera le label
     * @return le label
     */
    private JLabel createLabel(String text) {
        return new JLabel(text);
    }

    /**
     * Méthode permettant de créer un champ texte
     * @param size la taille du champ texte
     * @return le champ texte
     */
    private JTextField createTextField(int size, String toolTipText) {
        JTextField text = new JTextField(size);
        text.setToolTipText(toolTipText);

        PlainDocument doc = (PlainDocument) text.getDocument();
        doc.setDocumentFilter(new IntFilter());

        return text;
    }

    /**
     * Méthode permettant de créer une combobox
     * @param values les valeurs qui seront affichée dans la combobox
     * @param al l'événement de cette combobox
     * @return la combobox
     */
    private JComboBox<Object> createComboBox(Object[] values, ActionListener al) {
        JComboBox<Object> cbo = new JComboBox<>(values);
        cbo.addActionListener(al);
        return cbo;
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
     * Méthode permettant de créer le titre de la fenêtre
     * @return le panel contenant tous les élements de cette partie de la fenêtre
     */
    private JPanel createHeader() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER));
        header.add(createLabel("Choose the settings for your game !"));
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
        settingsContents.add(createLabel("Grid's size : "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        settingsContents.add(createTextField(10, "Enter a value between 2 and 50"), gbc);

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
        settingsContents.add(createTextField(10, ""), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        settingsContents.add(createLabel("Number of turns : "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        settingsContents.add(createTextField(10, ""), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        settingsContents.add(createGamesOption(_gameOptions, 2), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        settingsContents.add(createLabel("Number of cells : "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        settingsContents.add(createTextField(10, ""), gbc);

        return settingsContents;
    }

    /**
     * Méthode permettant de créer les boutons du bas de la fenêtre
     * @return le panel contenant les boutons
     */
    private JPanel createFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setBorder(new EmptyBorder(0, 0, 0, 6));
        footer.add(_userMessage);
        footer.add(createButton("Quit", 90, 30, actionEvent -> System.exit(0)));
        footer.add(createButton("Validate", 90, 30, actionEvent -> confirmSettings()));
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
            gamesOptionPanel.add(createLabel("Player n°" + (i + 1) + " : "));
            gamesOptionPanel.add(createComboBox(gameOptions.toArray(), actionEvent -> updateGameOptions(actionEvent)));
        }

        return gamesOptionPanel;
    }

    /**
     * Méthode permettant de récupérer les informations dans les combobox
     * @param c le composant qu'on doit cast en combobox
     * @return l'information récupérer de la combobox
     */
    private String cboDataRetrieving(Component c) {
        JComboBox cbo = (JComboBox) c;
        return String.valueOf(cbo.getSelectedItem());
    }

    /**
     * Méthode permettant de mettre à jour les options de jeu restantes
     * @param e l'action event
     */
    private void updateGameOptions(ActionEvent e) {
        JComboBox<String> cboSelected = (JComboBox) e.getSource();
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
     * Méthode permettant de récupérer toutes les informations de tous les champs de la fenêtre
     */
    private void confirmSettings() {
        ArrayList<Integer> numericParameters = new ArrayList<>();
        ArrayList<String> textParameters = new ArrayList<>();
        JPanel settingsContents = (JPanel) getContentPane().getComponent(1);

        for(Component comp: settingsContents.getComponents()) {
            if(comp instanceof JTextField) {
                JTextField text = (JTextField) comp;
                int parameter = Integer.parseInt(text.getText());
                numericParameters.add(parameter);
            }
            if(comp instanceof JComboBox) {
                textParameters.add(cboDataRetrieving(comp));
            }
            if(comp instanceof JPanel) {
                JPanel gameOptionsPanel = (JPanel) comp;
                for(Component c: gameOptionsPanel.getComponents()) {
                    if(c instanceof JComboBox) {
                        textParameters.add(cboDataRetrieving(c));
                    }
                }
            }
        }


        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSED));
        Facade.initGameWindow(numericParameters, textParameters);
    }
}