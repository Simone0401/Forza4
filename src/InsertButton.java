import javax.swing.*;



import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class InsertButton implements ActionListener {
	private Match m;
	private Color transyellow=new Color(1f,1f,0f,.5f );
	private JButton btnColumn0 = new JButton("");
	private int column;
	private JLabel [][] holes;
	private JLayeredPane layeredPane;
	private game g;
	
	public InsertButton( Match m, int column, JLabel [][] holes, JLayeredPane layeredPane, game g) {
		this.column = column;
		this.holes = holes;
		this.m = m;
		this.layeredPane = layeredPane;
		this.g = g;
	}
	
	public void init() {
		
		int x = 350;
		
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
		btnColumn0.setBounds(x + column*82, 129, 80, 488);
		layeredPane.add(btnColumn0);
	}
	
	
	
	
	private boolean insert(int p) throws FontFormatException, IOException {
		String wnr;
		int row = this.m.getG().getRow(column);
		boolean result = m.getG().insert(column, p);
		boolean bwin;
		boolean btie;
		if (!result) {
			JOptionPane.showMessageDialog(null, "Non puoi inserire la pedina qui!", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		else {
			JLabel disc = holes[5-row][column];
			insertDisc(p,disc);
			m.changep();
			System.out.println(row);
			bwin = m.getG().checkGrid(column, p);
			btie =  m.getG().tieCheck();
			if(bwin) {
				if (p==1) {
					wnr = m.getP1().getUsername().toUpperCase();
					m.getP1().addWon();
					m.getP2().addLost();
				}
				else {
					wnr = m.getP2().getUsername().toUpperCase();
					m.getP2().addWon();
					m.getP1().addLost();
				}
				Winned w = new Winned(g,wnr);
				this.m.ended();
			}
			else {
				if(btie) {
					Tied t = new Tied(g);
					m.getP1().addTie();
					m.getP2().addTie();
				}
			}
			
			}
		JSONHandler.save(m.getP1());
		JSONHandler.save(m.getP2());
		return result;
	}

	private static void spawnDiscs(String path,JLabel disc) {
		disc.setIcon(new ImageIcon(path));
		
	}
	
	private static void insertDisc(int p,JLabel disc) {
		String path;
		if(p == 1 ) {
			 path = "Images/purple.png" ;
			
		}
		else {
			path = "Images/yellow.png" ;
		}
		spawnDiscs(path,disc);
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(!this.m.isended()) {
			try {
				this.insert(this.m.getValue());
			} catch (FontFormatException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

}
}
