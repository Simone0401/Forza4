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
	abstract public boolean checkMatch(String matchName);
	abstract public Match getMatch(String matchName);
	abstract public Map<String, Match> getMatches();
	abstract public void save(Match match);
	abstract public void updateMatch(Player oldPlayer, Player newPlayer);
	abstract public void removeMatchFromPlayers(Player p1, Player p2);
	abstract public void removeMatchFromPlayer(Player player);
	// --------------------------------------------------------------------
	// METODI PER I GIOCATORI
	// --------------------------------------------------------------------
	abstract public boolean checkPlayer(String playerUsername);
	abstract public Player getPlayer(String username);
	abstract public Map<String, Player> getPlayers();
	abstract public void save(Player player);
	abstract public void updatePlayer(String username, Player oldPlayer);
	abstract public void remove(Player player);
	// --------------------------------------------------------------------
}
