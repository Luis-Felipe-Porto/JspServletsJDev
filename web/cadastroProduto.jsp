<%-- 
    Document   : acessoNegado
    Created on : 12/06/2020, 16:51:10
    Author     : NATANIELE
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Usuario</title>
        <link rel="stylesheet" href="resources/css/style.css">

        <script src="resources/javascript/jquery.min.js" type="text/javascript"></script>
        <script src="resources/javascript/jquery.maskMoney.min.js" type="text/javascript"></script>
    </head>
    <body>
        <a href="acessoLiberado.jsp">inicio</a>
        <div id="cadastro-usuario">
            <h1>Cadastro</h1>
            <form class="formulario" action="ProdutoServelt" method="post" id="formUser">

                <h3 class="waring">${msg}</h3>
                <h3 class="sucessfull"> ${sucess}</h3>
                <fieldset>

                    <legend>Novo Usuario</legend>
                    <div class="field-group">
                        <div class="field">
                            <label for="id">Codigo:</label>
                            <input type="text" readonly="readonly" id="id" name="id" value="${prd.id}"/>
                        </div>
                        <div class="field">
                            <label for="nome">Nome:</label>
                            <input type="text" id="nome" name="nome" value="${prd.nome}" required="required"/>
                        </div>
                    </div>
                    <div class="field-group">
                        <div class="field">
                            <label for="senha">Valor:</label>
                            <input type="text" id="valor" name="valor" value="${prd.valor}" required="required" data-thousands="." data-decimal=","/>
                        </div>
                        <div class="field">
                            <label for="quantidade">Quantidade:</label>
                            <input type="number" id="quantidade" name="quantidade" value="${prd.quantidade}" required="required" maxlength="5" length="10"/>
                        </div>
                        <div class="field">
                            <label for="categoria">Categoria:</label>
                            <select id="categorias" name="categoria_id">
                                <c:forEach items="${categorias}" var="categoria">
                                    <option value="${categoria.id}" id="${categoria.id}"
                                            <c:if test="${categoria_id == prd.id}">
                                                <c:out value="selected=selected"/>
                                            </c:if>>
                                        ${categoria.nome}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="btn-save">
                        <input type="submit" value="Salvar">
                        <input type="submit" value="Cancelar" onclick="document.getElementById('formUser').action = 'ProdutoServelt?acao=reset'"> 
                    </div>
                </fieldset>
            </form>
        </div>
        <table class="result">
            <div class="header">
                <h2>Nossos Produtos</h2>
            </div>
            <div class="menu-grid produto">
                <!--
                -->
                <c:forEach items="${produtos}" var="prd">
                    <li>
                        <a href="#">
                            <!--<<img src="resources/imagens/icons/money.svg"/> -->
                            <p><c:out value="${prd.id}"></c:out></p>
                                <h1>
                                <c:out value="${prd.nome} "></c:out>
                                </h1>
                                <h3>
                                    R\$:<fmt:formatNumber type="number" value="${prd.valor}"></fmt:formatNumber>
                                </h3>
                                <h3>
                                    Quantidade:<c:out value=" ${prd.quantidade}"></c:out>
                                </h3>
                                <span class="comando-deletar">
                                    <a href="ProdutoServelt?acao=delete&produto=${prd.id}" onclick="return confirm('Confirmar Exclusão ?')">
                                    excluir
                                </a>
                            </span>
                            <div class="comando-editar">
                                <a href="ProdutoServelt?acao=editar&produto=${prd.id}">
                                    editar<span alt="editar" title="editar"></span>
                                </a>
                            </div>
                        </a>



                    </li>  
                </c:forEach>     
            </div>
        </table>                 
    </body>
    <script>
        $(function () {
            $('#valor').maskMoney();
        });
    </script>
</html>
