<%-- 
    Document   : CadastroDesenvolvedora
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
        <title>Cadastro Desenvolvedora</title>
    </head>
    
    
    <body>
        <h1>Cadastro Desenvolvedora</h1>
        <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/DesenvolvedoraControlador" method="get">
            <input type="hidden" name="opcao" value="${opcao}" />
            <input type="hidden" name="idDev" value="${idDev}" />
            <p><label>Nome: </label><input type="text" name="nomeDev" value="${nomeDev}" size="40" /> </p>
            <p><label>Descricao: </label><input type="text" name="descricao" value="${descricao}" size="40" /> </p>
            <p><label>CNPJ: </label><input type="text" name="cnpj" value="${cnpj}" size="40" /> </p>
            <td>
                <input type="submit"  value="Salvar" name="Salvar" />
            </td>
            
        </form>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/DesenvolvedoraControlador" method="get">
                <td>
                    <input type="submit"  value="Cancelar" name="Cancelar" />
                </td>
                <input type="hidden" name="opcao" value="${opcao}" />
            </form>
        ${mensagem}

        <table border =" 1">
            <c:if test="${not empty desenvolvedoras}">
                  <tr>
                      <th>Loop Dev ID</th>
                      <th>Nome</th>
                      <th>Descricao</th>
                      <th>CNPJ</th>
                  </tr>
            </c:if>
            <c:forEach var="desenvolvedora" items="${desenvolvedoras}">
                <tr>
                    <td>${desenvolvedora.idDev}</td>
                    <td>${desenvolvedora.nomeDev}</td>
                    <td>${desenvolvedora.descricao}</td>
                    <td>${desenvolvedora.cnpj}</td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/DesenvolvedoraControlador" method="get">
                            <input type="hidden" name="idDev" value="${desenvolvedora.idDev}">
                            <input type="hidden" name="nomeDev" value="${desenvolvedora.nomeDev}">
                            <input type="hidden" name="descricao" value="${desenvolvedora.descricao}">
                            <input type="hidden" name="cnpj" value="${desenvolvedora.cnpj}">
                            <input type="hidden" name="opcao" value="editar">
                            <button type="submit">Editar</button>
                        </form>
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/DesenvolvedoraControlador" method="get">
                           <input type="hidden" name="idDev" value="${desenvolvedora.idDev}">
                            <input type="hidden" name="nomeDev" value="${desenvolvedora.nomeDev}">
                            <input type="hidden" name="descricao" value="${desenvolvedora.descricao}">
                            <input type="hidden" name="cnpj" value="${desenvolvedora.cnpj}">
                            <input type="hidden" name="opcao" value="excluir">
                            <button type="submit">Excluir</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
