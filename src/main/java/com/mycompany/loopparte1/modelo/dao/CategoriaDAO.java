/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.modelo.dao;

import com.mycompany.loopparte1.modelo.entidade.Categoria;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Quiqu
 */
public class CategoriaDAO extends GenericoDAO <Categoria> {
    public void salvar(Categoria cat){
        String insert = "INSERT INTO CATEGORIA(NOME, DESCRICAO) VALUES (?,?)";
        save(insert, cat.getNome(), cat.getDescricao());
    }
    public void alterar(Categoria cat){
        String update = "UPDATE CATEGORIA SET NOME = ?,DESCRICAO=? WHERE IDCATEGORIA=?";
        save(update, cat.getNome(), cat.getDescricao(), cat.getIdCategoria());
    }
    public void excluir(Categoria cat){
        String delete = "DELETE FROM CATEGORIA WHERE IDCATEGORIA = ?";
        save(delete, cat.getIdCategoria());
    }
    public Categoria buscarPorID(int id){
        String select = "SELECT FROM CATEGORIA WHERE IDCATEGORIA = ?";
        return buscarPorId(select, new CategoriaRowMapper(), id);
    }
    
    public List<Categoria> buscarTodas(){
        String select = "SELECT *  FROM CATEGORIA";
        return buscarTodos(select, new CategoriaRowMapper());
    }
    
    public static class CategoriaRowMapper implements RowMapper<Categoria>{
        @Override
        public Categoria mapRow(ResultSet rs) throws SQLException{
            Categoria cate = new Categoria();
            cate.setIdCategoria(rs.getInt("IDCATEGORIA"));
            cate.setNome(rs.getString("NOME"));
            cate.setDescricao(rs.getString("DESCRICAO"));
            return cate;
        }
    }
}    
