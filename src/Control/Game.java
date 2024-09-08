package Control;

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

    // dazugefügt
    // PHASES
    // 1 = Reinforcment
    // 2 = Attack
    // 3 = Movement
    // 4 = Game over
    public int gamePhase;

    public Game() {
        this.board = new Board();
        this.currentPlayerIndex = 0;
        this.random = new Random();
        this.playerCards = new HashMap<>();
       // Scanner scanner = new Scanner(System.in);
        this.isDistributing = false;
        this.armiesToDistribute = 16;
        //ersetzt die Übergabe von players
        setPlayer();
        initializeGame();
    }

    private void initializeGame() {
        this.gamePhase = 1;

        for (Player player : players) {
            playerCards.put(player, new ArrayList<>());
        }

        List<Territory> allTerritories = new ArrayList<>(board.getTerritories());
        Collections.shuffle(allTerritories);

        for (int i = 0; i < allTerritories.size(); i++) {
            Player player = players[i % players.length];
            Territory territory = allTerritories.get(i);
            territory.setOwner(player);
            territory.addArmies(1);
            player.addTerritory(territory);

        }

        List<Card> allCards = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            allCards.add(new Card("Infantry"));
        }
        Collections.shuffle(allCards);
    }
    //aus Main importiert und angepasst
    private void setPlayer(){
        players = new Player[Settings.PLAYERCOUNT];
        for (int i = 0; i < Settings.PLAYERCOUNT; i++) {
            players[i] = new Player(Settings.PLAYERNAMES.get(i));
        }
    }

    public Player[] getPlayers() {
        return players;
    }
    // dazugefügt
    public int getGamePhase(){
        return  this.gamePhase;
    }
    public void setReinformentPhase(){
        this.gamePhase = 1;
    }
    public void setAttackPhase(){
        this.gamePhase = 2;
    }
    public void setMovementPhase(){
        this.gamePhase = 3;
    }
    public void setGameEndPhase(){
        this.gamePhase = 4;
    }

    //wird benutzt in GUI createAndShow
    public boolean isDistributing() {
        return isDistributing;
    }

    public void startDistributingArmies() {
        isDistributing = true;
        armiesToDistribute = 16;
    }

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
        return players[currentPlayerIndex];
    }

    public void nextTurn() {
        if (!isDistributing) {
            reinforcePhase();
            cardReinforcementPhase();
            attackPhase();
            fortifyPhase();
            setCurrentPlayer();
            checkGameOver();
        }
    }
public void setCurrentPlayer(){
    currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
}
    public boolean checkWinCondition() {
        //angepasst
        for (Territory territory : board.getTerritories()) {
            if (territory.getOwner() != getCurrentPlayer()) {
                return false;
            }
        }
        return true;
    }

    private void reinforcePhase() {
        Player currentPlayer = getCurrentPlayer();
        int reinforcements = Math.max(3, currentPlayer.getTerritories().size() / 3);

        for (Continent continent : board.getContinents()) {
            if (currentPlayer.controlsContinent(continent)) {
                reinforcements += 3;
            }
        }

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

    private void attackPhase() {
        Player currentPlayer = getCurrentPlayer();

        boolean territoryConquered = false;
        for (Territory from : currentPlayer.getTerritories()) {
            for (Territory to : from.getAdjacentTerritories()) {
                if (to.getOwner() != currentPlayer) {
                    int attackArmies = Math.min(3, from.getArmyCount() - 1);
                    int defendArmies = Math.min(2, to.getArmyCount());
                    attackTerritory(from, to, attackArmies, defendArmies);
                    territoryConquered = true;
                    break;
                }
            }
            if (territoryConquered) break;
        }
        if (territoryConquered) {
            playerCards.get(currentPlayer).add(new Card("Infantry"));
            if (playerCards.get(currentPlayer).size() > 5) {
                cardReinforcementPhase();
            }
        }
    }

    private void fortifyPhase() {
        Player currentPlayer = getCurrentPlayer();

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

    public int[] rollDice(int numDice) {
        int[] dice = new int[numDice];
        for (int i = 0; i < numDice; i++) {
            dice[i] = random.nextInt(6) + 1;
        }
        return dice;
    }

    public void fortifyTerritory(Territory from, Territory to, int armiesToMove) {
        if (from.getOwner() == getCurrentPlayer() && to.getOwner() == getCurrentPlayer()) {
            from.removeArmies(armiesToMove);
            to.addArmies(armiesToMove);
        }
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
