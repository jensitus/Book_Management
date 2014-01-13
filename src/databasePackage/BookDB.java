package databasePackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * - Verbindungsaufbau zur Datenbank (poll.person)
 * - Speichern des Datensatz in die Datenbank
 * - Ermittlung des Durchschnitts der Punkteanzahlen
 * 
 * @author Eva Weinberger
 * 
 */


/**
 * As mysql root user, create a book_database and a book_user with a 
 * password book_password:
 * 
 * mysql> CREATE DATABASE book_database;
 * mysql> GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, INDEX, ALTER, LOCK TABLES, CREATE TEMPORARY TABLES ON `book_database`.* TO 'book_user'@'localhost' IDENTIFIED BY 'book_password';
 * 
 * Then login to mysql as book_user and create the table:
 * 
 * mysql> --user  book_user --database book_database --password book_password;
 * 
 * Be careful! You have to change to your new database first:
 * 
 * mysql> use book_database;
 * 
 * Setup the table:
 * 
 * mysql> CREATE TABLE books (id INT NOT NULL AUTO_INCREMENT, isbn VARCHAR(20), title VARCHAR(30), author VARCHAR(50), publicationDate VARCHAR(20), formatb VARCHAR(20), shortDescription VARCHAR(100), commentb VARCHAR(100), readb VARCHAR(10), PRIMARY KEY(id));
 * 
 * Insert a test book in the table
 * 
 * mysql>INSERT INTO books VALUES (default, '978-3-8362-1802-3', 'Java ist auch eine Insel' , 'Ullenboom, Christian', 2012, 'Gebunden', 'Java-Lehrbuch', 'gut zu lesen', 'ja');
 *
 */

public class BookDB {
	
	private Connection connect = null;	
	private PreparedStatement myPreparedStatement = null;	
	private ResultSet myResultSet = null;
	public int result = 0;	//Variable, die anzeigt, ob Datensatz speichern erfolgreich war

	//Verbindungsaufbau zur Datenbank
	public void connectDB() {
		
		try {
			//Treiber laden + beim DriverManager registrieren
			Class.forName("com.mysql.jdbc.Driver");

			//DriverManager verwenden + Verbindung zur DB aufbauen
			connect = DriverManager.getConnection("jdbc:mysql://localhost/book_database", "book_user", "book_password");			

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	

	//Speichert Datensatz in die Datenbank
	public int saveBook(String isbn, String title, String author, String publicatonDate, String format, 
			String shortDescription, String comment, String read) {
		
		try {
			//Datenbankverbindung
			this.connectDB();
			
			//PreparedStatement für SQL-Befehl
			myPreparedStatement = connect.prepareStatement("INSERT INTO book_database.books VALUES(default,?, ?, ?, ?, ?, ?, ?, ?)");
			
			//ISBN-Nummer setzen
			myPreparedStatement.setString(1, isbn);

			//Titel setzen
			myPreparedStatement.setString(2, title);
			
			//Author setzen
			myPreparedStatement.setString(3, author);
			
			//Ausgabe-Jahr setzen
			myPreparedStatement.setString(4, publicatonDate);
			
			//Buchformat setzen
			myPreparedStatement.setString(5, format);
			
			//Kurzbeschreibung setzen
			myPreparedStatement.setString(6, shortDescription);
			
			//Kommentar setzen
			myPreparedStatement.setString(7, comment);
			
			//Gelesen setzen
			myPreparedStatement.setString(8, read);

			//Ausfuehren des Speichern
			myPreparedStatement.executeUpdate();
			
			result = 1;
			return result;
			

		} catch (Exception e) {
			System.out.println(e.toString());
			result = 0;
			return result;
			
		} finally {
			close();
		}	

	}
	
	//Anzeigen aller Datensätze
    public void displayAll() {
        try {
                this.connectDB();
                myPreparedStatement = connect.prepareStatement("SELECT * FROM book_database.books;");
                myResultSet = myPreparedStatement.executeQuery();

                while (myResultSet.next()) {
                        String tempIsbn = myResultSet.getString("isbn");
                        String tempTitle = myResultSet.getString("title");
                        System.out.println(tempIsbn + " " + tempTitle);
                }
        } catch (Exception e) {
                System.out.println(e.toString());
                
        } finally {
			close();
		}	
}
	
//	// Ermittlung des Durchschnitts der Punkteanzahlen
//	public double getAverage() {
//
//		double numRows = 0.0;    //Anzahl der Datensaetze
//		double allScores = 0.0;  //Summe der Punkteanzahl
//		double average = 0.0;    //Durchschnitt
//		
//		try {
//			//Datenbankverbindung
//			this.connectDB();
//					
//			//Anzahl der Datensaetze ermitteln
//			myPreparedStatement = connect.prepareStatement("SELECT COUNT(*) FROM person");
//			myResultSet = myPreparedStatement.executeQuery();
//			
//			while (myResultSet.next()) {
//				numRows = myResultSet.getInt("count(*)");
//			}
//						
//			//Gesamtsumme der Punkteanzahl ermitteln
//			myPreparedStatement = connect.prepareStatement("SELECT qsum FROM poll.person");
//			myResultSet = myPreparedStatement.executeQuery();
//			
//			while (myResultSet.next()) {
//				int tempSum = myResultSet.getInt("qsum");	
//				allScores += tempSum;
//			}
//			
//			//Durchschnitt errechnen
//			average = allScores/numRows;
//			
//			//Durchschnitt runden auf 2 Nachkommastellen
//			average = Math.round(average*100.)/100.;
//			
//			return average;
//			
//		} catch (Exception e) {
//			return 0.0;
//			
//		} finally {
//			close();
//		}						
//	}
	
	
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