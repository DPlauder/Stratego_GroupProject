package View;

import Config.Config;
import Utils.Utils;
import Model.Settings;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.EmptyBorder;

public class StartScreenDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;

    private JRadioButton playerCount2;
    private JRadioButton playerCount3;
    private JRadioButton playerCount4;

    private JPanel playerCount;
    private JPanel playerInfo;
    private JPanel playerOneInfo;
    private JPanel playerTwoInfo;
    private JPanel playerThreeInfo;
    private JPanel playerFourInfo;
    private JTextField playerOneName;
    private JTextField playerTwoName;
    private JTextField PlayerThreeName;
    private JTextField PlayerFourName;
    private JComboBox comboMapVersion;
    private JPanel playerOneColors;
    private JPanel playerTwoColors;
    private JPanel playerThreeColors;
    private JPanel playerFourColors;
    private JButton buttonCancel;

    private List<JLabel> selectedColorLabels;


    private int playerNumber;
    private List<String> playerColors;
    private List<String> playerNames;
    private String mapType;

    public Settings settings;


    public StartScreenDialog() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("Stratego");
        getRootPane().setDefaultButton(buttonOK);



        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        initPlayerColorsStart();
        initColorsSelector();
        initNumberRadioButtons();

        setTwoPlayerInfos();
        setSize(500, 350);
        setLocationRelativeTo(null);
        setVisible(true);




    }

    //TODO OK
    private void onOK() {
        checkPlayerNames();

        // Validate that all players have selected a color
        if (!validatePlayerColors()) {
            JOptionPane.showMessageDialog(this,
                    "Each player must select a unique color.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        settings = new Settings(playerNames, playerColors, mapType);
        dispose();
    }

    private boolean validatePlayerColors() {
        for (int i = 0; i < playerNumber; i++) {
            if (playerColors.get(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }


    //TODO RADIO BTNS
    private void initNumberRadioButtons(){
        ButtonGroup group = new ButtonGroup();
        group.add(playerCount2);
        group.add(playerCount3);
        group.add(playerCount4);
        playerCount2.setSelected(true);

        playerCount2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTwoPlayerInfos();
                System.out.println(playerColors);

            }
        });
        playerCount3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setThreePlayerInfos();
                System.out.println(playerColors);

            }
        });
        playerCount4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFourPlayerInfos();
                System.out.println(playerColors);

            }
        });
    }
    private void setTwoPlayerInfos(){
        playerNumber = 2;
        playerThreeInfo.setVisible(false);
        playerFourInfo.setVisible(false);
        resetColors();
    }
    private void setThreePlayerInfos(){
        playerNumber = 3;
        playerThreeInfo.setVisible(true);
        playerFourInfo.setVisible(false);
        resetColors();
    }
    private void setFourPlayerInfos(){
        playerNumber = 4;
        playerThreeInfo.setVisible(true);
        playerFourInfo.setVisible(true);
        resetColors();
    }

    //TODO COLORS

    private void initPlayerColorsStart(){
        playerColors = new ArrayList<>(Config.MAXPLAYERS);
        selectedColorLabels = new ArrayList<>(Config.MAXPLAYERS);
        for(int i = 0; i < Config.MAXPLAYERS; i++){
            playerColors.add("");
            selectedColorLabels.add(null);
        }
        System.out.println(playerColors);
    }


    private void initColorsSelector(){
        List <JPanel> playerColorPanels = new ArrayList<>();
        playerColorPanels.add(playerOneColors);
        playerColorPanels.add(playerTwoColors);
        playerColorPanels.add(playerThreeColors);
        playerColorPanels.add(playerFourColors);
        int playerIndex = 0;

        for(JPanel panel: playerColorPanels){
            panel.setLayout(new GridLayout(1, Config.PLAYERCOLORS.length));
            for(String color: Config.PLAYERCOLORS){
                JLabel label = createLabel(color, playerIndex);
                panel.add(label);
            }
            playerIndex++;
        }
    }
    private JLabel createLabel(String colorString, int playerIndex) {
        JLabel label = new JLabel();
        Color color = Utils.stringToColor(colorString);
        label.setOpaque(true);
        label.setBackground(color);
        label.setName(colorString);

        label.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!checkColorUsed(colorString, playerIndex)) {
                    setColor(colorString, playerIndex, label);
                }
            }
        });
        return label;
    }
    private boolean checkColorUsed(String color, int playerIndex ){
        for(String item: playerColors){
            if(item.equals(color) && !playerColors.get(playerIndex).equals(color)){
                JOptionPane.showMessageDialog(this,
                        "Color is already taken.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                return true;
            }
        }
        return false;
    }
    private void setColor(String color, int playerIndex, JLabel label) {
        JLabel previousLabel = selectedColorLabels.get(playerIndex);
        if (previousLabel != null) {
            resetHighlight(previousLabel);
        }

        if (playerColors.get(playerIndex).equals(color)) {
            playerColors.set(playerIndex, "");
            selectedColorLabels.set(playerIndex, null);
            resetHighlight(label);
        } else {
            setHighlight(label);
            playerColors.set(playerIndex, color);
            selectedColorLabels.set(playerIndex, label);
        }
    }

    private void resetColors(){
        for(int i = (Config.MAXPLAYERS - 1); i >= (Config.MAXPLAYERS - (Config.MAXPLAYERS - playerNumber)); i--){
            playerColors.set(i, "");
        }
    }
    private void setHighlight(JLabel label) {
        label.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
    }

    // Update the resetHighlight method
    private void resetHighlight(JLabel label) {
        // Reapply the same empty border instead of removing the border entirely
        label.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
    }

    //TODO Player Names
    private List<String> getPlayerNames(){
        return playerNames;
    }
    private void checkPlayerNames(){
        playerNames = new ArrayList<>();
        playerNames.add(playerOneName.getText());
        playerNames.add(playerTwoName.getText());
        if (playerNumber > 2) {
            playerNames.add(PlayerThreeName.getText());
        }
        if (playerNumber > 3) {
            playerNames.add(PlayerFourName.getText());
        }
        for (int i = 0; i < playerNames.size(); i++) {
            String name = playerNames.get(i);
            if (name.isEmpty()) {
                playerNames.set(i, "Player " + (i + 1));
            }
        }
    }

    //TODO MapType

}

