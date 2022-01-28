package freeorg.Forza4;
import java.util.Map;

/**
 * Classe astratta creata in modo tale da poter usare un Handler generico nelle varie
 * schermate di gioco.
 * @author Argento Simone
 * @version 1.00 14 Jan 2022
 *
 */
public abstract class Handler {
	
	// --------------------------------------------------------------------
	// METODI PER LE PARTITE
	// --------------------------------------------------------------------
	
	/**
	 * Controlla se è presente un match all'interno del documento digitale utilizzato
	 * @param matchName nome del match come composizione dei nomi dei due giocatori (player1player2)
	 * @return true se esiste un match salvato, false altrimenti
	 */
	abstract public boolean checkMatch(String matchName);
	
	/**
	 * Consente di ottenere una partita salvata sotto forma di oggetto Match
	 * @param matchName nome del match come composizione dei nomi dei due giocatori (player1player2)
	 * @return la partita come oggetto Match
	 */
	abstract public Match getMatch(String matchName);
	
	/**
	 * Consente di ottenere un dizionario di partite. 
	 * @return dizionario delle partite. La chiave è il nome del match
	 * come composizione dei nomi dei due giocatori, il valore è la partita come oggetto Match
	 */
	abstract public Map<String, Match> getMatches();
	
	/**
	 * Consente di salvare una partita sul documento digitale
	 * @param match partita da salvare
	 */
	abstract public void save(Match match);
	
	/**
	 * Consente di aggiornare le statistiche di una partita già presente sul documento digitale
	 * nel caso è stato modificato un giocatore
	 * @param oldPlayer vecchio giocatore della partita non più esistente
	 * @param newPlayer nuovo giocatore da inserire al posto del vecchio
	 */
	abstract public void updateMatch(Player oldPlayer, Player newPlayer);
	
	/**
	 * Consente di eliminare una partita rimasta in sospeso tra due giocatori
	 * @param p1 giocatore 1 della partita
	 * @param p2 giocatore 2 della partita
	 */
	abstract public void removeMatchFromPlayers(Player p1, Player p2);
	
	/**
	 * Consente di eliminare tutte le partite relative ad un giocatore che si vuole eliminare
	 * @param player giocatore non più esistente
	 */
	abstract public void removeMatchesFromPlayer(Player player);
	
	// --------------------------------------------------------------------
	// METODI PER I GIOCATORI
	// --------------------------------------------------------------------
	
	/**
	 * Controlla se è presente un giocatore all'interno del documento digitale utilizzato
	 * @param playerUsername nome del giocatore da controllare
	 * @return true se il giocatore esiste ed è salvato, false altrimenti
	 */
	abstract public boolean checkPlayer(String playerUsername);
	
	/**
	 * Consente di ottenere un giocatore salvato sotto forma di oggetto Player
	 * @param username nome del giocatore da recuperare
	 * @return il giocatore come oggetto Player
	 */
	abstract public Player getPlayer(String username);
	
	/**
	 * Consente di ottenere un dizionario di giocatori
	 * @return dizionario dei giocatori. La chiave è l'username del giocatore, il 
	 * valore è il giocatore come oggetto Player
	 */
	abstract public Map<String, Player> getPlayers();
	
	/**
	 * Consente di salvare un giocatore sul documento digitale
	 * @param player il giocatore da memorizzare
	 */
	abstract public void save(Player player);
	
	/**
	 * Consente di modificare l'username di un giocatore precedente salvato
	 * @param username nuovo username del giocatore
	 * @param oldPlayer vecchio username del giocatore
	 */
	abstract public void updatePlayer(String username, Player oldPlayer);
	
	/**
	 * Consente di cancellare dal documento digitale un giocatore salvato
	 * @param player il giocatore da cancellare
	 */
	abstract public void remove(Player player);
	
	// --------------------------------------------------------------------
}
