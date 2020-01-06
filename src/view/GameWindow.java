package view;

import controler.Game;
import controler.GridController;
import model.State;
import utils.Observer;
import utils.ViewUtils;
import javax.swing.*;
import java.awt.*;
import java.security.SecureRandom;
import java.util.ArrayList;

public class GameWindow extends JFrame implements Observer {


    /**
     * Le nombre de cellule par joueur au depart
     */
    private final int _startCell;
    /**
     * est en initialisé
     */
    private boolean _init = false;
    /**
     * couleur des joueurs
     */
    private final Color[] _players;
    /**
     * liste des bouttons representant les cellules
     */
    private final ArrayList<MyButton> _cells = new ArrayList<>();
    /**
     * Nombre aleatoire pour les combat
     */
    private static final SecureRandom _rand = new SecureRandom();

    /**
     * Méthode permettant de créer la fenêtre de jeu
     * @param size la taille de la grille de jeu
     * @param players les méthodes d'évolution des joueurs
     * @param cellNum le nombre de cellule par joueur
     */
    public GameWindow(int size, String[] players, int cellNum,int gamespeed,Color[] playersColor) {
        _startCell = cellNum;
        _players = playersColor;

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
        windowsContents.add(createFooter(gamespeed), BorderLayout.SOUTH);

        //Setting the font for the window
        ViewUtils.changeFont(windowsContents);

        JOptionPane.showMessageDialog(windowsContents, "Le joueur 2 commence !", "Message", JOptionPane.INFORMATION_MESSAGE);

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
        JLabel playerOne = new JLabel("Player n°1: " + players[0].toLowerCase() + " vs ");
        JLabel playerTwo = new JLabel("Player n°2: " + players[1].toLowerCase());

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
        JPanel gridGame = new JPanel(gridLayout);
        for(int i = 0; i < (gridSize * gridSize); i++) {
            MyButton cell = new MyButton(i);
            cell.setBackground(Color.white);
            cell.addActionListener(actionEvent -> select(cell));
            gridGame.add(cell, false);
            _cells.add(cell);
        }
        return gridGame;
    }

    /**
     * Méthode permettant de créer les boutons du bas de la fenêtre
     * @return le panel contenant bouton/curseur
     */
    private JPanel createFooter(int initvalue) {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel cSpeedLabel = new JLabel("Speed's cursor : ");
        JSlider cSpeedSlider = new JSlider(JSlider.HORIZONTAL,1,20, initvalue);
        cSpeedSlider.setPreferredSize(new Dimension(280, 20));


        JButton button = new JButton("Start");
        button.setPreferredSize(new Dimension(80, 30));
        button.addActionListener(actionEvent -> {
            if(_init){
                new Thread(()-> Game.getInstance().automatonGame()).start();
                button.setEnabled(false);
            }
        });

        cSpeedSlider.addChangeListener(changeEvent -> Game.getInstance().setGameSpeed(cSpeedSlider.getValue()*1000));

        footer.add(cSpeedLabel);
        footer.add(cSpeedSlider);
        footer.add(button);

        return footer;
    }

    /**
     * Méthode qui gère le changement de couleur du bouton, selectionné
     * @param button bouton selectionné
     */
    public void select(MyButton button){
        if(!_init){
            GridController GC = GridController.getInstance();
            if(GC.count(0)==GC.count(1)){
                if (button.getBackground()==Color.white) {
                    changeColor(button, 1, true);
                    JOptionPane.showMessageDialog(button.getParent().getParent(),"turn : player 1");
                }
            }else {
                if (button.getBackground()==Color.white){
                    changeColor(button,0,true);
                    if(GC.count(0)==_startCell){
                        _init = true;
                    }
                    JOptionPane.showMessageDialog(button.getParent().getParent(),"turn : player 2");
                }
            }
        }
    }

    /**
     * Méthode qui change la couleur du bouton selectionné par la couleur du joueur qui la possède.
     * Cette methode est utilisé pour l'initialisation de la grille
     * @param button selectionné
     * @param player index du joueur modifé
     * @param send mets a jour le model si true
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
                GridController.getInstance().setStateGrid(player, button.getIndex(), State.DEAD);
            } else{
                GridController.getInstance().setStateGrid(player, button.getIndex(), State.ALIVE);
            }
        }
    }

    /**
     * Methode qui effectue les combat entre cellule
     * @param player tableau associant true au cellule participant au combat sinon false
     * @param nbPlayer nombre de joueur impliqué dans le combat
     * @param pos position de la cellule où a lieu le combat
     * @return la couleur du jeur vainqueur
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
     * Méthode mettant à jour la grille affiché avec les nouvelles grilles. Lance les combats si conflit entre differente
     * grille
     */
    @Override
    public void update() {
        int nbCell;
        boolean[] players;
        MyButton button;
        GridController GC = GridController.getInstance();
        for(int i=0;i<_cells.size();i++) {
            button = _cells.get(i);
            nbCell = -1;
            players = new boolean[_players.length];//{false, false};
            for (int j = 0; j < _players.length; j++) {
                if (GC.getState(j, i) != State.DEAD) {
                    nbCell++;
                    players[j] = true;
                    //System.out.println("#### cellule vivante pos="+i);
                } else {
                    players[j] = false;
                }
            }
            if (nbCell == -1) {
                button.setBackground(Color.white);
            } else {
                //System.out.println("#### combat pos="+i);
                Color newColor = fight(players, nbCell, i);
                //System.out.println("couleur : "+newColor);
                button.setBackground(newColor);
            }
        }

    }
}
