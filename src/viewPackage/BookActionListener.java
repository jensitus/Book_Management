package viewPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import databasePackage.BookDB;

/**
 * ActionListener
 * 
 * @author Eva Weinberger
 * 
 */
public class BookActionListener implements ActionListener {

	BookGUI gui;

	/**
	 * Konstruktor
	 * 
	 * @param gui
	 */
	public BookActionListener(BookGUI gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		// Wenn auf den Button "alle anzeigen" geklickt wird, werden
		// alle Datensätze der Datenbank angezeigt, der Text im Suchfeld
		// zurückgesetzt und der Button "speichern" sichtbar gemacht
		if (event.getSource() instanceof JButton
				&& event.getActionCommand().equals("alle anzeigen")) {
			gui.createBookTable();
			gui.getSaveButton().setEnabled(true);
		}

		// Wenn auf den Button "suchen" geklickt wird, wird in der Datenbank
		// nach dem entsprechenden Buchtitel gesucht und der Button "speichern"
		// sichtbar gemacht
		if (event.getSource() instanceof JButton
				&& event.getActionCommand().equals("suchen")) {
			gui.createBookTable();
			gui.getSaveButton().setEnabled(true);

			// Wenn kein Datensatz gefunden wurde, wird eine entsprechende
			// Meldung ausgegeben
			int row = gui.getBookTable().getModel().getRowCount();
			if (row == 0) {
				// Dialog-Fenster wird erstellt
				JDialog emptyDialog = new JDialog();

				// Dialog-Text wird erstellt
				JTextField emptyField = new JTextField(
						"Es wurde kein Datensatz gefunden.");
				emptyField.setFont(new Font("Verdana", Font.BOLD, 12));
				emptyField.setBackground(Color.red);

				// Dialog-Text wird dem Dialog-Fenster zugewiesen und die Größe
				// wird festgelegt
				emptyDialog.getContentPane().add(emptyField);
				emptyDialog.setSize(400, 120);

				// Positionierung am Desktop
				emptyDialog.setLocation(550, 600);

				// Suchfeld wird zurückgesetzt
				gui.getSearchText().setText("");

			}
		}

		// Wenn auf den Button "neu" geklickt wird, dann wird der Inhalt der
		// Textfelder zurückgesetzt. Der neue Datensatz wird erst beim
		// Klick auf den Button "speichern" in die Datenbank eingefügt
		if (event.getSource() instanceof JButton
				&& event.getActionCommand().equals("neu")) {
			// Alle Textfelder werden zurückgesetzt
			gui.resetTableEast();

			// Speicher-Button sichtbar machen
			gui.getSaveButton().setEnabled(true);
		}

		// Wenn auf den Button "speichern" geklickt wird, wird der Inhalt der
		// Tabellenfelder eingelesen und der Datensatz in die Datenbank
		// gespeichert.
		if (event.getSource() instanceof JButton
				&& event.getActionCommand().equals("speichern")) {
			// Die neu eingegebenen Daten werden eingelesen
			String tempIsbn = String.valueOf(gui.getIsbnText().getText());
			String tempTitle = String.valueOf(gui.getTitleText().getText());
			String tempAuthor = String.valueOf(gui.getAuthorText().getText());
			String tempPublicationDate = String.valueOf(gui
					.getPublicationDateText().getText());
			String tempFormat = String.valueOf(gui.getFormatCombo()
					.getSelectedItem());
			String tempShortDescription = String.valueOf(gui
					.getShortDescriptionText().getText());
			String tempComment = String.valueOf(gui.getCommentText().getText());
			String tempRead = String.valueOf(gui.getReadCombo()
					.getSelectedItem());

			// Eine Verbindung zur Datenbank wird aufgebaut und der Datensatz
			// wird in die Datenbank gespeichert
			BookDB tempDB = new BookDB();
			tempDB.saveBook(tempIsbn, tempTitle, tempAuthor,
					tempPublicationDate, tempFormat, tempShortDescription,
					tempComment, tempRead);

			// Wenn der Datensatz erfolgreich gespeichert wurde, wird eine
			// entsprechende Meldung ausgegeben
			if (tempDB.successful == 1) {
				// Dialog-Fenster wird erstellt
				JDialog okDialog = new JDialog();

				// Dialog-Text wird erstellt
				JTextField okField = new JTextField(
						"Datensatz erfolgreich gespeichert!");
				okField.setFont(new Font("Verdana", Font.BOLD, 12));
				okField.setBackground(Color.green);

				// Dialog-Text wird dem Dialog-Fenster zugewiesen und die Größe
				// wird festgelegt
				okDialog.getContentPane().add(okField);
				okDialog.setSize(400, 120);

				// Positionierung am Desktop
				okDialog.setLocation(550, 600);

				// Sichtbar machen
				okDialog.setVisible(true);

				// Alle Textfelder werden zurückgesetzt, damit weitere
				// Datensätze eingegeben werden können
				gui.resetTableEast();

				// Wenn der Datensatz nicht gespeichert wurde, wird eine
				// entsprechende Meldung ausgegeben
			} else {
				// Dialog-Fenster wird erstellt
				JDialog notOkDialog = new JDialog();

				// Dialog-Text wird erstellt
				JTextField notOkField = new JTextField("Fehler beim Speichern!");
				notOkField.setFont(new Font("Verdana", Font.BOLD, 12));
				notOkField.setBackground(Color.red);

				// Dialog-Text wird dem Dialog-Fenster zugewiesen und die Größe
				// wird festgelegt
				notOkDialog.getContentPane().add(notOkField);
				notOkDialog.setSize(400, 120);

				// Positionierung am Desktop
				notOkDialog.setLocation(550, 600);

				// Sichtbar machen
				notOkDialog.setVisible(true);

				// Alle Textfelder werden zurückgesetzt, damit der Datensatz
				// erneut eingegeben werden kann
				gui.resetTableEast();

			}

		}

	}

}
