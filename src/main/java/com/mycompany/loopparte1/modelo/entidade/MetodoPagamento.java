/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.modelo.entidade;

/**
 *
 * @author Quiqu
 */
public class MetodoPagamento {
    
    private Integer idPaga;
    private String nomePg, descricao;
    private Double taxa;

    public Integer getIdPaga() {
        return idPaga;
    }

    public void setIdPaga(Integer idPaga) {
        this.idPaga = idPaga;
    }

    public String getNomePg() {
        return nomePg;
    }

    public void setNomePg(String nome) {
        this.nomePg = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getTaxa() {
        return taxa;
    }

    public void setTaxa(Double taxa) {
        this.taxa = taxa;
    }
    
    
    
}
