/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.controlador;

/**
 *
 * @author João Henrique
 */
import com.mycompany.loopparte1.modelo.dao.UsuarioDAO;
import com.mycompany.loopparte1.modelo.entidade.Usuario;
import com.mycompany.loopparte1.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author 10414032675
 */
@WebServlet(WebConstantes.BASE_PATH+"/UsuarioControlador")
public class UsuarioControlador extends HttpServlet{
private UsuarioDAO usuarioDao;
private Usuario usuario;
String nome="";
String email="";
String senha="";
String pais="";
String pontos="";
String idUsuario="";
String opcao="";

    @Override
    public void init() throws ServletException {
       usuarioDao  = new UsuarioDAO();
       usuario = new Usuario();
    }
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String opcao = request.getParameter("opcao");
            idUsuario = request.getParameter("idUsuario");
            nome = request.getParameter("nome");
            email = request.getParameter("email");
            senha = request.getParameter("senha");
            pais = request.getParameter("pais");
            pontos = request.getParameter("pontos");
            if(opcao == null || opcao.isEmpty()){
                opcao="cadastrar";
            }
            switch (opcao) {
                case "cadastrar": cadastrar(request, response);break;
                case "editar": editar(request, response);break;
                case "confirmarEditar": confirmarEditar(request, response);break;
                 case "excluir": excluir(request, response);break;
                case "confirmarExcluir": confirmarExcluir(request, response);break;
                case "cancelar": cancelar(request, response);break;
                default:
                    throw new IllegalArgumentException("Opcao Invalida " +opcao);
            }
            
        }catch(NumberFormatException e){
            response.getWriter().println("Erro: um ou mais parâmetros não são números válidos");
        }catch(IllegalArgumentException e){
            response.getWriter().println("Erro: "+e.getMessage());
        }
    }
    
    private void cadastrar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        validaCampos();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setPais(pais);
        usuario.setPontos(Double.valueOf(pontos));
        usuarioDao.salvar(usuario);
        encaminharParaPagina(request, response);
    }
    
      private void editar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idUsuario", idUsuario);
       request.setAttribute("opcao", "confirmarEditar");
       request.setAttribute("nome", nome);
       request.setAttribute("email", email);
       request.setAttribute("senha", senha);
       request.setAttribute("pais", pais);
       request.setAttribute("pontos", pontos);
       request.setAttribute("mensagem", "Edite os dados e clique em Salvar");
        encaminharParaPagina(request, response);
    }
      
      private void excluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idUsuario", idUsuario);
       request.setAttribute("opcao", "confirmarExcluir");
       request.setAttribute("nome", nome);
       request.setAttribute("email", email);
       request.setAttribute("senha", senha);
       request.setAttribute("pais", pais);
       request.setAttribute("pontos", pontos);
       request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }
      
      private void confirmarEditar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       validaCampos();
       usuario.setIdUsuario(Integer.valueOf(idUsuario));
       usuario.setNome(nome);
       usuario.setEmail(email);
       usuario.setSenha(senha);
       usuario.setPais(pais);
       usuario.setPontos(Double.valueOf(pontos));
       usuarioDao.alterar(usuario);
        cancelar(request, response);
    }
      private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       usuario.setIdUsuario(Integer.valueOf(idUsuario));
       usuario.setNome(nome);
       usuario.setEmail(email);
       usuario.setSenha(senha);
       usuario.setPais(pais);
       usuario.setPontos(Double.valueOf(pontos));
       usuarioDao.excluir(usuario);
        cancelar(request, response);
    }
      private void cancelar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idUsuario", "0");
       request.setAttribute("opcao", "cadastrar");
       request.setAttribute("nome", "");
       request.setAttribute("email", "");
       request.setAttribute("senha", "");
       request.setAttribute("pais", "");
       request.setAttribute("pontos", "");
        encaminharParaPagina(request, response);
    }
      
    
    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        List<Usuario> usuarios = usuarioDao.buscarTodas();
        request.setAttribute("usuarios", usuarios);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroUsuario.jsp");
            dispatcher.forward(request, response);
    }
    
  
    public void validaCampos(){
        if(nome==null || nome.isEmpty() || email == null || email.isEmpty() || senha == null || senha.isEmpty() || pais == null || pais.isEmpty() || pontos == null || pontos.isEmpty()){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
    
    
    
}
