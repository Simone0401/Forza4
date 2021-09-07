
public class griglia {
	
	private int [] [] matrice= new int[6][7];
	 
	private int[] occupati = {5,5,5,5,5,5,5};
	
	
	
	public boolean checkwin(int c,int p) {
		
		boolean result=false;
		int k=0;
		int r=this.occupati[c]+1;
		System.out.println("colonna "+c +"   riga "+r);
		if(c<4) {
			k=0;
			for(int i=1;i<4;i++) { //controllo orizzontale a dx dal punto di immissione
			
			if(this.matrice[r][c+i]==p) {
				k++;
			}
			
		}
		if(k==3) {
				return true;
			}
		}
		if (c>2) {
			k=0;
			for(int i=1;i<4;i++) { //controllo orizzontale a sx dal punto di immissione
			
			if(this.matrice[r][c-i]==p) {
				k++;
			}
		}
		if(k==3) {
				return true;
			}
		}
		
		if(r<3) {
			k=0;
			for(int i=1;i<4;i++) { //controllo verticale verso il basso dal punto di immissione
			
			if(this.matrice[r+i][c]==p) {
				k++;
			}
	
		}
		if(k==3) {
				return true;
			}
		
		}
		if(c<3 && r<4) {
			k=0;
			int j=1;
			for(int i=1;i<4;i++) { //controllo obliquo verso dx dal punto di immissione
		
			
			if(this.matrice[r+i][c+j]==p) {
				k++;
			}
			j++;
		}
		if(k==3) {
				return true;	
			}
		
		}
		if (c>3 && r<3) {
			k=0;
			for(int i=1;i<4;i++) { //controllo obliquo verso sx dal punto di immissione
			
			int j=1;
			if(this.matrice[r+1][c-j]==p) {
				k++;
			}
			j++;
		}
		if(k==3) {
				return true;
			}
		
		}
		
		return result;
	}
	
	public void inserisci(int c,int p) {
		int r = this.occupati[c];
		this.matrice[r][c]=p;
		this.occupati[c]=this.occupati[c]-1;
	}
	
	public void show() {
		
		for (int j=0;j<6;j++) {
			System.out.print("[");
		for(int i=0;i<7;i++) {
			System.out.print(this.matrice[j][i] +" , ");
		}
		System.out.print("]");
		System.out.println();
		System.out.println();
		}
		
	}
	
	

}


