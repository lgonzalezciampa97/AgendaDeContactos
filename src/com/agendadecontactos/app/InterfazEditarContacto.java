package com.agendadecontactos.app;

import javax.swing.*;

import com.agendadecontactos.daos.ContactoDAO;
import com.agendadecontactos.modelos.ContactoVO;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InterfazEditarContacto extends JFrame implements ActionListener{
	
	private JButton btnAceptar, btnBorrarTodo, btnVolver;
	private JScrollPane scrPane;
	private JTextArea areaInfo;
	private JTextField txtNombre, txtApellido, txtNumero, txtEmail;
	private JLabel lblNombre, lblApellido, lblNumero, lblEmail, lblInfo, lblAste1, lblAste2;
	
	private Integer numero;
	private String nombre;
	private String apellido;
	private String email;
	private String info;
	private Integer id;
	private int idSeleccionado = InterfazVerContactos.idSeleccionado;
	private List<ContactoVO> datosContacto = new ArrayList<ContactoVO>();
	

	public InterfazEditarContacto() {

		setLayout(null);
		getContentPane().setBackground(new Color(140,185,244));
		setIconImage(new ImageIcon(getClass().getResource("contactoLogo.png")).getImage()); 
		setTitle("EDITAR CONTACTO");	
		ContactoVO miContactoAEditar = new ContactoVO();
		ContactoDAO.agregarContactoALista(datosContacto, idSeleccionado);
		
		for (ContactoVO contacto : datosContacto) {
			miContactoAEditar.setNombreContacto(contacto.getNombreContacto());
			miContactoAEditar.setApellidoContacto(contacto.getApellidoContacto());
			miContactoAEditar.setNumeroContacto(contacto.getNumeroContacto());
			miContactoAEditar.setEmailContacto(contacto.getEmailContacto());
			miContactoAEditar.setInfoContacto(contacto.getInfoContacto());
		}
		
		String numeroContacto = String.valueOf(miContactoAEditar.getNumeroContacto());

		lblNombre = new JLabel("NOMBRE:");
		lblNombre.setBounds(20, 20, 80, 20);
		lblNombre.setForeground(new Color(15,91,153));
		lblNombre.setFont(new Font("Franklin Gothic Book", 1, 14));
		add(lblNombre);

		lblAste1 = new JLabel("*");
		lblAste1.setBounds(320, 20, 20, 20);
		lblAste1.setForeground(new Color(255, 0, 0));
		lblAste1.setFont(new Font("Franklin Gothic Book", 1, 22));
		add(lblAste1);

		lblApellido = new JLabel("APELLIDO:");
		lblApellido.setBounds(20, 60, 80, 20);
		lblApellido.setForeground(new Color(15,91,153));
		lblApellido.setFont(new Font("Franklin Gothic Book", 1, 14));
		add(lblApellido);

		lblNumero = new JLabel("TELEFONO:");
		lblNumero.setBounds(20, 100, 180, 20);
		lblNumero.setForeground(new Color(15,91,153));
		lblNumero.setFont(new Font("Franklin Gothic Book", 1, 14));
		add(lblNumero);

		lblAste2 = new JLabel("*");
		lblAste2.setBounds(320, 100, 20, 20);
		lblAste2.setForeground(new Color(255, 0, 0));
		lblAste2.setFont(new Font("Franklin Gothic Book", 1, 22));
		add(lblAste2);

		lblEmail = new JLabel("EMAIL:");
		lblEmail.setBounds(20, 140, 80, 20);
		lblEmail.setForeground(new Color(15,91,153));
		lblEmail.setFont(new Font("Franklin Gothic Book", 1, 14));
		add(lblEmail);

		lblInfo = new JLabel("INFO:");
		lblInfo.setBounds(20, 180, 80, 20);
		lblInfo.setForeground(new Color(15,91,153));
		lblInfo.setFont(new Font("Franklin Gothic Book", 1, 14));
		add(lblInfo);

		txtNombre = new JTextField(miContactoAEditar.getNombreContacto());
		txtNombre.setBounds(97, 18, 220, 25);
		txtNombre.setForeground(new Color(70, 70, 70));
		txtNombre.setBackground(new Color(208, 226, 251));
		txtNombre.setFont(new Font("Franklin Gothic Book", 1, 18));
		add(txtNombre);

		txtApellido = new JTextField(miContactoAEditar.getApellidoContacto());
		txtApellido.setBounds(97, 57, 220, 25);
		txtApellido.setForeground(new Color(70, 70, 70));
		txtApellido.setBackground(new Color(208, 226, 251));
		txtApellido.setFont(new Font("Franklin Gothic Book", 1, 18));
		add(txtApellido);

		txtNumero = new JTextField(numeroContacto);
		txtNumero.setBounds(97, 99, 220, 25);
		txtNumero.setForeground(new Color(70, 70, 70));
		txtNumero.setBackground(new Color(208, 226, 251));
		txtNumero.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evento) {
				char caracter = evento.getKeyChar();

				if ((caracter < '0') || (caracter > '9') && (caracter != '\b')) {
					evento.consume();
				}
			}
		});
		txtNumero.setFont(new Font("Franklin Gothic Book", 1, 16));
		add(txtNumero);

		txtEmail = new JTextField(miContactoAEditar.getEmailContacto());
		txtEmail.setBounds(97, 138, 220, 25);
		txtEmail.setForeground(new Color(70, 70, 70));
		txtEmail.setBackground(new Color(208, 226, 251));
		txtEmail.setFont(new Font("Franklin Gothic Book", 0, 14));
		add(txtEmail);

		areaInfo = new JTextArea(miContactoAEditar.getInfoContacto());
		areaInfo.setBackground(new Color(208, 226, 251));
		scrPane = new JScrollPane(areaInfo);
		scrPane.setBounds(97, 180, 220, 80);
		scrPane.setForeground(new Color(70, 70, 70));
		scrPane.setFont(new Font("Franklin Gothic Book", 1, 16));
		add(scrPane);

		btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setBounds(97, 280, 220, 40);
		btnAceptar.setForeground(new Color(245, 245, 245));
		btnAceptar.setFont(new Font("Consolas", 1, 18));
		btnAceptar.setBackground(new Color(15, 91, 153));
		btnAceptar.addActionListener(this);
		add(btnAceptar);

		btnBorrarTodo = new JButton("BORRAR TODO");
		btnBorrarTodo.setBounds(97, 340, 220, 40);
		btnBorrarTodo.setForeground(new Color(245, 245, 245));
		btnBorrarTodo.setFont(new Font("Consolas", 1, 18));
		btnBorrarTodo.setBackground(new Color(15, 91, 153));
		btnBorrarTodo.addActionListener(this);
		add(btnBorrarTodo);
		
		btnVolver = new JButton("ATRAS");
		btnVolver.setBounds(97, 400, 220, 40);
		btnVolver.setForeground(new Color(245, 245, 245));
		btnVolver.setFont(new Font("Consolas", 1, 18));
		btnVolver.setBackground(new Color(15, 91, 153));
		btnVolver.addActionListener(this);
		add(btnVolver);

	}

	public void actionPerformed(ActionEvent evento) {
		
		if (evento.getSource() == btnBorrarTodo) {
			txtNombre.setText("");
			txtApellido.setText("");
			txtNumero.setText("");
			txtEmail.setText("");
			areaInfo.setText("");
		}
		else if (evento.getSource() == btnVolver) {
			this.setVisible(false);
		}
		else if(evento.getSource() == btnAceptar && txtNombre.getText().equals("") || txtNumero.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Los campos marcados deben estar completos");
		}
		else if (evento.getSource() == btnAceptar && txtNombre.getText() != "" || txtNumero.getText() != "") {
			numero = Integer.parseInt(txtNumero.getText());
			nombre = txtNombre.getText().toUpperCase();
			apellido = txtApellido.getText().toUpperCase();
			email = txtEmail.getText().toUpperCase();
			info = areaInfo.getText().toUpperCase();
			id = idSeleccionado;
			
			ContactoVO miContacto = new ContactoVO(nombre, apellido, numero, email, info, id);
			ContactoDAO.editarContacto(miContacto);
			
			txtNombre.setText("");
			txtApellido.setText("");
			txtNumero.setText("");
			txtEmail.setText("");
			areaInfo.setText("");
			this.setVisible(false);
		}

	}

	public static void main(String[] args) {
		
		InterfazEditarContacto app = new InterfazEditarContacto();
		app.setBounds(0,0,360,500);
		app.setLocationRelativeTo(null);
		app.setVisible(true);
		app.setResizable(false);

	}

}
