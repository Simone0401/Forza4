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
			grid.show();
			System.out.println("----------------------------------------------------");
			
			/*
			Prova di lettura file JSON
			*/
			
			// JSONHandler.checkPlayer(String player);
			
			//JSON parser object to parse read file
			System.out.println("Working Directory = " + System.getProperty("user.dir"));
		    JSONParser parser = new JSONParser();
		    
			Object object = null;
			try {
				object = parser.parse(new FileReader("src/Player.json"));
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONObject jasonObject = (JSONObject) object;
			JSONArray giocatori = (JSONArray) jasonObject.get("players");
			for (Object o: giocatori) {
				JSONObject giocatore = (JSONObject) o;
				String nome = (String) giocatore.get("username");
				System.out.println(nome);
				int vittorie = (int) (long) giocatore.get("won");
				System.out.println("Vittorie:" + vittorie);
			}

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
