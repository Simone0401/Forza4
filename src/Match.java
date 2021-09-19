/**
 * La classe rappresenta la partita in corso. Conosce lo stato della griglia di gioco, i due giocatori e il turno di gioco. 
 * @version 1.00 16 Sept 2021
 * @author Ferri Francesco
 *
 */

import java.util.Random;

public class Match{
	
	private Grid g;			// Griglia di gioco
	private Player p1;		// Giocatore 1
	private Player p2;		// Giocatore 2
	private int t;			// Turno di gioco
	private boolean e;		// Variabile per rappresentare la terminazione della partita
	
	/**
	 * Costruttore da utilizzare quando si crea una nuova partita
	 * @param p1 giocatore 1
	 * @param p2 giocatore 2
	 */
	public Match(Player p1, Player p2) {
		this.g = new Grid();
		this.p1 = p1;
		this.p2 = p2;
		this.t = this.randTurn();		// Si evita che sia sempre il primo giocatore a iniziare
		this.e = false;
	}
	
	/**
	 * Costruttore da utilizzare quando si carica una partita già esistente
	 * @param g griglia di gioco di una precedente partita
	 * @param p1 giocatore 1 della relativa partita salvata
	 * @param p2 giocatore 2 della relativa partita salvata
	 * @param t turno del giocatore che deve ricominciare a giocare
	 */
	public Match(Grid g, Player p1, Player p2, int t) {
		this.g = g;
		this.p1 = p1;
		this.p2 = p2;
		this.t = t;
		this.e = false;
	}
	
	/**
	 * Metodo per ottenere la griglia di gioco
	 * @return la griglia di gioco
	 */
	public Grid getG() {
		return g;
	}

	/**
	 * Metodo per ottenere il giocatore 1
	 * @return il player 1
	 */
	public Player getP1() {
		return p1;
	}

	/**
	 * Metodo per ottenere il giocatore 2
	 * @return il player 2
	 */
	public Player getP2() {
		return p2;
	}
	
	/**
	 * Metodo per ottenere l'attuale turno di gioco
	 * @return 1 se è il turno del giocatore 1, 2 se è il turno del giocatore 2
	 */
	public int getTurn() {
		return this.t;
	}
	
	/**
	 * Metodo per switchare il turno del giocatore che deve posizionare la pedina
	 */
	public void changeT() {
		if (this.t == 1) {
			this.t = 2;
		}
		else {
			this.t = 1;
		}
	}
	
	/**
	 * Metodo per settare la partita come terminata
	 */
	public void ended() {
		this.e = true;
	}
	
	/**
	 * Metodo per verificare se la partita è finita
	 * @return true se la partita è finita, false altrimenti
	 */
	public boolean isEnded() {
		return this.e;
	}
	
	/**
	 * Metodo per scegliere in maniera randomica il primo giocatore a posizionare la pedina
	 * @return il turno generato. 1 per il primo giocatore, 2 per il secondo giocatore
	 */
	private int randTurn() {
		Random rand = new Random();
	    int int_random = rand.nextInt(2) + 1;	// genera un numero tra 1 e 2
	    return int_random;
	}
	
	
}
