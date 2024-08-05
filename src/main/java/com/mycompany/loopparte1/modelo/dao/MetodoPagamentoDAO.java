/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.modelo.dao;

/**
 *
 * @author Quiqu
 */

import com.mycompany.loopparte1.modelo.entidade.MetodoPagamento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MetodoPagamentoDAO extends GenericoDAO<MetodoPagamento> {
    
    
    public void salvar(MetodoPagamento metPg){
        String insert = "INSERT INTO METODO_PAGAMENTO(NOME, DESCRICAO, TAXA )VALUES(?,?,?)";
        save(insert, metPg.getNomePg(), metPg.getDescricao(), metPg.getTaxa());
    }
    public void alterar(MetodoPagamento metPg){
        String update = "UPDATE METODO_PAGAMENTO SET NOME=?, DESCRICAO=?, TAXA=? WHERE IDPAGA = ?";
        save(update, metPg.getNomePg(), metPg.getDescricao(), metPg.getTaxa(), metPg.getIdPaga());
    }
    public void excluir(MetodoPagamento metPg){
        String delete = "DELETE FROM METODO_PAGAMENTO WHERE IDPAGA = ?";
        save(delete, metPg.getIdPaga());
   }
    
    public MetodoPagamento buscarPorID(int id){
        String select = "SELECT * FROM METODO_PAGAMENTO WHERE IDPAGA=?";
        return buscarPorId(select, new MetodoPagamentoRowMapper(), id);
    }
     public List<MetodoPagamento> buscarTodas(){
        String select = "SELECT *  FROM METODO_PAGAMENTO";
        return buscarTodos(select, new MetodoPagamentoRowMapper());
    }
    
    
        public static class MetodoPagamentoRowMapper implements RowMapper<MetodoPagamento>{
        
        @Override
        public MetodoPagamento mapRow(ResultSet rs) throws SQLException{
            MetodoPagamento metPg = new MetodoPagamento();
            metPg.setIdPaga(rs.getInt("IDPAGA"));
            metPg.setNomePg(rs.getString("NOME"));
            metPg.setDescricao(rs.getString("DESCRICAO"));
            metPg.setTaxa(rs.getDouble("TAXA"));
            return metPg;
        }
        
    }
    
}
