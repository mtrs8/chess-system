package App;

import java.util.Scanner;

import Boardgame.BoardException;
import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;

public class App {
	
	public void run(){
		try{
			Scanner sc = new Scanner(System.in);
			ChessMatch match = new ChessMatch();
			
			while(true){
				UI.printBoard(match.getPieces());			
				System.out.println();
				System.out.print("Source Position: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				System.out.println();
				System.out.print("Target Position: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				ChessPiece capturedPiece = match.performChessMove(source, target);
			}
		} catch(BoardException b){
			b.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		App app = new App();
		app.run();
	}

}
