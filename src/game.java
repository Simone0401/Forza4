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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionListener;

public class game {

	private JFrame frame;
	private Match match;
	private JLabel [][] holes = new JLabel [6] [7];
	private JLayeredPane layeredPane = new JLayeredPane();
	private boolean saved = false;
	JLabel t1 = new JLabel("");
	JLabel t2 = new JLabel("");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					game window = new game(null,null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void checkwin(InsertButton b,int p) throws FontFormatException, IOException {
		String wnr;
		boolean btie = match.getG().tieCheck();
		boolean bwin = match.getG().checkGrid(b.getColumn(), p);
		if(bwin) {
			if (p==1) {
				wnr = match.getP1().getUsername().toUpperCase();
				match.getP1().addWon();
				match.getP2().addLost();
				
			}
			else {
				wnr = match.getP2().getUsername().toUpperCase();
				match.getP2().addWon();
				match.getP1().addLost();
			}
			Winned w = new Winned(this,wnr);
			this.match.ended();
			JSONHandler.save(match.getP1());
			JSONHandler.save(match.getP2());
			JSONHandler.removeMatchFromPlayers(match.getP2(),match.getP1());
		}
		else {
			if(btie) {
				Tied t = new Tied(this);
				match.getP1().addTie();
				match.getP2().addTie();
				JSONHandler.save(match.getP1());
				JSONHandler.save(match.getP2());
				JSONHandler.removeMatchFromPlayers(match.getP2(),match.getP1());
			}
		}
	}
	
	/**
	 * Metodo che restituisce i giocatori della partita in corso
	 * @return lista dei giocatori
	 */
	public Player[] getplayers() {
		Player[] giocatori = { this.match.getP1() ,this.match.getP2()};
		return giocatori;
	}
	
	/**
	 * Metodo che imposta la variabile saved a false, che indica che ci sono modifiche da salvare nella griglia
	 */
	public void modified() {
		this.saved = false;
	}
	
	/**
	 * Metodo che imposta la variabile saved a true, che indica che non ci sono modifiche da salvare nella griglia
	 */
	public void saved() {
		this.saved = true;
	}
	

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public game(Player p1, Player p2) throws FontFormatException, IOException {
		this.match = new Match(p1,p2);
		this.initializeGrid();
		initialize();
	}
	
	public game(Match match) throws FontFormatException, IOException {
		this.match = match;
		this.initializeGrid();
		this.restoreGrid();
		this.saved();
		initialize();
	}
	
	/**
	 * Metodo che verifica se ci sono modifiche da salvare alla griglia nel file JSON
	 * @return true se la griglia è salvata, false altrimenti
	 */
	private boolean isSaved() {
		return this.saved;
	}
	
	
	/**
	 * Metodo che imposta la griglia grafica come quella del match recuperato dal file JSON
	 */
	private void restoreGrid() {
		for(int i = 0; i<6; i++) {
			for(int j = 0; j<7; j++) {
				
				InsertButton.insertDisc(this.match.getG().getMatrix()[i][j], this.holes[5-i][j] );
				
			}
		}
	}
	
	/**
	 * Metodo che crea la griglia grafica con tutti buchi vuoti.
	 */
	public void initializeGrid() {
		int x = 350;
		int y = 539;
		for(int i = 0; i<6; i++) {
			for(int j = 0; j<7; j++) {
				holes[i][j] = new JLabel("");
				layeredPane.setLayer(holes[i][j], 1);
				holes[i][j].setIcon(new ImageIcon("Images/hole.png"));
				holes[i][j].setBounds(x + j * 82, y - i*82 , 80, 80);
				layeredPane.add(holes[i][j]);
				
			}
		}
	}
	
	/**
	 * Metodo che fa mostra il giocatore che deve posizionare la pedina in base ai turni.
	 */
	public void swapPlaying() {
		if(this.match.getTurn()==1) {
			this.t2.setVisible(false);
			this.t1.setVisible(true);
		}
		else {
			this.t2.setVisible(true);
			this.t1.setVisible(false);
		}
	}
	
	/**
	 * Metodo che viene chiamato dall'esterno per far partire la finestra
	 * @throws FontFormatException
	 * @throws IOException
	 */
	public void restart() throws FontFormatException, IOException  {
		this.frame.dispose();
		this.initialize();
		this.frame.setVisible(true);
	}
	
	/**
	 * Metodo che resetta la griglia a fine partita
	 * @throws FontFormatException
	 * @throws IOException
	 */
	public void reset() throws FontFormatException, IOException  {
		this.frame.dispose();
		game g = new game(this.match.getP1(),this.match.getP2());
		g.restart();
	}


	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	private void initialize()   throws FontFormatException, IOException {
		
	
		
		boolean result = false;
		
		File font_file = new File("Font/Kid_Games.ttf");
		Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
		
		
		
		//---------------------------------------------------------------------------------------------------------------------
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("Images/icon.png"));
		frame.setResizable(false);
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
	            //I skipped unused callbacks for readability

	            @Override
	            public void windowClosing(WindowEvent e) {
	                if(JOptionPane.showConfirmDialog(frame, "Sicuri di voler abbandonare la partita?") == JOptionPane.OK_OPTION){
	                	if(!game.this.isSaved()) {
	                		if(JOptionPane.showConfirmDialog(frame, "Salvare la partita in corso?") == JOptionPane.OK_OPTION){
	                			JSONHandler.save(game.this.match);
	            				game.this.saved();
	            				JOptionPane.showMessageDialog(null, "Partita salvata", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
	                	}
	                	}
	                	
	                    frame.setVisible(false);
	                    Menu m = new Menu();
	                    try {
							m.restart();
						} catch (FontFormatException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                    frame.dispose();
	                }
	            }
	        });
		frame.getContentPane().setLayout(null);
		
		
		layeredPane.setBounds(0, 0, 1274, 694);
		frame.getContentPane().add(layeredPane);
		
		frame.setLocationRelativeTo(null);
		t1.setIcon(new ImageIcon("Images/ptriangle.png"));
		layeredPane.setLayer(t1, 4);
		t1.setHorizontalAlignment(SwingConstants.CENTER);
		t1.setBounds(125, 56, 60, 60);
		layeredPane.add(t1);
		
		
		t2.setIcon(new ImageIcon("Images/ytriangle.png"));
		layeredPane.setLayer(t2, 4);
		t2.setBounds(1100, 630, 60, 60);
		layeredPane.add(t2);
		t2.setVisible(false);
		t1.setVisible(false);
		this.swapPlaying();
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("Images/match_background.png"));
		lblNewLabel.setBounds(0, 0, 1274, 694);
		layeredPane.add(lblNewLabel);
		
		JLabel lblp1 = new JLabel(this.match.getP1().getUsername().toUpperCase());
		lblp1.setHorizontalAlignment(SwingConstants.CENTER);
		lblp1.setFont(font.deriveFont(20f));
		layeredPane.setLayer(lblp1, 1);
		lblp1.setBounds(41, 121, 224, 36);
		Color purple = new Color(108,0,255);
		lblp1.setForeground(purple);
		layeredPane.add(lblp1);
		
		JLabel lblp2 = new JLabel(this.match.getP2().getUsername().toUpperCase());
		lblp2.setHorizontalAlignment(SwingConstants.CENTER);
		lblp2.setFont(font.deriveFont(20f));
		layeredPane.setLayer(lblp2, 1);
		lblp2.setBounds(1013, 590, 224, 36);
		Color myellow = new Color(255,204,0);
		lblp2.setForeground(myellow);
		layeredPane.add(lblp2);
		
		
		JButton backbutton = new JButton("");
		layeredPane.setLayer(backbutton, 4);
		backbutton.setIcon(new ImageIcon("Images/back.png"));
		backbutton.setRolloverIcon(new ImageIcon("Images/back-over.png"));
		backbutton.setPressedIcon(new ImageIcon("Images/back-pressed.png"));
		backbutton.setBounds(10, 11, 50, 50);
		layeredPane.add(backbutton);
		backbutton.setBorderPainted(false); 
		backbutton.setContentAreaFilled(false); 
		backbutton.setFocusPainted(false); 
		backbutton.setOpaque(false);
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(frame, "Sicuri di voler abbandonare la partita?") == JOptionPane.OK_OPTION){
                	if(!game.this.isSaved()) {
                		if(JOptionPane.showConfirmDialog(frame, "Salvare la partita in corso?") == JOptionPane.OK_OPTION){
                			JSONHandler.save(game.this.match);
            				game.this.saved();
            				JOptionPane.showMessageDialog(null, "Partita salvata", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                	}
                	}
                	
                    frame.setVisible(false);
                    Menu m = new Menu();
                    try {
						m.restart();
					} catch (FontFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    frame.dispose();
                }
            }
        });
		JButton save = new JButton("");
		layeredPane.setLayer(save, 4);
		save.setIcon(new ImageIcon("Images/save.png"));
		save.setBounds(51, 11, 50, 50);
		layeredPane.add(save);
		save.setBorderPainted(false); 
		save.setContentAreaFilled(false); 
		save.setFocusPainted(false); 
		save.setOpaque(false);
		
		
		
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JSONHandler.save(game.this.match);
				game.this.saved();
				JOptionPane.showMessageDialog(null, "Partita salvata", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            }
        });
		
		
		
		
		
		
		
		
		//--------------------------------------------------------------------BOTTONI------------------------------------------------------
		InsertButton btnColumn[] = new InsertButton[7];
		for(int i = 0; i < 7; i++) {
			btnColumn[i] = new InsertButton(this.match,i,holes,layeredPane,this);
			btnColumn[i].init();
			
		}
		
		//--------------------------------------------------------------------BOTTONI------------------------------------------------------
		
		
	}
}
