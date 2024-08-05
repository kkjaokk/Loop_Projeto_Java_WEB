/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.modelo.entidade;

/**
 *
 * @author João Henrique
 */
public class Game_Associa_Categoria {
    
    private Integer qtdPlayers;
    private Game gameAssocia = new Game();
    private Categoria categoriaAssocia = new Categoria();

    public Integer getQtdPlayers() {
        return qtdPlayers;
    }

    public void setQtdPlayers(Integer qtdPlayers) {
        this.qtdPlayers = qtdPlayers;
    }

    public Game getGameAssocia() {
        return gameAssocia;
    }

    public void setGameAssocia(Game gameAssocia) {
        this.gameAssocia = gameAssocia;
    }

    public Categoria getCategoriaAssocia() {
        return categoriaAssocia;
    }

    public void setCategoriaAssocia(Categoria categoriaAssocia) {
        this.categoriaAssocia = categoriaAssocia;
    }
    
    
    
}
