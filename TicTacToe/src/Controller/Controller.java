package Controller;

import Model.GameLogic;
import View.GameBoardJFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller {

    private static GameLogic model;
    private static GameBoardJFrame view;


    public void start(){
        model = new GameLogic();
        view = new GameBoardJFrame();
        view.getTicRadioButton().addActionListener(new TicRadioButtonListener());
        view.getTacRadioButton().addActionListener(new TacRadioButtonListener());
        ArrayList<JButton> gameSquareButtons = view.getGameSquares();
        for (int i = 0; i < gameSquareButtons.size(); i++){
            gameSquareButtons.get(i).setEnabled(false);
            //перебираем все кнопки из GUI и передаем в конструктор обработчика событий текущую кнопку
            // и её порядковый номер в списке(0 - 8)
            gameSquareButtons.get(i).addActionListener(new GameSquareListener(gameSquareButtons.get(i), i));
        }

    }




    private static class GameSquareListener implements ActionListener{

        private JButton gameSquare;
        private int gameSquarePosition;



        public GameSquareListener(JButton button, int position){
            gameSquare = button;
            gameSquarePosition = position;
        }



        @Override
        public void actionPerformed(ActionEvent e) {
            view.deactivateRadioButtons();
            if (!gameSquare.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Выбранная ячейка уже занята!");
            }else{
                    model.userMove(gameSquarePosition);
                    model.upCountMovies();
                    updateGameBoard();
                    if (model.checkGameStatus(model.getUserGameSimbol())) {
                        userWon();
                    } else {
                        //проверка ничьей(тоесть последний 9 ход делает пользователь, и он не занимает
                        // выйгрышное положение)
                        if (model.getCountMovies() == 9){
                            noOneWon();
                        }else {
                            model.computerMove();
                            model.upCountMovies();
                            updateGameBoard();
                            if (model.checkGameStatus(model.getComputerGameSimbol())) {
                                computerWon();
                            }
                        }
                    }
            }
        }



        private void userWon(){
            JOptionPane.showMessageDialog(null, "Поздравляю, вы победили!");
            restartGame();
        }


        private void computerWon(){
            JOptionPane.showMessageDialog(null, "Вы проиграли, не расстраивайтесь!");
            restartGame();
        }


        private void noOneWon(){
            JOptionPane.showMessageDialog(null, "В этот раз получилась ничья!");
            restartGame();
        }

        private void updateGameBoard(){
            ArrayList<GameLogic.GameSquare>modelGameSquares = model.getGameSquares();
            ArrayList<JButton>viewGameSquares = view.getGameSquares();
            for (int i = 0; i < modelGameSquares.size(); i++){
                if (modelGameSquares.get(i).getWrittenSimbol() != null) {
                    viewGameSquares.get(i).setText(modelGameSquares.get(i).getWrittenSimbol() + "");
                }else{
                    viewGameSquares.get(i).setText("");
                }
            }
        }



        private void restartGame(){
            model.clearGameBoard();
            updateGameBoard();
            view.activateRadioButtons();
            view.deactivateGameSquares();
        }
    }



    private static class TicRadioButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            model.setUserGameSimbol('X');
            view.activateGameSquares();
        }
    }



    private static class TacRadioButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            model.setUserGameSimbol('O');
            view.activateGameSquares();
        }
    }

}
