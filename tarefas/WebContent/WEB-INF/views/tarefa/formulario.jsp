<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="caelum" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <link type="text/css" href="resources/css/tarefas.css" rel="stylesheet" />
  <link href="/tarefas/jquery/jquery-ui.css" rel="stylesheet">
  <script src="/tarefas/jquery/external/jquery/jquery.js"></script>
  <script src="/tarefas/jquery/jquery-ui.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tarefas</title>
</head>
<body>
	<h3>Adicionar tarefas</h3>
	<form:errors path="tarefa.descricao"/>
	<form action="adicionaTarefa" method="post">
    <table border="1">
     <tr bgcolor="ffffff">
      <td><font color="000000">Descrição</font></td>
      <td><textarea name="descricao" rows="5" cols="100"></textarea></td>
	 </tr>
     <tr bgcolor="ffffff">
      <td><font color="000000">Finalizada?</font></td>
	  <td><input type="checkbox" name="finalizado" /></td>
	 </tr>
     <tr bgcolor="ffffff">
      <td><font color="000000">Data Finalizacao</font></td>
      <td><caelum:campoData id="dataFinalizacao"/></td>
	 </tr>
    </table>   
	 <input type="submit" value="Adicionar">
	</form>
</body>
</html>