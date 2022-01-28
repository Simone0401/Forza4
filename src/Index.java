import java.awt.CardLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Taskbar;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.net.URL;
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
	
	/**
	 * Costruttore vuoto
	 */
	public Index() {
		
	}
	
	/**
	 * Inizializza il frame
	 * @param menu
	 */
	private void initialize(Menu menu) {
		MainPanel.setLayout(cl);
		
		MainPanel.add(menu,"menu");
		
		cl.show(MainPanel,"menu");
		
		final Toolkit defaulToolkit = Toolkit.getDefaultToolkit();
		final Image image = defaulToolkit.getImage("Images/icon.png");
		
		try {
			// necessaria da JDK 9
			final Taskbar taskbar = Taskbar.getTaskbar();
            // set icona per mac os (e altri sistemi operativi che supportano questo metodo)
            taskbar.setIconImage(image);
        } catch (final UnsupportedOperationException e) {
            System.out.println("Il sistema operativo non supporta: 'taskbar.setIconImage'");
        } catch (final SecurityException e) {
            System.out.println("C'è stata un'eccezione di sicurezza in: 'taskbar.setIconImage'");
        }
		
		// set icona per windows (e altri sistemi operativi che supportano questo metodo)   
		frame.setIconImage(image);  
		
		frame.add(MainPanel);
		frame.setBounds(100, 100, 1280, 720);
		frame.setLocationRelativeTo(null); 
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
	
	/**
	 * Metodo che rimuove tutti i window listener del frame
	 */
	public void removelisteners() {
		WindowListener[] wls  = this.frame.getWindowListeners();
		for (WindowListener listener : wls) {
			this.frame.removeWindowListener(listener);
		}
	}
	
	/**
	 * Metodo che dato un nuovo layeredpane e un id lo aggiunge al card layout
	 * @param p LayeredPane da aggiungere
	 * @param id id del LayeredPane
	 */
	public void addToCl(JLayeredPane p,String id) {
		if(!this.open.contains(id) || id.equals("g")) {
			this.MainPanel.add(p,id);
		}
		
	}
	
	/**
	 * Metodo che dato un id di un layeredPane mostra quella determinata schermata
	 * @param id id del PayeredPane
	 */
	
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
