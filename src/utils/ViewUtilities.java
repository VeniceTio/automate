package utils;

import model.Automaton;
import model.Expansion;
import view.SettingsWindow;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

public class ViewUtilities {
    /**
     * Classe permettant de permettant de filtrer seulement les chiffres dans JTextfield
     */
    private static class IntFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.insert(offset, string);

            if (isInt(sb.toString())) {
                super.insertString(fb, offset, string, attr);
                correctValue();
            }
            else {
                incorrectValue("Invalid value !");
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.replace(offset, offset + length, text);

            if(isInt(sb.toString())) {
                super.replace(fb, offset, length, text, attrs);
                correctValue();
            }
            else {
                incorrectValue("Invalid value !");
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
                    super.remove(fb, offset, length);
                    correctValue();

                }
                else {
                    incorrectValue("Invalid value !");
                }
            }
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
     * Classe permettant de mettre un filtre sur l'étendue des valeurs requises pour le jeu
     */
    private static class RangeInputVerifier extends InputVerifier {
        private int _lowerBound;
        private int _upperBound;

        public RangeInputVerifier(int min, int max) {
            _lowerBound = min;
            _upperBound = max;
        }

        private int getLowerBound() {
            return _lowerBound;
        }

        private int getUpperBound() {
            return _upperBound;
        }

        @Override
        public boolean verify(JComponent input) {
            JTextField textField = (JTextField) input;
            boolean inRange = true;
            if(!textField.getText().isEmpty()) {
                inRange = inRange(getLowerBound(), getUpperBound(), Integer.parseInt(textField.getText()));
                if(inRange) {
                    correctValue();
                }
                else {
                    incorrectValue("Invalid value !");
                }
            }
            return inRange;
        }

        private boolean inRange(int min, int max, int value) {
            return min <= value && value <= max;
        }
    }

    /**
     * Méthode permettant d'enlever le message d'erreur
     */
    public static void correctValue() {
        SettingsWindow.getUserMessage().setText("");
        SettingsWindow.getUserMessage().setVisible(false);
    }

    /**
     * Méthode permettant d'afficher un message d'erreur
     */
    public static void incorrectValue(String text) {
        SettingsWindow.getUserMessage().setText(text);
        SettingsWindow.getUserMessage().setVisible(true);
    }

    /**
     * Méthode permettant de créer un label
     * @param text le texte qu'affichera le label
     * @return le label
     */
    public static JLabel createLabel(String text) {
        return new JLabel(text);
    }

    /**
     * Méthode permettant de créer un champ texte
     * @param size la taille du champ texte
     * @return le champ texte
     */
    public static JTextField createTextField(int size, int min, int max, String toolTipText, FocusListener fl) {
        JTextField text = new JTextField(size);
        text.setToolTipText(toolTipText);
        text.setInputVerifier(new RangeInputVerifier(min, max));
        text.addFocusListener(fl);
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
    public static JComboBox<Object> createComboBox(Object[] values, ActionListener al) {
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
    public static JButton createButton(String text, int width, int height, ActionListener al) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));
        button.addActionListener(al);
        return button;
    }

    /**
     * Méthode permettant de changer la police de chaque composant d'une JFrame
     * @param component le composant root qui contient tous les autres composants
     */
    public static void changeFont(Component component) {
        component.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        if (component instanceof Container) {
            Container container = (Container) component;
            for (Component child: container.getComponents()) {
                changeFont(child);
            }
        }
    }
}
