package View;

import Control.Game;
import Model.Territory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttackDialog extends JDialog {
    private Game game;
    private GUI ui;
    private Territory attackTerritory;
    private Territory defendTerritory;
    private int attackArmy;
    private int attackTerritoryArmyCount;
    private int defendTerritoryArmyCount;
    private JPanel contentPane;
    private JLabel attackTerritoryName;
    private JLabel attackTerritoryArmy;
    private JLabel defendTerritoryName;
    private JLabel defendTerritoryArmy;
    private JLabel attackArmyUi;
    private JButton addArmyBtn;
    private JButton minusArmyBtn;
    private JButton attackBtn;
    private JButton cancelBtn;

    private boolean isConfirmed;

    public AttackDialog(Game game, Territory attackTerritory, Territory defendTerritory){

        this.setContentPane(contentPane);
        this.setModal(true);

        this.setTitle("Attack Phase");

        this.game = game;
        this.attackTerritory = attackTerritory;
        this.defendTerritory = defendTerritory;

        this.attackTerritoryArmyCount = attackTerritory.getArmyCount() - 1;
        this.defendTerritoryArmyCount = defendTerritory.getArmyCount();

        this.attackTerritoryName.setText(attackTerritory.getName());
        this.attackTerritoryArmy.setText(String.valueOf(attackTerritory.getArmyCount() - 1));
        this.defendTerritoryName.setText(defendTerritory.getName());
        this.defendTerritoryArmy.setText(String.valueOf(defendTerritory.getArmyCount()));

        this.isConfirmed = false;
        this.attackArmy = 1;
        this.attackArmyUi.setText(String.valueOf(attackArmy));

        addArmyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (attackArmy < attackTerritory.getArmyCount() - 1) {
                    attackArmy++;
                    attackArmyUi.setText(String.valueOf(attackArmy));
                    attackTerritoryArmyCount--;
                    attackTerritoryArmy.setText(String.valueOf(attackTerritoryArmyCount));
                }
            }
        });

        minusArmyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (attackArmy > 1) {
                    attackArmy--;
                    attackArmyUi.setText(String.valueOf(attackArmy));
                    attackTerritoryArmyCount++;
                    attackTerritoryArmy.setText(String.valueOf(attackTerritoryArmyCount));
                }
            }
        });

        attackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isConfirmed){
                    isConfirmed = true;
                    addArmyBtn.setVisible(false);
                    minusArmyBtn.setVisible(false);

                }
                else{
                    game.attackTerritory(attackTerritory, defendTerritory, attackArmy, defendTerritory.getArmyCount());
                    attackArmy = attackTerritory.getArmyCount();
                    attackArmyUi.setText(String.valueOf(attackArmy));
                    defendTerritoryArmy.setText(String.valueOf(defendTerritory.getArmyCount()));
                    if(defendTerritory.getArmyCount() == 0 || attackArmy == 0){
                        dispose();
                    }
                }
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    dispose();
            }
        });

        this.pack();
        this.setLocationRelativeTo(null);
    }
}
