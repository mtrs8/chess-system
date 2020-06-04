package Boardgame;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns){
		if(rows < 1 || columns < 1)
			throw new BoardException("Error creating board: There must be at least 1 row and 1 column!");
		this.rows = rows;
		this.columns = columns;
		this.pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	// Verifica se a pe�a est� dentro das possibilidades do tabuleiro
	public Piece piece(int row, int column){
		if(!positionExists(row, column)){
			throw new BoardException("Position not on the board!");
		}
		return pieces[row][column];
	}
	
	// Sobrecarrega o m�todo acima buscando a posi��o de determinada pe�a
	public Piece piece(Position position){
		if(!positionExists(position))
			throw new BoardException("Position not on the board!");
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position){
		if(thereIsAPiece(position)){
			throw new BoardException("There is already a piece on position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	// Remove uma pe�a do tabuleiro
	public Piece removePiece(Position position) {
		if(!positionExists(position)) throw new BoardException("Position not on the board!");
		
		if(piece(position) == null) return null;
		
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}
	
	// Verifica a exist�ncia da pe�a no tabuleiro
	public boolean positionExists(int row, int column){
		return (row >= 0 && row < rows) 
				&& (column >= 0 && column < columns);
	}
	
	// Verifica a disponibilidade de uma posi��o do tabuleiro
	public boolean positionExists(Position position){
		return positionExists(position.getRow(), position.getColumn());
	}
	
	// Verifica qual pe�a est� em determinada posi��o
	public boolean thereIsAPiece(Position position){
		if(!positionExists(position))
			throw new BoardException("Position not on the board!");
		return piece(position) != null;
	}
}
