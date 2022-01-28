import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JInternalFrame;
import java.awt.Toolkit;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionListener;

/**
 * Classe che implementa la schermata di gioco.
 * @author Ferri Francesco
 *
 */

public class Game extends JLayeredPane {
	
	int x = 0;
	private Match match;
	private JLabel[][] holes = new JLabel[6][7];
	private boolean saved = true;
	JLabel t1 = new JLabel("");
	JLabel t2 = new JLabel("");
	private Index i;
	private Handler handler = new JSONHandler();

	/**
	 * Metodo che controlla la vittoria o il pareggio.
	 *
	 * @param b Ultimo ColumnButton cliccato.
	 * @param p Il numero corrispondente al turno.
	 * @throws FontFormatException
	 * @throws IOException
	 */

	public void checkwin(InsertButton b, int p) throws FontFormatException, IOException {
		String wnr;
		boolean btie = match.getG().tieCheck();
		boolean bwin = match.getG().checkGrid(b.getColumn(), p);
		if (bwin) {
			if (p == 1) {
				wnr = match.getP1().getUsername().toUpperCase();
				match.getP1().addWon();
				match.getP2().addLost();

			} else {
				wnr = match.getP2().getUsername().toUpperCase();
				match.getP2().addWon();
				match.getP1().addLost();
			}
			Winned w = new Winned(this, wnr, this.i);
			this.match.end();
			this.handler.save(match.getP1());
			this.handler.save(match.getP2());
			this.handler.removeMatchFromPlayers(match.getP2(), match.getP1());
		} else {
			if (btie) {
				this.match.end();
				Tied t = new Tied(this, this.i);
				match.getP1().addTie();
				match.getP2().addTie();
				this.handler.save(match.getP1());
				this.handler.save(match.getP2());
				this.handler.removeMatchFromPlayers(match.getP2(), match.getP1());
			}
		}
	}

	/**
	 * Metodo che restituisce i giocatori della partita in corso.
	 * 
	 * @return Lista dei giocatori.
	 */
	public Player[] getplayers() {
		Player[] giocatori = { this.match.getP1(), this.match.getP2() };
		return giocatori;
	}

	/**
	 * Metodo che imposta la variabile saved a false, ad indicare che ci sono
	 * modifiche da salvare nella griglia.
	 */
	public void modified() {
		this.saved = false;
	}

	/**
	 * Metodo che imposta la variabile saved a true, ad indicare che non ci sono
	 * modifiche da salvare nella griglia
	 */
	public void save() {
		this.saved = true;
	}

	/**
	 * Metodo costruttore che crea una nuova partita.
	 * @param p1 Primo giocatore.
	 * @param p2 Secondo giocatore.
	 * @param i Oggetto che consente il cambio di schermate.
	 * @throws FontFormatException
	 * @throws IOException
	 */
	public Game(Player p1, Player p2, Index i) throws FontFormatException, IOException {
		this.i = i;
		this.match = new Match(p1, p2);
		this.initializeGrid();
		initialize();
	}
	
	/**
	 * Metodo costruttore che crea un oggetto game da una partita salvata.
	 * @param match Partita da caricare.
	 * @param i Oggetto che consente il cambio di schermate.
	 * @throws FontFormatException
	 * @throws IOException
	 */
	public Game(Match match, Index i) throws FontFormatException, IOException {
		this.i = i;
		this.match = match;
		this.initializeGrid();
		this.restoreGrid();
		this.save();
		initialize();
	}

	/**
	 * Metodo che verifica se ci sono modifiche da salvare.
	 * 
	 * @return true se la griglia è salvata, false altrimenti.
	 */
	private boolean isSaved() {
		return this.saved;
	}

	/**
	 * Metodo che carica la griglia dal match recuperato.
	 */
	private void restoreGrid() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {

				InsertButton.wichDisk(this.match.getG().getMatrix()[i][j], this.holes[5 - i][j]);

			}
		}
	}

	/**
	 * Metodo che crea la griglia con tutti buchi vuoti.
	 */
	public void initializeGrid() {
		int x = 355;
		int y = 549;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				holes[i][j] = new JLabel("");
				this.setLayer(holes[i][j], 1);
				holes[i][j].setIcon(new ImageIcon("Images/hole.png"));
				holes[i][j].setBounds(x + j * 82, y - i * 82, 80, 80);
				this.add(holes[i][j]);

			}
		}
	}

	/**
	 * Metodo che mostra il giocatore che deve posizionare la pedina in base ai turni.
	 */
	public void swapPlaying() {
		if (this.match.getTurn() == 1) {
			this.t2.setVisible(false);
			this.t1.setVisible(true);
		} else {
			this.t2.setVisible(true);
			this.t1.setVisible(false);
		}
	}

	/**
	 * Metodo che resetta la griglia a fine partita.
	 * 
	 * @throws FontFormatException
	 * @throws IOException
	 */
	public void reset() throws FontFormatException, IOException {
		Game g = new Game(this.match.getP1(), this.match.getP2(), this.i);
		this.i.addToCl(g, "g");
		this.i.switchTo("g");
	}

	/**
	 * Inizializza il frame.
	 * 
	 * @throws IOException
	 * @throws FontFormatException
	 */
	private void initialize() throws FontFormatException, IOException {

		boolean result = false;

		
 
		File font_file = new File("Font/Kid_Games.ttf"); 
		Font font = Font.createFont(Font.TRUETYPE_FONT, font_file); 
		this.i.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		this.i.removelisteners();
		
		this.i.frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.showConfirmDialog(Game.this.i.frame,
						"Sicuri di voler abbandonare la partita?") == JOptionPane.OK_OPTION) {
					
					if (!Game.this.match.isEnded()) {
						if (!Game.this.isSaved()) {
							if (JOptionPane.showConfirmDialog(Game.this.i.frame,
									"Salvare la partita in corso?") == JOptionPane.OK_OPTION) {
								handler.save(Game.this.match);
								Game.this.save();
								JOptionPane.showMessageDialog(null, "Partita salvata", "SUCCESS",
										JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
					
					Game.this.i.removelisteners();
					Menu m = new Menu(Game.this.i);
					
					Game.this.i.addToCl(m, "menu");
					Game.this.i.switchTo("menu");
					
				}
			}
		});
		

		this.setBounds(0, 0, 1280, 720);

		t1.setIcon(new ImageIcon("Images/ptriangle.png"));
		this.setLayer(t1, 4);
		t1.setHorizontalAlignment(SwingConstants.CENTER);
		t1.setBounds(125, 70, 60, 60);
		this.add(t1);

		t2.setIcon(new ImageIcon("Images/ytriangle.png"));
		this.setLayer(t2, 4);
		t2.setBounds(1100, 630, 60, 60);
		this.add(t2);
		t2.setVisible(false);
		t1.setVisible(false);
		this.swapPlaying();

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("Images/match_background.png"));
		lblNewLabel.setBounds(0, 0, 1280, 720);
		this.add(lblNewLabel);

		JLabel lblp1 = new JLabel(this.match.getP1().getUsername().toUpperCase());
		lblp1.setHorizontalAlignment(SwingConstants.CENTER);
		lblp1.setFont(font.deriveFont(20f));
		this.setLayer(lblp1, 1);
		lblp1.setBounds(41, 131, 224, 36);
		Color purple = new Color(108, 0, 255);
		lblp1.setForeground(purple);
		this.add(lblp1);

		JLabel lblp2 = new JLabel(this.match.getP2().getUsername().toUpperCase());
		lblp2.setHorizontalAlignment(SwingConstants.CENTER);
		lblp2.setFont(font.deriveFont(20f));
		this.setLayer(lblp2, 1);
		lblp2.setBounds(1013, 590, 224, 36);
		Color myellow = new Color(255, 204, 0);
		lblp2.setForeground(myellow);
		this.add(lblp2);

		JButton backbutton = new JButton("");
		this.setLayer(backbutton, 4);
		backbutton.setIcon(new ImageIcon("Images/back.png"));
		backbutton.setRolloverIcon(new ImageIcon("Images/back-over.png"));
		backbutton.setPressedIcon(new ImageIcon("Images/back-pressed.png"));
		backbutton.setBounds(10, 11, 50, 50);
		this.add(backbutton);
		backbutton.setBorderPainted(false);
		backbutton.setContentAreaFilled(false);
		backbutton.setFocusPainted(false);
		backbutton.setOpaque(false);		
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(Game.this.i.frame,
						"Sicuri di voler abbandonare la partita?") == JOptionPane.OK_OPTION) {
					if(!Game.this.match.isEnded()) {
						if (!Game.this.isSaved()) {
							if (JOptionPane.showConfirmDialog(Game.this.i.frame,
									"Salvare la partita in corso?") == JOptionPane.OK_OPTION) {
								handler.save(Game.this.match);
								Game.this.save();
								JOptionPane.showMessageDialog(null, "Partita salvata", "SUCCESS",
										JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
					Menu m = new Menu(Game.this.i);
					Game.this.i.addToCl(m, "menu");
					Game.this.i.switchTo("menu");
				}
			}
		});
		JButton save = new JButton("");
		this.setLayer(save, 4);
		save.setIcon(new ImageIcon("Images/save.png"));
		save.setRolloverIcon(new ImageIcon("Images/save-over.png"));
		save.setPressedIcon(new ImageIcon("Images/save-pressed.png"));
		save.setBounds(51, 11, 50, 50);
		this.add(save);
		save.setBorderPainted(false);
		save.setContentAreaFilled(false);
		save.setFocusPainted(false);
		save.setOpaque(false);

		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!Game.this.match.isEnded()) {
					handler.save(Game.this.match);
					Game.this.save();
					JOptionPane.showMessageDialog(null, "Partita salvata", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "Non puoi salvare una partita finita", "FAILED", JOptionPane.INFORMATION_MESSAGE);

				}
				
			}
		});

		// --------------------------------------------------------------------BOTTONI------------------------------------------------------
		InsertButton btnColumn[] = new InsertButton[7];
		for (int i = 0; i < 7; i++) {
			btnColumn[i] = new InsertButton(this.match, i, holes, this, this);
			btnColumn[i].init();

		}
		// --------------------------------------------------------------------BOTTONI------------------------------------------------------
	}
}
