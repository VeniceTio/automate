package view;

import javax.swing.*;

public class MyButton extends JButton {
    /*
    * index correspondant a la cellule quel représente
    */
    private final int _index;

    /**
     * Constructor de MyButton
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
