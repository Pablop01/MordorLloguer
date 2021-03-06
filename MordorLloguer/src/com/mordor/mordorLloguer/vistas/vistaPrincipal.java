package com.mordor.mordorLloguer.vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.alee.laf.WebLookAndFeel;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class vistaPrincipal extends JFrame {

	private JPanel contentPane;
	private JButton btnlogin;
	private JButton btnlogout;
	private JDesktopPane desktopPane;
	private JMenuItem mntmExit;
	private JMenuItem mntmPreferences;
	private JButton btnTabla;
	private JButton btnClientes;
	private JButton btnVehiculos;
	private JLabel lblBienvenido;
	private JLabel lblNombre;
	private JButton btnInvoice;
	
	/**
	 * Create the frame.
	 */
	public vistaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 915, 626);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		mntmPreferences = new JMenuItem("Preferences");
		mnEdit.add(mntmPreferences);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar(SwingConstants.HORIZONTAL);
		toolBar.setEnabled(false);
		toolBar.putClientProperty("styleId", "attached-north");
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		btnlogin = new JButton("");
		btnlogin.setIcon(new ImageIcon(vistaPrincipal.class.getResource("/com/mordor/mordorLloguer/assets/login.png")));
		toolBar.add(btnlogin);
		
		btnlogout = new JButton("");
		btnlogout.setEnabled(false);
		btnlogout.setIcon(new ImageIcon(vistaPrincipal.class.getResource("/com/mordor/mordorLloguer/assets/logout.png")));
		toolBar.add(btnlogout);
		
		toolBar.addSeparator(new Dimension(10,10));
		
		btnTabla = new JButton("");
		btnTabla.setEnabled(false);
		btnTabla.setIcon(new ImageIcon(vistaPrincipal.class.getResource("/com/mordor/mordorLloguer/assets/badge.png")));
		toolBar.add(btnTabla);
		
		btnClientes = new JButton("");
		btnClientes.setEnabled(false);
		btnClientes.setIcon(new ImageIcon(vistaPrincipal.class.getResource("/com/mordor/mordorLloguer/assets/group.png")));
		toolBar.add(btnClientes);
		
		btnVehiculos = new JButton("");
		btnVehiculos.setEnabled(false);
		btnVehiculos.setIcon(new ImageIcon(vistaPrincipal.class.getResource("/com/mordor/mordorLloguer/assets/car.png")));
		toolBar.add(btnVehiculos);
		
		btnInvoice = new JButton("");
		btnInvoice.setEnabled(false);
		btnInvoice.setIcon(new ImageIcon(vistaPrincipal.class.getResource("/com/mordor/mordorLloguer/assets/invoice.png")));
		toolBar.add(btnInvoice);
		toolBar.addSeparator(new Dimension(500,0));
		lblBienvenido = new JLabel("");
		toolBar.add(lblBienvenido);
		
		lblNombre = new JLabel("");
		toolBar.add(lblNombre);
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
	}

	public JButton getBtnlogin() {
		return btnlogin;
	}

	public JButton getBtnlogout() {
		return btnlogout;
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public JMenuItem getMntmExit() {
		return mntmExit;
	}

	public JMenuItem getMntmPreferences() {
		return mntmPreferences;
	}

	public JButton getBtnTabla() {
		return btnTabla;
	}

	public JButton getBtnClientes() {
		return btnClientes;
	}

	public JButton getBtnVehiculos() {
		return btnVehiculos;
	}

	public JLabel getLblBienvenido() {
		return lblBienvenido;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JButton getBtnInvoice() {
		return btnInvoice;
	}
	
}
