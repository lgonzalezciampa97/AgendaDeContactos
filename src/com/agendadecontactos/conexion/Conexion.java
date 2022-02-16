package com.agendadecontactos.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	static String db = "agenda_contactos";
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost/" + db;
	static String user = "root";
	static String pass = "mysql21";

	Connection conexion = null;

	public Conexion() {

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conexion = DriverManager.getConnection(url, user, pass);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return conexion;
	}

	public void desconectar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	public static void main (String args []) {
//		Conexion nuevaConexion = new Conexion ();
//		nuevaConexion.desconectar();
//	}

}
