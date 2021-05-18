package com.mordor.mordorLloguer.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import com.alee.laf.table.WebTable;

public abstract class MyTableModel<T> extends AbstractTableModel {

	private WebTable table;

	public final String[] HEADER;

	List<T> data;

	public MyTableModel(List<T> data, String[] header) {
		this.data = data;
		this.HEADER = header;
	}

	@Override
	public int getColumnCount() {
		return HEADER.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public String getColumnName(int column) {
		return HEADER[column];
	}

	@Override
	public abstract Object getValueAt(int row, int col);

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return false;
		} else {
			return true;
		}
	}

//	@Override
//	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//
//		switch (columnIndex) {
//
//		case 1:
//			data.get(rowIndex).setNombre(aValue.toString());
//			break;
//		case 2:
//			java.util.Date fecha = (java.util.Date) aValue;
//			data.get(rowIndex).setFechaNac(new java.sql.Date(fecha.getTime()));
//			break;
//		}
//
//		fireTableCellUpdated(rowIndex, columnIndex);
//
//	}

	public void add(T t) {
		data.add(t);
		fireTableRowsInserted(data.size() - 1, data.size() - 1);
	}

	public ArrayList<T> get(int[] rows) {
		ArrayList<T> lista = new ArrayList<T>();

		for (int row : rows) {
			lista.add(get(row));
		}
		return lista;

	}

	public T get(int row) {
		if (row < 0 || row >= data.size()) {
			return null;
		} else {
			return data.get(row);
		}
	}

//	public void delete(String dni) {
//
//		delete(new Empleado(dni));
//		
//		
//	}

	public void delete(T t) {
		int pos = data.indexOf(t);
		if (data.remove(t))
			fireTableRowsDeleted(pos, pos);
	}

	public synchronized void delete(int row) {

		if (data.remove(row) != null)
			fireTableRowsDeleted(row, row);
	}

//	@Override
//	public void tableChanged(TableModelEvent arg0) {
//
//		MyTableModel mtm = (MyTableModel) table.getModel();
//
//		if (arg0.getType() == TableModelEvent.UPDATE) {
//			System.out.println("Se ha actualizado el empleado:  " + mtm.getEmployee(arg0.getFirstRow()));
//
//		}
//
//	}

}
