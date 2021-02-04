<%-- 
    Document   : CadastroUsuario
    Created on : 16/06/2020, 15:59:37
    Author     : NATANIELE
--%>

<%@page import="beans.BeansCursoJsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Usuario</title>
        <link rel="stylesheet" href="resources/css/style.css">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"
                integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>

    </head>
    <body>
        <div id="cadastro-usuario">
            <h1>Cadastro</h1>
            <form class="formulario" action="salvarUsuario" method="post" id="formUser" enctype="multipart/form-data">

                <h3 class="waring">${msg}</h3>
                <h3 class="sucessfull"> ${sucess}</h3>
                <fieldset>
                    <legend>Novo Usuario</legend>
                    <div class="field-group">
                        <div class="field">
                            <label for="id">Codigo:</label>
                            <input type="text" readonly="readonly" id="id" name="id" value="${user.id}"/>
                        </div>
                        <div class="field">
                            <label for="login">Login:</label>
                            <input type="text" id="login" name="login" value="${user.login}" />
                        </div>
                    </div>
                    <div class="field-group">
                        <div class="field">
                            <label for="nome">Nome:</label>
                            <input type="text" id="nome" name="nome" value="${user.nome}" />
                        </div>
                        <div class="field">
                            <label for="senha">Senha:</label>
                            <input type="password" id="senha" name="senha" value="${user.senha}" />
                        </div>
                    </div>
                    <div class="field-group">

                        <div class="field">
                            <label for="cep">CEP:</label>
                            <input type="text" id="cep" name="cep" value="${user.cep}" />
                            <label for="rua">Rua:</label>
                            <input type="text" id="rua" name="rua" value="${user.rua}" />
                            <label for="bairro">Bairro:</label>
                            <input type="text" id="bairro" name="bairro" value="${user.bairro}" />
                        </div>
                        <div class="field">
                            <label for="cidade">Cidade:</label>
                            <input type="text" id="cidade" name="cidade" value="${user.cidade}" />
                            <label for="uf">Estado:</label>
                            <input type="text" id="uf" name="uf" value="${user.estado}" />
                            <label for="ibge">IBGE:</label>
                            <input type="text" id="ibge" name="ibge" value="${user.ibge}" />
                            <label for="ativo">Ativo:</label>
                            <input type="checkbox" id="ativo" name="ativo" width="50px"
                                   <%
                                       if (request.getAttribute("user") != null) {
                                           BeansCursoJsp user = (BeansCursoJsp) request.getAttribute("user");
                                           if (user.isAtivo()) {
                                               out.print(" ");
                                               out.print("checked=\"checked\"");
                                               out.print(" ");
                                           }
                                       }
                                   %> 
                                   />
                            <label for="masc">Masculino:<input type="radio" id="masc"  name="sexo" value="masculino"
                                                               <%
                                                                   if (request.getAttribute("user") != null) {
                                                                       BeansCursoJsp user = (BeansCursoJsp) request.getAttribute("user");
                                                                       if (user.getSexo().equalsIgnoreCase("masculino")) {
                                                                           out.print(" ");
                                                                           out.print("checked=\"checked\"");
                                                                           out.print(" ");
                                                                       }
                                                                   }
                                                               %> 
                                                               ></label>

                            <label for="fem">Feminino:<input type="radio"  id="fem" name="sexo" value="feminino"
                                                             <%
                                                                 if (request.getAttribute("user") != null) {
                                                                     BeansCursoJsp user = (BeansCursoJsp) request.getAttribute("user");
                                                                     if (user.getSexo().equalsIgnoreCase("feminino")) {
                                                                         out.print(" ");
                                                                         out.print("checked=\"checked\"");
                                                                         out.print(" ");
                                                                     }
                                                                 }
                                                             %>
                                                             ></label>

                        </div>
                        <div class="field">
                            <label for="cidade">Foto:</label>
                            <input type="file" id="foto" name="foto">
                            <input type="text" readonly="readonly" name="fotoTemp" value="${user.fotoBase64}" style="display: none"/>
                            <input type="text" readonly="readonly" name="contentTypeTemp" value="${user.contentType}" style="display: none"/>
                            <label for="cidade">Curriculo:</label>
                            <input type="file" id="curriculo" name="curriculo"value="curriculo"/>
                            <label for="cidade">Perfil:</label>
                            <select id="perfil"name="perfil">
                                <option value="nao_informado">[--Selecione--]</option>

                                <option value="administrador"
                                        <%
                                            if (request.getAttribute("user") != null) {
                                                BeansCursoJsp user = (BeansCursoJsp) request.getAttribute("user");
                                                if (user.getPerfil().equalsIgnoreCase("administrador")) {
                                                    out.print(" ");
                                                    out.print("selected=\"selected\"");
                                                    out.print(" ");
                                                }
                                            }
                                        %>
                                        >Administrador</option>

                                <option value="secretario"
                                        <%
                                            if (request.getAttribute("user") != null) {
                                                BeansCursoJsp user = (BeansCursoJsp) request.getAttribute("user");
                                                if (user.getPerfil().equalsIgnoreCase("secretario")) {
                                                    out.print(" ");
                                                    out.print("selected=\"selected\"");
                                                    out.print(" ");
                                                }
                                            }
                                        %>
                                        >Secretário(a)</option>

                                <option value="gerente"
                                        <%
                                            if (request.getAttribute("user") != null) {
                                                BeansCursoJsp user = (BeansCursoJsp) request.getAttribute("user");
                                                if (user.getPerfil().equalsIgnoreCase("gerente")) {
                                                    out.print(" ");
                                                    out.print("selected=\"selected\"");
                                                    out.print(" ");
                                                }
                                            }
                                        %>
                                        >Gerente</option>
                                <option value="funcionario"
                                        <%
                                            if (request.getAttribute("user") != null) {
                                                BeansCursoJsp user = (BeansCursoJsp) request.getAttribute("user");
                                                if (user.getPerfil().equalsIgnoreCase("funcionario")) {
                                                    out.print(" ");
                                                    out.print("selected=\"selected\"");
                                                    out.print(" ");
                                                }
                                            }
                                        %>
                                        >Funcionário</option>
                            </select>
                        </div>
                    </div>
                    <div class="btn-save">
                        <input type="submit" value="Salvar">
                        <input type="submit" value="Cancelar" onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'"> 
                    </div>
                </fieldset>
            </form>
               <form class="formulario" method="post" action="PesquisaServelt">
                <div class="field">
                    <label for="senha">Pesquisar:</label>
                    <input type="text" id="pesquisar" name="pesquisar"  placeholder="Pesquisar"/>
                </div>
                 <div class="btn-save">
                        <input type="submit" value="Pesquisar">
                    </div>
            </form>

            <table class="result">

                <tr>

                    <th>Id</th>
                    <th>Foto</th>
                    <th>Nome</th>
                    <th>Rua</th>
                    <th>Bairro</th>
                    <th>Cidade</th>
                    <th>Estado</th>
                    <th>IBGE</th>
                    <th>CEP</th>

                    <th class="comando-table">Deletar</th>
                    <th class="comando-table">Editar</th>
                    <th class="comando-table">Telefone</th>

                </tr>
                <c:forEach items="${usuarios}" var="user">
                    <tr class="data">
                        <td>
                            <c:if test="${user.fotoBase64Miniatura != null}">
                                <a href="salvarUsuario?acao=download&tipo=imagem&user=${user.id}">
                                    <img src='<c:out value="${user.fotoBase64Miniatura}"></c:out>' height="50px" width="50px"></a>
                                </c:if>
                                <c:if test="${user.fotoBase64Miniatura == null}">
                                <img src="resources/imagens/icons/userPadrao.png" height="40px" width="40px">
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${user.curriculoBase64.isEmpty() == false}">
                                <a href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id}">
                                    <img src="resources/imagens/icons/pdf.jpg" height="50px" width="50px" alt="baixar pdf"></a>
                                </c:if>

                            <c:if test="${user.curriculoBase64 == null}">
                                <img src="resources/imagens/icons/pdf.jpg" height="50px" width="50px" alt="baixar pdf" onclick="alert('nao possui curriculo')">
                            </c:if>
                            <c:if test="${user.curriculoBase64.isEmpty() == true}">                             
                                <img src="resources/imagens/icons/pdf.jpg" height="50px" width="50px" alt="baixar pdf">
                            </c:if>

                        </td>
                        <td>
                        <td>
                            <c:out value="${user.id}"></c:out> 
                            </td>
                            <td >
                            <c:out value="${user.login}"></c:out> 
                            </td>
                            <td>
                            <c:out value="${user.nome}"></c:out> 
                            </td>
                            <td>
                            <c:out value="${user.rua}"></c:out> 
                            </td>
                            <td>
                            <c:out value="${user.bairro}"></c:out> 
                            </td>
                            <td>
                            <c:out value="${user.cidade}"></c:out> 
                            </td>
                            <td>
                            <c:out value="${user.estado}"></c:out> 
                            </td>
                            <td>
                            <c:out value="${user.ibge}"></c:out> 
                            </td>
                            <td>
                            <c:out value="${user.cep}"></c:out> 
                            </td>

                            <td class="comando-deletar">
                                <a href="salvarUsuario?acao=delete&user=${user.id}"
                               onclick="return confirm('Confirmar Exclusão ?');">
                                <span alt="excluir" title="excluir"></span>
                            </a>
                        </td>
                        <td class="comando-editar">
                            <a href="salvarUsuario?acao=editar&user=${user.id}">
                                <span alt="editar" title="editar"></span>
                            </a>
                        </td>
                        <td class="comando-telefone">
                            <a href="SalvarTelefone?acao=addTelefone&user=${user.id}">
                                <span alt="telefone" title="telefone"></span>
                            </a>
                        </td>
                    </tr> 
                </c:forEach>
            </table>
        </div>
        <script>

            $(document).ready(function () {

                function limpa_formulário_cep() {
                    // Limpa valores do formulário de cep.
                    $("#rua").val("");
                    $("#bairro").val("");
                    $("#cidade").val("");
                    $("#uf").val("");
                    $("#ibge").val("");
                }

                //Quando o campo cep perde o foco.
                $("#cep").blur(function () {

                    //Nova variável "cep" somente com dígitos.
                    var cep = $(this).val().replace(/\D/g, '');

                    //Verifica se campo cep possui valor informado.
                    if (cep != "") {

                        //Expressão regular para validar o CEP.
                        var validacep = /^[0-9]{8}$/;

                        //Valida o formato do CEP.
                        if (validacep.test(cep)) {

                            //Preenche os campos com "..." enquanto consulta webservice.
                            $("#rua").val("...");
                            $("#bairro").val("...");
                            $("#cidade").val("...");
                            $("#uf").val("...");
                            $("#ibge").val("...");

                            //Consulta o webservice viacep.com.br/
                            $.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?", function (dados) {

                                if (!("erro" in dados)) {
                                    //Atualiza os campos com os valores da consulta.
                                    $("#rua").val(dados.logradouro);
                                    $("#bairro").val(dados.bairro);
                                    $("#cidade").val(dados.localidade);
                                    $("#uf").val(dados.uf);
                                    $("#ibge").val(dados.ibge);
                                } //end if.
                                else {
                                    //CEP pesquisado não foi encontrado.
                                    limpa_formulário_cep();
                                    alert("CEP não encontrado.");
                                }
                            });
                        } //end if.
                        else {
                            //cep é inválido.
                            limpa_formulário_cep();
                            alert("Formato de CEP inválido.");
                        }
                    } //end if.
                    else {
                        //cep sem valor, limpa formulário.
                        limpa_formulário_cep();
                    }
                });
            });
        </script>
    </body>

</html>
