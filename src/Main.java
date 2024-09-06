import Control.Game;
import Model.Player;
import Model.Settings;
import View.GUI;
import View.StartScreenDialog;

public class Main {
    private static final int NUM_PLAYERS = 2;
    private static final String[] PLAYER_NAMES = {"Player 1", "Player 2"};

    public static void main(String[] args) {

        StartScreenDialog startScreenDialog  = new StartScreenDialog();


        Game game = new Game();
        GUI gui = new GUI(game);
        javax.swing.SwingUtilities.invokeLater(gui::createAndShowGUI);
    }
}
