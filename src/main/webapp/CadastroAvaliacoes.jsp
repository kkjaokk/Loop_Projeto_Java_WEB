<%-- 
    Document   : CadastroAvaliacoes
    Created on : 4 de ago de 2024, 22:19:09
    Author     : João Henrique
--%>

<%@page import="java.util.List"%>
<%@page import="com.mycompany.loopparte1.modelo.entidade.Avaliacoes"%>
<%@page import="com.mycompany.loopparte1.modelo.dao.AvaliacoesDAO"%>
<%@page import="com.mycompany.loopparte1.modelo.dao.UsuarioDAO"%>
<%@page import="com.mycompany.loopparte1.modelo.dao.GameDAO"%>
<%@page import="com.mycompany.loopparte1.modelo.entidade.Usuario"%>
<%@page import="com.mycompany.loopparte1.modelo.entidade.Game"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Avaliacao</title>
    </head>
    
    
    <body>
        <h1>Cadastro Avaliacao</h1>
        <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/AvaliacoesControlador" method="get">
            <input type="hidden" name="opcao" value="${opcao}" />
            <input type="hidden" name="idAvalia" value="${idAvalia}" />
             <p><label>Usuario Avaliador:</label>
                    <select name="user">
                     <c:forEach var="usuario" items="${usuarios}">
                         <c:choose> 
                            
                            <c:when test="${usuario.idUsuario eq user}">
                                <option selected value="${usuario.idUsuario}">${usuario.nome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${usuario.idUsuario}">${usuario.nome}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                </p>
                
                
                 <p><label>Game Avaliado: </label>
                    <select name="game">
                     <c:forEach var="gameobj" items="${games}">
                         <c:choose> 
                            
                            <c:when test="${gameobj.idGame eq game}">
                                <option selected value="${gameobj.idGame}">${gameobj.titulo}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${gameobj.idGame}">${gameobj.titulo}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                </p>
                
            <p><label>Nota: </label><input type="number" name="nota" value="${nota}" size="40" /> </p>
            <p><label>Review: </label><input type="text" name="review" value="${review}" size="40" /> </p>
            
            <td>
                <input type="submit"  value="Salvar" name="Salvar" />
            </td>
            
        </form>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/AvaliacoesControlador" method="get">
                <td>
                    <input type="submit"  value="Cancelar" name="Cancelar" />
                </td>
                <input type="hidden" name="opcao" value="${opcao}" />
            </form>
        ${mensagem}

        <table border =" 1">
            <c:if test="${not empty games}">
                  <tr>
                      <th>Loop Avaliacao ID</th>
                      <th>Usuario Avaliador</th>
                      <th>Game Avaliado</th>
                      <th>Nota</th>
                      <th>Review</th>
                      <th>Alterar</th>
                      <th>Excluir</th>
                  </tr>
            </c:if>
            <c:forEach var="avalia" items="${avaliacoes}">
                <tr>
                    <td>${avalia.idAvalia}</td>
                    <td>${avalia.user.nome}</td>
                    <td>${avalia.game.titulo}</td>
                    <td>${avalia.nota}</td>
                    <td>${avalia.review}</td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/AvaliacoesControlador" method="get">
                            <input type="hidden" name="idAvalia" value="${avalia.idAvalia}">
                            <input type="hidden" name="user" value="${avalia.user.idUsuario}">
                            <input type="hidden" name="game" value="${avalia.game.idGame}">
                            <input type="hidden" name="nota" value="${avalia.nota}">
                            <input type="hidden" name="review" value="${avalia.review}">
                            <input type="hidden" name="opcao" value="editar">
                            <button type="submit">Editar</button>
                        </form>
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/AvaliacoesControlador" method="get">
                            <input type="hidden" name="idAvalia" value="${avalia.idAvalia}">
                            <input type="hidden" name="user" value="${avalia.user.idUsuario}">
                            <input type="hidden" name="game" value="${avalia.game.idGame}">
                            <input type="hidden" name="nota" value="${avalia.nota}">
                            <input type="hidden" name="review" value="${avalia.review}">
                            <input type="hidden" name="opcao" value="excluir">
                            <button type="submit">Excluir</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
