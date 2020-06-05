package App;

import java.util.InputMismatchException;
import java.util.Scanner;

import Chess.ChessPiece;
import Chess.ChessPosition;
import Chess.Color;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	// Trecho para atribuir cores as peças - Necessário usar um terminal colorido. 
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void clearSreen() { //Limpa tabuleiro antigo após as jogadas
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	//Recebe as peças no formado padrão do xadrez e converte para Matrizes
	public static ChessPosition readChessPosition(Scanner sc){ 
		try{
			String s = sc.nextLine();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column, row);			
			
		} catch(RuntimeException e){
			throw new InputMismatchException("Error reading ChessPosition. Valid value are from a1 to h8.");
		}
	}
	
	// Mostra o tabuleiro na tela
	public static void printBoard(ChessPiece[][] pieces) {

		for (int iCont = 0; iCont < pieces.length; iCont++) {
			System.out.print((8 - iCont) + " ");
			for (int jCont = 0; jCont < pieces.length; jCont++) {
				printPiece(pieces[iCont][jCont], false);
			}

			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	//Mostra o tabuleiro na tela mudando o background para as posições possíveis
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {

		for (int iCont = 0; iCont < pieces.length; iCont++) {
			System.out.print((8 - iCont) + " ");
			for (int jCont = 0; jCont < pieces.length; jCont++) {
				printPiece(pieces[iCont][jCont], possibleMoves[iCont][jCont]);
			}

			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	// Mostra as peças na tela
	private static void printPiece(ChessPiece piece, boolean background) {
		if(background)
			System.out.print(ANSI_BLUE_BACKGROUND);
		
		if (piece == null) {
			System.out.print("-" + ANSI_RESET);
		}
        else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
}
