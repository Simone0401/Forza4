/**
 * La classe Grid rappresenta la griglia di gioco composta da 6 righe e 7 colonne
 * @version 1.00 08 Sept 2021
 * @author Ferri Francesco, Argento Simone
 *
 */
public class Grid {
	
	// Matrix per rappresentare la griglia
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
	public boolean checkGrid(int column, int player) {
		
		int row = this.available[column] + 1;
		System.out.println("colonna " + column + "   riga " + row);
		
		// Controllo orizzontale a dx dal punto di immissione
		if (this.horizontalDxCheck(column, row, player)) {
			System.out.println("ORIZZONTALE DESTRA");
			return true;
		}
		
		// Controllo orizzontale a sx dal punto di immissione
		if (this.horizontalSxCheck(column, row, player)) {
			System.out.println("ORIZZONTALE SINISTRA");
			return true;
		}
		
		// Controllo verticale verso il basso dal punto di immissione
		if (this.verticalDownCheck(column, row, player)) {
			System.out.println("VERTICALE SOTTO");
			return true;
		}
		
		// Controllo obliquo verso dx in basso dal punto di immissione
		if (this.crossDownDxCheck(column, row, player)) {
			System.out.println("DIAGONALE SOTTO DESTRA");
			return true;
		}
		
		// Controllo obliquo verso sx in basso dal punto di immissione
		if (this.crossDownSxCheck(column, row, player)) {
			System.out.println("DIAGONALE SOTTO SINISTRA");
			return true;
		}
		
		// Controllo obliquo verso dx in alto dal punto di immissione
		if (this.crossUpDxCheck(column, row, player)) {
			System.out.println("DIAGONALE SOPRA DESTRA");
			return true;
		}
		
		// Controllo obliquo verso sx in alto dal punto di immissione
		if (this.crossUpSxCheck(column, row, player)) {
			System.out.println("DIAGONALE SOPRA SINISTRA");
			return true;
		}
		
		// Controllo pareggio
		if (this.tieCheck()) {
			System.out.println("PAREGGIO");
		}
		
		return false;
	}
	
	/**
	 * Metodo per controllare se ci sono 4 pedine orizzontali verso destra dal punto di inserimento
	 * @param column colonna dove è stata inserita la pedina
	 * @param row riga dove è posizionata la pedina
	 * @param player giocatore che ha posizionato la pedina
	 * @return true se ci sono 4 pedine allineate orizzontalemente a destra, false altrimenti
	 */
	private boolean horizontalDxCheck(int column, int row, int player) {
		// Ottimizzazione nel caso in cui si incontrasse subito una cella diversa
		if(column < 4) {
			for(int i = 1; i < 4; i++) {
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
	 * @param column colonna dove � stata inserita la pedina
	 * @param row riga dove � posizionata la pedina
	 * @param player giocatore che ha posizionato la pedina
	 * @return true se ci sono 4 pedine allineate orizzontalemente a sinistra, false altrimenti
	 */
	private boolean horizontalSxCheck(int column, int row, int player) {
		// Ottimizzazione nel caso in cui si incontrasse subito una cella diversa
		if (column > 2) {
			for(int i = 1; i < 4; i++) {
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
		// Ottimizzazione nel caso in cui si incontrasse subito una cella diversa
		if(row < 3) {
			for(int i = 1; i < 4; i++) {
				if (this.matrix[row+i][column] != player) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo per controllare se ci sono 4 pedine allineate in diagonale verso il basso a destra dal punto di inserimento
	 * @param column colonna dove è stata inserita la pedina
	 * @param row riga dove è posizionata la pedina
	 * @param player giocatore che ha posizionato la pedina
	 * @return true se ci sono 4 pedine allineate diagonalmente verso il basso a destra, false altrimenti
	 */
	private boolean crossDownDxCheck(int column, int row, int player) {
		if (column < 3 && row < 3) {
			for (int i = 1; i < 4; i++) {
				if (this.matrix[row+i][column+i] != player) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo per controllare se ci sono 4 pedine allineate in diagonale verso il basso a sinistra dal punto di inserimento
	 * @param column colonna dove è stata inserita la pedina
	 * @param row riga dove è posizionata la pedina
	 * @param player giocatore che ha posizionato la pedina
	 * @return true se ci sono 4 pedine allineate diagonalmente verso il basso a sinistra, false altrimenti
	 */
	private boolean crossDownSxCheck(int column, int row, int player) {
		if (column > 3 && row < 3) {
			for (int i = 1; i < 4; i++) {
				if (this.matrix[row+i][column-i] != player) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo per controllare se ci sono 4 pedine allineate in diagonale verso l'alto a destra dal punto di inserimento
	 * @param column colonna dove è stata inserita la pedina
	 * @param row riga dove è posizionata la pedina
	 * @param player giocatore che ha posizionato la pedina
	 * @return true se ci sono 4 pedine allineate diagonalmente verso l'alto a destra, false altrimenti
	 */
	private boolean crossUpDxCheck(int column, int row, int player) {
		if (column < 4 && row > 2) {
			for (int i = 1; i < 4; i++) {
				if (this.matrix[row-i][column+i] != player) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo per controllare se ci sono 4 pedine allineate in diagonale verso l'alto a sinistra dal punto di inserimento
	 * @param column colonna dove è stata inserita la pedina
	 * @param row riga dove è posizionata la pedina
	 * @param player giocatore che ha posizionato la pedina
	 * @return true se ci sono 4 pedine allineate diagonalmente verso l'alto a sinistra, false altrimenti
	 */
	private boolean crossUpSxCheck(int column, int row, int player) {
		if (column > 2 && row > 2) {
			for (int i = 1; i < 4; i++) {
				if (this.matrix[row-i][column-i] != player) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo per controllare se la partita è finita in pareggio
	 
	 * @return true se la griglia è piena e non ci sono pedine allineate verticalmente, orizzontalmente o diagonalmente, false altrimenti
	 */
	private boolean tieCheck() {
		int somma = 0; 
		for (int i = 0; i < 7; i++) {
			somma += this.available[i];
		}
		// La griglia è piena se ogni cella dell'array contiene -1
		if (somma == -7) { 
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo per inserire una pedina nella colonna selezionata dal giocatore
	 * La pedina è posizionata alla prima riga libera incontrata nella colonna corrispondente
	 * @param column colonna dove inserire la pedina
	 * @param player giocatore che ha inserito la pedina
	 */
	public boolean insert(int column, int player) {
		int row = this.available[column];
		if (this.fullCheck(row)) {
			System.out.println("COLONNA PIENA, SCEGLIERE UN ALTRA COLONNA");
			return false;
		}
		this.matrix[row][column] = player;
		this.available[column] = this.available[column]-1;
		return true;
	}
	
	/**
	 * Metodo per controllare se la colonna indicata per l'inserimento di una pedina è piena
	 * @param row numero righe disponibili
	 * @return true se la colonna è piena, fase altrimenti
	 */
	
	public boolean fullCheck(int row) {
		if (row == -1) {
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * Metodo per stampare a video la griglia di gioco e le pedine contenute al suo interno
	 */
	public void show() {
		
		for (int j=0;j<6;j++) {
			System.out.print(j + " [ ");
			
			for(int i=0;i<7;i++) {
				if (this.matrix[j][i] != 0) {
					System.out.print(this.matrix[j][i] +" , ");
				}
				else if (i != 6) {
					System.out.print("  , ");
				}
				else {
					System.out.print("  ");
				}
			}
			
			System.out.print("]");
			System.out.println();
			
			if (j != 5) {
				System.out.println();
			}
		}
		System.out.println("   ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
		System.out.println("    0   1   2   3   4   5   6\n");	
	}
}


