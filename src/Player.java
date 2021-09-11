/**
 * Classe per rappresentare un giocatore
 * @version 1.00 11 Sept 2021
 * @author Argento Simone
 *
 */
public class Player {

	String username;	// Nome inserito dal giocatore
	int won;			// Storico partite vinte
	int tied;			// Storico partite pareggiate
	int lost;			// Storico partite perse
	
	/**
	 * Costruttore per il giocatore
	 * @param username nome del giocatore
	 * @param won partite vinte
	 * @param tied partite pareggiate
	 * @param lost partite perse
	 */
	public Player(String username, int won, int tied, int lost) {
		this.username = username;
		this.won = won;
		this.tied = tied;
		this.lost = lost;
	}
	
	/**
	 * Metodo per ottenere l'username del giocatore
	 * @return l'username del giocatore
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Metodo per cambiare l'username del giocatore
	 * @param newUsername nuovo username da usare
	 */
	public void changeUsername(String newUsername) {
		this.setUsername(newUsername);
	}
	
	/**
	 * Metodo per settare l'username
	 * @param username
	 */
	private void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Metodo per ottenere le vittorie totali del giocatore
	 * @return numero di partite vinte
	 */
	public int getWon() {
		return won;
	}

	/**
	 * Metodo per settare il numero di partite vinte
	 * @param won nuovo numero di partite vinte
	 */
	public void setWon(int won) {
		this.won = won;
	}

	/**
	 * Metodo per ottenere i pareggi totali del giocatore
	 * @return numero di partite pareggiate
	 */
	public int getTied() {
		return tied;
	}

	/**
	 * Metodo per settare il numero di partite pareggiate
	 * @param tied nuovo numero di partite pareggiate
	 */
	public void setTied(int tied) {
		this.tied = tied;
	}

	/**
	 * Metodo per ottenere le sconfitte totali del giocatore
	 * @return numero di partite perse
	 */
	public int getLost() {
		return lost;
	}

	/**
	 * Metodo per settare il numero di partite perse
	 * @param lost nuovo numero di partite perse
	 */
	public void setLost(int lost) {
		this.lost = lost;
	}
	
	/**
	 * Metodo per ottenere il numero totale di partite giocate
	 * @return numero di partite disputate
	 */
	public int getMatchesNumber() {
		int matches;
		matches = this.getWon() + this.getTied() + this.getLost();
		return matches;
	}
	
}