package Chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Boardgame.Board;
import Boardgame.Piece;
import Boardgame.Position;
import Chess.pieces.King;
import Chess.pieces.Rook;

public class ChessMatch {
	
	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	
	private List<Piece> piecesOnTheBoard;
	private List<Piece> capturedPieces;
	
	public ChessMatch(){
		//this.check = false;
		this.board = new Board(8, 8);
		this.turn = 1;
		this.currentPlayer = Color.WHITE;
		this.piecesOnTheBoard = new ArrayList<>();
		this.capturedPieces = new ArrayList<>();
		initialSetup();
	}
	
	//Get methods
	public int getTurn(){
		return this.turn;
	}
	
	public Color getCurrentPlayer(){
		return this.currentPlayer;
	}
	
	public boolean getCheck(){
		return this.check;
	}
	
	private void setCheck(boolean check){
		this.check = check;
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
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	// Movimenta as pe�as dentro do tabuleiro
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		
		if(testCheck(currentPlayer)){
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put yourself in check!");
		}
		setCheck((testCheck(opponent(currentPlayer))) ? true : false);
		nextTurn();
		return (ChessPiece)capturedPiece;
	}
	
	// Sub-Rotina para movimenta��o das pe�as
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		
		if(capturedPiece != null){
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}
	
	private void undoMove(Position source, Position target, Piece capturedPiece){
		Piece p = board.removePiece(target);
		board.placePiece(p, source);
		
		if(capturedPiece != null){
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}
	
	// Sub-Rotina para movimenta��o das pe�as
	private void validateSourcePosition(Position position){
		if(!board.thereIsAPiece(position))
			throw new ChessException("There is no piece on source position!");
		
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor())
			throw new ChessException("The chosen piece isn't yours!");
		
		if(!board.piece(position).isThereAnyPossibleMove())
			throw new ChessException("There is no possible moves for chosen piece!");
	}
	
	private void validateTargetPosition(Position source, Position target) {
		if(!board.piece(source).possibleMove(target))
			throw new ChessException("The chosen piece can't move to target position!");
	}
	
	//Cria turnos entre os jogadores branco e preto
	private void nextTurn(){
		this.turn++;
		this.currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private Color opponent(Color color){
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king(Color color){
		List<Piece> list = piecesOnTheBoard
				.stream().filter(x -> ((ChessPiece)x)
				.getColor() == color).collect(Collectors.toList());
		
		for(Piece p : list) { 
			if(p instanceof King){
				return (ChessPiece) p;
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the board!");
	}
	
	//Verifica se o Rei est� em cheque
	private boolean testCheck(Color color){
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard
				.stream().filter(x -> ((ChessPiece)x)
				.getColor() == opponent(color))
				.collect(Collectors.toList());
		
		for(Piece p : opponentPieces){
			boolean[][] mat = p.possibleMoves();
			if(mat[kingPosition.getRow()][kingPosition.getColumn()])
				return true;
		}
		return false;
	}
	
	// Faz uma "tradu��o" entre o modelo padr�o e o modelo de matrizes
	private void placeNewPiece(char column, int row, ChessPiece piece){
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
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
