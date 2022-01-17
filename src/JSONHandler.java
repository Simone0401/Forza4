import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.io.FileWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Classe per la gestione dei file JSON per il salvataggio e il recupero delle partite e dei giocatori
 * @version 1.10 14 Jan 2022
 * @author Argento Simone
 *
 */
public class JSONHandler extends Handler {
	
	// Percorso file dei salvataggi delle partite
	private final static String matchesFile = "data/Matches.json";
	
	// Percorso file salvataggio giocatori creati
	private final static String playersFile = "data/Players.json";
	
	/**
	 * Metodo per controllare se l'username è stato già usato
	 * @param playerUsername username del giocatore da controllare
	 * @return true se l'username è già stato usato, false altrimenti
	 */
	public boolean checkPlayer(String playerUsername) {
		Map<String, Player> giocatori = getPlayers();
		
		if (giocatori.get(playerUsername) != null) {
			return true;
		}
		
		return false;
	}
	
	
	/*
	 * TODO: la map deve essere String, Object. Necessario creare un metodo che parsa per restituire la map con i player
	 */
	
	/**
	 * Metodo per ottenere un dizionario di tutti gli username inseriti.
	 * @return il dizionario di tutti gli username. Come chiave si ha il nome dell'utente (username), come valore si ha l'oggetto Player ad esso associato
	 * @throws FileNotFoundException 
	 */
	public Map<String, Player> getPlayers() {
		Map<String, Player> giocatori = new HashMap<String, Player>();
		
		Object object = readPlayers();
		
		JSONObject jsonObject = (JSONObject) object;
		JSONArray players = null;
		
		// Si verifica che se il file esiste, altrimenti lo si crea
		try {
			players = (JSONArray) jsonObject.get("players");
		} catch (NullPointerException fileEx) {
			createPlayersFile();
			players = (JSONArray) jsonObject.get("players");
		}
		
		try {
			for (Object o: players) {
				JSONObject player = (JSONObject) o;
				Player p = this.buildPlayerFromJSON(player);
				giocatori.put((String) player.get("username"), p);	
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return giocatori;
	}
	
	/**
	 * Metodo per ottenere uno specifico giocatore dal file JSON
	 * @return il giocatore dal file JSON come JSONObject
	 */
	public Player getPlayer(String username){
		Map<String, Player> players = getPlayers();
		Player p = players.get(username);
		return p;
	}
	
	/**
	 * Metodo per creare un Player da un Player memorizzato come JSONObject
	 * @param playerJSON il giocatore JSONObject da parsare
	 * @return il giocatore parsato dal file JSON
	 */
	private Player buildPlayerFromJSON(JSONObject playerJSON) {
		Player p = new Player((String) playerJSON.get("username"),
				 (int) (long) playerJSON.get("won"),
				 (int) (long) playerJSON.get("tied"),
				 (int) (long) playerJSON.get("lost"));
		return p;
	}
	
	/**
	 * Metodo per salvare il giocatore sul file JSON
	 * @param player giocatore del quale salvare i dati
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void save(Player player) {
		JSONObject obj = new JSONObject();
		JSONArray players = new JSONArray();
		
		JSONObject inserimento = getPlayerForJSON(player);
		
		Map<String, Player> giocatori = this.getPlayers();
		
		// Se il giocatore è presente si aggiornano le sue statistiche
		if (!this.checkPlayer(player.getUsername())) {
			for (Map.Entry element : giocatori.entrySet()) {		// MAP.Entry : è un'interfaccia per accedere a tutti gli elementi di una Map
				players.add(getPlayerForJSON((Player)element.getValue()));
			}
			players.add(inserimento);
		}
		else {
			for (Map.Entry element : giocatori.entrySet()) {		// MAP.Entry : è un'interfaccia per accedere a tutti gli elementi di una Map
				if (element.getKey().toString().compareTo(player.getUsername()) == 0) {
					players.add(inserimento);
				}
				else {
					players.add(getPlayerForJSON((Player)element.getValue()));
				}
				
			}
			
		}
		
		obj.put("players", players);
		writePlayers(obj);
	}
	
	/**
	 * Metodo per modificare l'username di un giocatore precedentemente creato
	 * @param player giocatore del quale salvare i dati
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updatePlayer(String username, Player oldPlayer) {
		JSONObject obj = new JSONObject();
		JSONArray players = new JSONArray();
		
		
		Map<String, Player> giocatori = getPlayers();
		
		// Se il giocatore è presente si aggiorna il suo username
		if (checkPlayer(username)) {
			System.out.println("Esiste già un giocatore con l'username scelto");
		}
		else {
			for (Map.Entry element : giocatori.entrySet()) {		// MAP.Entry : è un'interfaccia per accedere a tutti gli elementi di una Map
				
				if (element.getKey().toString().compareTo(oldPlayer.getUsername()) == 0) {
					
					Player updatablePlayer = new Player(username, 
														oldPlayer.getWon(), 
														oldPlayer.getTied(), 
														oldPlayer.getLost());
					
					JSONObject inserimento = getPlayerForJSON(updatablePlayer);
					
					players.add(inserimento);
				}
				
				else {
					players.add(getPlayerForJSON((Player)element.getValue()));
				}
				
			}
			
		}
		
		obj.put("players", players);
		writePlayers(obj);
	}
	
	/**
	 * Metodo per cancellare il giocatore sul file JSON
	 * @param player giocatore da eliminare
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void remove(Player player) {
		JSONObject obj = new JSONObject();
		JSONArray players = new JSONArray();
		
		
		Map<String, Player> giocatori = getPlayers();
		
		// Se il giocatore è presente lo si elimina
		if (checkPlayer(player.getUsername())) {
			for (Map.Entry element : giocatori.entrySet()) {		// MAP.Entry : è un'interfaccia per accedere a tutti gli elementi di una Map
				System.out.println(element.getKey() + "-->" + player.getUsername());
				if (element.getKey().toString().compareTo(player.getUsername()) != 0) {
					players.add(getPlayerForJSON((Player)element.getValue()));
				}
			}
			System.out.println("Player eliminato correttamente!");
			obj.put("players", players);
			writePlayers(obj);
		}
		else {
			System.out.println("L'utente non esiste!");
		}
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
		} catch (FileNotFoundException fileEx) {
			createPlayersFile();
			try {
				object = parser.parse(new FileReader(playersFile));
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return object;
	}
	
	/**
	 * Metodo per creare il file JSON dei giocatori nel caso in cui esso non fosse già presente all'interno della cartella
	 */
	private static void createPlayersFile() {
		String incipit = "{ \"players\": [] }";
		try {
			FileWriter myWriter = new FileWriter(playersFile);
		    myWriter.write(incipit);
		    myWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo per creare un oggetto "Player" inseribile all'interno di un file JSON
	 * @param player giocatore da prepare per il file JSON
	 * @return il giocatore pronto per essere gestito come JSONObject
	 */
	@SuppressWarnings("unchecked")
	private static JSONObject getPlayerForJSON(Player player) {
		JSONObject giocatore = new JSONObject();
		giocatore.put("username", player.getUsername());
		giocatore.put("won", player.getWon());
		giocatore.put("tied", player.getTied());
		giocatore.put("lost", player.getLost());
		return giocatore;
	}
	
	/**
	 * Metodo per controllare se esiste già una partita salvata tra due giocatori
	 * @param matchName il nome della partita da controllare (è costituito dai due username dei giocatori)
	 * @return true se la partita è presente, false altrimenti
	 */
	public boolean checkMatch(String matchName) {
		Map<String, Match> matches = getMatches();
		
		if (matches.get(matchName) != null) {
			return true;
		}
		
		return false;
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
		} catch (FileNotFoundException fileEx) {
			createMatchesFile();
			try {
				object = parser.parse(new FileReader(matchesFile));
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
	
	/**
	 * Metodo per creare il file JSON delle partite nel caso in cui esso non fosse già presente all'interno della cartella
	 */
	private static void createMatchesFile() {
		String incipit = "{ \"matches\": [] }";
		try {
			FileWriter myWriter = new FileWriter(matchesFile);
		    myWriter.write(incipit);
		    myWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo per salvare la partita sul file JSON. Il metodo controlla anche se la partita è già stata salvata, in caso di esito positivo ne aggiorna le statistiche.
	 * @param match partita da salvare sul file JSON
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void save(Match match) {
		JSONObject obj = new JSONObject();
		JSONArray matches = new JSONArray();
		
		JSONObject inserimento = getMatchForJSON(match);
		
		Map<String, Match> partite = getMatches();
		
		// Se la partita è presente si aggiornano le sue statistiche e la si sovrascrive
		String matchName1 = match.getP1().getUsername() + match.getP2().getUsername();
		String matchName2 = match.getP2().getUsername() + match.getP1().getUsername();
		if (!(checkMatch(matchName1) || checkMatch(matchName2))) {
			for (Map.Entry element : partite.entrySet()) {		// MAP.Entry : è un'interfaccia per accedere a tutti gli elementi di una Map
				matches.add(getMatchForJSON((Match)element.getValue()));
			}
			matches.add(inserimento);
		}
		else {
			for (Map.Entry element : partite.entrySet()) {		// MAP.Entry : è un'interfaccia per accedere a tutti gli elementi di una Map
				if (element.getKey().toString().compareTo(matchName1) == 0 || element.getKey().toString().compareTo(matchName2) == 0) {
					matches.add(inserimento);
				}
				else {
					matches.add(getMatchForJSON((Match)element.getValue()));
				}
				
			}
			
		}
		
		obj.put("matches", matches);
		writeMatches(obj);
	}
	
	/**
	 * Metodo per scrivere i matches sul file JSON
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
	
	/**
	 * Metodo per creare un oggetto "Match" inseribile all'interno di un file JSON
	 * @param match partita da prepare per il file JSON
	 * @return la partita pronta per essere gestita come JSONObject
	 */
	@SuppressWarnings("unchecked")
	private static JSONObject getMatchForJSON(Match match) {
		JSONObject partita = new JSONObject();
		partita.put("match_name", match.getP1().getUsername() + match.getP2().getUsername());
		partita.put("player1", match.getP1().getUsername());
		partita.put("player2", match.getP2().getUsername());
		partita.put("griglia", match.getG().toString());
		partita.put("turn", match.getTurn());
		return partita;
	}
	
	/**
	 * Metodo per ottenere un dizionario di tutte le partite salvate.
	 * @return il dizionario di tutte le partite. Come chiave si ha il nome della partita (username1username2), come valore si ha l'oggetto JSON
	 */
	public Map<String, Match> getMatches(){
		Map<String, Match> partite = new HashMap<String, Match>();
		
		Object object = readMatches();
		JSONObject jsonObject = (JSONObject) object;
		JSONArray matches = null;
		
		// Si verifica prima se il file esiste, altrimenti viene creato
		try {
			matches = (JSONArray) jsonObject.get("matches");
		} catch (NullPointerException fileEx) {
			createMatchesFile();
			matches = (JSONArray) jsonObject.get("matches");
		}
		
		try {
			for (Object o: matches) {
				JSONObject match = (JSONObject) o;
				
				Match m = parseMatch(match); 
				
				partite.put((String) match.get("match_name"), m);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return partite;
	}
	
	private Map<String, Object> getMatchesOBJ(){
		Map<String, Object> partite = new HashMap<String, Object>();
		
		Object object = readMatches();
		JSONObject jsonObject = (JSONObject) object;
		JSONArray matches = null;
		
		// Si verifica prima se il file esiste, altrimenti viene creato
		try {
			matches = (JSONArray) jsonObject.get("matches");
		} catch (NullPointerException fileEx) {
			createMatchesFile();
			matches = (JSONArray) jsonObject.get("matches");
		}
		
		try {
			for (Object o: matches) {
				JSONObject match = (JSONObject) o;
				
				partite.put((String) match.get("match_name"), match);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return partite;
	}
	
	/**
	 * Metodo per ottenere una partita dal file JSON
	 * @return la partita dal file JSON come oggetto Match
	 */
	public Match getMatch(String matchName){
		Map<String, Match> matches = getMatches();
		return matches.get(matchName);
	}
	
	/**
	 * Metodo per ottenere i due giocatori relativi alla partita salvata
	 * @param matchName nome del match da recuperare
	 * @return lista dei due giocatori come array di 2 elementi
	 */
	public String[] getMatchPlayers(String matchName) {
		String[] players = new String[2];
		Map<String, Match> matches = getMatches();
		Match partita = matches.get(matchName);
		players[0] = partita.getP1().getUsername();
		players[1] = partita.getP2().getUsername();
		return players;
	}
	
	/**
	 * Metodo per ottenere un Match da un oggetto di tipo JSONObject
	 * @param match partita da parsare
	 * @return la partita come oggetto Match
	 */
	private Match parseMatch(JSONObject match) {
		String matrixRead = (String) match.get("griglia");
		Grid grid = new Grid(parseMatrix(matrixRead));
		
		Player player1 = this.getPlayer((String) match.get("player1"));
		
		Player player2 = this.getPlayer((String) match.get("player2"));
		
		Match loadedMatch = new Match(grid, player1, player2, (int) (long) match.get("turn"));
		return loadedMatch;
	}
	
	/**
	 * Metodo per ottenere un Match da un oggetto di tipo Object e da un giocatore passato
	 * @param match partita da parsare
	 * @param player giocatore già creato da parsare
	 * @param p numero del giocatore da parsare (1 per giocatore uno, 2 per giocatore due)
	 * @return
	 */
	private Match parseMatch(JSONObject match, Player player, int p) {
		String matrixRead = (String) match.get("griglia");
		Grid grid = new Grid(parseMatrix(matrixRead));
		
		Player player1, player2;
		
		if (p == 1) {
			player1 = player;
			player2 = this.getPlayer((String) match.get("player2"));
		}
		else {
			player1 = this.getPlayer((String) match.get("player1"));
			player2 = player;
		}
		
		Match loadedMatch = new Match(grid, player1, player2, (int) (long) match.get("turn"));
		return loadedMatch;
	}
	
	/**
	 * Metodo per ottenere una matrice di gioco di 6 righe e 7 colonne
	 * @param matrix salvata come stringa sul file JSON
	 * @return matrice di gioco come matrice di int
	 */
	private static int[][] parseMatrix(String matrix) {
		int start = 1;
		int[][] matrixGame = new int[6][7];
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 7; c++) {
				matrixGame[r][c] = Character.getNumericValue(matrix.charAt(start));
				start += 2;
			}
			start += 2;
		}
		return matrixGame;
	}
	
	/**
	 * Metodo per eliminare una partita in sospeso fra due giocatori
	 * @param p1 giocatore1
	 * @param p2 giocatore2
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void removeMatchFromPlayers(Player p1, Player p2) {
		JSONObject obj = new JSONObject();
		JSONArray matches = new JSONArray();	
		
		Map<String, Match> partite = getMatches();
		
		// Se la partita ha il giocatore è da eliminare
		for (Map.Entry element : partite.entrySet()) {		// MAP.Entry : è un'interfaccia per accedere a tutti gli elementi di una Map
			if (element.getKey().toString().compareTo(p1.getUsername()+p2.getUsername())!=0 &&
					element.getKey().toString().compareTo(p2.getUsername()+p1.getUsername())!=0) {
				matches.add(getMatchForJSON((Match) element.getValue()));
			}
			
		}
		
		obj.put("matches", matches);
		writeMatches(obj);
	}
	
	/**
	 * Metodo per eliminare tutti i match relativi ad un giocatore che si vuole eliminare
	 * @param player giocatore che si vuole eliminare
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void removeMatchFromPlayer(Player player) {
		JSONObject obj = new JSONObject();
		JSONArray matches = new JSONArray();	
		
		Map<String, Match> partite = getMatches();
		
		// Se la partita ha il giocatore è da eliminare
		for (Map.Entry element : partite.entrySet()) {		// MAP.Entry : è un'interfaccia per accedere a tutti gli elementi di una Map
			if (! element.getKey().toString().contains(player.getUsername())) {
				matches.add(getMatchForJSON((Match)element.getValue()));
			}
		}
		
		obj.put("matches", matches);
		writeMatches(obj);
	}
	
	/**
	 * Metodo per modificare i nomi e gli attributi delle partite salvate nel caso si modifica un utente
	 * @param oldPlayer rappresenta il vecchio giocatore ancora presente sul file
	 * @param newPlayer rappresenta il giocatore con l'username aggiornato
	 */
	public void updateMatch(Player oldPlayer, Player newPlayer) {
		updateMatchOBJ(oldPlayer, newPlayer);
	}
	
	/**
	 * Metodo per modificare il nome di una partita sul file JSON relativa ad un vecchio Player di cui è stato modificato il nome
	 * @param oldPlayer vecchio giocatore del quale rimuovere le partite a lui associate
	 * @param newPlayer nuovo giocatore da associare alle partite relative del vecchio giocatore
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void updateMatchOBJ(Player oldPlayer, Player newPlayer) {
		JSONArray matches = new JSONArray();
		
		
		Map<String, Object> partite = getMatchesOBJ();
		
		// Se la partita ha il giocatore è da modificare
		for (Map.Entry element : partite.entrySet()) {		// MAP.Entry : è un'interfaccia per accedere a tutti gli elementi di una Map
			if (! element.getKey().toString().contains(oldPlayer.getUsername())) {
				matches.add((JSONObject) element.getValue());
			}
			else {
				JSONObject obj = (JSONObject) element.getValue();
				if (obj.get("player1").toString().compareTo(oldPlayer.getUsername()) == 0) {
					
					Match rechargeMatch = parseMatch(obj, newPlayer, 1);
					this.removeMatchFromPlayers(oldPlayer, new Player((String) obj.get("player2")));
					this.save(rechargeMatch);
					
				}
				
				
				else {
					
					Match rechargeMatch = parseMatch(obj, newPlayer, 2);
					this.removeMatchFromPlayers(oldPlayer, new Player((String) obj.get("player1")));
					this.save(rechargeMatch);
					
				}
				
			}
		}
	}
	
}
