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
	
	private int changePlayer(int p) {
		if (p == 1) {
			p = 2;
		}
		else {
			p = 1;
		}
		return p;
	}
	
	private static boolean graphicInsert(Grid grid, int column,int p, JLabel [][] holes) {
		int row = grid.getrow(column);
		JLabel disc = holes[row][column];
		boolean result = grid.insert(column, p);
		
		if (result) {
			insertDisc(column,row,p,disc);
		}
		else {
			//error message
		}
		return result;
	}
	
	private static void spawnDiscs(String path,JLabel disc) {
		disc.setIcon(new ImageIcon(path));
		
	}
	
	private static void insertDisc(int column,int row,int p,JLabel disc) {
		String path;
		if(p == 0 ) {
			 path = "src/yellow.png" ;
			
		}
		else {
			path = "src/purple.png" ;
		}
		spawnDiscs(path,disc);
		
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Grid grid = new Grid();
		boolean result = false;
		int p = 1;
		
		
		//---------------------------------------------------------------------------------------------------------------------
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Desktop\\forza4\\icon.png"));
		frame.setResizable(false);
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1274, 694);
		frame.getContentPane().add(layeredPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Administrator\\eclipse-workspace\\forza 4\\src\\match_background.png"));
		lblNewLabel.setBounds(0, 0, 1274, 694);
		layeredPane.add(lblNewLabel);
		
		JLabel lblp1 = new JLabel("FRANCESCO");
		lblp1.setHorizontalAlignment(SwingConstants.CENTER);
		lblp1.setFont(new Font("Kid Games", Font.PLAIN, 28));
		layeredPane.setLayer(lblp1, 1);
		lblp1.setBounds(41, 121, 224, 36);
		Color purple = new Color(108,0,255);
		lblp1.setForeground(purple);
		layeredPane.add(lblp1);
		
		JLabel lblp2 = new JLabel("SIMONE");
		lblp2.setHorizontalAlignment(SwingConstants.CENTER);
		lblp2.setFont(new Font("Kid Games", Font.PLAIN, 28));
		layeredPane.setLayer(lblp2, 1);
		lblp2.setBounds(1013, 590, 224, 36);
		Color myellow = new Color(255,204,0);
		lblp2.setForeground(myellow);
		layeredPane.add(lblp2);
		
		Color transyellow=new Color(1f,1f,0f,.5f );
		
		JLabel [][] holes = new JLabel [6] [7];
		
		int x = 350;
		int y = 539;
		for(int i = 0; i<6; i++) {
			for(int j = 0; j<7; j++) {
				holes[i][j] = new JLabel("");
				layeredPane.setLayer(holes[i][j], 1);
				holes[i][j].setIcon(new ImageIcon("C:\\\\Users\\\\Administrator\\\\eclipse-workspace\\\\forza 4\\\\src\\\\hole.png"));
				holes[i][j].setBounds(x + j * 82, y - i*82 , 80, 80);
				layeredPane.add(holes[i][j]);
				
			}
		}
		
		
		
		//--------------------------------------------------------------------BOTTONI------------------------------------------------------
		JButton btnColumn0 = new JButton("");
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
		btnColumn0.addActionListener(new Insertion(grid,p,0,holes));
		btnColumn0.setAction(Insertion);
		btnColumn0.setOpaque(false);
		btnColumn0.setContentAreaFilled(false);
		btnColumn0.setBorderPainted(false);
		layeredPane.setLayer(btnColumn0, 1);
		btnColumn0.setBounds(350, 129, 80, 488);
		layeredPane.add(btnColumn0);
		
		JButton btnColumn1 = new JButton("");
		btnColumn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnColumn1.setOpaque(true);
				btnColumn1.setBackground(transyellow);
			}
		
			@Override
			public void mouseExited(MouseEvent e) {
				btnColumn1.setOpaque(false);
				btnColumn1.setBackground(UIManager.getColor("control"));
			}
		});
		layeredPane.setLayer(btnColumn1, 1);
		btnColumn1.setOpaque(false);
		btnColumn1.setContentAreaFilled(false);
		btnColumn1.setBorderPainted(false);
		btnColumn1.setBounds(432, 128, 80, 488);
		layeredPane.add(btnColumn1);
		
		JButton btnColumn2 = new JButton("");
		layeredPane.setLayer(btnColumn2, 1);
		btnColumn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnColumn2.setOpaque(true);
				btnColumn2.setBackground(transyellow);
			}
		
			@Override
			public void mouseExited(MouseEvent e) {
				btnColumn2.setOpaque(false);
				btnColumn2.setBackground(UIManager.getColor("control"));
			}
		});
		btnColumn2.setOpaque(false);
		btnColumn2.setContentAreaFilled(false);
		btnColumn2.setBorderPainted(false);
		btnColumn2.setBounds(514, 129, 80, 488);
		layeredPane.add(btnColumn2);
		
		JButton btnColumn3 = new JButton("");
		layeredPane.setLayer(btnColumn3, 1);
		btnColumn3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnColumn3.setOpaque(true);
				btnColumn3.setBackground(transyellow);
			}
		
			@Override
			public void mouseExited(MouseEvent e) {
				btnColumn3.setOpaque(false);
				btnColumn3.setBackground(UIManager.getColor("control"));
			}
		});
		btnColumn3.setOpaque(false);
		btnColumn3.setContentAreaFilled(false);
		btnColumn3.setBorderPainted(false);
		btnColumn3.setBounds(596, 129, 80, 488);
		layeredPane.add(btnColumn3);
		
		JButton btnColumn4 = new JButton("");
		layeredPane.setLayer(btnColumn4, 1);
		btnColumn4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnColumn4.setOpaque(true);
				btnColumn4.setBackground(transyellow);
			}
		
			@Override
			public void mouseExited(MouseEvent e) {
				btnColumn4.setOpaque(false);
				btnColumn4.setBackground(UIManager.getColor("control"));
			}
		});
		btnColumn4.setOpaque(false);
		btnColumn4.setContentAreaFilled(false);
		btnColumn4.setBorderPainted(false);
		btnColumn4.setBounds(678, 129, 80, 488);
		layeredPane.add(btnColumn4);
		
		JButton btnColumn5 = new JButton("");
		layeredPane.setLayer(btnColumn5, 1);
		btnColumn5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnColumn5.setOpaque(true);
				btnColumn5.setBackground(transyellow);
			}
		
			@Override
			public void mouseExited(MouseEvent e) {
				btnColumn5.setOpaque(false);
				btnColumn5.setBackground(UIManager.getColor("control"));
			}
		});
		btnColumn5.setOpaque(false);
		btnColumn5.setContentAreaFilled(false);
		btnColumn5.setBorderPainted(false);
		btnColumn5.setBounds(760, 129, 80, 488);
		layeredPane.add(btnColumn5);
		
		JButton btnColumn6 = new JButton("");
		layeredPane.setLayer(btnColumn6, 1);
		btnColumn6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnColumn6.setOpaque(true);
				
				btnColumn6.setBackground(transyellow);
			}
		
			@Override
			public void mouseExited(MouseEvent e) {
				btnColumn6.setOpaque(false);
				btnColumn6.setBackground(UIManager.getColor("control"));
			}
		});
		btnColumn6.setOpaque(false);
		btnColumn6.setContentAreaFilled(false);
		btnColumn6.setBorderPainted(false);
		btnColumn6.setBounds(842, 129, 80, 488);
		layeredPane.add(btnColumn6);
		
		
		
		

		
		
		//--------------------------------------------------------------------BOTTONI------------------------------------------------------
		
		
	}

	private class Insertion extends AbstractAction {
		
		private boolean result;
		
		public Insertion(Grid grid,int p, int column,JLabel [][] holes) {
			putValue(NAME, "Insertion");
			putValue(SHORT_DESCRIPTION, "inserimento di una pedina in una colonna");
			graphicInsert( grid,  column, p,holes);
			
			
		}
		public void actionPerformed(ActionEvent e) {
			if(!this.result) {
				//messaggio errore
			}
		}
	}
}
