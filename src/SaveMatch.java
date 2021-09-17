import java.util.Scanner;

import org.json.simple.JSONObject;

/**
 * Classe di prova per salvare la partita sul file JSON
 * @version 1.00 17 Sept 2021
 * @author Argento Simone
 *
 */
public class SaveMatch {
	
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		
		int scelta = 0;
		boolean result = false;
		
		Player giocatore1 = null;
		Player giocatore2 = null;
		
		System.out.println("1. Nuova partita");
		System.out.println("2. Carica partita");
		System.out.println("3. Modifica utenti"); // CREA, MODIFICA, CANCELLA
		System.out.print("Cosa vuoi fare?: ");
		
		scelta = in.nextInt();
		
		switch (scelta) {
		case 1: {
			String check;
			do {
				
				System.out.print("Inserisci username giocatore 1: ");
				String username = "";
				in.nextLine(); // Per svuotare il buffer
				username = in.nextLine();
				
				if (JSONHandler.checkPlayer(username)) {
					System.out.println("Il giocatore già esiste, sicuro di volerlo utilizzare[y/n]: ");
					 check = in.next();
					 if (check.compareTo("y") == 0) {
						 JSONObject playerJSON = JSONHandler.getPlayer(username);
						 giocatore1 = new Player((String) playerJSON.get("username"),
								 				 (int) (long) playerJSON.get("won"),
								 				 (int) (long) playerJSON.get("tied"),
								 				 (int) (long) playerJSON.get("lost"));
					}
				}
				else {
					giocatore1 = new Player(username);
					check = "y";
				}
				
			} while (check.compareTo("n") == 0);
			
			System.out.println(giocatore1.getUsername() + " statistiche:");
			System.out.println("Vittorie: " + giocatore1.getWon());
			System.out.println("Pareggi: " + giocatore1.getTied());
			System.out.println("Sconfitte: " + giocatore1.getLost());
			
			in.nextLine();
			
			
			do {
				
				System.out.print("Inserisci username giocatore 2: ");
				String username = "";
				username = in.nextLine();
				//in.nextLine();
				check = "n";
				
				if (username.compareTo(giocatore1.getUsername()) == 0) {
					System.out.println("Non puoi scegliere lo stesso username!");
				}
				
				else if (JSONHandler.checkPlayer(username)) {
					System.out.println("Il giocatore già esiste, sicuro di volerlo utilizzare[y/n]: ");
					check = in.next();
					if (check.compareTo("y") == 0) {
						JSONObject playerJSON = JSONHandler.getPlayer(username);
						giocatore2 = new Player((String) playerJSON.get("username"),
				 				 	(int) (long) playerJSON.get("won"),
				 				 	(int) (long) playerJSON.get("tied"),
				 				 	(int) (long) playerJSON.get("lost"));
					}
				}
				else {
					giocatore2 = new Player(username);
					check = "y";
				}
				
			} while (check.compareTo("n") == 0);
			
			System.out.println(giocatore2.getUsername() + " statistiche:");
			System.out.println("Vittorie: " + giocatore2.getWon());
			System.out.println("Pareggi: " + giocatore2.getTied());
			System.out.println("Sconfitte: " + giocatore2.getLost());
			
			System.out.println();
			
			// ----------------------------- INIZIO PARTITA -----------------------------------------------------
			int player = 1;
			boolean binsert;
			
			Grid grid = new Grid();
			grid.show();
			
			System.out.println("----------------------------------------------------");
			
			do {
				
				do {
					if (player == 1) {
						System.out.print("In quale colonna vuoi inserire la pedina " + giocatore1.getUsername() + " [10 per salvare la partita]: ");
					}
					else {
						System.out.print("In quale colonna vuoi inserire la pedina " + giocatore2.getUsername() + " [10 per salvare la partita]: ");
					}
					
					// Lettura della colonna
					if (in.hasNextInt()) {
						scelta = in.nextInt();
					}
					
					if (scelta == 10) {
						// TODO: salva partita
						String matchName = giocatore1.getUsername() + giocatore2.getUsername();
						if (JSONHandler.checkMatch(matchName)) {
							System.out.print("C'è già una partita salvata tra i due giocatori, vuoi sovrscriverla? [y/n]: ");
							check = in.nextLine();
							if (check.compareTo("y") == 0) {
								
							}
						}
					}
					
					// TODO: da modificare quando c'è l'unione con la classe dell'interfaccia Grafica
					binsert = grid.insert(scelta, player);
				}while(!binsert);
				
				grid.show();
				result = grid.checkGrid(scelta, player);
				if (player == 1) {
					player = 2;
				}
				else {
					player = 1;
				}
				
			}while(!result);
			
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + scelta);
		}
		
		/*
		String username = "";
		System.out.println("Inserisci nome utente da modificare: ");
		username = in.nextLine();
		
		System.out.println("Inserisci nome utente nuovo: ");
		String newUsername = in.nextLine();
		
		// Prova costruzione player dati i dati sulle sue statistiche
		System.out.println("Vuoi inserire le statistiche? [y/n] ");
		String risp = "";
		
		risp = in.next().strip();
		
		
		if (risp.equals("y")) {
			int vittorie;
			int pareggi;
			int sconfitte;
			
			System.out.println("Inserisci numero vittorie: ");
			vittorie = in.nextInt();
			System.out.println("Inserisci numero pareggi: ");
			pareggi = in.nextInt();
			System.out.println("Inserisci numero sconfitte: ");
			sconfitte = in.nextInt();
			
			
			giocatore = new Player(username, vittorie, pareggi, sconfitte);
		}
		
		else {
			giocatore = new Player(username);
		}
		
		JSONHandler.save(giocatore);
		
		
		System.out.println(JSONHandler.checkPlayer(username));
		

		do {
			
			do {
				System.out.print("In quale colonna vuoi inserire la pedina player " + player + ": ");
				// Lettura della colonna
				if (in.hasNextInt()) {
					scelta = in.nextInt();
				}
				binsert = grid.insert(scelta, player);
			}while(!binsert);
			
			grid.show();
			result = grid.checkGrid(scelta, player);
			if (player == 1) {
				player = 2;
			}
			else {
				player = 1;
			}
			
		}while(!result);
		
		System.out.println("Hai vinto!");
	*/	
	}
	
}
