package viewPackage;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import databasePackage.Book;

/**
 * Die Klasse BookTable ist für den Aufbau und den Inhalt der Büchertabelle
 * zuständig
 * 
 * Implementierung der abstrakten Klasse AbstractTableModel
 * 
 * @author Eva Weinberger
 * 
 * Letztes Änderungsdatum: 15.01.2014
 * 
 */
public class BookTable extends AbstractTableModel {

	private static final long serialVersionUID = 7055287075166243444L;
	
	public List bookList;
	

	/**
	 * Konstruktor
	 * 
	 * @param books
	 */

	public BookTable(List books) {
		bookList = books;
	}


	/**
	 * Rückgabe der Spaltenanzahl der Büchertabelle
	 */
	public int getColumnCount() {
		return 9;
	}


	/**
	 * Rückgabe der Anzahl der Datensätze, die sich in der Datenbank befinden
	 */
	public int getRowCount() {
		return bookList.size();
	}

	/**
	 * Festlegung der Spaltenbeschriftungen
	 */
	public String getColumnName(int arg0) {

		switch (arg0) {
		case 0:
			return "ID-Nr";
		case 1:
			return "ISBN";
		case 2:
			return "Buch-Titel";
		case 3:
			return "Autor";
		case 4:
			return "Ausgabe-Jahr";
		case 5:
			return "Buch-Format";
		case 6:
			return "Kurzbeschreibung";
		case 7:
			return "Kommentar";
		case 8:
			return "gelesen";

		default:
			return null;
		}
	}


	/**
	 * Rückgabe des Inhalts der jeweiligen Tabellenzeile
	 */
	public Object getValueAt(int row, int column) {
		Book b = (Book) bookList.get(row);
		switch (column) {
		case 0:
			return b.getId();
		case 1:
			return b.getIsbn();
		case 2:
			return b.getTitle();
		case 3:
			return b.getAuthor();
		case 4:
			return b.getPublicationDate();
		case 5:
			return b.getFormat();
		case 6:
			return b.getShortDescription();
		case 7:
			return b.getComment();
		case 8:
			return b.getRead();
			
		default:
			return null;

		}
	}

}
