package Chess.pieces;

import Boardgame.Board;
import Chess.ChessPiece;
import Chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color){
		super(board, color);
	}
	
	public String toString(){
		return "R";
	}
}
