package App;

import Chess.ChessPiece;

public class UI {
	
	public static void printBoard(ChessPiece[][] pieces){
		
		for(int iCont=0; iCont<pieces.length; iCont++){
			System.out.print((8 - iCont) + " ");
			for(int jCont=0; jCont<pieces.length; jCont++){
				printPiece(pieces[iCont][jCont]);
			}
			
			System.out.println();		
		}
		System.out.println("  a b c d e f g h");
	}
	
	private static void printPiece(ChessPiece piece){
		if(piece == null){
			System.out.print("-");			
		} else{
			System.out.print(piece);			
		}
		System.out.print(" ");
	}
}
