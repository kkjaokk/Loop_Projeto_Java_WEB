/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.modelo.dao;

import com.mycompany.loopparte1.modelo.entidade.Avaliacoes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author João Henrique
 */
public class AvaliacoesDAO extends GenericoDAO<Avaliacoes>{
    
        public void salvar(Avaliacoes d){
        String insert = "INSERT INTO AVALIACOES(GAME_ID,USER_ID,NOTA,REVIEW) VALUES (?,?,?,?)";
        save(insert, d.getGame().getIdGame(), d.getUser().getIdUsuario(), d.getNota(), d.getReview());
    }
    
    public void alterar(Avaliacoes d){
        String update = "UPDATE AVALIACOES SET GAME_ID = ?,USER_ID=?,NOTA=?,REVIEW=? WHERE IDAVALIA=?";
        save(update, d.getGame().getIdGame(), d.getUser().getIdUsuario(), d.getNota(), d.getReview(), d.getIdAvalia());
    }
    
    public void excluir(Avaliacoes d){
        String delete = "DELETE FROM AVALIACOES WHERE IDAVALIA = ?";
        save(delete, d.getIdAvalia());
    }
    
    public Avaliacoes buscarPorId(int id){
        String select = "SELECT * FROM AVALIACOES WHERE IDAVALIA=?";
        return buscarPorId(select, new AvaliacoesRowMapper(), id);
    }
    
    public List<Avaliacoes> buscarTodas(){
        String select = "SELECT *  FROM AVALIACOES";
        return buscarTodos(select, new AvaliacoesRowMapper());
    }
    
    public static class AvaliacoesRowMapper implements RowMapper<Avaliacoes>{
        
        GameDAO gameDAO = new GameDAO();
        UsuarioDAO userDAO = new UsuarioDAO();
        
        @Override
        public Avaliacoes mapRow(ResultSet rs) throws SQLException{
            Avaliacoes avalia = new Avaliacoes();
            avalia.setIdAvalia(rs.getInt("IDAVALIA"));
            avalia.setGame(gameDAO.buscarPorId(rs.getInt("GAME_ID")));
            avalia.setUser(userDAO.buscarPorId(rs.getInt("USER_ID")));
            avalia.setNota(rs.getInt("NOTA"));
            avalia.setReview(rs.getString("REVIEW"));
            return avalia;
        }
        
    }
    
}
