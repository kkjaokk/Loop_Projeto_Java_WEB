/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.modelo.entidade;

/**
 *
 * @author João Henrique
 */
public class Avaliacoes {
    
    private Integer idAvalia, nota;
    private String review;
    private Usuario user = new Usuario();
    private Game game = new Game();

    public Integer getIdAvalia() {
        return idAvalia;
    }

    public void setIdAvalia(Integer idAvalia) {
        this.idAvalia = idAvalia;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    
    
    
}
