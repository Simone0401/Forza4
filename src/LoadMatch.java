import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import org.json.simple.JSONObject;

public class LoadMatch {

	private JFrame frame;
	private HashMap <String,Match> matches = new HashMap <>(); 
	private JList list;
	private Object[] partite;
	private ArrayList<Object> vs = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadMatch window = new LoadMatch();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	/**
	 * Create the application.
	 */
	public LoadMatch() {
		Player p;
		Map<String, Object> matchs = JSONHandler.getMatches();
		for( String mn : matchs.keySet()) {
			Match match = JSONHandler.getMatch(mn);
			
			this.matches.put(mn, match);
			
		}
		
		initialize();
	}
	
	public void restart() throws FontFormatException, IOException  {
		this.frame.dispose();
		this.initialize();
		this.frame.setVisible(true);
	}
	
	public void show() {
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("Images/icon.png"));
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); 
		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1264, 699);
		frame.getContentPane().add(layeredPane);
		
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Images/menu.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		layeredPane.setLayer(lblNewLabel, 1);
		lblNewLabel.setBounds(0, 0, 1264, 684);
		layeredPane.add(lblNewLabel);
		
		
		this.partite = (matches.keySet().toArray());
		String stringa;
		for (Object x : this.partite) {
			stringa = matches.get(x).getP1().getUsername() + "  VS  " + matches.get(x).getP2().getUsername();
			this.vs.add(stringa);
		}
		
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(1);
		layeredPane.setLayer(splitPane, 2);
		splitPane.setBounds(397, 255, 470, 307);
		layeredPane.add(splitPane);
		splitPane.setEnabled( false );
		
		
		this.list = new JList(this.vs.toArray());
		this.list.setBounds(400, 544, 470, -361);
		this.list.setFont(new Font("Kid Games", Font.PLAIN, 21));
		this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.list.setVisibleRowCount(vs.size());
		DefaultListCellRenderer renderer =  (DefaultListCellRenderer)this.list.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);
		splitPane.setLeftComponent(new JScrollPane(this.list));
		splitPane.setRightComponent(null);
		
		JButton gioca = new JButton("");
		gioca.setIcon(new ImageIcon("Images/gioca.png"));
		layeredPane.setLayer(gioca, 2);
		gioca.setBounds(482, 589, 304, 69);
		gioca.setBorderPainted(false); 
		gioca.setContentAreaFilled(false); 
		gioca.setFocusPainted(false); 
		gioca.setOpaque(false);
		gioca.setRolloverIcon(new ImageIcon("Images/gioca-over.png"));
		gioca.setPressedIcon(new ImageIcon("Images/gioca-pressed.png"));
		
		
		
		gioca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index = LoadMatch.this.list.getSelectedIndex();
				String selezione = LoadMatch.this.vs.toArray()[index].toString().replace("  VS  ", "");
				game g;
				try {
					g = new game(JSONHandler.getMatch(selezione));
					g.restart();
					LoadMatch.this.frame.dispose();
				} catch (FontFormatException | IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		
		layeredPane.add(gioca);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("Images/matchchoice.png"));
		layeredPane.setLayer(lblNewLabel_1, 3);
		lblNewLabel_1.setBounds(491, 184, 295, 60);
		layeredPane.add(lblNewLabel_1);
		
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
				
				Menu m = new Menu();
				try {
					m.restart();
					LoadMatch.this.frame.dispose();
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
			
		
	}
}