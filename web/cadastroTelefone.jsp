<%-- 
    Document   : CadastroUsuario
    Created on : 16/06/2020, 15:59:37
    Author     : NATANIELE
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Usuario</title>
        <link rel="stylesheet" href="resources/css/style.css">

    </head>
    <body>
        <div id="cadastro-usuario">
            <h1>Cadastro</h1>
            <form class="formulario" action="SalvarTelefone" method="post" id="formUser">

                <h3 class="waring">${msg}</h3>
                <h3 class="sucessfull"> ${sucess}</h3>
                <fieldset>
                    <legend>Novo Telefone</legend>
                    <div class="field-group">
                        <div class="field">
                            <label for="id">Codigo:</label>
                            <input type="text" readonly="readonly" id="id" name="id" value="${userEscolhido.id}" />
                        </div>
                        <div class="field">
                            <label for="id">User:</label>
                            <input type="text" readonly="readonly" id="nome" name="nome" value="${userEscolhido.nome}"/>
                        </div>

                    </div>
                    <div class="field-group">
                        <div class="field">
                            <label for="tipo">Tipo:</label>
                            <select id="tipo" name="tipo"required="required">
                                <option>Celular</option>
                                <option>Residencial</option> 
                                <option>Corporativo</option> 
                            </select>
                        </div>
                        <div class="field">
                            <label for="telefone">Telefone:</label>
                            <input type="text" id="telefone" name="telefone" required="required" />
                        </div>
                    </div>

                    <div class="btn-save">
                        <input type="submit" value="Salvar">
                       
                    </div>
                </fieldset>
            </form>

            <table class="result">

                <tr>
                    <th>Id</th>
                    <th>Tipo</th>
                    <th>Telefone</th>

                    <th class="comando-table">Deletar</th>

                </tr>
                <c:forEach items="${telefones}" var="fone">
                    <tr class="data">
                        <td>
                            <c:out value="${fone.id}"></c:out> 
                            </td>
                            <td >
                            <c:out value="${fone.tipo}"></c:out> 
                            </td>

                            <td >
                            <c:out value="${fone.numero}"></c:out> 
                            </td>
                            <td class="comando-deletar">
                                <a href="SalvarTelefone?acao=deleteFone&userFone=${fone.id}
                               onclick="return confirm('Confirmar Exclusão ?');">
                                <span alt="excluir" title="excluir"></span>
                            </a>
                        </td>

                    </tr> 
                </c:forEach>


            </table>
        </div>

    </body>

</html>
