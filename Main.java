import java.util.Scanner;

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
