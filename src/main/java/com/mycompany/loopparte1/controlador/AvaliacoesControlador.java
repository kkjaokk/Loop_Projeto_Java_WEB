/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.controlador;

import com.mycompany.loopparte1.modelo.dao.AvaliacoesDAO;
import com.mycompany.loopparte1.modelo.dao.GameDAO;
import com.mycompany.loopparte1.modelo.dao.UsuarioDAO;
import com.mycompany.loopparte1.modelo.entidade.Avaliacoes;
import com.mycompany.loopparte1.modelo.entidade.Game;
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
 * @author João Henrique
 */
@WebServlet(WebConstantes.BASE_PATH+"/AvaliacoesControlador")
public class AvaliacoesControlador extends HttpServlet{
    
private AvaliacoesDAO avaliaDAO;
private Avaliacoes avalia;
private Game gameobj;
private GameDAO gameDAO;
private Usuario userobj;
private UsuarioDAO userDAO;
String idAvalia = "";
String game = "";
String user = "";
String nota = "";
String review = "";
String opcao = "";

    @Override
    public void init() throws ServletException {
       userDAO  = new UsuarioDAO();
       userobj = new Usuario();
       gameobj = new Game();
       gameDAO = new GameDAO();
       avalia = new Avaliacoes();
       avaliaDAO = new AvaliacoesDAO();
    }
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            
            opcao = request.getParameter("opcao");
            idAvalia = request.getParameter("idAvalia");
            game = request.getParameter("game");
            user = request.getParameter("user");
            nota = request.getParameter("nota");
            review = request.getParameter("review");
            
            
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
        avalia.getGame().setIdGame(Integer.valueOf(game));
        avalia.getUser().setIdUsuario(Integer.valueOf(user));
        avalia.setNota(Integer.valueOf(nota));
        avalia.setReview(review);
        avaliaDAO.salvar(avalia);
        encaminharParaPagina(request, response);
    }
    
      private void editar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idAvalia", idAvalia);
       request.setAttribute("opcao", "confirmarEditar");
       request.setAttribute("game", game);
       request.setAttribute("user", user);
       request.setAttribute("nota", nota);
       request.setAttribute("review", review);
       request.setAttribute("mensagem", "Edite os dados e clique em Salvar");
        encaminharParaPagina(request, response);
    }
      
      private void excluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idAvalia", idAvalia);
       request.setAttribute("opcao", "confirmarExcluir");
       request.setAttribute("game", game);
       request.setAttribute("user", user);
       request.setAttribute("nota", nota);
       request.setAttribute("review", review);
       request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }
      
      private void confirmarEditar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       validaCampos();
       avalia.setIdAvalia(Integer.valueOf(idAvalia));
       avalia.getGame().setIdGame(Integer.valueOf(game));
       avalia.getUser().setIdUsuario(Integer.valueOf(user));
       avalia.setNota(Integer.valueOf(nota));
       avalia.setReview(review);
       avaliaDAO.alterar(avalia);
       cancelar(request, response);
    }
      private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       avalia.setIdAvalia(Integer.valueOf(idAvalia));
       avalia.getGame().setIdGame(Integer.valueOf(game));
       avalia.getUser().setIdUsuario(Integer.valueOf(user));
       avalia.setNota(Integer.valueOf(nota));
       avalia.setReview(review);
       avaliaDAO.excluir(avalia);
       cancelar(request, response);
    }
      private void cancelar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idAvalia", "0");
       request.setAttribute("opcao", "cadastrar");
       request.setAttribute("game", "");
       request.setAttribute("user", "");
       request.setAttribute("nota", "");
       request.setAttribute("review", "");
        encaminharParaPagina(request, response);
    }
      
    
    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        
        List<Avaliacoes> avaliacoes = avaliaDAO.buscarTodas();
        request.setAttribute("avaliacoes", avaliacoes);
        
        List<Game> games = gameDAO.buscarTodas();
        request.setAttribute("games", games);
        
        List<Usuario> usuarios = userDAO.buscarTodas();
        request.setAttribute("usuarios", usuarios);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroAvaliacoes.jsp");
        dispatcher.forward(request, response);
    }
    
  
    public void validaCampos(){
        if(game==null || game.isEmpty()
                || user == null || user.isEmpty()
                || nota == null || nota.isEmpty()
                || review == null || review.isEmpty()
            ){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
    
}
