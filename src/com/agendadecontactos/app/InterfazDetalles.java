package com.agendadecontactos.app;

import com.agendadecontactos.daos.ContactoDAO;
import com.agendadecontactos.modelos.ContactoVO;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class InterfazDetalles extends JPanel {

    private String nombreContacto;
    private String apellidoContacto;
    private Integer numeroContacto;
    private String emailContacto;
    private String infoContacto;
    private Integer idContacto;

    private JTextField txtNom, txtApe, txtNum, txtMail;
    private JTextArea areaInfo;
    private JLabel lblNom, lblApe, lblNum, lblMail, lblInf;

    private int idSeleccionado = InterfazVerContactos.idSeleccionado;
    private List<ContactoVO> datosContacto = new ArrayList<ContactoVO>();

    public void paint(Graphics g) {

        ImageIcon logo = new ImageIcon(getClass().getResource("phone-book.png"));
        g.drawImage(logo.getImage(), 40, 40, 100, 100, this);
        setOpaque(false);
        super.paint(g);
    }

    public InterfazDetalles() {
        setLayout(null);
        ContactoVO miContactoDetalles = new ContactoVO();
        ContactoDAO.agregarContactoALista(datosContacto, idSeleccionado);
        
        for (ContactoVO contacto : datosContacto) {
			miContactoDetalles.setNombreContacto(contacto.getNombreContacto());
			miContactoDetalles.setApellidoContacto(contacto.getApellidoContacto());
			miContactoDetalles.setNumeroContacto(contacto.getNumeroContacto());
			miContactoDetalles.setEmailContacto(contacto.getEmailContacto());
			miContactoDetalles.setInfoContacto(contacto.getInfoContacto());
		}
		
		String numeroContacto = String.valueOf(miContactoDetalles.getNumeroContacto());

		lblNom = new JLabel("Nombre:");
        lblNom.setBounds(190, 20, 100, 30);
        add(lblNom);
		
        txtNom = new JTextField(miContactoDetalles.getNombreContacto());
        txtNom.setBounds(250, 20, 200, 30);
        txtNom.setEditable(false);
        add(txtNom);
        
        lblApe = new JLabel("Apellido:");
        lblApe.setBounds(190, 60, 100, 30);
        add(lblApe);

        txtApe = new JTextField(miContactoDetalles.getApellidoContacto());
        txtApe.setBounds(250, 60, 200, 30);
        txtApe.setEditable(false);
        add(txtApe);
        
        lblNum = new JLabel("Numero:");
        lblNum.setBounds(190, 100, 100, 30);
        add(lblNum);

        txtNum = new JTextField(numeroContacto);
        txtNum.setBounds(250, 100, 200, 30);
        txtNum.setEditable(false);
        add(txtNum);
        
        lblMail = new JLabel("Email:");
        lblMail.setBounds(190, 140, 100, 30);
        add(lblMail);

        txtMail = new JTextField(miContactoDetalles.getEmailContacto());
        txtMail.setBounds(250, 140, 200, 30);
        txtMail.setEditable(false);
        add(txtMail);
        
        lblInf = new JLabel("Info:");
        lblInf.setBounds(190, 180, 100, 30);
        add(lblInf);

        areaInfo = new JTextArea(miContactoDetalles.getInfoContacto());
        areaInfo.setBounds(250, 180, 200, 80);
        areaInfo.setEditable(false);
        add(areaInfo);
    }

    public static void main(String[] args) {

        JFrame ventana = new JFrame("DETALLES DE CONTACTO");
        InterfazDetalles app = new InterfazDetalles();
        ventana.add(app);
        ventana.setBackground(new Color(26,115,251));
        ventana.setSize(520, 340);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
