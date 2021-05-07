package com.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
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
							.addComponent(comboBoxDirection, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnAdd)
								.addGap(18)
								.addComponent(btnDelete)
								.addGap(18)
								.addComponent(btnClose))
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 644, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(132, Short.MAX_VALUE))
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
					.addContainerGap(145, Short.MAX_VALUE))
		);
		
		table = new WebTable();
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);

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
	
}
