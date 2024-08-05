<%-- 
    Document   : CadastroGame
    Created on : 4 de ago de 2024, 12:23:12
    Author     : João Henrique
--%>

<%@page import="java.util.List"%>
<%@page import="com.mycompany.loopparte1.modelo.dao.DesenvolvedoraDAO"%>
<%@page import="com.mycompany.loopparte1.modelo.dao.GameDAO"%>
<%@page import="com.mycompany.loopparte1.modelo.entidade.Desenvolvedora"%>
<%@page import="com.mycompany.loopparte1.modelo.entidade.Game"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Game</title>
    </head>
    
    
    <body>
        <h1>Cadastro Game</h1>
        <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/GameControlador" method="get">
            <input type="hidden" name="opcao" value="${opcao}" />
            <input type="hidden" name="idGame" value="${idGame}" />
            <p><label>Titulo: </label><input type="text" name="titulo" value="${titulo}" size="40" /> </p>
            <p><label>Descricao: </label><input type="text" name="descricao" value="${descricao}" size="40" /> </p>
            <p><label>Preco: </label><input type="number" name="preco" value="${preco}" size="40" /> </p>
            <p><label>Lancamento: </label><input type="date" name="lancamento" value="${lancamento}" size="40" /> </p>
            
            <p><label>Desenvolvedora:</label>
                    <select name="gameDev">
                     <c:forEach var="desenvolvedora" items="${desenvolvedoras}">
                         <c:choose> 
                            
                            <c:when test="${desenvolvedora.idDev eq gameDev}">
                                <option selected value="${desenvolvedora.idDev}">${desenvolvedora.nomeDev}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${desenvolvedora.idDev}">${desenvolvedora.nomeDev}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                </p>
            
            <td>
                <input type="submit"  value="Salvar" name="Salvar" />
            </td>
            
        </form>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/GameControlador" method="get">
                <td>
                    <input type="submit"  value="Cancelar" name="Cancelar" />
                </td>
                <input type="hidden" name="opcao" value="${opcao}" />
            </form>
        ${mensagem}

        <table border =" 1">
            <c:if test="${not empty games}">
                  <tr>
                      <th>Loop Game ID</th>
                      <th>Titulo</th>
                      <th>Descricao</th>
                      <th>Preco</th>
                      <th>Lancamento</th>
                      <th>Desenvolvedora</th>
                      <th>Alterar</th>
                      <th>Excluir</th>
                  </tr>
            </c:if>
            <c:forEach var="game" items="${games}">
                <tr>
                    <td>${game.idGame}</td>
                    <td>${game.titulo}</td>
                    <td>${game.descricao}</td>
                    <td>${game.preco}</td>
                    <td>${game.lancamentoFormatado}</td>
                    <td>${game.gameDev.nomeDev}</td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/GameControlador" method="get">
                            <input type="hidden" name="idGame" value="${game.idGame}">
                            <input type="hidden" name="titulo" value="${game.titulo}">
                            <input type="hidden" name="descricao" value="${game.descricao}">
                            <input type="hidden" name="preco" value="${game.preco}">
                            <input type="hidden" name="lancamento" value="${game.lancamentoFormatado}">
                            <input type="hidden" name="gameDev" value="${game.gameDev.idDev}">
                            <input type="hidden" name="opcao" value="editar">
                            <button type="submit">Editar</button>
                        </form>
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/GameControlador" method="get">
                            <input type="hidden" name="idGame" value="${game.idGame}">
                            <input type="hidden" name="titulo" value="${game.titulo}">
                            <input type="hidden" name="descricao" value="${game.descricao}">
                            <input type="hidden" name="preco" value="${game.preco}">
                            <input type="hidden" name="lancamento" value="${game.lancamentoFormatado}">
                            <input type="hidden" name="gameDev" value="${game.gameDev.idDev}">
                            <input type="hidden" name="opcao" value="excluir">
                            <button type="submit">Excluir</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
