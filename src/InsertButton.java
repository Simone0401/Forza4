import javax.swing.*;



import java.awt.*;
import java.awt.event.*;


public class InsertButton implements ActionListener {
	private WinMessage w;
	private Match m;
	private Color transyellow=new Color(1f,1f,0f,.5f );
	private JButton btnColumn0 = new JButton("");
	private  Grid grid;
	private int column;
	private JLabel [][] holes;
	private JLayeredPane layeredPane;
	
	public InsertButton(Grid grid,Match m, int column,JLabel [][] holes,JLayeredPane layeredPane,WinMessage w) {
		this.grid = grid;
		this.column = column;
		this.holes = holes;
		this.m = m;
		this.layeredPane = layeredPane;
		this.w = w;
		
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
	
	
	
	
	private boolean insert(int p) {
		int row = grid.getrow(column);
		JLabel disc = holes[5-row][column];
		boolean result = grid.insert(column, p);
		boolean bwin;
		if (result) {
			insertDisc(p,disc);
			m.changep();
			System.out.println(row);
			bwin = grid.checkGrid(column, p);
			if(bwin) {
				w.show(p);
				this.m.ended();
			}
		}
		else {
			//error message
		}
		return result;
	}

	private static void spawnDiscs(String path,JLabel disc) {
		disc.setIcon(new ImageIcon(path));
		
	}
	
	private static void insertDisc(int p,JLabel disc) {
		String path;
		if(p == 1 ) {
			 path = "Images/yellow.png" ;
			
		}
		else {
			path = "Images/purple.png" ;
		}
		spawnDiscs(path,disc);
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(!this.m.isended()) {
			this.insert(this.m.getValue());
		}

}
}
