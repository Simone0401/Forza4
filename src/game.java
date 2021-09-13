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
import javax.swing.JInternalFrame;
import java.awt.Toolkit;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;

public class game {

	private JFrame frame;
	private  Action Insertion ;

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
	 */
	public game() {
		initialize();
	}
	


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Player p1 = new Player("Ferrix",0,0,0);
		Player p2 = new Player("Simone041",0,0,0);
		Match m = new Match();
		Grid grid = new Grid();
		boolean result = false;
		System.out.println(System.getProperty("user.dir"));
		
		//---------------------------------------------------------------------------------------------------------------------
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("Images/icon.png"));
		frame.setResizable(false);
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1274, 694);
		frame.getContentPane().add(layeredPane);
		
		WinMessage w = new WinMessage(p1,p2,layeredPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("Images/match_background.png"));
		lblNewLabel.setBounds(0, 0, 1274, 694);
		layeredPane.add(lblNewLabel);
		
		JLabel lblp1 = new JLabel(p1.getUsername().toUpperCase());
		lblp1.setHorizontalAlignment(SwingConstants.CENTER);
		lblp1.setFont(new Font("Kid Games", Font.PLAIN, 20));
		layeredPane.setLayer(lblp1, 1);
		lblp1.setBounds(41, 121, 224, 36);
		Color purple = new Color(108,0,255);
		lblp1.setForeground(purple);
		layeredPane.add(lblp1);
		
		JLabel lblp2 = new JLabel(p2.getUsername().toUpperCase());
		lblp2.setHorizontalAlignment(SwingConstants.CENTER);
		lblp2.setFont(new Font("Kid Games", Font.PLAIN, 20));
		layeredPane.setLayer(lblp2, 1);
		lblp2.setBounds(1013, 590, 224, 36);
		Color myellow = new Color(255,204,0);
		lblp2.setForeground(myellow);
		layeredPane.add(lblp2);
		
		
		
		
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
			btnColumn[i] = new InsertButton(grid,m,i,holes,layeredPane,w);
			btnColumn[i].init();
			
		}
		
		//--------------------------------------------------------------------BOTTONI------------------------------------------------------
		
		
	}
}
