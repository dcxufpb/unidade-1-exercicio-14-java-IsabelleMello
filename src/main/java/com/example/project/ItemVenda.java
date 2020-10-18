package com.example.project;

public class ItemVenda {
    private int item;
    private Produto produto;
    private int quantidade;

    private String Quebra_Linha = System.lineSeparator();

    public ItemVenda(int item, Produto produto, int quantidade) {
        this.item = item;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public int getItem() {
        return this.item;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public double valorDoItem() {
        return this.quantidade * this.produto.getValorUnitario();
    }

    public String dadosDoItem() {
        return String.format("%d %d %s %d %s %.2f %s %.2f" + Quebra_Linha, 
        this.getItem(), produto.getCodigo(), produto.getDescricao(),
        this.getQuantidade(), produto.getUnidade(), 
        produto.getValorUnitario(), produto.getSubTributario(),
        this.valorDoItem());
    }

}