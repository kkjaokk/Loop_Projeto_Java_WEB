/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.modelo.dao;

import com.mycompany.loopparte1.modelo.entidade.Game;
import com.mycompany.loopparte1.servico.ConverteData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author João Henrique
 */
public class GameDAO  extends GenericoDAO<Game>{
    
    public void salvar(Game d){
        String insert = "INSERT INTO GAME(TITULO,DESCRICAO,PRECO,LANCAMENTO,DESENVOLVEDORA_IDDEV) VALUES (?,?,?,?,?)";
        save(insert, d.getTitulo(), d.getDescricao(), d.getPreco(), d.getLancamento(), d.getGameDev().getIdDev());
    }
    
    public void alterar(Game d){
        String update = "UPDATE GAME SET TITULO = ?,DESCRICAO=?,PRECO=?,LANCAMENTO=?,DESENVOLVEDORA_IDDEV=? WHERE IDGAME=?";
        save(update, d.getTitulo(), d.getDescricao(), d.getPreco(), d.getLancamento(), d.getGameDev().getIdDev(), d.getIdGame());
    }
    
    public void excluir(Game d){
        String delete = "DELETE FROM GAME WHERE IDGAME = ?";
        save(delete, d.getIdGame());
    }
    
    public Game buscarPorId(int id){
        String select = "SELECT * FROM GAME WHERE IDGAME=?";
        return buscarPorId(select, new GameRowMapper(), id);
    }
    
    public List<Game> buscarTodas(){
        String select = "SELECT *  FROM GAME";
        return buscarTodos(select, new GameRowMapper());
    }
    
    public static class GameRowMapper implements RowMapper<Game>{
        
        ConverteData converte = new ConverteData();
        DesenvolvedoraDAO desenvolvedoraDAO = new DesenvolvedoraDAO();
        
        @Override
        public Game mapRow(ResultSet rs) throws SQLException{
            Game game = new Game();
            game.setIdGame(rs.getInt("IDGAME"));
            game.setTitulo(rs.getString("TITULO"));
            game.setDescricao(rs.getString("DESCRICAO"));
            game.setPreco(rs.getDouble("PRECO"));
            game.setLancamento(converte.converteCalendario(rs.getDate("LANCAMENTO")));
            game.setGameDev(desenvolvedoraDAO.buscarPorId(rs.getInt("DESENVOLVEDORA_IDDEV")));
            return game;
        }
        
    }
    
}
