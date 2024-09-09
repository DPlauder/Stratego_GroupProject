package View;
import Control.Game;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class GUI {
    private final Game game;
    private final JFrame frame;
    private final JPanel boardPanel;
    private final JLabel statusLabel;
    private Territory selectedFrom;
    private Territory selectedTo;
    private boolean isFortifying;
    private boolean isDistributing;

    public GUI(Game game) {
        this.game = game;
        this.frame = new JFrame("Risiko");
        this.boardPanel = new JPanel();
        this.statusLabel = new JLabel("Current Player: ");
        this.selectedFrom = null;
        this.selectedTo = null;
        this.isFortifying = false;
        this.isDistributing = false;

    }
    //TODO START
    public void createAndShowGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 800);
        frame.setLayout(new BorderLayout());

        boardPanel.setLayout(new GridLayout(4, 6));
        updateStatusLabel();
        updateBoard();

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);
        JPanel controlPanel = new JPanel();
        //TODO NEXT TURN BTN

            JButton nextTurnButton = new JButton("Next Turn");
            nextTurnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateStatusLabel();
                    // gamephases dazugefügt
                    if(game.getGamePhase() == 0 && game.getArmiesToDistribute() == 0){
                        System.out.println(game.getCurrentPlayer().getArmyCount());
                        game.setNextGamePhase();
                    }
                    else if(game.getGamePhase() == 1){
                        game.setNextGamePhase();
                    }
                    else if(game.getGamePhase() == 2){
                        game.setNextGamePhase();
                        game.setCurrentPlayer();
                    }
                    else if (game.isDistributing()) {
                        JOptionPane.showMessageDialog(frame, "Distributing phase is not finished yet.");
                    } else {
                        //game.nextTurn();
                        System.out.println("hello else");
                        //System.out.println("Current Player after turn: " + game.getCurrentPlayer().getName());
                        if (game.checkWinCondition()) {
                            JOptionPane.showMessageDialog(frame, "Player " + game.getCurrentPlayer().getName() + " wins!");
                            System.exit(0);
                        }
                        updateBoard();
                    }
                }
            });
            controlPanel.add(nextTurnButton);

        //TODO Fortify Btn
            JButton fortifyButton = new JButton("Fortify");
            fortifyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isFortifying = !isFortifying;
                    fortifyButton.setText(isFortifying ? "Attack" : "Fortify");
                    statusLabel.setText("Current Player: " + game.getCurrentPlayer().getName() + " (Fortify Mode: " + isFortifying + ")");
                    isDistributing = false;
                }
            });
            controlPanel.add(fortifyButton);


        //TODO Dist Btn
        JButton distributeButton = new JButton("Distribute Armies");
        distributeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!game.isDistributing()) {
                    game.startDistributingArmies();
                    isDistributing = true;
                    distributeButton.setText("Done");
                    //statusLabel.setText("Current Player: " + game.getCurrentPlayer().getName() + " (Distribute Mode: " + isDistributing + ")");
                } else {
                    isDistributing = false;
                    distributeButton.setText("Distribute Armies");
                    statusLabel.setText("Current Player: " + game.getCurrentPlayer().getName());
                    updateBoard();
                }
            }
        });
        controlPanel.add(distributeButton);

        //TODO Cards Btn
        JButton useCardButton = new JButton("Use Cards");
        useCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                useCards();
            }
        });
        controlPanel.add(useCardButton);

        frame.add(controlPanel, BorderLayout.NORTH);
        frame.setVisible(true);
    }


    private void updateBoard() {
        boardPanel.removeAll();
        boardPanel.setLayout(null);
        boardPanel.setBackground(Color.BLUE);
        int rows = 4;
        int cols = 6;
        int buttonWidth = 120;
        int buttonHeight = 60;
        int padding = 20;
        int groupSpacing = 300;
        int startX = 20;
        int startY = 20;

        int territoryIndex = 0;
        for (int group = 0; group <4; group++){
            int groupX = startX + (group % 2) * (6 *(padding ) + groupSpacing + (padding *2 ));
            int groupY = startY + (group / 2) * (3 * (padding) + groupSpacing);

            for (int i = 0; i < 6; i++) {
                Territory territory = game.getBoard().getTerritories().stream()
                        .skip(territoryIndex++)
                        .findFirst()
                        .orElse(null);

                if (territory == null) {
                    continue;
                }
                JButton button = new JButton(territory.getName() + " (" + territory.getArmyCount() + ")");

                if (territory.getOwner() != null) {
                    button.setBackground(territory.getOwner().getColor());
                } else {
                    button.setBackground(Color.GRAY);  // Set background to gray if no owner
                }
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleTerritoryClick(territory);
                    }
                });
                int xPosition = groupX + (i % 3) * (buttonWidth + padding);
                int yPosition = groupY + (i / 3) * (buttonHeight + padding);

                button.setBounds(xPosition, yPosition, buttonWidth, buttonHeight);
                boardPanel.add(button);

            }
        }
        boardPanel.repaint();
    }
    //ausgelagert aus updateBoard
    private void updateStatusLabel(){
        this.statusLabel.setText("Player: " + game.getCurrentPlayer().getName() +
                " | Territories: " + game.getCurrentPlayer().getTerritories().size() + " | Armies: " + game.getCurrentPlayer().getArmyCount() +
                " | Cards: " + game.getCurrentPlayer().getCards().size() + " | Phase " + game.getGamePhase());
    }
    //TODO Handler Territory
    private void handleTerritoryClick(Territory clickedTerritory) {
        if (clickedTerritory == null) {
            JOptionPane.showMessageDialog(frame, "Fehler: Das ausgewählte Territorium existiert nicht.");
            return;
        }
        if(game.getGamePhase() == 0){

        }
        else if(game.getGamePhase() == 1){

        }
        else if(game.getGamePhase() == 2){

        }
        else if(game.getGamePhase() == 5){

        }
        if (isFortifying) {
            if (selectedFrom == null) {
                if (clickedTerritory.getOwner() == game.getCurrentPlayer()) {
                    selectedFrom = clickedTerritory;
                    JOptionPane.showMessageDialog(frame, "Territory selected for fortification: " + selectedFrom.getName() + ". Now select the target territory.");
                } else {
                    JOptionPane.showMessageDialog(frame, "You must select a territory that you own.");
                }
            } else {
                selectedTo = clickedTerritory;
                if (!selectedFrom.getAdjacentTerritories().contains(selectedTo)) {
                    JOptionPane.showMessageDialog(frame, "You can only fortify adjacent territories.");
                    return;
                }

                int armiesToMove = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter number of armies to move (1-3):"));
                if (armiesToMove >= 1 && armiesToMove <= selectedFrom.getArmyCount() - 1) {
                    game.fortifyTerritory(selectedFrom, selectedTo, armiesToMove);
                    selectedFrom = null;
                    selectedTo = null;
                    updateBoard();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid number of armies. Must be between 1 and 3 and not more than available.");
                }
            }
        } else {
            if (selectedFrom == null) {
                if (clickedTerritory.getOwner() == game.getCurrentPlayer()) {
                    selectedFrom = clickedTerritory;
                    JOptionPane.showMessageDialog(frame, "Territory selected for attack: " + selectedFrom.getName() + ". Now select the target territory.");
                } else {
                    JOptionPane.showMessageDialog(frame, "You must select a territory that you own.");
                }
            } else {
                selectedTo = clickedTerritory;
                if (!selectedFrom.getAdjacentTerritories().contains(selectedTo)) {
                    JOptionPane.showMessageDialog(frame, "You can only attack adjacent territories.");
                    return;
                }
                if (selectedTo.getOwner() == game.getCurrentPlayer()) {
                    JOptionPane.showMessageDialog(frame, "You cannot attack your own territory.");
                    return;
                }
                    System.out.println(selectedFrom.getName() + selectedTo.getName());
                int attackArmies = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter number of armies to attack with (1-3):"));
                if (attackArmies >= 1 && attackArmies <= 3 && attackArmies <= selectedFrom.getArmyCount() - 1) {
                    int defendArmies = Math.min(2, selectedTo.getArmyCount());
                    game.attackTerritory(selectedFrom, selectedTo, attackArmies, defendArmies);

                    handleAttackPhase();

                    if (selectedTo.getArmyCount() == 0) {
                        JOptionPane.showMessageDialog(frame, "Territory conquered! You receive a card.");
                        game.getCurrentPlayer().addTerritory(selectedTo);
                        game.getCurrentPlayer().addCard(new Card("Infantry"));
                    }

                    selectedFrom = null;
                    selectedTo = null;
                    updateBoard();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid number of armies. Must be between 1 and 3 and not more than available.");
                }
            }
        }
    }

    private void useCards() {
        Player currentPlayer = game.getCurrentPlayer();
        List<Card> cards = currentPlayer.getCards();

        if (cards.size() < 3) {
            JOptionPane.showMessageDialog(frame, "You need at least 3 cards to trade for armies.");
            return;
        }

        int response = JOptionPane.showConfirmDialog(frame, "Do you want to trade 3 cards for 5 armies?");
        if (response == JOptionPane.YES_OPTION) {
            for (int i = 0; i < 3; i++) {
                currentPlayer.useCard();
            }
            currentPlayer.addArmies(5);
            JOptionPane.showMessageDialog(frame, "You have received 5 additional armies.");

            distributeBonusArmies(currentPlayer, 5);
            updateBoard();
        }
    }

    private void handleAttackPhase() {
        String diceResult = rollDiceResult();

        JFrame diceFrame = new JFrame("Roll Dice");
        diceFrame.setSize(300, 200);
        diceFrame.setLayout(new BorderLayout());

        JTextArea diceResultArea = new JTextArea(diceResult);
        diceResultArea.setEditable(false);
        diceFrame.add(new JScrollPane(diceResultArea), BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diceFrame.dispose();
                game.nextTurn();
                if (game.checkWinCondition()) {
                    JOptionPane.showMessageDialog(frame, "Player " + game.getCurrentPlayer().getName() + " wins!");
                    System.exit(0);
                }
                updateBoard();
            }
        });
        diceFrame.add(okButton, BorderLayout.SOUTH);
        diceFrame.setVisible(true);
    }


    private String rollDiceResult() {
        if (selectedFrom == null || selectedTo == null) {
            System.out.println("Fehler: Kein Angriff ausgewählt.");
        }
        int attackDiceCount = Math.min(3, selectedFrom.getArmyCount());
        int defendDiceCount = Math.min(2, selectedTo.getArmyCount());

        int[] attackDice = game.rollDice(attackDiceCount);
        int[] defendDice = game.rollDice(defendDiceCount);
        return compareDiceResults(attackDice, defendDice);
    }

    private String compareDiceResults (int[] attackDice, int[] defendDice){
        if (attackDice == null || defendDice == null) {
            System.out.println("Fehler: Keine Würfelzahl vorhanden.");
        }
        assert attackDice != null;
        Arrays.sort(attackDice);
        assert defendDice != null;
        Arrays.sort(defendDice);

        StringBuilder result = new StringBuilder();
        result.append("Attacker's dice: ").append(Arrays.toString(reverseArray(attackDice))).append("\n");
        result.append("Defender's dice: ").append(Arrays.toString(reverseArray(defendDice))).append("\n");

        int minComparisons = Math.min(attackDice.length, defendDice.length);
        int attackerLosses = 0;
        int defenderLosses = 0;

        for (int i = 0; i < minComparisons; i++) {
            if (attackDice[i] > defendDice[i]) {
                defenderLosses++;
            } else {
                attackerLosses++;
            }
        }

        result.append("Attacker loses ").append(attackerLosses).append(" army/armies.\n");
        result.append("Defender loses ").append(defenderLosses).append(" army/armies.\n");

        selectedFrom.removeArmies(attackerLosses);
        selectedTo.removeArmies(defenderLosses);

        if (selectedTo.getArmyCount() == 0) {
            game.getCurrentPlayer().addTerritory(selectedTo);
            game.getCurrentPlayer().addCard(new Card("Infantry"));
            result.append("Territory conquered! ").append(selectedTo.getName()).append(" is now owned by ").append(game.getCurrentPlayer().getName()).append(".");
        }

        return result.toString();
    }
    private int[] reverseArray(int[] array) {
        int[] reversed = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            reversed[i] = array[array.length - 1 - i];
        }
        return reversed;
    }

    private void distributeBonusArmies(Player player, int armiesToDistribute) {
        while (armiesToDistribute > 0) {
            Territory selectedTerritory = (Territory) JOptionPane.showInputDialog(
                    frame,
                    player.getName() + ", select a territory to place armies:",
                    "Distribute Armies",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    player.getTerritories().toArray(),
                    player.getTerritories().get(0)
            );

            if (selectedTerritory != null) {
                int armiesToPlace = Integer.parseInt(JOptionPane.showInputDialog(
                        frame,
                        "Enter number of armies to place (1-" + armiesToDistribute + "):"
                ));

                if (armiesToPlace >= 1 && armiesToPlace <= armiesToDistribute) {
                    boolean success = game.distributeArmy(selectedTerritory, armiesToPlace);
                    if (success) {
                        armiesToDistribute -= armiesToPlace;
                        JOptionPane.showMessageDialog(frame, "You placed " + armiesToPlace + " armies at " + selectedTerritory.getName());
                        updateBoard();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to place armies. Try again.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid number of armies. Must be between 1 and " + armiesToDistribute + ".");
                }
            }
        }

        JOptionPane.showMessageDialog(frame, "All bonus armies are distributed.");
    }
}
