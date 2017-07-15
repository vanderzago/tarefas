package br.com.vanderz.tarefas.persistencia;

import java.util.List;

import br.com.vanderz.tarefas.entities.Tarefa;

public interface TarefaDAO {
	Tarefa buscaPorId(Long id);
	List<Tarefa> lista();
	void adiciona(Tarefa t);
	void altera(Tarefa t);
	void remove(Tarefa t);
	void finaliza(Long id);
}
