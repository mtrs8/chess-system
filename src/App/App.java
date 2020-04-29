package App;

import Boardgame.BoardException;
import Chess.ChessMatch;

public class App {
	
	public static void main(String[] args) {
		try{
			ChessMatch match = new ChessMatch();
			UI.printBoard(match.getPieces());			
		} catch(BoardException b){
			b.printStackTrace();
		}
	}

}
