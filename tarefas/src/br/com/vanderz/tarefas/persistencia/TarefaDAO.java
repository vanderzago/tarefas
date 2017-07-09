package br.com.vanderz.tarefas.persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.vanderz.tarefas.entities.Tarefa;

public class TarefaDAO {
	// a conexão com o banco de dados
	private Connection connection;
	
	public TarefaDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public TarefaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<Tarefa> getLista(){
		try {
			PreparedStatement stmt = this.connection.prepareStatement("select * from tarefas");
			ResultSet rs = stmt.executeQuery();
			List<Tarefa> tarefas = new ArrayList<Tarefa>();
			while (rs.next()) {
				// criando o objeto tarefa
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getLong("id"));
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setFinalizado(rs.getBoolean("finalizado"));
				// montando a data através do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataFinalizacao"));
				tarefa.setDataFinalizacao(data);
				// adicionando o objeto à lista
				tarefas.add(tarefa);
			}
			rs.close();
			stmt.close();
			return tarefas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Tarefa getListaById(Long id){
		try {
			PreparedStatement stmt = this.connection.prepareStatement("select * from tarefas where id = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			Tarefa tarefa = new Tarefa();
			if (rs.next()) {
				// criando o objeto tarefa
				tarefa.setId(rs.getLong("id"));
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setFinalizado(rs.getBoolean("finalizado"));
				// montando a data através do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataFinalizacao"));
				tarefa.setDataFinalizacao(data);
			}
			rs.close();
			stmt.close();
			return tarefa;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void adiciona(Tarefa tarefa) {
		String sql = "insert into tarefas " +
		"(Descricao,finalizado,dataFinalizacao)" +
		" values (?,?,?)";
		try {
			// prepared statement para inserção
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1,tarefa.getDescricao());
			stmt.setBoolean(2,tarefa.isFinalizado());
			stmt.setDate(3, new Date(
					tarefa.getDataFinalizacao().getTimeInMillis()));
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void altera(Tarefa tarefa) {
		String sql = "update tarefas set Descricao=?, Finalizado=?, DataFinalizacao=? where id=?";
		try {
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setString(1, tarefa.getDescricao());
				stmt.setBoolean(2, tarefa.isFinalizado());
				stmt.setDate(3, new Date(tarefa.getDataFinalizacao().getTimeInMillis()));
				stmt.setLong(4, tarefa.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}
	
	public void remove(Long id) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from tarefas where id=?");
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void finaliza(Long id){
		try {
			PreparedStatement stmt = connection.prepareStatement("update tarefas set Finalizado=true, dataFinalizacao = now() where id=?");
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
