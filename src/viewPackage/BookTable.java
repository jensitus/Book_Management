package viewPackage;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;



/**
 * Die Klasse BookTable ist für den Aufbau und den Inhalt der
 * Büchertabelle zuständig
 * 
 * @author Bergsocke
 *
 */
public class BookTable extends AbstractTableModel {

	
	public JTable showAllData() {
		
		String [][] rowData = {{"Japan", "245"}, {"USA", "240"}, {"Italien", "270"}, {"Griechenland", "185"}};
		String [] columNames = {"Land", "Fernsehdauer pro Tag"};
		
		JTable bookTable = new JTable(rowData, columNames);
		
		return bookTable;

	}
	
	public JTable selectetData() {
		
		String [][] rowData = {{"Österreich", "345"}, {"Deutschland", "245"}};
		String [] columNames = {"Land", "Fernsehdauer pro Tag"};
		
		JTable bookTable = new JTable(rowData, columNames);
		
		return bookTable;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
