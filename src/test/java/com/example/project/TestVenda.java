package com.example.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestVenda {

    private void verificarCampoObrigatorio(String mensagemEsperada, Venda venda) {
        try {
            venda.dadosVenda();
        } catch (RuntimeException e) {
            assertEquals(mensagemEsperada, e.getMessage());
        }
    }

    private void validaImpressao(String mensagemEsperada, Venda venda) {
        try {
            venda.imprimeCupom();
        } catch (RuntimeException e) {
            assertEquals(mensagemEsperada, e.getMessage());
        }
    }

    private String NOME_LOJA = "Loja 1";
    private String LOGRADOURO = "Log 1";
    private int NUMERO = 10;
    private String COMPLEMENTO = "C1";
    private String BAIRRO = "Bai 1";
    private String MUNICIPIO = "Mun 1";
    private String ESTADO = "E1";
    private String CEP = "11111-111";
    private String TELEFONE = "(11) 1111-1111";
    private String OBSERVACAO = "Obs 1";
    private String CNPJ = "11.111.111/1111-11";
    private String INSCRICAO_ESTADUAL = "123456789";
    private String DATAHORA = Venda.dataAtual();
    private String CCF_VENDA = "021784";
    private String COO_VENDA = "035804";

    private Produto produto = new Produto(100, "banana", "cx", 7.45, "ST");
    private Produto produto_sem_valor = new Produto(102, "leite", "l", 0, "");

    //validações

    Endereco paramEndereco = new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, MUNICIPIO, ESTADO, CEP);
    Loja paramLoja = new Loja(NOME_LOJA, paramEndereco, TELEFONE, OBSERVACAO, CNPJ, INSCRICAO_ESTADUAL);

    //Mensagens
    
    private String MENSAGEM_VENDA_SEM_ITENS = "Venda sem item";
    private String MENSAGEM_PRODUTO_DUPLICADO = "Produto duplicado";
    private String MENSAGEM_QUANTIDADE = "Item com quantidade zero ou negativa";
    private String MENSAGEM_VALOR_PRODUTO = "Produto com valor unitário zero ou negativo";
        
    //Testes campos obrigatórios

    @Test
	public void validarLoja() {
		Venda validarLoja = new Venda(null, DATAHORA, CCF_VENDA, COO_VENDA);
		verificarCampoObrigatorio("O campo loja deve está preenchida", validarLoja);               
	}
	

    @Test
	public void validarDataHora() {
		Venda validarDataHora = new Venda(paramLoja, "", CCF_VENDA, COO_VENDA);
		verificarCampoObrigatorio("O campo datahora da venda é obrigatório", validarDataHora);               
    }

    @Test
	public void validarCcf() {
		Venda ccfVazio = new Venda(paramLoja, DATAHORA,"", COO_VENDA);
		verificarCampoObrigatorio("O campo CCF da venda é obrigatório", ccfVazio);               
    }

    @Test
	public void validarCoo() {
		Venda cooVazio = new Venda(paramLoja, DATAHORA, CCF_VENDA, "");
		verificarCampoObrigatorio("O campo COO da venda é obrigatório", cooVazio);               
    }
    
    @Test
    public void testeSemItens() {
        Venda venda = paramLoja.vender(DATAHORA, CCF_VENDA, COO_VENDA);
        validaImpressao(MENSAGEM_VENDA_SEM_ITENS, venda);
    }

    @Test
    public void testeMesmoProduto() {
        try{
            Venda venda = paramLoja.vender(DATAHORA, CCF_VENDA, COO_VENDA);
            venda.adicionarItem(1, produto, 2);
            venda.adicionarItem(2, produto, 5);
        } catch(RuntimeException e) {
            assertEquals(MENSAGEM_PRODUTO_DUPLICADO, e.getMessage());
        }
    }

    @Test
    public void TestQuantItem() {
        try{
            Venda venda = paramLoja.vender(DATAHORA, CCF_VENDA, COO_VENDA);
            venda.adicionarItem(0, produto, 4);
        } catch(RuntimeException e) {
            assertEquals(MENSAGEM_QUANTIDADE, e.getMessage());
        }
    }

    @Test
    public void testValorProduto() {
        try{
            Venda venda = paramLoja.vender(DATAHORA, CCF_VENDA, COO_VENDA);
            venda.adicionarItem(4, produto, 3);
            venda.adicionarItem(7, produto_sem_valor, 5);
        } catch(RuntimeException e) {
            assertEquals(MENSAGEM_VALOR_PRODUTO, e.getMessage());
        }
    }    
}