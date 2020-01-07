package view;

import javax.swing.*;

public class MyButton extends JButton {

    /**
     * L'index du bouton représentant sur quel cellule il se situe
     */
    private final int _index;

    /**
     * Méthode permettant d'initialiser une instance de la classe MyButton
     * @param index cellule représenté
     */
    MyButton(int index){
        super();
        _index=index;
        setOpaque(true);
        setEnabled(true);
    }

    /**
     * return l'index de la cellule représenté
     * @return _index
     */
    public int getIndex(){
        return _index;
    }
}
