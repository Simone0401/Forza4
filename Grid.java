/**
 * La classe Grid rappresenta la griglia di gioco composta da 6 righe e 7 colonne
 * @version 1.00 08 Sept 2021
 * @author Ferri Francesco
 *
 */
public class Grid {
	
	// matrix per rappresentare la griglia
	private int [][] matrix= new int[6][7];
	
	// Array per rappresentare quante sono le celle libere per ogni colonna
	// All'inizio la griglia è vuota e ogni colonna ha 6 celle libere (dalla cella 0 alla 5)
	private int [] available = {5,5,5,5,5,5,5};
	
	/**
	 * Metodo per controllare se l'ultimo giocatore che ha inserito la pedina ha vinto
	 * @param colonna rappresenta la colonna dove il giocatore vuole inserire la pedina
	 * @param player rappresenta il 
	 * @return
	 */
	public boolean checkWin(int column, int player) {
		
		boolean result=false;
		int k = 0;
		int row = this.available[column] + 1;
		System.out.println("colonna " + column + "   riga " + row);
		
		// Controllo orizzontale a dx dal punto di immissione
		if (this.horizontalDxCheck(column, row, player)) {
			return true;
		}
		
		if (this.horizontalSxCheck(column, row, player)) {
			return true;
		}
		
		if (this.verticalDownCheck(column, row, player)) {
			return true;
		}
		
		if(column<3 && row<4) {
			k=0;
			int j=1;
			for(int i=1;i<4;i++) { //controllo obliquo verso dx dal punto di immissione
		
			
			if(this.matrix[row+i][column+j]==player) {
				k++;
			}
			j++;
		}
		if(k==3) {
				return true;	
			}
		
		}
		if (column>3 && row<3) {
			k=0;
			for(int i=1;i<4;i++) { //controllo obliquo verso sx dal punto di immissione
			
			int j=1;
			if(this.matrix[row+1][column-j]==player) {
				k++;
			}
			j++;
		}
		if(k==3) {
				return true;
			}
		
		}
		
		return result;
	}
	
	/**
	 * Metodo per controllare se ci sono 4 pedine orizzontali verso destra dal punto di inserimento
	 * @param column colonna dove è stata inserita la pedina
	 * @param row riga dove è posizionata la pedina
	 * @param player giocatore che ha posizionato la pedina
	 * @return true se ci sono 4 pedine allineate orizzontalemente a destra, false altrimenti
	 */
	private boolean horizontalDxCheck(int column, int row, int player) {
		//ottimizzazione nel caso in cui si beccasse subito una cella diversa
		if(column<4) {
			for(int i=1;i<4;i++) {
				if(this.matrix[row][column+i] != player) {
					return false;
				}
			}
		return true;
		}
		return false;
	}
	
	/**
	 * Metodo per controllare se ci sono 4 pedine orizzontali verso sinistra dal punto di inserimento
	 * @param column colonna dove è stata inserita la pedina
	 * @param row riga dove è posizionata la pedina
	 * @param player giocatore che ha posizionato la pedina
	 * @return true se ci sono 4 pedine allineate orizzontalemente a sinistra, false altrimenti
	 */
	private boolean horizontalSxCheck(int column, int row, int player) {
		//ottimizzazione nel caso in cui si beccasse subito una cella diversa
		if (column>2) {
			for(int i=1;i<4;i++) { //controllo orizzontale a sx dal punto di immissione
				if(this.matrix[row][column-i] != player) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo per controllare se ci sono 4 pedine verticali verso il basso dal punto di inserimento
	 * @param column colonna dove è stata inserita la pedina
	 * @param row riga dove è posizionata la pedina
	 * @param player giocatore che ha posizionato la pedina
	 * @return true se ci sono 4 pedine allineate verticalmente verso il basso, false altrimenti
	 */
	private boolean verticalDownCheck(int column, int row, int player) {
		//ottimizzazione nel caso in cui si beccasse subito una cella diversa
		if(row<3) {
			for(int i=1;i<4;i++) { //controllo verticale verso il basso dal punto di immissione
				if(this.matrix[row+i][column] != player) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public void inserisci(int column,int player) {
		int row = this.available[column];
		this.matrix[row][column]=player;
		this.available[column]=this.available[column]-1;
	}
	
	public void show() {
		
		for (int j=0;j<6;j++) {
			System.out.print("[");
		for(int i=0;i<7;i++) {
			System.out.print(this.matrix[j][i] +" , ");
		}
		System.out.print("]");
		System.out.println();
		System.out.println();
		}
		
	}
	
	

}


