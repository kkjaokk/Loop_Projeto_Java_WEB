<%-- 
    Document   : CadastroUsuario
    Created on : 28 de jul de 2024, 19:20:23
    Author     : João Henrique
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Usuario</title>
    </head>
    
    
    <body>
        <h1>Cadastro Usuario</h1>
        <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/UsuarioControlador" method="get">
            <input type="hidden" name="opcao" value="${opcao}" />
            <input type="hidden" name="idUsuario" value="${idUsuario}" />
            <p><label>Nome: </label><input type="text" name="nome" value="${nome}" size="40" /> </p>
            <p><label>Email: </label><input type="text" name="email" value="${email}" size="40" /> </p>
            <p><label>Senha: </label><input type="text" name="senha" value="${senha}" size="40" /> </p>
            <p><label>País: </label><input type="text" name="pais" value="${pais}" size="40" /> </p>
            <p><label>Pontos: </label><input type="text" name="pontos" value="${pontos}" size="40" /> </p>
            <td>
                <input type="submit"  value="Salvar" name="Salvar" />
            </td>
            
        </form>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/UsuarioControlador" method="get">
                <td>
                    <input type="submit"  value="Cancelar" name="Cancelar" />
                </td>
                <input type="hidden" name="opcao" value="${opcao}" />
            </form>
        ${mensagem}

        <table border =" 1">
            <c:if test="${not empty usuarios}">
                  <tr>
                      <th>Loop ID</th>
                      <th>Nome</th>
                      <th>Email</th>
                      <th>Senha</th>
                      <th>País</th>
                      <th>Pontos</th>
                  </tr>
            </c:if>
            <c:forEach var="usuario" items="${usuarios}">
                <tr>
                    <td>${usuario.idUsuario}</td>
                    <td>${usuario.nome}</td>
                    <td>${usuario.email}</td>
                    <td>${usuario.senha}</td>
                    <td>${usuario.pais}</td>
                    <td>${usuario.pontos}</td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/UsuarioControlador" method="get">
                            <input type="hidden" name="idUsuario" value="${usuario.idUsuario}">
                            <input type="hidden" name="nome" value="${usuario.nome}">
                            <input type="hidden" name="email" value="${usuario.email}">
                            <input type="hidden" name="senha" value="${usuario.senha}">
                            <input type="hidden" name="pais" value="${usuario.pais}">
                            <input type="hidden" name="pontos" value="${usuario.pontos}">
                            <input type="hidden" name="opcao" value="editar">
                            <button type="submit">Editar</button>
                        </form>
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/UsuarioControlador" method="get">
                           <input type="hidden" name="idUsuario" value="${usuario.idUsuario}">
                            <input type="hidden" name="nome" value="${usuario.nome}">
                            <input type="hidden" name="email" value="${usuario.email}">
                            <input type="hidden" name="senha" value="${usuario.senha}">
                            <input type="hidden" name="pais" value="${usuario.pais}">
                            <input type="hidden" name="pontos" value="${usuario.pontos}">
                            <input type="hidden" name="opcao" value="excluir">
                            <button type="submit">Excluir</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
