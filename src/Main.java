import Control.Game;
import Model.Player;
import View.GUI;
import View.StartScreenDialog;

public class Main {
    private static final int NUM_PLAYERS = 2;
    private static final String[] PLAYER_NAMES = {"Model.Player 1", "Model.Player 2"};

    public static void main(String[] args) {
        Player[] players = new Player[NUM_PLAYERS];
        for (int i = 0; i < NUM_PLAYERS; i++) {
            players[i] = new Player(PLAYER_NAMES[i]);
        }
        StartScreenDialog startScreenDialog  = new StartScreenDialog();

        Game game = new Game(players);
        GUI gui = new GUI(game);
        javax.swing.SwingUtilities.invokeLater(gui::createAndShowGUI);
    }
}
