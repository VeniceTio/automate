package controler;

import view.GameWindow;
import view.SettingsWindow;

public class ViewController {
    //private ...
    private SettingsWindow _settingsWin;
    private GameWindow _gameWin;
    /**
     * L'instance de la classe ViewController (Singleton)
     */
    private static ViewController _instance = null;

    /**
     * Méthode permettant de récupérer l'instance de la classe
     * @return l'instance de la classe
     */
    public static ViewController getInstance(){
        if(_instance == null){
            _instance = new ViewController();
        }
        return _instance;
    }

    public void createSettingWindow(){
        _settingsWin = new SettingsWindow();
        _settingsWin.setVisible(true);
    }

    public void createGameWindow(int size, String[] players, int cellNum) {
        _settingsWin.setVisible(false);
        _gameWin = new GameWindow(size, players,cellNum);
        _gameWin.setVisible(true);
    }
    public void clockForward() {
        _gameWin.update();
    }
}
