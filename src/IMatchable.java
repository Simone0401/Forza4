import java.util.Map;

/**
 * Interfaccia per le classi che si occupano dei matches "Forza 4"
 * Usando questa interfaccia si è sicuri che qualsiasi Handler sia in grado di usare il programma e
 * interfacciarsi con i salvataggi delle partite e la loro modifica.
 * @author Argento Simone
 * @version 1.00 11 Jan 2022
 */
public interface IMatchable {
	public boolean checkMatch(String matchName);
	public Match getMatch(String matchName);
	public Map<String, Object> getMatches();
	public void save(Match match);
	public void updateMatch(Player oldPlayer, Player newPlayer);
	public void removeMatchFromPlayers(Player p1, Player p2);
	public void removeMatchFromPlayer(Player player);
}
