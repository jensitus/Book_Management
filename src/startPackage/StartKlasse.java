package startPackage;

import javax.swing.JFrame;

import viewPackage.BookGUI;

/**
 * Start-Klasse mit Main-Methode
 * 
 * @author Eva Weinberger
 * 
 */

public class StartKlasse {

	/**
	 * Main-Methode zum Starten des Programms
	 */
	public static void main(String[] args) {
		//Aufruf des Konstruktors der Klasse BookGUI und Zuweisung der Überschrift
		BookGUI gui = new BookGUI("Bücherverwaltung");
		
		//Festlegung der Fenstergröße
		gui.setSize(950, 500);
		
		//Positionierung am Desktop
		gui.setLocation(200, 300);
				
		//Window-Close-Funktion
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Sichtbar machen
		gui.setVisible(true);

	}

}
