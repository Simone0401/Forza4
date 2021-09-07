
public class main {
	
	public static void main(String[] args) {
		
			griglia grid = new griglia();
			
			grid.show();
			System.out.println("----------------------------------------------------");
			
			grid.inserisci(6, 1);
			grid.show();
			if(grid.checkwin(6, 1)) {
				System.out.println("hai vinto");
			}
			else {
				System.out.println("non hai ancora vinto");
			}
			grid.inserisci(5, 1);
			grid.show();
			if(grid.checkwin(5, 1)) {
				System.out.println("hai vinto");
			}
			else {
				System.out.println("non hai ancora vinto");
			}
			grid.inserisci(4, 1);
			grid.show();
			if(grid.checkwin(4, 1)) {
				System.out.println("hai vinto");
			}
			else {
				System.out.println("non hai ancora vinto");
			}
			grid.inserisci(3, 1);
			grid.show();
			if(grid.checkwin(3, 1)) {
				System.out.println("hai vinto");
			}
			else {
				System.out.println("non hai ancora vinto");
			}
			grid.show();
			
		}
}
