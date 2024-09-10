package Control;

import Config.Gamephase;
import Model.*;
import java.util.*;

public class Game {
    private final Board board;
    private Player[] players;
    private int currentPlayerIndex;
    private final Random random;
    private final Map<Player, List<Card>> playerCards;
    private boolean isDistributing;
    private int armiesToDistribute;
    // dazu weil öfters verwendet;
    private Player currentPlayer;



    // dazugefügt
    // PHASES
    // 0 = Reinforcement
    // 1 = Attack
    // 2 = Movement
    // 3 = Game over
    // 5 = Distribution
    private Gamephase gamePhase;

    public Game() {
        this.board = new Board();
        this.currentPlayerIndex = 0;
        this.random = new Random();
        this.playerCards = new HashMap<>();
       // Scanner scanner = new Scanner(System.in);
        this.isDistributing = false;
        this.armiesToDistribute = 0;
        //ersetzt die Übergabe von players
        setPlayer();
        initializeGame();
    }

    private void initializeGame() {
        this.gamePhase = Gamephase.DISTRIBUTION_TERRITORIES;
        for (Player player : players) {
            playerCards.put(player, new ArrayList<>());
        }
        this.currentPlayer = players[this.currentPlayerIndex];
        List<Territory> allTerritories = new ArrayList<>(board.getTerritories());

        //Territorien Zuweisungen rausgenommen
        for (int i = 0; i < allTerritories.size(); i++) {
            Player player = players[i % players.length];
            Territory territory = allTerritories.get(i);
        }
        this.armiesToDistribute = players.length * Settings.STARTARMY;

    }
    //aus Main importiert und angepasst
    private void setPlayer(){
        players = new Player[Settings.PLAYERCOUNT];
        for (int i = 0; i < Settings.PLAYERCOUNT; i++) {
            players[i] = new Player(Settings.PLAYERNAMES.get(i), Settings.PLAYERCOLORS.get(i), Settings.STARTARMY);
        }
    }

    public Player[] getPlayers() {
        return players;
    }
    // dazugefügt
    public Gamephase getGamePhase(){
        return  this.gamePhase;
    }
    public void setNextGamePhase(){
        switch (gamePhase){
            case Gamephase.REINFORCEMENT:
                gamePhase = Gamephase.ATTACK;
                break;
            case Gamephase.ATTACK:
                gamePhase = Gamephase.FORTIFY;
                break;
            case Gamephase.FORTIFY:
                gamePhase = Gamephase.REINFORCEMENT;
                reinforcePhase();
                break;
            case Gamephase.DISTRIBUTION_TERRITORIES:
                gamePhase = Gamephase.DISTRIBUTION_ARMIES;
                break;
            case Gamephase.DISTRIBUTION_ARMIES:
                gamePhase = Gamephase.REINFORCEMENT;
                reinforcePhase();
                break;
            default:
                break;

        }
    }
    public void setGameEndPhase(){
        this.gamePhase = Gamephase.GAME_OVER;
    }

    //wird benutzt in GUI createAndShow
    public boolean isDistributing() {
        return isDistributing;
    }

    public int getArmiesToDistribute(){
        return this.armiesToDistribute;
    }
    public void removeOneArmyToDistribute(){
        this.armiesToDistribute--;
    }
    /*
    public void startDistributingArmies() {
        isDistributing = true;
        armiesToDistribute = 16;
    }
    */

    //TODO Reinforcement Phase
    public void reinforcePhase() {
        int reinforcements = Math.max(3, currentPlayer.getTerritories().size() / 3);
        currentPlayer.addArmies(reinforcements);
        System.out.println(currentPlayer.getName() + " receives " + reinforcements + " reinforcement armies.");
    }

    private void cardReinforcementPhase() {
        Player currentPlayer = getCurrentPlayer();
        List<Card> cards = playerCards.get(currentPlayer);

        if (cards.size() >= 3) {
            int bonus = (cards.size() / 3) * 5;
            currentPlayer.addArmies(bonus);
            System.out.println(currentPlayer.getName() + " exchanges cards for " + bonus + " armies.");
            cards.subList(0, 3).clear();
        }
    }
    public void initDistributeArmies(){
        for(Player player : players){
            player.addArmies(Settings.STARTARMY);
        }
    }
    //TODO Distribution
    public boolean distributeArmy(Territory territory, int armies) {

        if (!isDistributing || armies <= 0 || armies > armiesToDistribute) {
            System.out.println("is not distributing" + armies);
            return false;
        }

        Player currentPlayer = getCurrentPlayer();
        if (territory.getOwner() != currentPlayer) {
            System.out.println("gehört ihm nicht" + territory.getOwner().getName() + currentPlayer.getName());
            return false;
        }
        territory.addArmies(armies);
        armiesToDistribute -= armies;
        if (armiesToDistribute <= 0) {
            isDistributing = false;
            return true;
        }
        return true;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void nextTurn() {
        checkGameOver();
        setCurrentPlayer();
        setNextGamePhase();


    }
    //TODO Next Turn
public void setCurrentPlayer(){
    this.currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
    // dazugefügt
    this.currentPlayer = players[currentPlayerIndex];


}
public boolean checkWinCondition() {
    for (Territory territory : board.getTerritories()) {
        if (territory.getOwner() != getCurrentPlayer()) {
            return false;
        }
    }
    return true;
}


    //TODO ATTACK Phase

    public void attackTerritory(Territory from, Territory to, int attackArmies, int defendArmies) {
        if (from.getOwner() == getCurrentPlayer() && to.getOwner() != getCurrentPlayer()) {
            int[] attackDice = rollDice(attackArmies);
            int[] defendDice = rollDice(defendArmies);

            Arrays.sort(attackDice);
            Arrays.sort(defendDice);

            int losses = 0;
            for (int i = 0; i < Math.min(attackDice.length, defendDice.length); i++) {
                if (attackDice[attackDice.length - 1 - i] > defendDice[defendDice.length - 1 - i]) {
                    to.removeArmies(1);
                } else {
                    from.removeArmies(1);
                    losses++;
                }
            }

            if (to.getArmyCount() == 0) {
                to.setOwner(from.getOwner());
                to.addArmies(attackArmies - losses);
                from.removeArmies(attackArmies - losses);
                System.out.println(from.getOwner().getName() + " conquered " + to.getName());
            }
        } else {
            System.out.println("Invalid attack!");
        }
    }

    private void fortifyPhase(){
        for (Territory from : currentPlayer.getTerritories()) {
            for (Territory to : from.getAdjacentTerritories()) {
                if (to.getOwner() == currentPlayer && from != to) {
                    int armiesToMove = Math.min(3, from.getArmyCount() - 1);
                    fortifyTerritory(from, to, armiesToMove);
                    System.out.println(currentPlayer.getName() + " moves " + armiesToMove + " armies from " + from.getName() + " to " + to.getName());
                    return;
                }
            }
        }
    }
    public void fortifyTerritory(Territory from, Territory to, int armiesToMove) {
        if (from.getOwner() == getCurrentPlayer() && to.getOwner() == getCurrentPlayer()) {
            from.removeArmies(armiesToMove);
            to.addArmies(armiesToMove);
        }
    }



    public int[] rollDice(int numDice) {
        int[] dice = new int[numDice];
        for (int i = 0; i < numDice; i++) {
            dice[i] = random.nextInt(6) + 1;
        }
        return dice;
    }



    public boolean isGameOver() {
        Set<Player> activePlayers = new HashSet<>();
        for (Player player : players) {
            if (!player.getTerritories().isEmpty()) {
                activePlayers.add(player);
            }
        }
        return activePlayers.size() == 1;
    }

    public void checkGameOver() {
        if (isGameOver()) {
            Player winner = getCurrentPlayer();
            System.out.println("Control.Game Over! " + winner.getName() + " wins!");
        }
    }
}
