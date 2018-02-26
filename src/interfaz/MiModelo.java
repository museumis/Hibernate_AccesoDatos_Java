package interfaz;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.DefaultTableModel;

public class MiModelo extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	ArrayList<Integer> modelo = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

	public MiModelo(Object[][] datos, Object[] titulos) {
		super(datos, titulos);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		if ((column == 0) || (column == 1)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
	/*
	 * Class<?> tipo = String.class;
	 * 
	 * switch (columnIndex) { case 2:{ tipo = Long.class; break; } case 3:{ tipo =
	 * Boolean.class; break; }
	 * 
	 * default: break; }
	 * 
	 * return tipo; 
	 */
		return Integer.class;
}
	@Override
	public int getRowCount() {
		return 5;
	}

	@Override
	public Object getValueAt(int row, int column) {
		return modelo.get(row);
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		modelo.set(row, (Integer)aValue);
	}
	
	

}
