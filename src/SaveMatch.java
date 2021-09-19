import java.util.Scanner;

import org.json.simple.JSONObject;

/**
 * Classe di prova per salvare la partita sul file JSON
 * @version 1.00 17 Sept 2021
 * @author Argento Simone
 *
 */
/*
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
			String check = null;
			do {
				
				System.out.print("Inserisci username giocatore 1: ");
				String username = "";
				in.nextLine();		// per svuotare il buffer
				username = in.nextLine();
				
				if (JSONHandler.checkPlayer(username)) {
					System.out.println("Il giocatore già esiste, sicuro di volerlo utilizzare[y/n]: ");
					 check = in.nextLine();
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
			JSONHandler.save(giocatore1);
			JSONHandler.save(giocatore2);
			
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
					String matchName = giocatore1.getUsername() + giocatore2.getUsername();
					if (scelta == 10) {
						// TODO: salva partita
						if (JSONHandler.checkMatch(matchName)) {
							System.out.print("C'è già una partita salvata tra i due giocatori, vuoi sovrscriverla? [y/n]: ");
							check = in.nextLine();
							if (check.compareTo("y") == 0) {
								JSONHandler.save();
							}
						}
						// la partita non esiste
						else {
							JSONHandler.save(grid, matchName, giocatore1, giocatore2);
						}
						break;
					}
					
					// TODO: da modificare quando c'è l'unione con la classe dell'interfaccia Grafica
					binsert = grid.insert(scelta, player);
				}while(!binsert);
				
				grid.show();
				if (scelta != 10) {
					result = grid.checkGrid(scelta, player);
				}
				
				if (player == 1) {
					player = 2;
				}
				else {
					player = 1;
				}
				
			}while(!result);
			
			break;
		}
		
		case 2: {
			String check;
			String username = "";
			
			do {
					System.out.print("Inserisci username giocatore 1: ");
					in.nextLine(); // Per svuotare il buffer
					username = in.nextLine();
					
					if (!JSONHandler.checkPlayer(username)) {
						System.out.println("Il giocatore non esiste. Riprova!");
						}
					else {
						JSONObject playerJSON = JSONHandler.getPlayer(username);
						giocatore1 = new Player((String) playerJSON.get("username"),
								 				 (int) (long) playerJSON.get("won"),
								 				 (int) (long) playerJSON.get("tied"),
								 				 (int) (long) playerJSON.get("lost"));
					}
					
			} while (!JSONHandler.checkPlayer(username));
			
			System.out.println(giocatore1.getUsername() + " statistiche:");
			System.out.println("Vittorie: " + giocatore1.getWon());
			System.out.println("Pareggi: " + giocatore1.getTied());
			System.out.println("Sconfitte: " + giocatore1.getLost());
			
			
			do {
				System.out.print("Inserisci username giocatore 2: ");
				username = in.nextLine();
				
				if (!JSONHandler.checkPlayer(username)) {
					System.out.println("Il giocatore non esiste. Riprova!");
					}
				else {
					JSONObject playerJSON = JSONHandler.getPlayer(username);
					giocatore2 = new Player((String) playerJSON.get("username"),
							 				 (int) (long) playerJSON.get("won"),
							 				 (int) (long) playerJSON.get("tied"),
							 				 (int) (long) playerJSON.get("lost"));
				}
				
			} while (!JSONHandler.checkPlayer(username));
			
			System.out.println(giocatore2.getUsername() + " statistiche:");
			System.out.println("Vittorie: " + giocatore2.getWon());
			System.out.println("Pareggi: " + giocatore2.getTied());
			System.out.println("Sconfitte: " + giocatore2.getLost());
			
			System.out.println();
			
			// ----------------------------- INIZIO PARTITA -----------------------------------------------------
			String toCheck = giocatore1.getUsername() + giocatore2.getUsername();
			String toCheck2 = giocatore2.getUsername() + giocatore1.getUsername();
			Grid grid = null;
			if (JSONHandler.checkMatch(toCheck) || JSONHandler.checkMatch(toCheck2)) {
				int[][] temp = JSONHandler.getMatch(toCheck);
				grid = new Grid(temp);
			}
			
			int player = 1;
			boolean binsert;
			
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
					String matchName = giocatore1.getUsername() + giocatore2.getUsername();
					String matchName2 = giocatore2.getUsername() + giocatore1.getUsername();
					if (scelta == 10) {
						// Non si conosce a priori l'ordine dei giocatori e può non essere sempre lo stesso
						if (JSONHandler.checkMatch(matchName) || JSONHandler.checkMatch(matchName2)) {
							System.out.print("C'è già una partita salvata tra i due giocatori, vuoi sovrscriverla? [y/n]: ");
							in.nextLine();
							check = in.nextLine();
							if (check.compareTo("y") == 0) {
								JSONHandler.save(grid, matchName, giocatore1, giocatore2);
							}
						}
						// la partita non esiste
						else {
							JSONHandler.save(grid, matchName, giocatore1, giocatore2);
						}
						break;
					}
					
					// TODO: da modificare quando c'è l'unione con la classe dell'interfaccia Grafica
					binsert = grid.insert(scelta, player);
				}while(!binsert);
				
				grid.show();
				if (scelta != 10) {
					result = grid.checkGrid(scelta, player);
				}
				
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
		
	}
	
}
*/
// {"matches":[{"player1":"Simone","player2":"Marco","griglia":"[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,1,0,0,0,0,0]","match_name":"SimoneMarco"}]}