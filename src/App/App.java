package App;

import java.util.Scanner;

import Boardgame.BoardException;
import Chess.ChessMatch;
import Chess.ChessPosition;

public class App {
	
	public void run(){
		try{
			Scanner sc = new Scanner(System.in);
			ChessMatch match = new ChessMatch();
			
			while(true){
				UI.printBoard(match.getPieces());			
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
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
