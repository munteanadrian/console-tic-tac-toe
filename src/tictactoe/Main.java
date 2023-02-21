package tictactoe;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    private String board;

    public Main() {
        this.board = "         ";
        this.print();
    }

    public void print() {
        System.out.println("---------");
        for(int i = 0; i < 9; i++) {
            char crt = this.board.charAt(i);
            System.out.print(i % 3 == 2 ? crt + " |\n" : i % 3 == 0 ? "| " + crt + " " : crt + " ");
        }
        System.out.println("---------");
    }

    public int count(char lookup) {
//        return (int) IntStream.range(0, 9).map(i -> this.board.charAt(i)).filter(crt -> crt == lookup).count();
        return this.board.length() - this.board.replace(String.valueOf(lookup), "").length();
    }

    public String check() {
        String result = "";

        boolean X = false;
        boolean O = false;

        for (int i = 0; i < 3; i++) {
//            check lines
            if (this.board.charAt(i * 3) == this.board.charAt(i * 3 + 1) && this.board.charAt(i * 3 + 1) == this.board.charAt(i * 3+ 2)) {
                X = this.board.charAt(i * 3) == 'X';
                O = this.board.charAt(i * 3) == 'O';
            }

//            check columns
            if (this.board.charAt(i) == this.board.charAt(i + 3) && this.board.charAt(i + 3) == this.board.charAt(i + 6)) {
                X = this.board.charAt(i) == 'X';
                O = this.board.charAt(i) == 'O';
            }
        }

//        check main diagonal
        if (this.board.charAt(0) == this.board.charAt(4) && this.board.charAt(4) == this.board.charAt(8)) {
            X = this.board.charAt(4) == 'X';
            O = this.board.charAt(4) == 'O';
        }

//        check secondary diagonal
        if (this.board.charAt(2) == this.board.charAt(4) && this.board.charAt(4) == this.board.charAt(6)) {
            X = this.board.charAt(4) == 'X';
            O = this.board.charAt(4) == 'O';
        }

        if (!X && !O) {
            if (this.count(' ') == 0 && this.count('_') == 0) {
                result = "Draw";
            } else {
                result = "Game not finished";
            }
        } else {
            if ((X && O) || Math.abs(this.count('X') - this.count('O')) >= 2) {
                result = "Impossible";
            } else {
                result = X ? "X wins" : (O ? "O wins" : null);
            }
        }

        return result;
    }

    public void makeMove(char player) {
        Scanner userInput = new Scanner(System.in);
        boolean validInput = false;

        do {
            String x = userInput.next();
            String y = userInput.next();

            if (!Character.isDigit(x.charAt(0)) || !Character.isDigit(y.charAt(0))) {
                System.out.println("You should enter numbers!");
            } else {
                int xInt = Integer.parseInt(x) - 1;
                int yInt = Integer.parseInt(y) - 1;

                if (xInt < 0 || xInt > 2 || yInt < 0 || yInt > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (this.board.charAt(xInt * 3 + yInt) != ' ' && this.board.charAt(xInt * 3 + yInt) != '_') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    validInput = true;
                    this.board = this.board.substring(0, xInt * 3 + yInt) + player + this.board.substring(xInt * 3 + yInt + 1);
                }
            }
        } while(!validInput);

        this.print();
    }

    public void play() {
        char currentPlayer = 'X';

        do {
            this.makeMove(currentPlayer);
            currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
        } while (this.check().equals("Game not finished"));

        System.out.println(this.check());
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.play();
    }
}
