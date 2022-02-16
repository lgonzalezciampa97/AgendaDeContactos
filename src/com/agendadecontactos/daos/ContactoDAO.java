package com.agendadecontactos.daos;

import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import com.agendadecontactos.conexion.Conexion;
import com.agendadecontactos.modelos.ContactoVO;
import com.mysql.cj.protocol.Resultset;

public class ContactoDAO {

	public static void agendarContacto(ContactoVO miContacto) {

		Conexion conexion = new Conexion();

		try {

			Statement estatuto = conexion.getConnection().createStatement();
			String query = "INSERT INTO contacto (" + "nombreContacto, apellidoContacto, numeroContacto"
					+ ", emailContacto, infoContacto) " + "VALUES (" + "'" + miContacto.getNombreContacto() + "'," + "'"
					+ miContacto.getApellidoContacto() + "'," + "'" + miContacto.getNumeroContacto() + "'," + "'"
					+ miContacto.getEmailContacto() + "'," + "'" + miContacto.getInfoContacto() + "')";

			estatuto.execute(query);
			estatuto.close();
			conexion.desconectar();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se logro registar el contacto");
			e.printStackTrace();
		} finally {
			JOptionPane.showMessageDialog(null, "Tu contacto \"" + miContacto.getNombreContacto() + " "
					+ miContacto.getApellidoContacto() + "\" se" + " agrego correctamente");
		}

	}

	public static void editarContacto(ContactoVO miContacto) {
		Conexion conexion = new Conexion();
		PreparedStatement stm = null;

		try {
			String consulta = "UPDATE contacto SET nombreContacto = ? , apellidoContacto = ? ,"
					+ " numeroContacto = ? , emailContacto = ? , infoContacto = ?  WHERE idContacto = ?";
			stm = conexion.getConnection().prepareStatement(consulta);

			stm.setString(1, miContacto.getNombreContacto());
			stm.setString(2, miContacto.getApellidoContacto());
			stm.setInt(3, miContacto.getNumeroContacto());
			stm.setString(4, miContacto.getEmailContacto());
			stm.setString(5, miContacto.getInfoContacto());
			stm.setInt(6, miContacto.getIdContacto());

			stm.executeUpdate();
			
			conexion.desconectar();
			stm.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se logro actualizar el contacto");
			e.printStackTrace();
		} finally {
			JOptionPane.showMessageDialog(null, "Su contacto fue actualizado correctamente", "CONTACTO ACTUALIZADO",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public void eliminarContacto(int codigo, String nombreContacto) {
		Conexion conexion = new Conexion();
		PreparedStatement stm = null;

		try {
			String query = "DELETE FROM contacto WHERE idContacto = ?";
			stm = conexion.getConnection().prepareStatement(query);
			stm.setInt(1, codigo);
			stm.execute();
			
			stm.close();
			conexion.desconectar();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se logro eliminar el contacto");
			e.printStackTrace();
		} finally {
			JOptionPane.showMessageDialog(null, "Su contacto " + nombreContacto + " fue eliminado correctamente",
					"CONTACTO ELIMINADO", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static Integer obtenerId(Integer numero) {
		Conexion conexion = new Conexion();
		ContactoVO contacto = new ContactoVO();
		Integer id = null;

		try {

			String query = "SELECT idContacto from contacto WHERE numeroContacto = ? ";
			PreparedStatement stm = conexion.getConnection().prepareStatement(query);
			stm.setInt(1, numero);
			ResultSet res = stm.executeQuery();

			if (res.next()) {
				id = res.getInt("idContacto");
			}

			stm.close();
			res.close();
			conexion.desconectar();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se logro ver los contactos");
			e.printStackTrace();
		}

		return id;

	}

	// AGREGA TODOS LOS CONTACTOS A UNA LISTA
	public static void agregarContactosALista(List<ContactoVO> listaContactos) {

		Conexion conexion = new Conexion();

		try {

			Statement stm = conexion.getConnection().createStatement();
			String query = "SELECT nombreContacto, apellidoContacto, numeroContacto, emailContacto from contacto;";
			ResultSet res = stm.executeQuery(query);
			while (res.next()) {
				// Creacion de objeto contacto para cada iteracion del while
				ContactoVO contacto = new ContactoVO();
				contacto.setNombreContacto(res.getString("nombreContacto"));
				contacto.setApellidoContacto(res.getString("apellidoContacto"));
				contacto.setNumeroContacto(res.getInt("numeroContacto"));
				contacto.setEmailContacto(res.getString("emailContacto"));
				listaContactos.add(contacto);
			}
			Collections.sort(listaContactos);
			stm.close();
			res.close();
			conexion.desconectar();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se logro ver los contactos");
			e.printStackTrace();
		}
	}

	// AGREGA UN SOLO CONTACTO A UNA LISTA DEPENDIENDO DEL ID
	public static void agregarContactoALista(List<ContactoVO> datosContacto, int idSeleccionado) {

		Conexion conexion = new Conexion();
		
		try {
			String query = "SELECT nombreContacto, apellidoContacto, numeroContacto, emailContacto, infoContacto from contacto where idContacto = " + idSeleccionado;
			Statement stm = conexion.getConnection().createStatement();
			ResultSet res = stm.executeQuery(query);
			
			
			if(res.next()) {
				
				ContactoVO contacto = new ContactoVO();
				contacto.setNombreContacto(res.getString("nombreContacto"));
				contacto.setApellidoContacto(res.getString("apellidoContacto"));
				contacto.setNumeroContacto(res.getInt("numeroContacto"));
				contacto.setEmailContacto(res.getString("emailContacto"));
				contacto.setInfoContacto(res.getString("infoContacto"));
				datosContacto.add(contacto);
				
			}
			
			stm.close();
			res.close();
			conexion.desconectar();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se logro cargar los datos del contacto");
			e.printStackTrace();
		}
	}
//
//	public static void main(String args[]) {
//		
//		System.out.println(ContactoDAO.obtenerId(1122517199));
//
//	}

}
