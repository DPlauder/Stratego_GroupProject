package View;

import Config.Config;
import Utils.Utils;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

    private int playerNumber;
    private List<String> playerColors;
    private List<String> playerNames;


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




    }


    private void onOK() {
        // add your code here
        dispose();
    }


    public static void main(String[] args) {
        StartScreenDialog dialog = new StartScreenDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
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
        playerColors =  new ArrayList<>(Config.MAXPLAYERS);
        for(int i = 0; i < (Config.MAXPLAYERS ); i++){
            playerColors.add("");
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

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!checkColorUsed(colorString, playerIndex)){
                    setColor(colorString, playerIndex, label);
                }
            }
        });
        return label;
    }
    private boolean checkColorUsed(String color, int playerIndex ){
        for(String item: playerColors){
            if(item.equals(color) && !playerColors.get(playerIndex).equals(color)){
                return true;
            }
        }
        return false;
    }
    private void setColor(String color, int playerIndex, JLabel label){
        if(playerColors.get(playerIndex).equals(color)){
            playerColors.set(playerIndex, "");
            resetHighlight(label);
        }
        else{
            setHighlight(label);
            playerColors.set(playerIndex, color);
        }
        System.out.println(playerColors);


    }
    private void resetColors(){
        for(int i = (Config.MAXPLAYERS - 1); i >= (Config.MAXPLAYERS - (Config.MAXPLAYERS - playerNumber)); i--){
            playerColors.set(i, "");
        }
    }
    private void setHighlight(JLabel label){
        label.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
    }
    private void resetHighlight(JLabel label){
        label.setBorder(null);
    }

    //TODO Player Names
    private List<String> getPlayerNames(){
        List<String> playerNames = new ArrayList<>();
        playerNames.add(playerOneName.getName());
        playerNames.add(playerOneName.getName());
        playerNames.add(playerOneName.getName());
        playerNames.add(playerOneName.getName());



        return playerNames;
    }

}

