/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.controlador;

/**
 *
 * @author João Henrique
 */
import com.mycompany.loopparte1.modelo.dao.DesenvolvedoraDAO;
import com.mycompany.loopparte1.modelo.entidade.Desenvolvedora;
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
@WebServlet(WebConstantes.BASE_PATH+"/DesenvolvedoraControlador")
public class DesenvolvedoraControlador extends HttpServlet{
private DesenvolvedoraDAO desenvolvedoraDao;
private Desenvolvedora desenvolvedora;
String nomeDev="";
String descricao="";
String cnpj="";
String idDev="";
String opcao="";

    @Override
    public void init() throws ServletException {
       desenvolvedoraDao  = new DesenvolvedoraDAO();
       desenvolvedora = new Desenvolvedora();
    }
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String opcao = request.getParameter("opcao");
            idDev = request.getParameter("idDev");
            nomeDev = request.getParameter("nomeDev");
            descricao = request.getParameter("descricao");
            cnpj = request.getParameter("cnpj");
            
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
        desenvolvedora.setNomeDev(nomeDev);
        desenvolvedora.setDescricao(descricao);
        desenvolvedora.setCnpj(cnpj);
        desenvolvedoraDao.salvar(desenvolvedora);
        encaminharParaPagina(request, response);
    }
    
      private void editar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idDev", idDev);
       request.setAttribute("opcao", "confirmarEditar");
       request.setAttribute("nomeDev", nomeDev);
       request.setAttribute("descricao", descricao);
       request.setAttribute("cnpj", cnpj);
       request.setAttribute("mensagem", "Edite os dados e clique em Salvar");
        encaminharParaPagina(request, response);
    }
      
      private void excluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idDev", idDev);
       request.setAttribute("opcao", "confirmarExcluir");
       request.setAttribute("nomeDev", nomeDev);
       request.setAttribute("descricao", descricao);
       request.setAttribute("cnpj", cnpj);
       request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }
      
      private void confirmarEditar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       validaCampos();
       desenvolvedora.setIdDev(Integer.valueOf(idDev));
       desenvolvedora.setNomeDev(nomeDev);
       desenvolvedora.setDescricao(descricao);
       desenvolvedora.setCnpj(cnpj);
       desenvolvedoraDao.alterar(desenvolvedora);
        cancelar(request, response);
    }
      private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       desenvolvedora.setIdDev(Integer.valueOf(idDev));
       desenvolvedora.setNomeDev(nomeDev);
       desenvolvedora.setDescricao(descricao);
       desenvolvedora.setCnpj(cnpj);
       desenvolvedoraDao.alterar(desenvolvedora);
       desenvolvedoraDao.excluir(desenvolvedora);
        cancelar(request, response);
    }
      private void cancelar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idDev", "0");
       request.setAttribute("opcao", "cadastrar");
       request.setAttribute("nomeDev", "");
       request.setAttribute("descricao", "");
       request.setAttribute("cnpj", "");
        encaminharParaPagina(request, response);
    }
      
    
    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        List<Desenvolvedora> desenvolvedoras = desenvolvedoraDao.buscarTodas();
        request.setAttribute("desenvolvedoras", desenvolvedoras);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroDesenvolvedora.jsp");
            dispatcher.forward(request, response);
    }
    
  
    public void validaCampos(){
        if(nomeDev==null || nomeDev.isEmpty() || descricao == null || descricao.isEmpty() || cnpj == null || cnpj.isEmpty()){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
    
    
    
}
