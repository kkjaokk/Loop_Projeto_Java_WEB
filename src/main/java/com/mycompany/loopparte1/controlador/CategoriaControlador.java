/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.controlador;


import com.mycompany.loopparte1.modelo.dao.CategoriaDAO;
import com.mycompany.loopparte1.modelo.entidade.Categoria;
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
 * @author Quiqu
 */
@WebServlet(WebConstantes.BASE_PATH+"/CategoriaControlador")
public class CategoriaControlador extends HttpServlet {
    private CategoriaDAO categoriaDao;
    private Categoria categoria;
    String nome="";
    String descricao="";
    String idCategoria="";
    String opcao="";
    
    @Override
    public void init() throws ServletException {
       categoriaDao  = new CategoriaDAO();
       categoria = new Categoria();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String opcao = request.getParameter("opcao");
            idCategoria = request.getParameter("idCategoria");
            nome = request.getParameter("nome");
            descricao = request.getParameter("descricao");
            
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
        categoria.setNome(nome);
        categoria.setDescricao(descricao);
        categoriaDao.salvar(categoria);
        encaminharParaPagina(request, response);
    }
    
    private void editar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idCategoria", idCategoria);
       request.setAttribute("opcao", "confirmarEditar");
       request.setAttribute("nome", nome);
       request.setAttribute("descricao", descricao);
       request.setAttribute("mensagem", "Edite os dados e clique em Salvar");
        encaminharParaPagina(request, response);
    }
    
    private void excluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idCategoria", idCategoria);
       request.setAttribute("opcao", "confirmarExcluir");
       request.setAttribute("nome", nome);
       request.setAttribute("descricao", descricao);
       request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }
    
    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       validaCampos();
       categoria.setIdCategoria(Integer.valueOf(idCategoria));
       categoria.setNome(nome);
       categoria.setDescricao(descricao);
       categoriaDao.alterar(categoria);
        cancelar(request, response);
    }
    
    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       categoria.setIdCategoria(Integer.valueOf(idCategoria));
       categoria.setNome(nome);
       categoria.setDescricao(descricao);
       categoriaDao.alterar(categoria);
       categoriaDao.excluir(categoria);
        cancelar(request, response);
    }
    
    private void cancelar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idCategoria", "0");
       request.setAttribute("opcao", "cadastrar");
       request.setAttribute("nome", "");
       request.setAttribute("descricao", "");
        encaminharParaPagina(request, response);
    }
    
    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        List<Categoria> categorias = categoriaDao.buscarTodas();
        request.setAttribute("categorias", categorias);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroCategorias.jsp");
            dispatcher.forward(request, response);
    }
    
    public void validaCampos(){
        if(nome==null || nome.isEmpty() || descricao == null || descricao.isEmpty()){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
}

