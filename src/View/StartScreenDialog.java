package View;

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
    private JPanel playerOneColors;
    private JLabel playerOneRed;
    private JLabel playerOneBlue;
    private JLabel playerOneYellow;
    private JLabel playerOneGreen;
    private JLabel playerOneBlack;
    private JPanel playerTwoInfo;
    private JPanel playerThreeInfo;
    private JPanel playerFourInfo;
    private JTextField playerOneName;
    private JTextField playerTwoName;
    private JTextField PlayerFourName;
    private JTextField PlayerThreeName;
    private JComboBox comboMapVersion;
    private JLabel playerFourRed;
    private JLabel playerFourBlue;
    private JLabel playerFourYellow;
    private JLabel playerFourGreen;
    private JLabel playerFourBlack;
    private JLabel playerTwoRed;
    private JLabel playerTwoBlue;
    private JLabel playerTwoYellow;
    private JLabel playerTwoGreen;
    private JLabel playerTwoBlack;
    private JLabel playerThreeRed;
    private JLabel playerThreeBlue;
    private JLabel playerThreeYellow;
    private JLabel playerThreeGreen;
    private JLabel playerThreeBlack;
    private JPanel playerTwoColors;
    private JPanel playerThreeColors;
    private JPanel playerFourColors;
    private JButton buttonCancel;

    private int playerNumber;
    private List<String> playerColors;


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

        initNumberRadioButtons();
        setTwoPlayerInfos();

        initPlayerColorsStart();
        initColorsSelector();

        this.pack();
        this.setLocationRelativeTo(null);
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
    //TODO RADIOBTNS
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

            }
        });
        playerCount3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setThreePlayerInfos();

            }
        });
        playerCount4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFourPlayerInfos();

            }
        });
    }
    private void setTwoPlayerInfos(){
        playerNumber = 2;
        playerThreeInfo.setVisible(false);
        playerFourInfo.setVisible(false);
    }
    private void setThreePlayerInfos(){
        playerNumber = 3;
        playerThreeInfo.setVisible(true);
        playerFourInfo.setVisible(false);
    }
    private void setFourPlayerInfos(){
        playerNumber = 4;
        playerThreeInfo.setVisible(true);
        playerFourInfo.setVisible(true);
    }

    //TODO COLORS

    private void initPlayerColorsStart(){
        playerColors =  new ArrayList<>(4);
        playerColors.add("colorOne");
        playerColors.add("colorTwo");
        playerColors.add("null");
        playerColors.add("null");
    }

    private void initColorsSelector(){

        Component[] playerOneColors = this.playerOneColors.getComponents();
        Component[] playerTwoColors = this.playerTwoColors.getComponents();
        Component[] playerThreeColors = this.playerThreeColors.getComponents();
        Component[] playerFourColors = this.playerFourColors.getComponents();

        for(Component component: playerOneColors){
            component.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    System.out.println(component.getName());
                }
            });
        }

        for(Component component: playerTwoColors){
            component.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    System.out.println(component.getName());
                }
            });
        }
        for(Component component: playerThreeColors){
            component.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    System.out.println(component.getName());
                }
            });
        }
        for(Component component: playerFourColors){
            component.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    System.out.println(component.getName());
                }
            });
        }
    }
}

