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

public class vistaTabla extends JInternalFrame {
	private WebTable table;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnClose;
	private JComboBox comboBoxSort;
	private JComboBox comboBoxDirection;
	private JPopupMenu popupMenu;
	private JMenuItem mntmAddRow;
	private JMenuItem mntmDeleteRow;

	/**
	 * Create the frame.
	 */
	public vistaTabla() {
		setClosable(true);
		setFrameIcon(new ImageIcon(vistaTabla.class.getResource("/com/mordor/mordorLloguer/assets/badge.png")));
		setBounds(100, 100, 688, 432);
		
		JLabel lblSortby = new JLabel("Sort by ");
		
		comboBoxSort = new JComboBox();
		
		JLabel lblDirection = new JLabel("Direction");
		
		comboBoxDirection = new JComboBox();
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnAdd = new JButton("Add");
		
		btnDelete = new JButton("Delete");
		
		btnClose = new JButton("Close");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSortby)
							.addGap(1)
							.addComponent(comboBoxSort, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblDirection)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxDirection, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnAdd)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnDelete)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnClose))
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 644, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSortby)
						.addComponent(comboBoxSort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDirection)
						.addComponent(comboBoxDirection, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnClose)
						.addComponent(btnDelete)
						.addComponent(btnAdd))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		
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

	public JComboBox getComboBoxSort() {
		return comboBoxSort;
	}

	public JComboBox getComboBoxDirection() {
		return comboBoxDirection;
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
	
	
	
}
