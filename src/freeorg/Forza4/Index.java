package freeorg.Forza4;
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


/**
 * Classe che viene utilizzata per passare da una schermata all'altra grazie ad un CardLayout
 * @version 1.20 13 Jan 2022
 * @author Ferri Francesco
 *
 */
public class Index {
	
	JFrame frame ; //frame che conterra tutte le schermate
	JPanel MainPanel ; //Jpanel
	CardLayout cl ; //cardlayout per il cambio schermata
	HashSet<String> open ; //set contenente gli id delle schermate
	
	/**
	 * Metodo costruttore
	 */
	public Index() {
		this.frame = new JFrame("FORZA 4"); 
		this.MainPanel = new JPanel();
		this.cl = new CardLayout();
		this.open = new HashSet<String>();
	}
	
	/**
	 * Inizializza il frame
	 * @param menu prima schermata che verra visualizzata.
	 */
	private void initialize(Menu menu) {
		MainPanel.setLayout(cl);
		
		MainPanel.add(menu,"menu");
		
		cl.show(MainPanel,"menu");
		
		final Toolkit defaulToolkit = Toolkit.getDefaultToolkit();
		final Image image = defaulToolkit.getImage("Images/icon.png");
				
		try {
			
			// necessaria da JDK 9
            // set icona per mac os (e altri sistemi operativi che supportano questo metodo)
			final Taskbar taskbar = Taskbar.getTaskbar();
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
	 * Metodo che rimuove tutti i window listener dal frame.
	 */
	public void removelisteners() {
		WindowListener[] wls  = this.frame.getWindowListeners();
		for (WindowListener listener : wls) {
			this.frame.removeWindowListener(listener);
		}
	}
	
	/**
	 * Metodo che dato un nuovo layeredpane e un id lo aggiunge al card layout.
	 * @param p LayeredPane da aggiungere
	 * @param id Id del LayeredPane
	 */
	public void addToCl(JLayeredPane p,String id) {
		if(!this.open.contains(id) || id.equals("g")) {
			this.MainPanel.add(p,id);
		}
		
	}
	
	/**
	 * Metodo che dato un id di un layeredPane mostra quella determinata schermata.
	 * @param id Id del LayeredPane da mostrare
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
