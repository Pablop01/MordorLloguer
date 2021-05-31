package com.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.ImageIcon;

public class vistaCarga extends JInternalFrame {
	private JButton btnCancel;
	private JProgressBar progressBar;
	private JLabel lblNewLabel;
	/**
	 * Create the frame.
	 */
	public vistaCarga(String text) {
		setFrameIcon(new ImageIcon(vistaCarga.class.getResource("/com/mordor/mordorLloguer/assets/clock.png")));
		setBounds(100, 100, 390, 170);
		getContentPane().setLayout(new MigLayout("", "[378.00]", "[][][][][][]"));
		
		lblNewLabel = new JLabel(text);
		getContentPane().add(lblNewLabel, "cell 0 0,alignx center");
		
		JLabel lblPleaseWait = new JLabel("Please wait");
		getContentPane().add(lblPleaseWait, "cell 0 1,alignx center");
		
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		getContentPane().add(progressBar, "cell 0 3,growx");
		
		btnCancel = new JButton("Cancel");
		getContentPane().add(btnCancel, "cell 0 4,alignx center");

	}
	public JButton getBtnCancel() {
		return btnCancel;
	}
	public JProgressBar getProgressBar() {
		return progressBar;
	}
	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}
	
}
