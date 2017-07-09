package br.com.vanderz.login.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/fj21", "root", "TR!c0lor");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch(ClassNotFoundException e){
			throw new RuntimeException(e);
		}
	}
}
