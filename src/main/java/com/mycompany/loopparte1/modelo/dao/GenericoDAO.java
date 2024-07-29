/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.modelo.dao;

/**
 *
 * @author João Henrique
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class GenericoDAO<T> {
    private  ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
/*Método para inserir,atualizar e excluir registros no banco de dados. Ele aceita uma string SQL e um número variável 
 de parâmetros que são usados para preencher a consulta. O método trata exceções, gerencia recursos e fornece 
    feedback sobre o sucesso da operação.*/
    public void save(String comandoSql, Object... parametros) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = connectionFactory.getConnection();
            pstmt = con.prepareStatement(comandoSql);
            for (int i = 0; i < parametros.length; i++) {
                if (parametros[i] instanceof Calendar) {
                    Calendar calendar = (Calendar) parametros[i];
                    pstmt.setTimestamp(i + 1, new java.sql.Timestamp(calendar.getTimeInMillis()));
                } else {
                    pstmt.setObject(i + 1, parametros[i]);
                }
            }
            if (pstmt.executeUpdate() > 0) {
                System.out.println("SAVE SUCESSO!");
            } else {
                System.out.println("SAVE NÃO EXECUTOU!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
/*public List<T> buscarTodos(String comandoSql, RowMapper<T> rowMapper): Método para recuperar uma lista 
    de objetos de um tipo específico (T) do banco de dados. Utiliza uma interface RowMapper para mapear
    linhas do ResultSet para objetos de tipo T.*/
    public List<T> buscarTodos(String comandoSql, RowMapper<T> rowMapper) {
        List<T> entidades = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = connectionFactory.getConnection();
            pstmt = con.prepareStatement(comandoSql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                entidades.add(rowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return entidades;
    }
/*Método para buscar um único registro pelo ID ou outros critérios, retornando um objeto do tipo T. 
    Similar ao método save, ele aceita uma consulta SQL e parâmetros que preenchem a consulta.*/
    public T buscarPorId(String comandoSql, RowMapper<T> rowMapper, Object... parametros) {
        T entidade = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = connectionFactory.getConnection();
            pstmt = con.prepareStatement(comandoSql);
            for (int i = 0; i < parametros.length; i++) {
                pstmt.setObject(i + 1, parametros[i]);
            }
            rs = pstmt.executeQuery();
            if (rs.next()) {
                entidade = rowMapper.mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return entidade;
    }
}
