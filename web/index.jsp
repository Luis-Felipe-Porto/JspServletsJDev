<%-- 
    Document   : index
    Created on : 11/06/2020, 16:30:36
    Author     : NATANIELE
--%>
<jsp:useBean id="calcula" class="beans.BeansCursoJsp" type="beans.BeansCursoJsp" scope="page"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="resources/css/style.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%--  <c:out value="${'hello word'}"/>--%>
        <%--<c:import var="data" url="https://www.google.com.br"/>--%>
        <%----%>
        <%--<c:set var="data" scope="page" value="${500*6}"/>

        <c:remove var="data"/>
        <c:out value="${data}"/>
        
        <c:set var="numero" value="${100/2}"/>
        <c:choose>             
            <c:when test="${numero >= 50}">
                <c:out value="${'valor maior que 50'}"/>
            </c:when>
            <c:when test="${numero < 50}">
                <c:out value="${'valor menor que 50'}"/>
            </c:when>
            <c:otherwise>
                <c:out value="${'nao encontrou valor correto'}"/>
            </c:otherwise>
        </c:choose>
        
        <c:set var="numero" value="${100/3}"/>
               <c:forEach var="n" begin="1" end="${numero}">
                   Item: ${n}
                </c:forEach>
        
        <c:forTokens items="Felipe-Porto-Luis" delims="-" var="nome">
            Nome: <c:out value ="${nome}"/>
        </c:forTokens>
        
        <c:url value="/acessoLiberado.jsp" var="acesso">
            <c:param name="para1" value="123"/>
            <c:param name="para2" value="111"/>
        </c:url>
        ${acesso}
        
        <c:set var="numero" value="${100/3}"/>
        <c:if test="${nume > 50}">
            <c:redirect url = "http://www.google.com.br"/>
        </c:if>
        <c:if test="${numero < 50}">
            <c:redirect url="http://www.javaavancado.com"/>
        </c:if>
        --%>
        <div class="middle">
            <div class="title">
                <h2>Login</h2>
            </div>
            <div class="container">

                <form action="Login" method="post">
                    <label for="login">Login:</label>
                    <input type="text" id="login" name="login" required="required">
                    <label for="senha">Senha:</label>
                    <input type="text" id="senha" name="senha" required="required">
                    <input type="submit" value="Login">
                </form>
                <div class="fild-btn">

                </div>
                <a href="#">esqueceu a senha?</a>
            </div>
        </div>

    </body>
</html>
