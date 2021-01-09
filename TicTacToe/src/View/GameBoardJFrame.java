package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

   public  class GameBoardJFrame extends JFrame {
        private JFrame mainFrame;
        private JPanelButtonTable buttonsTable;
        private JPanel mainPanel;
        private JPanelSettings settingsPanel;
       private JRadioButton ticRadioButton;
       private JRadioButton tacRadioButton;
       private ButtonGroup groupTicTac;
       private ImageIcon icon = new ImageIcon("icon.png");

       ArrayList<JButton> gameSquares = new ArrayList<JButton>();




       public JRadioButton getTicRadioButton(){
            return ticRadioButton;
        }

       public JRadioButton getTacRadioButton() {
           return tacRadioButton;
       }

        public ArrayList<JButton> getGameSquares(){
           return gameSquares;
        }



        public GameBoardJFrame() {
            setDefaultLookAndFeelDecorated(true);
            mainFrame = new JFrame("TicTacToe");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            mainPanel = new JPanel();
            settingsPanel = new JPanelSettings();
            buttonsTable = new JPanelButtonTable();
            GridLayout layout = new GridLayout(1, 2, 5, 12);
            mainPanel.setLayout(layout);
            getRootPane().setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
            setIconImage(icon.getImage());
            mainPanel.add(settingsPanel);
            mainPanel.add(buttonsTable);

            getContentPane().add(mainPanel);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
        }



       public void activateRadioButtons(){
           ticRadioButton.setEnabled(true);
           tacRadioButton.setEnabled(true);
           groupTicTac.clearSelection();
       }
       public void deactivateRadioButtons(){
           ticRadioButton.setEnabled(false);
           tacRadioButton.setEnabled(false);
       }



       public void activateGameSquares(){
           for(JButton square : gameSquares){
               square.setEnabled(true);
           }
       }
       public void deactivateGameSquares(){
           for(JButton square : gameSquares){
               square.setEnabled(false);
           }
       }


        class JPanelButtonTable extends JPanel {


            public JPanelButtonTable() {
                super();
                GridLayout layout = new GridLayout(3, 3, 5, 12);
                setLayout(layout);
                for (int i = 0; i < 9; i++) {
                    JButton button = new JButton();
                    button.setPreferredSize(new Dimension(50, 50));
                    gameSquares.add(button);
                    add(button);
                }
            }
        }



        class JPanelSettings extends JPanel {

            private JPanel radioButtonsGroupPanel;
            private JPanel textHintPanel;


            public JPanelSettings() {
                GridLayout layout = new GridLayout(2, 1, 5, 5);
                setLayout(layout);
                setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                createTextHint();
                createRadioButtonsGroup();
                add(textHintPanel);
                add(radioButtonsGroupPanel);
            }


            public void createRadioButtonsGroup() {
                radioButtonsGroupPanel = new JPanel();
                groupTicTac = new ButtonGroup();
                ticRadioButton = new JRadioButton("крестик", false);
                tacRadioButton = new JRadioButton("нолик", false);
                groupTicTac.add(ticRadioButton);
                groupTicTac.add(tacRadioButton);
                radioButtonsGroupPanel.add(ticRadioButton);
                radioButtonsGroupPanel.add(tacRadioButton);
            }

            public void createTextHint(){
                textHintPanel = new JPanel();
                textHintPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                JLabel ticTacLabel = new JLabel("Выберите чем будете играть");
                textHintPanel.add(ticTacLabel);
            }

        }
    }
