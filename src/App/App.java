package App;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Chess.ChessException;
import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;

public class App {
	
	public void run(){
			Scanner sc = new Scanner(System.in);
			ChessMatch match = new ChessMatch();
			List<ChessPiece> captured = new ArrayList<>();
			
			while(true){
				try {
					UI.clearSreen();
					UI.printMatch(match, captured);			
					System.out.println();
					System.out.print("Source Position: ");
					ChessPosition source = UI.readChessPosition(sc);
					
					boolean[][] possibleMoves = match.possibleMoves(source);
					UI.clearSreen();
					UI.printBoard(match.getPieces(), possibleMoves);
					
					System.out.println();
					System.out.print("Target Position: ");
					ChessPosition target = UI.readChessPosition(sc);
					
					ChessPiece capturedPiece = match.performChessMove(source, target);
					
					if(capturedPiece != null)
						captured.add(capturedPiece);
					
				} catch(ChessException ce) {
					System.out.println(ce.getMessage());
					sc.nextLine();
				} catch (InputMismatchException ie){
					System.out.println(ie.getMessage());
					sc.nextLine();
				}
			}
	}
	
	public static void main(String[] args) {
		App app = new App();
		app.run();
	}

}
