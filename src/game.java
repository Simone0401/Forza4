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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					game window = new game();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public game() throws FontFormatException, IOException {
		initialize();
	}
	
	public void restart() throws FontFormatException, IOException {
		this.frame.dispose();
		this.main(null);
	}


	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	private void initialize()   throws FontFormatException, IOException {
		
		Player p1 = new Player("Ferrix",0,0,0);
		Player p2 = new Player("Simone041",0,0,0);
		Match m = new Match();
		Grid grid = new Grid();
		boolean result = false;
		System.out.println(System.getProperty("user.dir"));
		
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
	                	if(JOptionPane.showConfirmDialog(frame, "Salvare la partita in corso?") == JOptionPane.OK_OPTION){
	                		//salva partita
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
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1274, 694);
		frame.getContentPane().add(layeredPane);
		
		frame.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("Images/match_background.png"));
		lblNewLabel.setBounds(0, 0, 1274, 694);
		layeredPane.add(lblNewLabel);
		
		JLabel lblp1 = new JLabel(p1.getUsername().toUpperCase());
		lblp1.setHorizontalAlignment(SwingConstants.CENTER);
		lblp1.setFont(font.deriveFont(20f));
		layeredPane.setLayer(lblp1, 1);
		lblp1.setBounds(41, 121, 224, 36);
		Color purple = new Color(108,0,255);
		lblp1.setForeground(purple);
		layeredPane.add(lblp1);
		
		JLabel lblp2 = new JLabel(p2.getUsername().toUpperCase());
		lblp2.setHorizontalAlignment(SwingConstants.CENTER);
		lblp2.setFont(font.deriveFont(20f));
		layeredPane.setLayer(lblp2, 1);
		lblp2.setBounds(1013, 590, 224, 36);
		Color myellow = new Color(255,204,0);
		lblp2.setForeground(myellow);
		layeredPane.add(lblp2);
		
		
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		
		
		
		JLabel [][] holes = new JLabel [6] [7];
		
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
		
		
		
		//--------------------------------------------------------------------BOTTONI------------------------------------------------------
		InsertButton btnColumn[] = new InsertButton[7];
		for(int i = 0; i < 7; i++) {
			btnColumn[i] = new InsertButton(grid,m,i,holes,layeredPane,p1,p2,this);
			btnColumn[i].init();
			
		}
		
		//--------------------------------------------------------------------BOTTONI------------------------------------------------------
		
		
	}
}
