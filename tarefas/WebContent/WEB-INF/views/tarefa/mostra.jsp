<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="caelum" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link href="/tarefas/jquery/jquery-ui.css" rel="stylesheet">
  <script src="/tarefas/jquery/external/jquery/jquery.js"></script>
  <script src="/tarefas/jquery/jquery-ui.js"></script>
<title>Tarefas</title>
</head>
<body>
<h3>Alterar tarefa - ${tarefa.id}</h3>
<form action="alteraTarefa" method="post">
<input type="hidden" name="id" value="${tarefa.id}" />
Descrição:<br />
<textarea name="descricao" cols="100" rows="5">
${tarefa.descricao}
</textarea>
<br />
Finalizada? <input type="checkbox" name="finalizado"
value="true" ${tarefa.finalizado? 'checked' : '' }/> <br />
Data de finalização: 
<input type="text" name="dataFinalizacao" value="<fmt:formatDate value="${tarefa.dataFinalizacao.time}" pattern="dd/MM/yyyy" />"/>
<caelum:campoData id="data_Finalizacao" value='<fmt:formatDate value="${tarefa.dataFinalizacao.time}" pattern="dd/MM/yyyy" />'/>
<br />
<input type="submit" value="Alterar"/>
</form>
</body>
</html>