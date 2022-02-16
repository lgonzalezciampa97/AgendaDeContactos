package com.agendadecontactos.app;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class InterfazPrincipal extends JPanel implements ActionListener {

	private final JLabel lblTitulo, lblTituloDock, lblSubTitulo;
	private final JButton btnAgregarContacto, btnVerContactos, btnSalir;
	
	public void paint (Graphics g) {
		
		ImageIcon logo = new ImageIcon(getClass().getResource("contactoLogo.png"));
		g.drawImage(logo.getImage(),40,20,100,100,this);
		setOpaque(false);
		super.paint(g);
	}

	public InterfazPrincipal() {
		setLayout(null);
		
		
		lblTitulo = new JLabel("Contactos");
		lblTitulo.setBounds(160, 40, 250, 30);
		lblTitulo.setForeground(new Color(100, 100, 100));
		lblTitulo.setFont(new Font("Franklin Gothic Book", 1, 42));
		add(lblTitulo);
		
		lblTituloDock = new JLabel("Agenda Personal");
		lblTituloDock.setBounds(210, 70, 250, 30);
		lblTituloDock.setForeground(new Color(150,150,150));
		lblTituloDock.setFont(new Font("Franklin Gothic Book", 1, 18));
		add(lblTituloDock);
		
		lblSubTitulo = new JLabel("Todos en un solo lugar");
		lblSubTitulo.setBounds(95, 150, 290, 30);
		lblSubTitulo.setForeground(new Color(100,100,100));
		lblSubTitulo.setFont(new Font("Franklin Gothic Book", 1, 22));
		add(lblSubTitulo);

		btnAgregarContacto = new JButton("Crear un Nuevo Contacto");
		btnAgregarContacto.setBounds(65, 240, 270, 40);
		btnAgregarContacto.addActionListener(this);
		btnAgregarContacto.setForeground(new Color(245,245,245));
		btnAgregarContacto.setFont(new Font("Consolas", 1, 18));
		btnAgregarContacto.setBackground(new Color(26, 115, 232));
		add(btnAgregarContacto);
		
		btnVerContactos = new JButton("Ver Contactos");
		btnVerContactos.setBounds(100, 290, 200, 40);
		btnVerContactos.addActionListener(this);
		btnVerContactos.setForeground(new Color(245,245,245));
		btnVerContactos.setFont(new Font("Consolas", 1, 18));
		btnVerContactos.setBackground(new Color(26, 115, 232));
		add(btnVerContactos);
		
		btnSalir= new JButton("Salir");
		btnSalir.setBounds(130, 340, 150, 40);
		btnSalir.addActionListener(this);
		btnSalir.setForeground(new Color(245,245,245));
		btnSalir.setFont(new Font("Consolas", 1, 18));
		btnSalir.setBackground(new Color(26, 115, 232));
		add(btnSalir);
		

	}

	public void actionPerformed(ActionEvent evento) {

		if (evento.getSource() == btnAgregarContacto) {

			InterfazAgregarContacto app = new InterfazAgregarContacto();
			app.setBounds(0,0,360,500);
			app.setLocationRelativeTo(null);
			app.setResizable(false);
			app.setVisible(true);

		}
		
		if (evento.getSource() == btnVerContactos) {
			
			JFrame ventana = new JFrame("VER CONTACTOS");
			InterfazVerContactos app = new InterfazVerContactos();
			ventana.add(app);
			ventana.setBackground(new Color(208,226,251));
			ventana.setSize(600,400);
			ventana.setLocationRelativeTo(null);
			ventana.setVisible(true);
			ventana.setResizable(false);
			
		}
		
		if (evento.getSource() == btnSalir) {
			System.exit(0);
		}

	}


	public static void main(String args[]) {
		
		JFrame ventana = new JFrame("AGENDA DE CONTACTOS");
		InterfazPrincipal app = new InterfazPrincipal();
		ventana.add(app);
		ventana.setBackground(new Color(208,226,251));
		ventana.setSize(430,460);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		ventana.setResizable(false);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	

}
