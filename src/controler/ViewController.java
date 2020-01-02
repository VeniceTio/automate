package controler;

public class ViewController {
    //private ...

    /**
     * L'instance de la classe ViewController (Singleton)
     */
    private static GridController _instance = null;

    /**
     * Méthode permettant de récupérer l'instance de la classe
     * @return l'instance de la classe
     */
    public static GridController getInstance(){
        if(_instance == null){
            _instance = new GridController();
        }
        return _instance;
    }
}
