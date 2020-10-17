package com.example.project;

public class Produto {
    private int codigo;
    private String descricao;
    private String unidade;
    private double valorUnitario;
    private String substituicaoTributaria;

    public Produto(int codigo, String descricao, String unidade, double valorUnitario, String substituicaoTributaria) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        this.unidade = unidade;
        this.substituicaoTributaria = substituicaoTributaria;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getUnidade() {
        return this.unidade;
    }

    public double getValorUnitario() {
        return this.valorUnitario;
    }

    public String getSubTributario() {
        return this.substituicaoTributaria;
    }

}