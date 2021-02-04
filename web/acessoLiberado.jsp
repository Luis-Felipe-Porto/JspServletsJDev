<%-- 
    Document   : acessoLiberado
    Created on : 12/06/2020, 16:50:54
    Author     : NATANIELE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/style.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="header">
            <h2>Seja bem vindo ao sistema em JSP</h2>
        </div>
        <div class="menu-grid">
            <li>
                <a href="#">
                    <img src="resources/imagens/icons/money.svg"/>
                    <p>Financeiro</p>
                </a>
            </li>
            <li id="2">
                <a href="#">
                    <img src="resources/imagens/icons/chart.svg"/>
                    <p>Planejamentos</p>
                </a>
            </li>
            <li id="3">
                <a href="#">
                    <img src="resources/imagens/icons/gears.svg"/>
                    <p>Configurações</p>
                </a>
            </li>
            <li id="4">
                <a href="ProdutoServelt">
                    <img src="resources/imagens/icons/report.svg"/>
                    <p>Realatórios</p>
                </a>
            </li>
            <li id="5">
                <a href="#">
                    <img src="resources/imagens/icons/team.svg"/>
                    <p>Colaboradores</p>
                </a>
            </li>
            <li id="6">
                <a href="salvarUsuario?acao=listarTodos">
                    <img src="resources/imagens/icons/add.svg"/>
                    <p>Cadastro</p>
                </a>
            </li>
            <li id="7">
                <a href="#">
                    <img src="resources/imagens/icons/support.svg"/>
                    <p>Call Center</p>
                </a>
            </li>

        </div>
    </body>
</html>
