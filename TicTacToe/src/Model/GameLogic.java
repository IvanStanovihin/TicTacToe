package Model;

import java.util.ArrayList;

public class GameLogic {

    private ArrayList<GameSquare>gameBoard;
    private char userGameSimbol;
    private char computerGameSimbol;
    private int countMovies;



    public char getUserGameSimbol(){
        return userGameSimbol;
    }
    public char getComputerGameSimbol(){
        return computerGameSimbol;
    }
    public ArrayList<GameSquare>getGameSquares(){
        return gameBoard;
    }
    public int getCountMovies(){
        return countMovies;
    }



    public void upCountMovies(){
        countMovies++;
    }



    public void setUserGameSimbol(char gameSimbol){
        if (gameSimbol == 'X') {
            userGameSimbol = 'X';
            computerGameSimbol = 'O';
        }else{
            userGameSimbol = 'O';
            computerGameSimbol = 'X';
        }
    }



    public GameLogic(){
        gameBoard = new ArrayList<GameSquare>();
        for (int i = 0; i < 9; i ++){
            gameBoard.add(new GameSquare());
        }
    }



    public void clearGameBoard(){
        countMovies = 0;
        for (GameSquare square : gameBoard){
            square.clear();
        }
    }


    //метод проверяет есть ли на игровой доске выйгрышная позиция для переданного символа(gameSimbol)
    public boolean checkGameStatus(char gameSimbol){
        ArrayList<int[]> endGamePosition = new ArrayList<int[]>();
        endGamePosition.add(new int[]{1, 4, 7});
        endGamePosition.add(new int[]{2, 5, 8});
        endGamePosition.add(new int[]{3, 6, 9});
        endGamePosition.add(new int[]{1, 2, 3});
        endGamePosition.add(new int[]{4, 5, 6});
        endGamePosition.add(new int[]{7, 8, 9});
        endGamePosition.add(new int[]{1, 5, 9});
        endGamePosition.add(new int[]{3, 5, 7});
        boolean endGame = false;
        for (int[] position : endGamePosition){
            endGame = true;
            for (int square : position){
                if (gameBoard.get(square-1).getWrittenSimbol() == null || gameBoard.get(square - 1).getWrittenSimbol() != gameSimbol){
                    endGame = false;
                    break;
                }
            }
            if (endGame){
                break;
            }
        }
        return endGame;
    }



    //Метод для хода игрока.
    public void userMove(int position){
        GameSquare chooseSquare = gameBoard.get(position);
        chooseSquare.writeSimbol(userGameSimbol);
    }



    //метод для хода компьютера
    public void computerMove(){
        int position = (int)(Math.random()*9);
        GameSquare chooseSquare = gameBoard.get(position);
        if (chooseSquare.isEmpty()){
            chooseSquare.writeSimbol(computerGameSimbol);
        }else{
                computerMove();
        }
    }




    public static class GameSquare{

        private Character writtenSimbol;


        public GameSquare(){}


        public Character getWrittenSimbol(){
            return writtenSimbol;
        }



        //Метод записывает символ(Х или 0) в ячейку.
        public void writeSimbol(char simbol){
                writtenSimbol = simbol;
        }



        public void clear(){
            writtenSimbol = null;
        }



        //Метод возвращает true - если ячейка пуста, или false - если ячейка уже занята.
        public boolean isEmpty(){
            return (writtenSimbol == null);
        }
    }



}