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
 * @author simone
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
		JSONParser parser = new JSONParser();
		Object object = null;
		
		try {
			object = parser.parse(new FileReader(playersFile));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	public static void write(Player player) {
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
		
		try {
		      FileWriter myWriter = new FileWriter(playersFile);
		      myWriter.write(obj.toJSONString());
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } 
		catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
}
