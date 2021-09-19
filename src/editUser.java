import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import org.json.simple.JSONObject;

public class editUser {

	private JFrame frame;
	private HashMap <String,Player> players = new HashMap <>(); 
	private JList list;
	private Object[] usernames;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editUser window = new editUser();
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
	public editUser() {
		Player p;
		Map<String, Object> users = JSONHandler.getPlayers();
		for( String username : users.keySet()) {
			JSONObject playerJSON = JSONHandler.getPlayer(username);
			p = new Player((String) playerJSON.get("username"),
					 				 (int) (long) playerJSON.get("won"),
					 				 (int) (long) playerJSON.get("tied"),
					 				 (int) (long) playerJSON.get("lost"));
			this.players.put(p.getUsername(), p);
			
		}
		
		initialize();
	}
	
	public void restart() throws FontFormatException, IOException {
		this.frame.dispose();
		this.main(null);
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
		
		
		this.usernames = (players.keySet().toArray());
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(1);
		layeredPane.setLayer(splitPane, 2);
		splitPane.setBounds(397, 255, 470, 307);
		layeredPane.add(splitPane);
		splitPane.setEnabled( false );
		
		this.list = new JList(usernames);
		this.list.setBounds(400, 544, 470, -361);
		this.list.setFont(new Font("Kid Games", Font.PLAIN, 21));
		this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.list.setVisibleRowCount(usernames.length);
		DefaultListCellRenderer renderer =  (DefaultListCellRenderer)this.list.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);
		splitPane.setLeftComponent(new JScrollPane(this.list));
		splitPane.setRightComponent(null);
		
		JLabel lblNewLabel_4_2 = new JLabel("");
		lblNewLabel_4_2.setIcon(new ImageIcon("Images/whoedit.png"));
		layeredPane.setLayer(lblNewLabel_4_2, 4);
		lblNewLabel_4_2.setBounds(479, 184, 295, 60);
		layeredPane.add(lblNewLabel_4_2);;

		
		
		JButton modifica = new JButton("");
		modifica.setIcon(new ImageIcon("Images/modifica.png"));
		modifica.setRolloverIcon(new ImageIcon("Images/modifica-over.png"));
		modifica.setPressedIcon(new ImageIcon("Images/modifica-pressed.png"));
		layeredPane.setLayer(modifica, 2);
		modifica.setBounds(482, 589, 304, 69);
		modifica.setBorderPainted(false); 
		modifica.setContentAreaFilled(false); 
		modifica.setFocusPainted(false); 
		modifica.setOpaque(false);
		frame.getRootPane().setDefaultButton(modifica);
		
		modifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					
					UserEdited up = new UserEdited(players.get(usernames[editUser.this.list.getSelectedIndex()]));
					try {
						up.restart();
						editUser.this.frame.dispose();
					} catch (FontFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}	
			
		});
		layeredPane.add(modifica);
		
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
				
				UsersModifier m = new UsersModifier();
				try {
					m.restart();
					editUser.this.frame.dispose();
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}
	}
