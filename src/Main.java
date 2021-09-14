import java.io.InputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Main {
	
	public static void main(String[] args) {
		
			Grid grid = new Grid();
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			int scelta = 0;
			boolean result = false;
			int player = 1;
			boolean binsert;
			Player giocatore;
			grid.show();
			System.out.println("----------------------------------------------------");
			
			String username = "";
			System.out.println("Inserisci nome utente da eliminare: ");
			username = in.nextLine();
			
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
			
			JSONHandler.remove(giocatore);
			
			
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
		}
	}
