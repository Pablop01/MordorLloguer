package com.mordor.mordorLloguer.vistas;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.alee.laf.table.WebTable;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class vistaTablaClientes extends JInternalFrame {
	private WebTable table;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnClose;
	private JPopupMenu popupMenu;
	private JMenuItem mntmAddRow;
	private JMenuItem mntmDeleteRow;
	private JButton btnEdit;
	private JTextField txtFieldDni;
	private JTextField txtFieldName;
	private JTextField txtFieldSurname;
	private JComboBox comboBox;

	/**
	 * Create the frame.
	 */
	public vistaTablaClientes() {
		setClosable(true);
		setFrameIcon(new ImageIcon(vistaTablaClientes.class.getResource("/com/mordor/mordorLloguer/assets/group.png")));
		setBounds(100, 100, 702, 438);
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnAdd = new JButton("Add");
		
		btnDelete = new JButton("Delete");
		
		btnClose = new JButton("Close");
		
		btnEdit = new JButton("Edit");
		
		JButton btnImprimir = new JButton(new ImageIcon(vistaTablaClientes.class.getResource("/com/mordor/mordorLloguer/assets/printer.png")));
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnImprimir, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 300, Short.MAX_VALUE)
								.addComponent(btnAdd)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnDelete)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnEdit)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnClose))
							.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnImprimir, GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnClose, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnEdit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(39))
		);
		panel.setLayout(new MigLayout("", "[][97.00][][133.00][][120.00][185.00]", "[]"));
		
		JLabel lblDni = new JLabel("DNI");
		panel.add(lblDni, "cell 0 0,aligny center");
		
		txtFieldDni = new JTextField();
		panel.add(txtFieldDni, "cell 1 0,aligny center");
		txtFieldDni.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		panel.add(lblName, "cell 2 0,aligny center");
		
		txtFieldName = new JTextField();
		panel.add(txtFieldName, "cell 3 0,aligny center");
		txtFieldName.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname");
		panel.add(lblSurname, "cell 4 0,aligny center");
		
		txtFieldSurname = new JTextField();
		panel.add(txtFieldSurname, "cell 5 0,aligny center");
		txtFieldSurname.setColumns(10);
		
		JLabel lblDrivingLicense = new JLabel("Driving License");
		panel.add(lblDrivingLicense, "flowx,cell 6 0,aligny center");
		
		comboBox = new JComboBox();
		panel.add(comboBox, "cell 6 0,growx,aligny center");
		
		table = new WebTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setRowHeight(25);
		table.setVisibleRowCount(5);
		table.optimizeColumnWidths(true);
		table.setOptimizeRowHeight(true);
		table.setEditable(true);
		scrollPane.setViewportView(table);
		
		popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		
		mntmAddRow = new JMenuItem("Add Row");
		popupMenu.add(mntmAddRow);
		
		mntmDeleteRow = new JMenuItem("Delete Row");
		popupMenu.add(mntmDeleteRow);
		
		table.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getButton() == (MouseEvent.BUTTON3)) {
					int row = table.rowAtPoint(e.getPoint());
					
					if(row<0 || row>table.getRowCount()) {
						table.clearSelection();
					}else if(table.getSelectedRowCount() <= 1) {
						table.setSelectedRow(row);
						popupMenu.show(table, e.getX(), e.getY());
					}else if(table.getSelectedRowCount() > 1) {
						popupMenu.show(table, e.getX(), e.getY());
					}
				}
			}
		});
		
		getContentPane().setLayout(groupLayout);

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnClose() {
		return btnClose;
	}
	
	public JButton getBtnEdit() {
		return btnEdit;
	}

	public WebTable getTable() {
		return table;
	}

	public JMenuItem getMntmAddRow() {
		return mntmAddRow;
	}

	public JMenuItem getMntmDeleteRow() {
		return mntmDeleteRow;
	}

	public JPopupMenu getPopupMenu() {
		return popupMenu;
	}

	public JTextField getTxtFieldDni() {
		return txtFieldDni;
	}

	public JTextField getTxtFieldName() {
		return txtFieldName;
	}

	public JTextField getTxtFieldSurname() {
		return txtFieldSurname;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}
	
}
