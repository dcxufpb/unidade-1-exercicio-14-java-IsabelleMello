package com.example.project;

import java.util.ArrayList;
import java.util.List;

public class Loja {

    private String nomeLoja;
    private Endereco endereco;
    private String telefone;
    private String observacao;
    private String cnpj;
    private String inscricaoEstadual;
    private List<Venda> vendas = new ArrayList<Venda>();

    public Loja(String nomeLoja, Endereco endereco, String telefone,
                String observacao, String cnpj, String inscricaoEstadual) {
        this.nomeLoja = nomeLoja;
        this.endereco = endereco;
        this.telefone = telefone;
        this.observacao = observacao;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getNomeLoja() {
        return this.nomeLoja;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public String getObservacao() {
        return this.observacao;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getInscricaoEstadual() {
        return this.inscricaoEstadual;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Venda> getVendas() {
        return this.vendas;
    }

    public void setVendas(Venda venda) {
        this.vendas.add(venda);
    }

    public Venda vender(String datahora, String ccf, String coo) {
        Venda venda = new Venda(this, datahora, ccf, coo);
        this.setVendas(venda);
        return venda;
    }
    
    public static boolean isEmpty(String input){
		if (input == null){
			return true;
		}
		
		if (input.equals("")){
			return true;
		}

		else{
			return false;
		}
	}

    public static String Quebra_Linha = System.lineSeparator();

    public void validarCamposObrigatorio(){
        
        if (isEmpty(this.getNomeLoja())){
			throw new RuntimeException ("O campo nome da loja é obrigatório");
        }
        
        if(isEmpty(this.getCnpj())){
            throw new RuntimeException("O campo cnpj da loja é obrigatório");
        }

        if(isEmpty(this.getInscricaoEstadual())){
            throw new RuntimeException("O campo inscrição estadual da loja é obrigatório");
        }
}
    public String dadosLoja() {

        String _telefone = isEmpty(getTelefone())? "" : "Tel " + getTelefone();
        _telefone = (!_telefone.isEmpty() && !isEmpty(endereco.getCep()))? " " + _telefone : _telefone;
		
		String _observacao = isEmpty(getObservacao())? "" : getObservacao();				
		String _cnpj = "CNPJ: " + getCnpj();
        String _inscricao_estadual = "IE: " + getInscricaoEstadual();
        

        String retornoLoja = getNomeLoja() + Quebra_Linha;
        retornoLoja += endereco.dadosEndereco();
        retornoLoja += _telefone + Quebra_Linha;
		retornoLoja += _observacao + Quebra_Linha;
		retornoLoja += _cnpj + Quebra_Linha;
        retornoLoja += _inscricao_estadual + Quebra_Linha;   
        
        return retornoLoja;

    }

}