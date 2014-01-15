package startPackage;

import javax.swing.JFrame;

import viewPackage.BookGUI;

/**
 * Klasse mit der Main-Methode zum Starten des Programms
 * 
 * @author Eva Weinberger
 * 
 * Letztes Änderungsdatum: 15.01.2014
 * 
 */

public class StartKlasse {

	/**
	 * Main-Methode zum Starten des Programms
	 */
	public static void main(String[] args) {
		// Aufruf des Konstruktors der Klasse BookGUI und Zuweisung der
		// Überschrift
		BookGUI gui = new BookGUI("Bücherverwaltung");

		// Fenstergröße wird automatisch angepasst an den Inhalt
		gui.pack();

		// Positionierung am Desktop
		gui.setLocation(200, 300);

		// Window-Close-Funktion
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Sichtbar machen
		gui.setVisible(true);

	}

}
