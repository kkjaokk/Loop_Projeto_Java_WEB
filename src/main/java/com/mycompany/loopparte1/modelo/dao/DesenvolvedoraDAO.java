/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.modelo.dao;

/**
 *
 * @author João Henrique
 */
import com.mycompany.loopparte1.modelo.entidade.Desenvolvedora;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 10414032675
 */
public class DesenvolvedoraDAO extends GenericoDAO<Desenvolvedora>{
    public void salvar(Desenvolvedora d){
        String insert = "INSERT INTO DESENVOLVEDORA(NOME,DESCRICAO,CNPJ) VALUES (?,?,?)";
        save(insert, d.getNomeDev(), d.getDescricao(), d.getCnpj());
    }
    
    public void alterar(Desenvolvedora d){
        String update = "UPDATE DESENVOLVEDORA SET NOME = ?,DESCRICAO=?,CNPJ=? WHERE IDDEV=?";
        save(update, d.getNomeDev(), d.getDescricao(), d.getCnpj(), d.getIdDev());
    }
    
    public void excluir(Desenvolvedora d){
        String delete = "DELETE FROM DESENVOLVEDORA WHERE IDDEV = ?";
        save(delete, d.getIdDev());
    }
    
    public Desenvolvedora buscarPorId(int id){
        String select = "SELECT * FROM DESENVOLVEDORA WHERE IDDEV=?";
        return buscarPorId(select, new DesenvolvedoraRowMapper(), id);
    }
    
    public List<Desenvolvedora> buscarTodas(){
        String select = "SELECT *  FROM DESENVOLVEDORA";
        return buscarTodos(select, new DesenvolvedoraRowMapper());
    }
    
    public static class DesenvolvedoraRowMapper implements RowMapper<Desenvolvedora>{
        
        @Override
        public Desenvolvedora mapRow(ResultSet rs) throws SQLException{
            Desenvolvedora dev = new Desenvolvedora();
            dev.setIdDev(rs.getInt("IDDEV"));
            dev.setNomeDev(rs.getString("NOME"));
            dev.setDescricao(rs.getString("DESCRICAO"));
            dev.setCnpj(rs.getString("CNPJ"));
            return dev;
        }
        
    }
    
}
