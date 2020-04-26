package Chess;

import Boardgame.Board;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch(){
		this.board = new Board(8, 8);
	}
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[this.board.getRows()][this.board.getColumns()];
		
		for(int i=0; i<board.getRows(); i++){
			for(int j=0; j<board.getColumns(); j++){
				mat[i][j] = (ChessPiece) this.board.piece(i, j);
			}
		}
		
		return mat;
	}
}	
