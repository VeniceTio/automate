package view;

import controler.Game;
import controler.GridController;
import model.State;
import utils.Observer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

public class GameWindow extends JFrame implements Observer{

    public static class MyButton extends JButton {
        int _index;
        MyButton(int index){
            super();
            _index=index;
            setOpaque(true);
            setEnabled(true);
        }
    }

    //TODO: commentaire à faire
    /**
     * Le nombre de cellule par joueur
     */
    private int _startCell;
    private boolean _init = false;
    private Color[] _players = {Color.BLUE,Color.RED};
    private ArrayList<MyButton> _cells = new ArrayList<>();
    private static SecureRandom _rand = new SecureRandom();
    private JPanel  _gridGame;

    /**
     * Méthode permettant de créer la fenêtre de jeu
     * @param size la taille de la grille de jeu
     * @param players les méthodes d'évolution des joueurs
     * @param cellNum le nombre de cellule par joueur
     */
    public GameWindow(int size, String[] players, int cellNum) {
        _startCell = cellNum;

        //Game window
        setTitle("Game Window");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //The panel containing everything
        JPanel windowsContents = (JPanel) getContentPane();
        windowsContents.setLayout(new BorderLayout());

        //Header of the window
        windowsContents.add(createHeader(players), BorderLayout.NORTH);

        //Grid of the window
        windowsContents.add(createGridGame(size), BorderLayout.CENTER);

        //Footer of the window
        windowsContents.add(createFooter(), BorderLayout.SOUTH);

        //Settings the content pane of the game window
        setContentPane(windowsContents);
    }

    /**
     * Méthode permettant de créer le titre de la fenêtre
     * @return le panel contenant tous les élements de cette partie de la fenêtre
     */
    private JPanel createHeader(String[] players) {
        JPanel header = new JPanel(new BorderLayout());
        JPanel title = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel playersPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel lbl = new JLabel("Automaton's game");
        JLabel playerOne = new JLabel("Player n°1: " + players[0]);
        JLabel playerTwo = new JLabel("Player n°2: " + players[1]);

        title.add(lbl);
        playersPanel.add(playerOne);
        playersPanel.add(playerTwo);

        header.add(title, BorderLayout.NORTH);
        header.add(playersPanel, BorderLayout.CENTER);

        return header;
    }

    /**
     * Méthode permettant de créer la grille de jeu
     * @param gridSize la taille de la grille
     * @return la panel contenant la grille de jeu
     */
    private JPanel createGridGame(int gridSize) {
        GridLayout gridLayout = new GridLayout(gridSize,gridSize,0,0);
        _gridGame = new JPanel(gridLayout);
        for(int i = 0; i < (gridSize * gridSize); i++) {
            MyButton cell = new MyButton(i);
            cell.setBackground(Color.white);
            cell.addActionListener(actionEvent -> select(cell));
            _gridGame.add(cell, false);
            _cells.add(cell);
        }
        return _gridGame;
    }

    /**
     * Méthode permettant de créer les boutons du bas de la fenêtre
     * @return le panel contenant bouton/curseur
     */
    private JPanel createFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel cSpeedLabel = new JLabel("Speed's cursor : ");
        JSlider cSpeedSlider = new JSlider();
        cSpeedSlider.setPreferredSize(new Dimension(280, 20));


        JButton button = new JButton("Start");
        button.setPreferredSize(new Dimension(80, 30));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(_init){
                    new Thread(()-> {
                        try {
                            Game.getInstance().automatonGame();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }).start();
                    button.setEnabled(false);
                }
            }
        });

        footer.add(cSpeedLabel);
        footer.add(cSpeedSlider);
        footer.add(button);

        return footer;
    }

    /**
     * TODO: commentaire à faire
     * @param button
     */
    public void select(MyButton button){
        if(!_init){
            GridController GC = GridController.getInstance();
            if (GC.count(0)!= _startCell){
                changeColor(button,0,true);
            }
            else if (GC.count(1)!= _startCell-1){
                if (button.getBackground()==Color.white){
                    changeColor(button,1,true);
                }
            } else if (GC.count(1)!= _startCell){
                if (button.getBackground()==Color.white) {
                    changeColor(button, 1, true);
                    _init = true;
                }
            }
        }
//        else {
//            button.setBackground(Color.MAGENTA);
//        }
    }

    /**
     * TODO: commentaire à faire
     * @param button
     * @param player
     * @param send
     */
    private void changeColor(MyButton button, int player,boolean send){
        Color newColor = Color.white;
        if(button.getBackground() == _players[player]){
            button.setBackground(newColor);
        }else {
            newColor = _players[player];
            button.setBackground(newColor);
        }
        if(send){
            if(newColor == Color.white) {
                GridController.getInstance().setStateGrid(player, button._index, State.DEAD);
            } else{
                GridController.getInstance().setStateGrid(player, button._index, State.ALIVE);
            }
        }
    }

    /**
     * TODO: commentaire à faire
     * @param player
     * @param nbPlayer
     * @param pos
     * @return
     */
    private Color fight(boolean[] player,int nbPlayer,int pos){
        int winner;
        Color newColor = Color.white;
        int index = -1;
        int idPlayer = -1;
        if(nbPlayer!=0) {
            winner = _rand.nextInt(nbPlayer+1);
        }else {
            winner = 0;
        }
        //System.out.println("## winner : "+winner);
        //System.out.println(Arrays.toString(player));
        for (boolean play:player){
            idPlayer++;
            if(play){
                index++;
                if(index==winner){
                    newColor = _players[idPlayer];
                }else {
                    GridController.getInstance().setStateGrid(idPlayer, pos, State.DEAD);
                }
            }
        }
        return newColor;
    }

    /**
     * TODO: commentaire à faire
     */
    public void update() {
        int nbCell;
        boolean[] players;
        MyButton button;
        GridController GC = GridController.getInstance();
        for(int i=0;i<_cells.size();i++){
            button = _cells.get(i);
            nbCell=-1;
            players = new boolean[]{false, false};
            for(int j=0;j<_players.length;j++){
                if (GC.getState(j,i) != State.DEAD){
                    nbCell++;
                    players[j] = true;
                    //System.out.println("#### cellule vivante pos="+i);
                }
            }
            if(nbCell==-1){
                button.setBackground(Color.white);
            } else {
                //System.out.println("#### combat pos="+i);
                Color newColor = fight(players, nbCell,i);
                //System.out.println("couleur : "+newColor);
                button.setBackground(newColor);
                //button.repaint();
            }
            //_gridGame.removeAll();
        }
//        _gridGame.repaint();
//        for(int i=0;i<_cells.size();i++){
//            _gridGame.add(_cells.get(i));
//        }

    }
}
