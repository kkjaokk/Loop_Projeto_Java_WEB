<%-- 
    Document   : CadastroMetodosPagamentos
    Created on : 29 de jul. de 2024, 19:31:37
    Author     : Quiqu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Metodos de Pagamento</title>
    </head>
    <body>
        <h1>Cadastro de Metodos de Pagamento!</h1>
        <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/MetodoPagamentoControlador" method="get">
            <input type="hidden" name="opcao" value="${opcao}" />
            <input type="hidden" name="idPaga" value="${idPaga}" />
            <p><label>Nome: </label><input type="text" name="nomePg" value="${nomePg}" size="20" /> </p>
            <p><label>Descrição: </label><input type="text" name="descricao" value="${descricao}" size="70" /> </p>
            <p><label>Taxa: </label><input type="text" name="taxa" value="${taxa}" size="20" /> </p>
            <td>
                <input type="submit"  value="Salvar" name="Salvar" />
            </td>
        </form>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/MetodoPagamentoControlador" method="get">
                <td>
                    <input type="submit"  value="Cancelar" name="Cancelar" />
                </td>
                <input type="hidden" name="opcao" value="${opcao}" />
            </form>
        ${mensagem}
        
        <table border="1">
            <c:if test="${not empty metodosPagamento}">
                <tr>
                      <th>ID</th>
                      <th>Nome</th>
                      <th>Descricao</th>
                      <th>Taxa</th>
                  </tr>
            </c:if>
            <c:forEach var="metodoPagamento" items="${metodosPagamento}">
                <tr>
                    <td>${metodoPagamento.idPaga}</td>
                    <td>${metodoPagamento.nomePg}</td>
                    <td>${metodoPagamento.descricao}</td>
                    <td>${metodoPagamento.taxa}</td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/MetodoPagamentoControlador" method="get">
                            <input type="hidden" name="idPaga" value="${metodoPagamento.idPaga}">
                            <input type="hidden" name="nomePg" value="${metodoPagamento.nomePg}">
                            <input type="hidden" name="descricao" value="${metodoPagamento.descricao}">
                            <input type="hidden" name="taxa" value="${metodoPagamento.taxa}">
                            <input type="hidden" name="opcao" value="editar">
                            <button type="submit">Editar</button>
                        </form>
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/MetodoPagamentoControlador" method="get">
                           <input type="hidden" name="idPaga" value="${metodoPagamento.idPaga}">
                            <input type="hidden" name="nomePg" value="${metodoPagamento.nomePg}">
                            <input type="hidden" name="descricao" value="${metodoPagamento.descricao}">
                            <input type="hidden" name="taxa" value="${metodoPagamento.taxa}">
                            <input type="hidden" name="opcao" value="excluir">
                            <button type="submit">Excluir</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
