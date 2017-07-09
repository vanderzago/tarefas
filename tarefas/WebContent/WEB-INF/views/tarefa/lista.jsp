<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tarefas</title>
</head>
<body>
<script type="text/javascript">
function finalizaAgora(id) {
	$.post("finalizaTarefa", {'id' : id}, function(resposta) {
		$("#tarefa_"+id).html(resposta);
	});
}

function removeAgora(id) {
	$.post("removeTarefa", {'id' : id}, function() {
		// selecionando o elemento html através da
		// ID e alterando o HTML dele
		$(this).closest("tr").hide();
	});
}

</script>
<a href="novaTarefa">Criar nova tarefa</a>
<br /> <br />
<table>
<tr>
 <th>Id</th>
 <th>Descrição</th>
 <th>Finalizado?</th>
 <th>Data de finalização</th>
</tr>
<c:forEach items="${tarefas}" var="tarefa">
<tr id="tarefa_${tarefa.id}">
 <td>${tarefa.id}</td>
 <td>${tarefa.descricao}</td>
  <c:if test="${tarefa.finalizado eq false}">
	<td>
	 <a href="#" onClick="finalizaAgora(${tarefa.id})">Finalizar agora!</a>
	</td>
  </c:if>
  <c:if test="${tarefa.finalizado eq true}">
   <td>Finalizada</td>
  </c:if>
 <td>
  <fmt:formatDate value="${tarefa.dataFinalizacao.time}" pattern="dd/MM/yyyy"/>
 </td>
 <td><a href="#" onClick="removeAgora(${tarefa.id})">Remover</a></td>
 <td><a href="mostraTarefa?id=${tarefa.id}">Alterar</a></td>
</tr>
</c:forEach>
</table>
<a href="logout">Sair do sistema</a>
</body>
</html>