package Control;

import Config.Gamephase;
import Model.*;
import View.GUI;
import java.util.*;
import java.util.Collections;

public class Game {
    private final Board board;
    private GUI gui;
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
    public int attackArmy;
    private int startAttackArmy;

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
    public void setGUI(GUI gui){
        this.gui = gui;
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
            case REINFORCEMENT:
                gamePhase = Gamephase.ATTACK;
                break;
            case ATTACK:
                gamePhase = Gamephase.FORTIFY;
                break;
            case FORTIFY:
                gamePhase = Gamephase.REINFORCEMENT;
                reinforcePhase();
                break;
            case DISTRIBUTION_TERRITORIES:
                gamePhase = Gamephase.DISTRIBUTION_ARMIES;
                break;
            case DISTRIBUTION_ARMIES:
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

            this.startAttackArmy = attackArmies;
            this.attackArmy = attackArmies;

            System.out.println(startAttackArmy);
            int numAttackDice = Math.min(attackArmies, 3);
            int numDefendDice = Math.min(defendArmies, 2);

            List<Integer> attackDice = rollDice(numAttackDice);
            List<Integer> defendDice = rollDice(numDefendDice);

            Collections.sort(attackDice, Collections.reverseOrder());
            Collections.sort(defendDice, Collections.reverseOrder());


            int lossAttack = 0;
            int lossDefend = 0;

            for (int i = 0; i < Math.min(numAttackDice, numDefendDice); i++) {
                if (attackDice.get(i) > defendDice.get(i)) {
                    System.out.println("hello win");
                    lossDefend++;
                } else {
                    System.out.println("hello lost");
                    lossAttack++;
                }

            }
            attackArmy -= lossAttack;
            defendArmies -= lossDefend;


            if (defendArmies <= 0) {
                System.out.println("in if " + startAttackArmy);
                to.setOwner(from.getOwner());
                to.setArmies(attackArmy);
                // TODO Redundanz
                from.setArmies(from.getArmyCount() - startAttackArmy);
                System.out.println(from.getOwner().getName() + " conquered " + to.getName());
            } else if(attackArmies <= 0){
                from.removeArmies(startAttackArmy);
                to.setArmies(defendArmies);
            }
            else{
                System.out.println(from.getArmyCount());
                System.out.println(startAttackArmy);
                System.out.println(attackArmies);
                to.setArmies(defendArmies);
                from.setArmies(from.getArmyCount() - startAttackArmy + attackArmy);
            }

        } else {
            System.out.println("Invalid attack!");
        }
        gui.updateBoard();

    }
    public int getAttackArmy(){
        return  this.attackArmy;
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


// array in ArrayList umgewandelt für mehr funktionen
    public List<Integer> rollDice(int numDice) {
        List<Integer> dice = new ArrayList<>();
        for (int i = 0; i < numDice; i++) {
            dice.add(random.nextInt(6) + 1);
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
