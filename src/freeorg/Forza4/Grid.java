package freeorg.Forza4;
/**
 * La classe Grid rappresenta la griglia di gioco composta da 6 righe e 7 colonne
 * @version 1.00 08 Sept 2021
 * @author Ferri Francesco, Argento Simone
 *
 */
public class Grid {
	
	private int [][] matrix;
	private int [] available;
	
	/**
	 * Costruttore della griglia di gioco
	 */
	public Grid() {
		// Matrix per rappresentare la griglia
		this.matrix = new int[6][7];
		
		// Array per rappresentare quante sono le celle libere per ogni colonna
		// All'inizio la griglia è vuota e ogni colonna ha 6 celle libere (dalla cella 0 alla 5)
		this.available = new int[7];
		this.setAvailable();
	}
	
	/**
	 * Costruttore della griglia di gioco nel caso è già presente su file JSON
	 * @param matrix la griglia di gioco come matrice di numeri interi
	 */
	public Grid(int[][] matrix) {
		// Matrix per rappresentare la griglia
		this.matrix = matrix;
		
		// Array per rappresentare quante sono le celle libere per ogni colonna
		// All'inizio la griglia è vuota e ogni colonna ha 6 celle libere (dalla cella 0 alla 5)
		this.available = new int[7];
		this.setAvailable(matrix);
	}
	
	/**
	 * Metodo per settare il numero di righe vuote per ogni colonna all'inizio della partita
	 */
	private void setAvailable() {
		for (int i = 0; i < 7; i++) {
			this.available[i] = 5;
		}
	}
	
	/**
	 * Metodo per settare il numero di righe vuote per ogni colonna
	 * @param matrix matrice di gioco salvata in un tempo precedente a quello di gioco attuale
	 */
	private void setAvailable(int[][] matrix) {
		for (int c = 0; c < 7; c++) {
			int free = -1;
			for (int r = 0; r < 6 && matrix[r][c] == 0; r++) {
				free += 1;
			}
			this.available[c] = free;
		}
	}
	
	
	/**
	 * Metodo per ritornare la riga nella quale è stata posizionata la pedina
	 * @param column colonna dove è stata aggiunta la pedina
	 * @return numero che identifica la riga dove è stata posizionata la pedina
	 */
	public int getRow(int column) {
		return this.available[column] ;
	}
	
	/**
	 * Metodo per ritornare lo stato della matrice di gioco
	 * @return la matrice di gioco
	 */
	public int[][] getMatrix() {
		return this.matrix;
	}
	
	/**
	 * Metodo per indicare che alla colonna è stata aggiunta una nuova pedina
	 * @param column colonna dove è stata aggiunta la pedina
	 */
	private void setAvailable(int column) {
		this.available[column] = this.available[column] - 1;
	}
	
	/**
	 * Metodo per ritornare l'oggetto come una stringa
	 * @return la matrice
	 */
	@Override
	public String toString() {
		String stringa = "";
		for(int r = 0; r < 6; r++) {
			stringa += "[";
			for (int c = 0; c < 7; c++) {
				stringa = stringa + this.matrix[r][c];
				if (c != 6) {
					stringa += ",";
				}
			}
			stringa += "]";
			if (r != 5) {
				stringa += ",";
			}
			
		}
		return stringa;
	}

	/**
	 * Metodo per controllare se l'ultimo giocatore che ha inserito la pedina ha vinto
	 * @param column rappresenta la colonna dove il giocatore vuole inserire la pedina
	 * @param player rappresenta il giocatore che inserito l'ultima pedina
	 * @return true se il giocatore è riuscito ad allineare 4 pedine orizzontalmente, verticalmente oppure diagonalmente, false altrimenti
	 */
	public boolean checkGrid(int column, int player) {
		
		int row = this.available[column] + 1;
		
		// Controllo orizzontale dal punto di immissione
		if (this.horizontalCheck(column, row, player)) {
			return true;
		}
		
		// Controllo verticale verso il basso dal punto di immissione
		if (this.verticalCheck(column, row, player)) {
			return true;
		}
		
		// Controllo obliquo dal punto di immissione
		if (this.crossCheck(column, row, player)) {
			return true;
		}
		
		// La partita non ha ancora un vincitore
		return false;
	}
	

	/**
	 * Metodo per controllare se ci sono 4 pedine allineate in orizzontale dal punto di inserimento
	 * @param column colonna dove è stata inserita la pedina
	 * @param row riga dove è posizionata la pedina
	 * @param player giocatore che ha posizionato la pedina
	 * @return true se ci sono 4 pedine allineate orizzontalmente, false altrimenti
	 */
	private boolean horizontalCheck(int column, int row, int player) {
		
		int disc  = this.horizontalDxCountDisc(column, row, player) 
					+ this.horizontalSxCountDisc(column, row, player) 
					+ 1;	// Si aggiunge al conteggio anche la pedina appena inserita
		
		if (disc >= 4) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Metodo per contare quante pedine sono allineate verso destra rispetto a quella appena inserita nella colonna
	 * @param column colonna dove è stata inserita la pedina
	 * @param row riga dove è stata posizionata la pedina
	 * @param player giocatore che ha inserito la pedina
	 * @return numero di pedine dello stesso colore allineate verso destra
	 */
	private int horizontalDxCountDisc(int column, int row, int player) {
		
		int disc = 0;
		
		while (column > 0 && this.matrix[row][column - 1] == player) {
			disc += 1;
			column -= 1;
		}
		
		return disc;
	}
	
	/**
	 * Metodo per contare quante pedine sono allineate verso sinistra rispetto a quella appena inserita nella colonna
	 * @param column colonna dove è stata inserita la pedina
	 * @param row riga dove è stata posizionata la pedina
	 * @param player giocatore che ha inserito la pedina
	 * @return numero di pedine dello stesso colore allineate verso destra
	 */
	private int horizontalSxCountDisc(int column, int row, int player) {
		
		int disc = 0;
		
		while (column < 6 && this.matrix[row][column + 1] == player) {
			disc += 1;
			column += 1;
		}
		
		return disc;
	}
	
	/**
	 * Metodo per controllare se ci sono 4 pedine verticali verso il basso dal punto di inserimento
	 * @param column colonna dove è stata inserita la pedina
	 * @param row riga dove è posizionata la pedina
	 * @param player giocatore che ha posizionato la pedina
	 * @return true se ci sono 4 pedine allineate verticalmente verso il basso, false altrimenti
	 */
	private boolean verticalCheck(int column, int row, int player) {
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
	 * Metodo per controllare se ci sono 4 pedine allineate in diagonale dal punto di inserimento
	 * @param column colonna dove è stata inserita la pedina
	 * @param row riga dove è posizionata la pedina
	 * @param player giocatore che ha posizionato la pedina
	 * @return true se ci sono 4 pedine allineate diagonalmente verso il basso a destra, false altrimenti
	 */
	private boolean crossCheck(int column, int row, int player) {
		int disc  = this.crossUpDxCountDisc(column, row, player)
					+ this.crossDownSxCountDisc(column, row, player)
					+ 1;	// Si aggiunge al conteggio anche la pedina appena inserita
		
		if (disc >= 4) {
			return true;
		}
		
		disc = 0;
		disc = this.crossDownDxCountDisc(column, row, player)
			   + this.crossUpSxCountDisc(column, row, player)
			   + 1;		// Si aggiunge al conteggio anche la pedina appena inserita
		
		if (disc >= 4) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Metodo per contare quante pedine sono allineate diagonalmente verso destra in alto rispetto a quella appena inserita nella colonna
	 * @param column colonna dove è stata inserita la pedina
	 * @param row riga dove è stata posizionata la pedina
	 * @param player giocatore che ha inserito la pedina
	 * @return numero di pedine dello stesso colore allineate diagonalmente verso destra in alto
	 */
	private int crossUpDxCountDisc(int column, int row, int player) {
		
		int disc = 0;
		
		while (column < 6 && row > 0 && this.matrix[row - 1][column + 1] == player) {
			disc += 1;
			column += 1;
			row -= 1;
		}
		
		return disc;
	}
	
	/**
	 * Metodo per contare quante pedine sono allineate diagonalmente verso destra in basso rispetto a quella appena inserita nella colonna
	 * @param column colonna dove è stata inserita la pedina
	 * @param row riga dove è stata posizionata la pedina
	 * @param player giocatore che ha inserito la pedina
	 * @return numero di pedine dello stesso colore allineate diagonalmente verso destra in basso
	 */
	private int crossDownDxCountDisc(int column, int row, int player) {
		
		int disc = 0;
		
		while (column < 6 && row < 5 && this.matrix[row + 1][column + 1] == player) {
			disc += 1;
			column += 1;
			row += 1;
		}
		
		return disc;
	}
	
	/**
	 * Metodo per contare quante pedine sono allineate diagonalmente verso sinistra in alto rispetto a quella appena inserita nella colonna
	 * @param column colonna dove è stata inserita la pedina
	 * @param row riga dove è stata posizionata la pedina
	 * @param player giocatore che ha inserito la pedina
	 * @return numero di pedine dello stesso colore allineate diagonalmente verso sinistra in alto
	 */
	private int crossUpSxCountDisc(int column, int row, int player) {
		
		int disc = 0;
		
		while (column > 0 && row > 0 && this.matrix[row - 1][column - 1] == player) {
			disc += 1;
			column -= 1;
			row -= 1;
		}
		
		return disc;
	}
	
	/**
	 * Metodo per contare quante pedine sono allineate diagonalmente verso sinistra in basso rispetto a quella appena inserita nella colonna
	 * @param column colonna dove è stata inserita la pedina
	 * @param row riga dove è stata posizionata la pedina
	 * @param player giocatore che ha inserito la pedina
	 * @return numero di pedine dello stesso colore allineate diagonalmente verso sinistra in basso
	 */
	private int crossDownSxCountDisc(int column, int row, int player) {
		
		int disc = 0;
		
		while (column > 0 && row < 5 && this.matrix[row + 1][column - 1] == player) {
			disc += 1;
			column -= 1;
			row += 1;
		}
		
		return disc;
	}
	
	/**
	 * Metodo per controllare se la partita è finita in pareggio
	 * @return true se la griglia è piena e non ci sono pedine allineate verticalmente, orizzontalmente o diagonalmente, false altrimenti
	 */
	public boolean tieCheck() {
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
	 * Metodo per inserire una pedina nella colonna selezionata dal giocatore.
	 * La pedina è posizionata alla prima riga libera incontrata nella colonna corrispondente.
	 * @param column colonna dove inserire la pedina.
	 * @param player giocatore che ha inserito la pedina.
	 * @return true se l'inserimento è andato a buon fine, false altrimenti.
	 */
	public boolean insert(int column, int player) {
		int row = this.available[column];
		if (this.fullCheck(row)) {
			return false;	
		}
		else {
			this.matrix[row][column] = player;
			this.setAvailable(column);
			return true;
		}
		
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
	
}


