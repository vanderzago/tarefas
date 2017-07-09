package br.com.vanderz.login.persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.vanderz.login.entities.Usuario;

@Repository
public class UsuarioDAO {
	// a conexão com o banco de dados
	private Connection connection;
	
	@Autowired
	public UsuarioDAO(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}	
	
	
	public List<Usuario> getLista(){
		try {
			PreparedStatement stmt = this.connection.prepareStatement("select * from usuarios");
			ResultSet rs = stmt.executeQuery();
			List<Usuario> usuarios = new ArrayList<Usuario>();
			while (rs.next()) {
				// criando o objeto usuario
				Usuario usuario = new Usuario();
				usuario.setId(rs.getLong("id"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				// montando a data através do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataCriacao"));
				usuario.setDataCriacao(data);
				// adicionando o objeto à lista
				usuarios.add(usuario);
			}
			rs.close();
			stmt.close();
			return usuarios;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Usuario getListaById(Long id){
		try {
			PreparedStatement stmt = this.connection.prepareStatement("select * from usuarios where id = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			Usuario usuario = new Usuario();
			if (rs.next()) {
				// criando o objeto usuario
				usuario.setId(rs.getLong("id"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				// montando a data através do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataCriacao"));
				usuario.setDataCriacao(data);
			}
			rs.close();
			stmt.close();
			return usuario;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void adiciona(Usuario usuario) {
		String sql = "insert into usuarios " +
		"(Login,senha)" +
		" values (?,?)";
		try {
			// prepared statement para inserção
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1,usuario.getLogin());
			stmt.setString(2,usuario.getSenha());
			stmt.setDate(3, new Date(
					usuario.getDataCriacao().getTimeInMillis()));
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void altera(Usuario usuario) {
		String sql = "update usuarios set Login=?, Senha=?, DataCriacao=? where id=?";
		try {
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setString(1, usuario.getLogin());
				stmt.setString(2, usuario.getSenha());
				stmt.setDate(3, new Date(usuario.getDataCriacao().getTimeInMillis()));
				stmt.setLong(4, usuario.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}
	
	public void remove(Long id) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from usuarios where id=?");
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Boolean existeUsuario(Usuario usuario) {
		try {
			Boolean verificaUsuario;
			PreparedStatement stmt = connection.prepareStatement("select * from usuarios where login=?");
			stmt.setString(1, usuario.getLogin());
			ResultSet rs = stmt.executeQuery();
			verificaUsuario = rs.next();
			rs.close();
			stmt.close();
			return verificaUsuario;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
