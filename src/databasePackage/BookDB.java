package databasePackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse BookDB stellte eine Verbindung zur Datenbank her. Sie bietet die
 * Methoden zum Anzeigen, Suchen und Speichern von Datensätzen
 * 
 * @author Eva Weinberger
 * 
 * Letztes Änderungsdatum: 15.01.2014
 * 
 */

public class BookDB {

	private static Connection connect = null;
	private static PreparedStatement myPreparedStatement = null;
	private static ResultSet myResultSet = null;
	// Variable, die anzeigt, ob Datensatz-Speichern erfolgreich war
	public static int successful = 0;

	
	/**
	 * Diese Methode baut die Datenbankverbindung zur MYSQL-Datenbank auf
	 */
	public static void connectDB() {

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
	 * Anzeigen aller Datensätze, die in der Datenbank vorhanden sind
	 */
	public static List displayAll() {

		List results = new ArrayList();

		try {
			connectDB();
			myPreparedStatement = connect
					.prepareStatement("SELECT * FROM book_database.books;");
			myResultSet = myPreparedStatement.executeQuery();

			while (myResultSet.next()) {
				results.add(new Book(myResultSet.getString(1), myResultSet
						.getString(2), myResultSet.getString(3), myResultSet
						.getString(4), myResultSet.getString(5), myResultSet
						.getString(6), myResultSet.getString(7), myResultSet
						.getString(8), myResultSet.getString(9)));

			}

		} catch (Exception e) {
			System.out.println(e.toString());

		} finally {
			close();
		}

		return results;

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
	public static int saveBook(String isbn, String title, String author,
			String publicatonDate, String format, String shortDescription,
			String comment, String read) {

		try {
			// Datenbankverbindung herstellen
			connectDB();

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
	 * Alle offenen Verbindungen werden wieder geschlossen
	 */
	private static void close() {
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