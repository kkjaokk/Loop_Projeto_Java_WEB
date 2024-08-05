<%-- 
    Document   : CadastroCategorias
    Created on : 29 de jul. de 2024, 19:31:07
    Author     : Quiqu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Categorias</title>
    </head>
    <body>
        <h1>Cadastro Categorias</h1>
        <form id="cadFrom" name="cadForm" action="${pageContext.request.contextPath}${URL_BASE}/CategoriaControlador" method="get">
            
            <input type="hidden" name="opcao" value="${opcao}"/>
            <input type="hidden" name="idCategoria" value="${idCategoria}" />
             <p><label>Nome: </label><input type="text" name="nome" value="${nome}" size="20" /></p>
             <p><label>Descrição: </label><input  type="text"  name="descricao" value="${descricao}" size="70"></p>
             <td>
                 <input type="submit" value="Salvar" name="Salvar" />
             </td>
        </form>
             <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/CategoriaControlador" method="get">
                 <td>
                     <input type="submit" value="Cancelar" name="Cancelar"/>
                 </td>
                 <input type="hidden" name="opcao" value="${opcao}" />
             </form>
             ${mensagem}
             <table border="1">
                 <c:if test="${not empty categorias}">
                     <tr>
                         <th>ID Categoria</th>
                         <th>Nome</th>
                         <th>Descrição</th>
                     </tr>
                 </c:if>
                 <c:forEach var="categoria" items="${categorias}">
                     <tr>
                         <td>${categoria.idCategoria}</td>
                         <td>${categoria.nome}</td>
                         <td>${categoria.descricao}</td>
                         <td>
                             <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/CategoriaControlador" method="get">
                            <input type="hidden" name="idCategoria" value="${categoria.idCategoria}">
                            <input type="hidden" name="nome" value="${categoria.nome}">
                            <input type="hidden" name="descricao" value="${categoria.descricao}">
                            <input type="hidden" name="opcao" value="editar">
                            <button type="submit">Editar</button>
                        </form>
                         </td>
                         <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/CategoriaControlador" method="get">
                           <input type="hidden" name="idCategoria" value="${categoria.idCategoria}">
                            <input type="hidden" name="nome" value="${categoria.nome}">
                            <input type="hidden" name="descricao" value="${categoria.descricao}">
                            <input type="hidden" name="opcao" value="excluir">
                            <button type="submit">Excluir</button>
                        </form>
                    </td>
                     </tr>
                 </c:forEach>   
             </table>
    </body>
</html>
