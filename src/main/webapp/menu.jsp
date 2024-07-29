<%-- 
    Document   : menu
    Created on : 28 de jul de 2024, 17:19:41
    Author     : João Henrique
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/estilo.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Loop</title>
    </head>
    <body>
        <nav>
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/index.html"> Home</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}${URL_BASE}/UsuarioControlador?opcao=cancelar"> Usuario</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}${URL_BASE}/DesenvolvedoraControlador?opcao=cancelar"> Desenvolvedora</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}${URL_BASE}/MetodoPagamentoControlador?opcao=cancelar"> Metodo Pagamento</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}${URL_BASE}/CategoriaControlador?opcao=cancelar"> Categoria</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/login.jsp"> Login</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}${URL_BASE}/LogoutControlador"> Logout</a>
                </li>
            </ul>
        </nav>
    </body>
</html>
