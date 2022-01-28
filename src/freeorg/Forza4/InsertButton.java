package freeorg.Forza4;
import javax.swing.*;



import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * Classe che implementa i bottoni delle colonne della griglia. 
 * @author Ferri Francesco
 *
 */
public class InsertButton implements ActionListener {
	private Match m;
	private Color transyellow=new Color(1f,1f,0f,.5f );
	private JButton btnColumn0 = new JButton("");
	private int column;
	private JLabel [][] holes;
	private JLayeredPane layeredPane;
	private Game g;
	
	/**
	 * Metodo Costruttore.
	 * @param m Partita in corso.
	 * @param column Numero corrispondente alla colonna.
	 * @param holes Griglia dei buchi per le pedine.
	 * @param layeredPane Layered Pane dove aggiungere il bottone.
	 * @param g Schermata di gioco.
	 */
	public InsertButton( Match m, int column, JLabel [][] holes, JLayeredPane layeredPane, Game g) {
		this.column = column;
		this.holes = holes;
		this.m = m;
		this.layeredPane = layeredPane;
		this.g = g;
	}
	
	
	/**
	 * Metodo che restituisce la colonna del bottone
	 * @return
	 */
	public int getColumn() {
		return this.column;
	}
	
	/**
	 * Metodo che inizializza il bottone sulla colonna della griglia
	 */
	public void init() {
		
		int x = 355;
		
		btnColumn0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnColumn0.setOpaque(true);
				btnColumn0.setBackground(transyellow);
			}
		
			@Override
			public void mouseExited(MouseEvent e) {
				btnColumn0.setOpaque(false);
				btnColumn0.setBackground(UIManager.getColor("control"));
			}
			
		});
		btnColumn0.addActionListener(this);
		btnColumn0.setOpaque(false);
		btnColumn0.setContentAreaFilled(false);
		btnColumn0.setBorderPainted(false);
		layeredPane.setLayer(btnColumn0, 1);
		btnColumn0.setBounds(x + column*82, 140, 80, 488);
		layeredPane.add(btnColumn0);
	}
	
	
	
	/**
	 * Metodo che inserisce la pedina.
	 * @param p numero che rappresenta quale giocatore ha inserito la pedina.
	 * @throws FontFormatException
	 * @throws IOException
	 */
	private void insert(int p) throws FontFormatException, IOException {
		int row = this.m.getG().getRow(column);
		boolean result = m.getG().insert(column, p);
		if (!result) {
			JOptionPane.showMessageDialog(null, "Non puoi inserire la pedina qui!", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		else {
			JLabel disc = holes[5-row][column];
			wichDisk(p,disc);
			m.changeT();
			g.swapPlaying();
			this.g.modified();
			System.out.println(row);
			this.g.checkwin(this , p);
			}
	}
	
	/**
	 * Metodo che sostituisce una pedina ad uno spazio vuoto
	 * @param path percorso immagine della pedina
	 * @param disc oggetto Jlabel della matrice di dischi
	 */
	private static void spawnDisk(String path,JLabel disc) {
		disc.setIcon(new ImageIcon(path));
		
	}
	
	/**
	 * Metodo che dato il turno chiama spawnDisk passando il path della pedina corrispondente
	 * @param p numero identificativo del turno
	 * @param disc oggetto Jlabel della matrice di dischi
	 */
	public static void wichDisk(int p,JLabel disc) {
		String path;
		if(p == 1 ) {
			 path = "Images/purple.png" ;
			 spawnDisk(path,disc);
			
		}
		else if(p==2) {
			path = "Images/yellow.png" ;
			spawnDisk(path,disc);
		}
		else {
		}
		
		
	}
	
	/**
	 * Metodo che viene chiamato al click sul bottone, posiziona la pedina in base al turno
	 */
	public void actionPerformed(ActionEvent e) {
		if(!this.m.isEnded()) {
			try {
				this.insert(this.m.getTurn());
			} catch (FontFormatException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

}
}
