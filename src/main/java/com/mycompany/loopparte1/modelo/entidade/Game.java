/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.modelo.entidade;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author João Henrique
 */
public class Game {
    
    private Integer idGame;
    private String titulo, descricao;
    private Double preco;
    private Calendar lancamento;
    private Desenvolvedora gameDev = new Desenvolvedora();

    public Integer getIdGame() {
        return idGame;
    }

    public void setIdGame(Integer idGame) {
        this.idGame = idGame;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Calendar getLancamento() {
        return lancamento;
    }

    public void setLancamento(Calendar lancamento) {
        this.lancamento = lancamento;
    }

    public Desenvolvedora getGameDev() {
        return gameDev;
    }

    public void setGameDev(Desenvolvedora gameDev) {
        this.gameDev = gameDev;
    }
    
    public String getLancamentoFormatado() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(lancamento.getTime());

    }
    
    
}
