package Chess;

import Boardgame.Board;
import Boardgame.Piece;
import Boardgame.Position;
import Chess.pieces.King;
import Chess.pieces.Rook;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch(){
		this.board = new Board(8, 8);
		initialSetup();
	}
	
	// Cria uma partida de Xadrez
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[this.board.getRows()][this.board.getColumns()];
		
		for(int i=0; i<board.getRows(); i++){
			for(int j=0; j<board.getColumns(); j++){
				mat[i][j] = (ChessPiece) this.board.piece(i, j);
			}
		}
		
		return mat;
	}
	
	// Movimenta as pe�as dentro do tabuleiro
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece)capturedPiece;
	}
	
	// Sub-Rotina para movimenta��o das pe�as
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		return capturedPiece;
	}
	
	// Sub-Rotina para movimenta��o das pe�as
	private void validateSourcePosition(Position position){
		if(!board.thereIsAPiece(position))
			throw new ChessException("There is no piece on source position!");
		
		if(!board.piece(position).isThereAnyPossibleMove())
			throw new ChessException("There is no possible moves for chosen piece!");
	}
	
	// Faz uma "tradu��o" entre o modelo padr�o e o modelo de matrizes
	private void placeNewPiece(char column, int row, ChessPiece piece){
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	// Inicializa o tabuleiro com as pe�as
	private void initialSetup(){
		//placeNewPiece('b', 6, new Rook(board, Color.WHITE));
		placeNewPiece('e', 8, new King(board, Color.BLACK));
		placeNewPiece('e', 1, new King(board, Color.WHITE));
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        //placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        //placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
		
	}
}	
