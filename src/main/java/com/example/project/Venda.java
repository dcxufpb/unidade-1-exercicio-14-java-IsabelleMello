package com.example.project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private Loja loja;
    private String datahora;
    private String ccf;
    private String coo;
    private List<ItemVenda> itens = new ArrayList<ItemVenda>();

    public Venda(Loja loja, String datahora, String ccf, String coo) {
        this.loja = loja;
        this.datahora = datahora;
        this.ccf = ccf;
        this.coo = coo;
	}

	public Loja getLoja() {
        return this.loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public String getDataHora(){
        return this.datahora;
    }

    public void setDataHora(String datahora) {
        this.datahora = datahora;
    }

    public String getCcf() {
        return this.ccf;
    }

    public void setCcf(String ccf) {
        this.ccf = ccf;
    }

    public String getCoo() {
        return this.coo;
    }

    public void setCoo(String coo){
        this.coo = coo;
    }

    public List<ItemVenda> getItens() {
        return this.itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public static String dataAtual(){
        //Data e hora atuais
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = formatterData.format(now);
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormatada = formatterHora.format(now);

        return dataFormatada + " " + horaFormatada + "V";
    }

    public void verificarCampoObrigatorioVenda(){
        if(this.loja == null) {
            throw new RuntimeException("O campo loja deve está preenchida");
        }
        if(this.datahora == null){
            throw new RuntimeException("O campo hora e data são obrigatórios");
        }

        if(this.ccf == "") {
            throw new RuntimeException("O campo CCF é obrigatório");
        }

        if(this.coo == "") {
            throw new RuntimeException("O campo COO é obrigatório");
        }
    }

    public String Quebra_linha = System.lineSeparator();

    public void verificaItem(int item, Produto produto, int quantidade) {

        if(quantidade <= 0) {
            throw new RuntimeException("Venda sem itens");
        }

        for(ItemVenda itemV: this.itens) {
            
            if(itemV.getItem() != item && itemV.getProduto().getCodigo() == produto.getCodigo()) {
                throw new RuntimeException("Produto duplicado");
            }
            
            if(itemV.getItem() <= 0) {
                throw new RuntimeException("Item de Venda com quantidade zero ou negativa");
            }
        }

        if(produto.getValorUnitario() <= 0) {
            throw new RuntimeException("Produto com valor unitário zero ou negativo");
        }
    }

    public void adicionarItem(int item, Produto produto, int quantidade) {
        verificaItem(item, produto, quantidade);
        ItemVenda itemDaVenda = new ItemVenda(item, produto, quantidade);
        this.itens.add(itemDaVenda);
    }

    public String dadosDoItem(){
        StringBuilder dados = new StringBuilder("ITEM CODIGO DESCRICAO QTD UN VL UNIT(R$) ST VL ITEM(R$)" + Quebra_linha);
        for(ItemVenda itemV: this.itens) {
            dados.append(itemV.dadosDoItem());
        }
        return dados.toString();
    }

    public double calcularValorTotal() {
        double total = 0;
        for(ItemVenda itemV: this.itens) {
            total += itemV.valorDoItem();
        }
        return total;
    }

    public String dadosVenda() {

        String _ccf = "CCF:" + this.ccf + " ";
        String _coo = "COO: " + this.coo;

        return dataAtual()+ _ccf + _coo;
        
    }

    public String imprimeCupom(){
        
        String loja = this.loja.dadosLoja();
        String venda = this.dadosVenda();
        String dadosDoItem = this.dadosDoItem();
        double valorTotal = this.calcularValorTotal();

        return (loja + Quebra_linha + "--------------------" + Quebra_linha + venda + "   CUPOM FISCAL   " +
        Quebra_linha + dadosDoItem + "--------------------" + Quebra_linha + String.format("%.2f", valorTotal));

    }

}