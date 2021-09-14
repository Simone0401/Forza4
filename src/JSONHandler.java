import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Classe per la gestione dei file JSON per il salvataggio e il recupero delle partite e dei giocatori
 * @version 1.00 12 Sept 2021
 * @author Argento Simone
 *
 */
public class JSONHandler {
	// Percorso file dei salvataggi delle partite
	private final static String matchesFile = "data/Matches.json";
	
	// Percorso file dei giocatori già creati
	private final static String playersFile = "data/Players.json";
	
	/**
	 * Metodo per controllare se l'username è stato già usato
	 * @param playerUsername username del giocatore da controllare
	 * @return vera se l'username è già stato usato, false altrimenti
	 */
	public static boolean checkPlayer(String playerUsername) {
		Map<String, Object> giocatori = getPlayers();
		
		if (giocatori.get(playerUsername) != null) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Metodo per ottenere un dizionario di tutti gli username inseriti.
	 * @return il dizionario di tutti gli username. Come chiave si ha il nome dell'utente (username), come valore si ha l'oggetto JSON
	 */
	private static Map<String, Object> getPlayers(){
		Map<String, Object> giocatori = new HashMap<String, Object>();
		
		Object object = readPlayers();
		
		JSONObject jasonObject = (JSONObject) object;
		JSONArray players = (JSONArray) jasonObject.get("players");
		
		for (Object o: players) {
			JSONObject player = (JSONObject) o;
			giocatori.put((String) player.get("username"), player);	
		}
		return giocatori;
	}
	
	/**
	 * Metodo per salvare il giocatore sul file JSON
	 * @param player giocatore del quale salvare i dati
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void save(Player player) {
		JSONObject obj = new JSONObject();
		JSONArray players = new JSONArray();
		
		JSONObject inserimento = new JSONObject();
		inserimento.put("username", player.getUsername());
		inserimento.put("won", player.getWon());
		inserimento.put("tied", player.getTied());
		inserimento.put("lost", player.getLost());
		
		Map<String, Object> giocatori = getPlayers();
		
		// Se il giocatore è presente si aggiornano le sue statistiche
		if (!checkPlayer(player.getUsername())) {
			for (Map.Entry element : giocatori.entrySet()) {		// MAP.Entry : è un'interfaccia per accedere a tutti gli elementi di una Map
				players.add(element.getValue());
			}
			players.add(inserimento);
		}
		else {
			for (Map.Entry element : giocatori.entrySet()) {		// MAP.Entry : è un'interfaccia per accedere a tutti gli elementi di una Map
				if (element.getKey() == player.getUsername()) {
					players.add(inserimento);
				}
				else {
					players.add(element.getValue());
				}
				
			}
			
		}
		
		obj.put("players", players);
		writePlayers(obj);
		
	}
	
	/**
	 * Metodo per scrivere i giocatori sul file JSON
	 * @param object lista di giocatori da scrivere sotto forma di JSONObject
	 */
	private static void writePlayers(JSONObject object) {
		try {
		      FileWriter myWriter = new FileWriter(playersFile);
		      myWriter.write(object.toJSONString());
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		      
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}

	/**
	 * Metodo per ottenere l'intera lista dei giocatori
	 * @return lista dei giocatori come oggetto JAVA
	 */
	private static Object readPlayers() {
		JSONParser parser = new JSONParser();
		Object object = null;
		try {
			object = parser.parse(new FileReader(playersFile));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
	
	/**
	 * Metodo per controllare se esiste già una partita salvata tra due giocatori
	 * @param matchName il nome della partita da controllare (è costituito dai due username dei giocatori)
	 * @return true se la partita è presente, false altrimenti
	 */
	public static boolean checkMatch(String matchName) {
		Map<String, Object> matches = getPlayers();
		
		if (matches.get(matchName) != null) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Metodo per ottenere un dizionario di tutte le partite salvate.
	 * @return il dizionario di tutte le partite. Come chiave si ha il nome della partita (username1 + username2), come valore si ha l'oggetto JSON
	 */
	private static Map<String, Object> getMatches(){
		Map<String, Object> partite = new HashMap<String, Object>();
		
		Object object = readMatches();
		
		JSONObject jsonObject = (JSONObject) object;
		JSONArray matches = (JSONArray) jsonObject.get("matches");
		
		for (Object o: matches) {
			JSONObject match = (JSONObject) o;
			partite.put((String) partite.get("matchName"), match);	
		}
		return partite;
	}
	
	/**
	 * Metodo per ottenere l'intera lista delle partite
	 * @return lista delle partite come oggetto JAVA
	 */
	private static Object readMatches() {
		JSONParser parser = new JSONParser();
		Object object = null;
		try {
			object = parser.parse(new FileReader(matchesFile));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
	
	/**
	 * Metodo per salvare la partita sul file JSON
	 * @param player giocatore del quale salvare i dati
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void save(/* inserire oggetto match */) {
		
		/**
		 * Sostituire Player con oggetto Match
		 */
		
		JSONObject obj = new JSONObject();
		JSONArray players = new JSONArray();
		
		JSONObject inserimento = new JSONObject();
		inserimento.put("username", player.getUsername());
		inserimento.put("won", player.getWon());
		inserimento.put("tied", player.getTied());
		inserimento.put("lost", player.getLost());
		
		Map<String, Object> giocatori = getPlayers();
		
		// Se il giocatore è presente si aggiornano le sue statistiche
		if (!checkPlayer(player.getUsername())) {
			for (Map.Entry element : giocatori.entrySet()) {		// MAP.Entry : è un'interfaccia per accedere a tutti gli elementi di una Map
				players.add(element.getValue());
			}
			players.add(inserimento);
		}
		else {
			for (Map.Entry element : giocatori.entrySet()) {		// MAP.Entry : è un'interfaccia per accedere a tutti gli elementi di una Map
				if (element.getKey() == player.getUsername()) {
					players.add(inserimento);
				}
				else {
					players.add(element.getValue());
				}
				
			}
			
		}
		
		obj.put("matches", players);
		writeMatches(obj);
		
	}
	
	/**
	 * Metodo per scrivere le partite sul file JSON
	 * @param object lista delle partite da scrivere sotto forma di JSONObject
	 */
	private static void writeMatches(JSONObject object) {
		try {
		      FileWriter myWriter = new FileWriter(matchesFile);
		      myWriter.write(object.toJSONString());
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		      
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}

}
