/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.modelo.dao;

/**
 *
 * @author João Henrique
 */
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tulio
 */


public interface RowMapper<T> {
    T mapRow(ResultSet rs) throws SQLException;
}

