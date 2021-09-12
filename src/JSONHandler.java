import java.io.FileReader;
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
			String username = (String) player.get("username");
			if (username == playerUsername) {
				return true;
			}
			
		}
		return false;
	}
}
