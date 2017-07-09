<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="id" required="true" %>
<%@ attribute name="value" required="false" %>
<input id="${id}" name="${id}" value="${value}"/>
<script>
$("#${id}").datepicker({dateFormat: 'dd/mm/yy'});
</script>