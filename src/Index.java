import java.awt.CardLayout;
import java.awt.Panel;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Index {
	
	JFrame frame = new JFrame("FORZA 4");
	JPanel MainPanel = new JPanel();
	CardLayout cl = new CardLayout();
	HashSet<String> open = new HashSet<String>();
	
	
	public Index() {
		
	}
	
	
	private void initialize(Menu menu) {
		MainPanel.setLayout(cl);
		
		MainPanel.add(menu,"menu");
		
		cl.show(MainPanel,"menu");
		
		frame.add(MainPanel);
		frame.setBounds(100, 100, 1280, 720);
		frame.setLocationRelativeTo(null); 
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
	public  void addToCl(JLayeredPane p,String id) {
		if(!this.open.contains(id) || id.equals("g")) {
			this.MainPanel.add(p,id);
		}
		
	}
	
	public void switchTo(String id) {
		this.cl.show(MainPanel, id);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Index i = new Index();
				Menu m = new Menu(i);
				i.initialize(m);
			}
		});
	}

}
