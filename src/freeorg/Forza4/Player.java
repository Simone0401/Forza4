package freeorg.Forza4;
/**
 * Classe che implementa un giocatore.
 * @author Argento Simone
 *
 */
public class Player {

	String username;	// Nome inserito dal giocatore
	int won;			// Storico partite vinte
	int tied;			// Storico partite pareggiate
	int lost;			// Storico partite perse
	
	/**
	 * Costruttore per il giocatore.
	 * @param username Nome del giocatore.
	 * @param won Partite vinte.
	 * @param tied Partite pareggiate.
	 * @param lost Partite perse.
	 */
	public Player(String username, int won, int tied, int lost) {
		this.username = username;
		this.won = won;
		this.tied = tied;
		this.lost = lost; 
	}
	
	/**
	 * Costruttore per il giocatore che non è mai stato creato.
	 * @param username Nome del giocatore.
	 * 
	 */
	public Player(String username) {
		this.username = username;
		this.won = 0;
		this.tied = 0;
		this.lost = 0;
	}
	
	/**
	 * Metodo per ottenere l'username del giocatore.
	 * @return L'username del giocatore
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Metodo che aggiunge una vittoria.
	 */
	public void addWon() {
		this.won +=1;
	}
	
	/**
	 * Metodo che aggiunge una sconfitta.
	 */
	public void addLost() {
		this.lost +=1;
	}
	
	/**
	 * Metodo che aggiunge un pareggio.
	 */
	public void addTie() {
		this.tied +=1;
	}
	
	/**
	 * Metodo per cambiare l'username del giocatore.
	 * @param newUsername Nuovo username da usare.
	 */
	public void changeUsername(String newUsername) {
		this.setUsername(newUsername);
	}
	
	/**
	 * Metodo per settare l'username.
	 * @param username
	 */
	private void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Metodo per ottenere le vittorie totali del giocatore.
	 * @return numero di partite vinte.
	 */
	public int getWon() {
		return won;
	}

	/**
	 * Metodo per settare il numero di partite vinte.
	 * @param won Nuovo numero di partite vinte.
	 */
	public void setWon(int won) {
		this.won = won;
	}

	/**
	 * Metodo per ottenere i pareggi totali del giocatore.
	 * @return numero di partite pareggiate.
	 */
	public int getTied() {
		return tied;
	}

	/**
	 * Metodo per settare il numero di partite pareggiate.
	 * @param tied Nuovo numero di partite pareggiate.
	 */
	public void setTied(int tied) {
		this.tied = tied;
	}

	/**
	 * Metodo per ottenere le sconfitte totali del giocatore.
	 * @return numero di partite perse.
	 */
	public int getLost() {
		return lost;
	}

	/**
	 * Metodo per settare il numero di partite perse.
	 * @param lost Nuovo numero di partite perse.
	 */
	public void setLost(int lost) {
		this.lost = lost;
	}
	
	/**
	 * Metodo per ottenere il numero totale di partite giocate.
	 * @return numero di partite disputate.
	 */
	public int getMatchesNumber() {
		int matches;
		matches = this.getWon() + this.getTied() + this.getLost();
		return matches;
	}
	
}
