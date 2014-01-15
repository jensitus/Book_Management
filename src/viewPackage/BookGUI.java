package viewPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Die Klasse BookGUI ist für den Aufbau der grafischen Oberfläche
 * zuständig.
 * Hier werden die einzelnen Komponenten des Fensters (Textfelder,
 * ComboBoxen, Bezeichungsfelder, Schaltflächen und die Büchertabelle)
 * festgelegt.
 * 
 * @author Eva Weinberger
 * 
 */

/**
 * Festlegung der einzelnen Fenster-Komponenten (Art, Zahl, Inhalt,
 * Schriftart,...)
 */
public class BookGUI extends JFrame {

	// Festlegung der Schriftart für die Bezeichnungsfelder und Schaltflächen
	static String labelFont = "Verdana";
	static int labelStyle = Font.BOLD;
	static int labelSize = 12;

	// Festlegung der Schriftart für die Textfelder und der Tabelle
	static String textFont = "Arial";
	static int textStyle = Font.PLAIN;
	static int textSize = 12;

	// Deklaration der erforderlichen Komponenten
	private JPanel northPanel;
	private JPanel eastPanel;
	private JPanel westPanel;

	private JLabel searchLabel;
	private JTextField searchText;
	private JButton searchButton;

	private JButton allButton;

	private JLabel isbnLabel;
	private JTextField isbnText;

	private JLabel titleLabel;
	private JTextField titleText;

	private JLabel authorLabel;
	private JTextField authorText;

	private JLabel formatLabel;
	private JComboBox<String> formatCombo;

	private JLabel publicationDateLabel;
	private JTextField publicationDateText;

	// Verbesserungsmöglichkeit -> JTextArea mit Scrollbalken
	private JLabel shortDescriptionLabel;
	private JTextField shortDescriptionText;

	// Verbesserungsmöglichkeit -> JTextArea mit Scrollbalken
	private JLabel commentLabel;
	private JTextField commentText;

	private JLabel readLabel;
	private JComboBox<String> readCombo;

	private JButton newButton;
	private JButton saveButton;

	private JTable bookTable;

	/**
	 * Konstruktur (Fensterbeschriftung und Initialisierung der Komponenten
	 * 
	 * @param title
	 */
	public BookGUI(String title) {
		super(title);

		// Initialisierung der Fenster-Komponenten
		initComponents();
	}

	/**
	 * Zuweisung: Inhalt und Form der einzelnen JPanels (North, East, West)
	 */
	private void initComponents() {

		initComponentsNorth();
		initComponentsEast();
		initComponentsWest();

	}

	/**
	 * Zuweisung Inhalt und Form der einzelnen Komponenten, die dann im
	 * North-Panel ausgegeben werden. Mit der Möglichkeit zur Suche nach dem
	 * Buchtitel
	 */
	private void initComponentsNorth() {

		northPanel = new JPanel();

		searchLabel = new JLabel("Nach Buchtitel suchen: ");
		searchLabel.setFont(new Font(labelFont, labelStyle, labelSize));

		searchText = new JTextField("Bitte Suchbegriff eingeben", 20);
		searchText.setFont(new Font(textFont, textStyle, textSize));
		searchText.setSelectionStart(0);
		searchText.setSelectionEnd(30);

		searchButton = new JButton("suchen");
		searchButton.setFont(new Font(labelFont, labelStyle, labelSize));
		searchButton.setBackground(Color.lightGray);
		searchButton.setBorder(BorderFactory.createRaisedBevelBorder());
		searchButton.addActionListener(new BookActionListener(this));

		allButton = new JButton("alle anzeigen");
		allButton.setFont(new Font(labelFont, labelStyle, labelSize));
		allButton.setBackground(Color.lightGray);
		allButton.setBorder(BorderFactory.createRaisedBevelBorder());
		allButton.addActionListener(new BookActionListener(this));

		// Hinzufügen der einzelnen Komponenten zum NorthPanel
		northPanel.add(searchLabel);
		northPanel.add(searchText);
		northPanel.add(searchButton);
		northPanel.add(allButton);

		// Hinzufügen des NorthPanel zum Fenster
		this.getContentPane().add(northPanel, BorderLayout.NORTH);
	}

	/**
	 * Zuweisung Inhalt und Form der einzelnen Komponenten, die dann im
	 * East-Panel ausgegeben werden. Felder für die Ausgabe, Bearbeitung und
	 * Neuerfassung einzelner Bücher
	 */
	private void initComponentsEast() {

		eastPanel = new JPanel();
		// Layout für 9 Zeilen und 2 Spalten
		eastPanel.setLayout(new GridLayout(10, 2));

		isbnLabel = new JLabel("ISBN-Nummer: ");
		isbnLabel.setFont(new Font(labelFont, labelStyle, labelSize));
		// Festlegung der Länge des Textfeldes, die anderen werden dann
		// ebenfalls an diese Größe angepasst
		isbnText = new JTextField(30);
		isbnText.setFont(new Font(textFont, textStyle, textSize));

		titleLabel = new JLabel("Buch-Titel: ");
		titleLabel.setFont(new Font(labelFont, labelStyle, labelSize));
		titleText = new JTextField();
		titleText.setFont(new Font(textFont, textStyle, textSize));

		authorLabel = new JLabel("Autor: (Nachname, Vorname) ");
		authorLabel.setFont(new Font(labelFont, labelStyle, labelSize));
		authorText = new JTextField();
		authorText.setFont(new Font(textFont, textStyle, textSize));

		publicationDateLabel = new JLabel("Ausgabe-Jahr: ");
		publicationDateLabel
				.setFont(new Font(labelFont, labelStyle, labelSize));
		publicationDateText = new JTextField();
		publicationDateText.setFont(new Font(textFont, textStyle, textSize));

		formatLabel = new JLabel("Buch-Format: ");
		formatLabel.setFont(new Font(labelFont, labelStyle, labelSize));
		formatCombo = new JComboBox<String>();
		formatCombo.setBackground(Color.white);
		formatCombo.setFont(new Font(textFont, textStyle, textSize));

		// Festlegung des Inhalts der Combo-Box formatCombo
		String[] format = { "", "Taschenbuch", "Gebundene Ausgabe", "Hoerbuch",
				"elektronisch", "unbekannt" };
		for (int i = 0; i < format.length; i++) {
			formatCombo.addItem(format[i]);
		}

		shortDescriptionLabel = new JLabel("Kurzbeschreibung: ");
		shortDescriptionLabel
				.setFont(new Font(labelFont, labelStyle, labelSize));
		shortDescriptionText = new JTextField();
		shortDescriptionText.setFont(new Font(textFont, textStyle, textSize));

		commentLabel = new JLabel("Kommentar: ");
		commentLabel.setFont(new Font(labelFont, labelStyle, labelSize));
		commentText = new JTextField();
		commentText.setFont(new Font(textFont, textStyle, textSize));

		readLabel = new JLabel("Gelesen: ");
		readLabel.setFont(new Font(labelFont, labelStyle, labelSize));
		readCombo = new JComboBox<String>();
		readCombo.setBackground(Color.white);
		readCombo.setFont(new Font(textFont, textStyle, textSize));

		// Festlegung des Inhalts der Combo-Box readCombo
		String[] read = { "", "Ja", "Nein", "unbekannt" };
		for (int i = 0; i < read.length; i++) {
			readCombo.addItem(read[i]);
		}

		newButton = new JButton("neu");
		newButton.setFont(new Font(labelFont, labelStyle, labelSize));
		newButton.setBackground(Color.lightGray);
		newButton.setBorder(BorderFactory.createRaisedBevelBorder());
		// wenn auf den Button "neu" geklickt wird, soll ein Datensatz
		// in der Datenbank neu erfasst werden
		newButton.addActionListener(new BookActionListener(this));

		saveButton = new JButton("speichern");
		saveButton.setFont(new Font(labelFont, labelStyle, labelSize));
		saveButton.setBackground(Color.lightGray);
		saveButton.setBorder(BorderFactory.createRaisedBevelBorder());
		// wenn auf den Button "speichern" geklickt wird, soll der Datensatz
		// in die Datenbank gespeichert werden
		saveButton.addActionListener(new BookActionListener(this));
		// Der Button "speichern" ist zu Beginn/beim Erscheinen des Fensters
		// noch ncht auswählbar; sie wird erst sichtbar, wenn ein Datensatz
		// ausgewählt wurde
		saveButton.setEnabled(false);

		// Dummy-Label für Abstand zwischen der Tabelle und den Buttons
		JLabel dummyLabel = new JLabel();
		JLabel dummyLabel2 = new JLabel();

		// Hinzufügen der einzelnen Komponenten zum EastPanel
		eastPanel.add(isbnLabel);
		eastPanel.add(isbnText);

		eastPanel.add(titleLabel);
		eastPanel.add(titleText);

		eastPanel.add(authorLabel);
		eastPanel.add(authorText);

		eastPanel.add(publicationDateLabel);
		eastPanel.add(publicationDateText);

		eastPanel.add(formatLabel);
		eastPanel.add(formatCombo);

		eastPanel.add(shortDescriptionLabel);
		eastPanel.add(shortDescriptionText);

		eastPanel.add(commentLabel);
		eastPanel.add(commentText);

		eastPanel.add(readLabel);
		eastPanel.add(readCombo);

		eastPanel.add(dummyLabel);
		eastPanel.add(dummyLabel2);

		eastPanel.add(newButton);
		eastPanel.add(saveButton);

		// Hinzufügen des EastPanel zum Fenster
		this.getContentPane().add(eastPanel, BorderLayout.EAST);

	}

	/**
	 * Zuweisung Inhalt und Form der einzelnen Komponenten, die dann im
	 * West-Panel ausgegeben werden.
	 */
	private void initComponentsWest() {
		// Initailisierung der Büchertabelle. Nachdem auch on anderes
		// Teilen des Programms auf die Tabelle zugegriffen wird bzw.
		// die Tabelle für die Suchfunktion neu aufgebaut werden muss,
		// wird dieser Teil in eine eigene Methode geschrieben.

		createBookTable();

	}

	/**
	 * Initialisierung der Büchertabelle
	 */
	public void createBookTable() {
		
		// Bei der erstmaligen Initialisierung des Fensters gibt es noch keine
		// Darstellungsprobleme mit der Tabelle. Es werden alle Datensätze angezeigt	
		if (getSearchText().getText().matches("Bitte Suchbegriff eingeben")) {			
			// alle Datensätze werden angezeigt
			bookTable = new BookTable().showAllData();
			
		} else {
			// Damit bei Eingabe eines Suchbegriffes die Tabelle neu aufgebaut wird,
			// wird sie zunächst einmal aus dem WestPanel entfernt, danach wird nach
			// dem entsprechenden Suchbegriff gesucht (im Datenbankfeld title) und am
			// Ende werden die Suchergebnisse wieder in einer neuen Tabelle angezeigt
			
			this.getContentPane().remove(westPanel);		
			bookTable = new BookTable().selectetData();
		}
		
		westPanel = new JPanel();
		
		// Mittels JScrollPane werden die Spaltenüberschriften angezeigt
		westPanel.add(new JScrollPane(bookTable));

		// Hinzufügen des WestPanel zum Fenster und auf sichtbar setzen
		this.getContentPane().add(westPanel, BorderLayout.WEST);
		this.setVisible(true);
		
		

	}

	/**
	 * Der Inhalt aller Tabellenfelder im EastPanel wird zurückgesetzt
	 */
	public void resetTableEast() {

		this.getIsbnText().setText("");
		this.getTitleText().setText("");
		this.getAuthorText().setText("");
		this.getPublicationDateText().setText("");
		this.getFormatCombo().setSelectedItem("");
		this.getShortDescriptionText().setText("");
		this.getCommentText().setText("");
		this.getReadCombo().setSelectedItem("");

	}

	/**
	 * Definition der Getter und Setter
	 */
	public JTextField getIsbnText() {
		return isbnText;
	}

	public void setIsbnText(JTextField isbnText) {
		this.isbnText = isbnText;
	}

	public JTextField getTitleText() {
		return titleText;
	}

	public void setTitleText(JTextField titleText) {
		this.titleText = titleText;
	}

	public JTextField getAuthorText() {
		return authorText;
	}

	public void setAuthorText(JTextField authorText) {
		this.authorText = authorText;
	}

	public JComboBox<String> getFormatCombo() {
		return formatCombo;
	}

	public void setFormatCombo(JComboBox<String> formatCombo) {
		this.formatCombo = formatCombo;
	}

	public JTextField getPublicationDateText() {
		return publicationDateText;
	}

	public void setPublicationDateText(JTextField publicationDateText) {
		this.publicationDateText = publicationDateText;
	}

	public JTextField getShortDescriptionText() {
		return shortDescriptionText;
	}

	public void setShortDescriptionText(JTextField shortDescriptionText) {
		this.shortDescriptionText = shortDescriptionText;
	}

	public JTextField getCommentText() {
		return commentText;
	}

	public void setCommentText(JTextField commentText) {
		this.commentText = commentText;
	}

	public JComboBox<String> getReadCombo() {
		return readCombo;
	}

	public void setReadCombo(JComboBox<String> readCombo) {
		this.readCombo = readCombo;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}

	public JTextField getSearchText() {
		return searchText;
	}

	public void setSearchText(JTextField searchText) {
		this.searchText = searchText;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}

	public JButton getAllButton() {
		return allButton;
	}

	public void setAllButton(JButton allButton) {
		this.allButton = allButton;
	}

	public JButton getNewButton() {
		return newButton;
	}

	public void setNewButton(JButton newButton) {
		this.newButton = newButton;
	}

	public JTable getBookTable() {
		return bookTable;
	}

	public void setBookTable(JTable bookTable) {
		this.bookTable = bookTable;
	}
	
	

}
