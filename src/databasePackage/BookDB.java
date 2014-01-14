package databasePackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Verbindungsaufbau zur Datenbank (book_database.books) 
 * Speichern des Datensatz in die Datenbank 
 * Anzeige aller Datensätze 
 * Suchfunktionen ausführen
 * 
 * @author Eva Weinberger
 * 
 */

public class BookDB {

	private Connection connect = null;
	private PreparedStatement myPreparedStatement = null;
	private ResultSet myResultSet = null;
	public int successful = 0; // Variable, die anzeigt, ob Datensatz-Speichern
								// erfolgreich war

	/**
	 * Die Datenbank-Verbindung wird hergestellt
	 */
	public void connectDB() {

		try {
			// Treiber wird geladen und die Regestrierung beim DriverManager
			// erfolgt
			Class.forName("com.mysql.jdbc.Driver");

			// DriverManager wird verwenden und die Verbindung zur DB wird
			// aufgebaut
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost/book_database", "book_user",
					"book_password");

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	/**
	 * Speichern des Datensatz in die Datenbank
	 * 
	 * @param isbn
	 * @param title
	 * @param author
	 * @param publicatonDate
	 * @param format
	 * @param shortDescription
	 * @param comment
	 * @param read
	 * @return
	 */
	public int saveBook(String isbn, String title, String author,
			String publicatonDate, String format, String shortDescription,
			String comment, String read) {

		try {
			// Datenbankverbindung herstellen
			this.connectDB();

			// PreparedStatement für SQL-Befehl
			myPreparedStatement = connect
					.prepareStatement("INSERT INTO book_database.books VALUES(default,?, ?, ?, ?, ?, ?, ?, ?)");

			// ISBN-Nummer setzen
			myPreparedStatement.setString(1, isbn);

			// Titel setzen
			myPreparedStatement.setString(2, title);

			// Author setzen
			myPreparedStatement.setString(3, author);

			// Ausgabe-Jahr setzen
			myPreparedStatement.setString(4, publicatonDate);

			// Buchformat setzen
			myPreparedStatement.setString(5, format);

			// Kurzbeschreibung setzen
			myPreparedStatement.setString(6, shortDescription);

			// Kommentar setzen
			myPreparedStatement.setString(7, comment);

			// Gelesen setzen
			myPreparedStatement.setString(8, read);

			// Ausfuehren des Speichern
			myPreparedStatement.executeUpdate();

			successful = 1;
			return successful;

		} catch (Exception e) {
			System.out.println(e.toString());
			successful = 0;
			return successful;

		} finally {
			close();
		}

	}

	/**
	 * Anzeigen aller Datensätze, die in der Datenbank vorhanden sind
	 */
	public void displayAll() {
		try {
			this.connectDB();
			myPreparedStatement = connect
					.prepareStatement("SELECT * FROM book_database.books;");
			myResultSet = myPreparedStatement.executeQuery();

			while (myResultSet.next()) {
				String tempIsbn = myResultSet.getString("isbn");
				String tempTitle = myResultSet.getString("title");
				System.out.println(tempIsbn + " " + tempTitle);

			}

			// Tabellenname und Spaltennamen anzeigen
			writeMetaData(myResultSet);

		} catch (Exception e) {
			System.out.println(e.toString());

		} finally {
			close();
		}
	}

	/**
	 * Tabellenname und Spaltenüberschriften anzeigen
	 * 
	 * @param resultSet
	 */
	private void writeMetaData(ResultSet resultSet) {

		try {
			// Tabellenname
			System.out.println("Tabellenname: "
					+ resultSet.getMetaData().getTableName(1));

			// Spaltenüberschriften
			System.out.println("Die Spalten in der Tabelle sind: ");

			for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
				System.out.println("Spalte " + i + ": "
						+ resultSet.getMetaData().getColumnName(i));
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	/**
	 * Alle offenen Verbindungen werden wieder geschlossen
	 */
	private void close() {
		try {

			if (myResultSet != null) {
				myResultSet.close();
			}

			if (myPreparedStatement != null) {
				myPreparedStatement.close();
			}

			if (connect != null) {
				connect.close();
			}

		} catch (Exception e) {
			System.out.println(e.toString());

		}
	}

}