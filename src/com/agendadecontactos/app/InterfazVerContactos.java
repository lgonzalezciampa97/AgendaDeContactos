package com.agendadecontactos.app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.agendadecontactos.daos.ContactoDAO;
import com.agendadecontactos.modelos.ContactoVO;
import java.awt.event.*;

public class InterfazVerContactos extends JPanel implements ActionListener {

    // Objeto para la creacion de la lista en el panel
    private JList<ContactoVO> listaContactos = new JList<ContactoVO>();
    // Objeto para el modelo de la vista de nuestra lista en el panel
    private DefaultListModel<ContactoVO> modeloLista = new DefaultListModel<ContactoVO>();
    private JScrollPane scr;
    private JLabel lblCantContactos;
    private JButton btnEditar, btnEliminar, btnRefresh;
    public static int idSeleccionado;

    // Lista de objetos tipo ContactoVO para agregar a nuestro panel
    private List<ContactoVO> lista = new ArrayList<ContactoVO>();

    public void paint(Graphics g) {

        ImageIcon logo = new ImageIcon(getClass().getResource("contact-book.png"));
        g.drawImage(logo.getImage(), 450, 10, 100, 85, this);
        setOpaque(false);
        super.paint(g);
    }

    @SuppressWarnings("unchecked")
    public InterfazVerContactos() {
        setLayout(null);
        ContactoDAO.agregarContactosALista(lista);

        for (ContactoVO datos : lista) {
            modeloLista.addElement(datos);
        }

        lblCantContactos = new JLabel();
        if (modeloLista.getSize() <= 1) {
            lblCantContactos.setText("TENGO " + modeloLista.getSize() + " CONTACTO");
        } else {
            lblCantContactos.setText("TENGO " + modeloLista.getSize() + " CONTACTOS");
        }
        lblCantContactos.setBounds(150, -10, 320, 100);
        lblCantContactos.setForeground(new Color(50, 50, 50));
        lblCantContactos.setFont(new Font("Franklin Gothic Book", 1, 28));
        add(lblCantContactos);

        listaContactos.setModel(modeloLista);
        listaContactos.setForeground(new Color(210, 227, 251));
        listaContactos.setFont(new Font("Franklin Gothic Book", 1, 16));
        listaContactos.setBackground(new Color(26, 115, 232));
        listaContactos.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent evt) {
                int selectedRow = listaContactos.getSelectedIndex();

                listaContactos = (JList<ContactoVO>) evt.getSource();

                if (evt.getClickCount() == 2) {

                    ContactoVO contactoSeleccionado = new ContactoVO();
                    contactoSeleccionado = modeloLista.getElementAt(selectedRow);
                    idSeleccionado = ContactoDAO.obtenerId(contactoSeleccionado.getNumeroContacto());

                    JFrame ventana = new JFrame("DETALLES DE CONTACTO");
                    InterfazDetalles app = new InterfazDetalles();
                    ventana.add(app);
                    ventana.setBackground(new Color(208, 226, 251));
                    ventana.setSize(520, 340);
                    ventana.setLocationRelativeTo(null);
                    ventana.setVisible(true);
                    ventana.setResizable(false);
                }
            }
        });

        scr = new JScrollPane(listaContactos);
        scr.setBounds(20, 100, 550, 200);
        add(scr);

        btnEditar = new JButton("EDITAR");
        btnEditar.setBounds(30, 310, 160, 35);
        btnEditar.setForeground(new Color(255, 255, 255));
        btnEditar.setBackground(new Color(26, 115, 232));
        btnEditar.setFont(new Font("Franklin Gothic Book", 1, 20));
        btnEditar.addActionListener(this);
        add(btnEditar);

        btnEliminar = new JButton("ELIMINAR");
        btnEliminar.setBounds(400, 310, 160, 35);
        btnEliminar.setForeground(new Color(255, 255, 255));
        btnEliminar.setBackground(new Color(26, 115, 232));
        btnEliminar.setFont(new Font("Franklin Gothic Book", 1, 20));
        btnEliminar.addActionListener(this);
        add(btnEliminar);

        btnRefresh = new JButton("ACTUALIZAR DATOS");
        btnRefresh.setBounds(195, 310, 200, 35);
        btnRefresh.setForeground(new Color(255, 255, 255));
        btnRefresh.setBackground(new Color(35, 199, 72));
        btnRefresh.setFont(new Font("Franklin Gothic Book", 1, 18));
        btnRefresh.addActionListener(this);
        add(btnRefresh);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        int selectedRow = this.listaContactos.getSelectedIndex();

        if (evento.getSource() == btnEditar && listaContactos.isSelectionEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No selecciono ningun contacto para editar, seleccione uno y presione \"EDITAR\"");
        } else if (evento.getSource() == btnEditar) {

            ContactoVO contactoSeleccionado = new ContactoVO();
            contactoSeleccionado = modeloLista.getElementAt(selectedRow);
            idSeleccionado = ContactoDAO.obtenerId(contactoSeleccionado.getNumeroContacto());

            InterfazEditarContacto app = new InterfazEditarContacto();
            app.setBounds(0, 0, 360, 500);
            app.setLocationRelativeTo(null);
            app.setVisible(true);
            app.setResizable(false);

        } else if (evento.getSource() == btnEliminar && listaContactos.isSelectionEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No selecciono ningun contacto para eliminar, seleccione uno y presione \"ELIMINAR\"");
        } else if (evento.getSource() == btnEliminar) {

            Integer opcionSeleccionada = JOptionPane.showConfirmDialog(listaContactos,
                    "�Realmente quiere eliminar el contacto?", "CONFIRMAR ELIMINAR CONTACTO", JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);

            if (opcionSeleccionada == 0) {
                ContactoVO contactoSeleccionado = new ContactoVO();
                ContactoDAO contactoDataAccess = new ContactoDAO();
                contactoSeleccionado = modeloLista.getElementAt(selectedRow);

                int idSeleccionado = ContactoDAO.obtenerId(contactoSeleccionado.getNumeroContacto());
                contactoDataAccess.eliminarContacto(idSeleccionado, contactoSeleccionado.getNombreContacto());

                modeloLista.remove(selectedRow);
            }
        } else if (evento.getSource() == btnRefresh) {

            modeloLista.removeAllElements();
            lista.clear();

            ContactoDAO.agregarContactosALista(lista);

            for (ContactoVO datos : lista) {
                modeloLista.addElement(datos);
            }
            if (modeloLista.getSize() <= 1) {
                lblCantContactos.setText("TENGO " + modeloLista.getSize() + " CONTACTO");
            } else {
                lblCantContactos.setText("TENGO " + modeloLista.getSize() + " CONTACTOS");
            }

        }

    }

    public static void main(String[] args) {

        JFrame ventana = new JFrame("VER CONTACTOS");
        InterfazVerContactos app = new InterfazVerContactos();
        ventana.add(app);
        ventana.setBackground(new Color(140, 185, 244));
        ventana.setSize(600, 400);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        ventana.setResizable(false);
    }

}
