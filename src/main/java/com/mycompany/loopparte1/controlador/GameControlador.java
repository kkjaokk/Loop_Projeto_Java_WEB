/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.controlador;

import com.mycompany.loopparte1.modelo.dao.DesenvolvedoraDAO;
import com.mycompany.loopparte1.modelo.dao.GameDAO;
import com.mycompany.loopparte1.modelo.entidade.Desenvolvedora;
import com.mycompany.loopparte1.modelo.entidade.Game;
import com.mycompany.loopparte1.servico.ConverteData;
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

@WebServlet(WebConstantes.BASE_PATH+"/GameControlador")
public class GameControlador extends HttpServlet{
    
private GameDAO gameDAO;
private Game game;
private Desenvolvedora desenvolvedora;
private DesenvolvedoraDAO desenvolvedoraDAO;
String idGame = "";
String titulo = "";
String descricao = "";
String preco = "";
String lancamento = "";
String gameDev = "";
String opcao = "";
private ConverteData converte = new ConverteData();

    @Override
    public void init() throws ServletException {
       desenvolvedoraDAO  = new DesenvolvedoraDAO();
       desenvolvedora = new Desenvolvedora();
       game = new Game();
       gameDAO = new GameDAO();
    }
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            opcao = request.getParameter("opcao");
            idGame = request.getParameter("idGame");
            titulo = request.getParameter("titulo");
            descricao = request.getParameter("descricao");
            preco = request.getParameter("preco");
            lancamento = request.getParameter("lancamento");
            gameDev = request.getParameter("gameDev");
            
            
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
        game.setTitulo(titulo);
        game.setDescricao(descricao);
        game.setPreco(Double.valueOf(preco));
        game.setLancamento(converte.converteCalendario(lancamento));
        game.getGameDev().setIdDev(Integer.valueOf(gameDev));
        gameDAO.salvar(game);
        encaminharParaPagina(request, response);
    }
    
      private void editar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idGame", idGame);
       request.setAttribute("opcao", "confirmarEditar");
       request.setAttribute("titulo", titulo);
       request.setAttribute("descricao", descricao);
       request.setAttribute("preco", preco);
       request.setAttribute("lancamento", ConverteData.convertDateFormat(lancamento));
       request.setAttribute("gameDev", gameDev);
       request.setAttribute("mensagem", "Edite os dados e clique em Salvar");
        encaminharParaPagina(request, response);
    }
      
      private void excluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idGame", idGame);
       request.setAttribute("opcao", "confirmarExcluir");
       request.setAttribute("titulo", titulo);
       request.setAttribute("descricao", descricao);
       request.setAttribute("preco", preco);
       request.setAttribute("lancamento", ConverteData.convertDateFormat(lancamento));
       request.setAttribute("gameDev", gameDev);
       request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }
      
      private void confirmarEditar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       validaCampos();
       game.setIdGame(Integer.valueOf(idGame));
       game.setTitulo(titulo);
       game.setDescricao(descricao);
       game.setPreco(Double.valueOf(preco));
       game.setLancamento(converte.converteCalendario(lancamento));
       game.getGameDev().setIdDev(Integer.valueOf(gameDev));
       gameDAO.alterar(game);
       cancelar(request, response);
    }
      private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       game.setIdGame(Integer.valueOf(idGame));
       game.setTitulo(titulo);
       game.setDescricao(descricao);
       game.setPreco(Double.valueOf(preco));
       game.setLancamento(converte.converteCalendario(lancamento));
       game.getGameDev().setIdDev(Integer.valueOf(gameDev));
       gameDAO.excluir(game);
       cancelar(request, response);
    }
      private void cancelar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idGame", "0");
       request.setAttribute("opcao", "cadastrar");
       request.setAttribute("titulo", "");
       request.setAttribute("descricao", "");
       request.setAttribute("preco", "");
       request.setAttribute("lancamento", "");
       request.setAttribute("gameDev", "");
        encaminharParaPagina(request, response);
    }
      
    
    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        
        List<Game> games = gameDAO.buscarTodas();
        request.setAttribute("games", games);
        
        List<Desenvolvedora> desenvolvedoras = desenvolvedoraDAO.buscarTodas();
        request.setAttribute("desenvolvedoras", desenvolvedoras);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroGame.jsp");
        dispatcher.forward(request, response);
    }
    
  
    public void validaCampos(){
        if(titulo==null || titulo.isEmpty()
                || descricao == null || descricao.isEmpty()
                || preco == null || preco.isEmpty()
                || lancamento == null || lancamento.isEmpty()
                || gameDev == null || gameDev.isEmpty()
            ){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
    
}
