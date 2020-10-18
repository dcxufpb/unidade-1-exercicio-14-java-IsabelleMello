package com.example.project;

public class Endereco {

    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private String municipio;
    private String estado;
    private String cep;

    public Endereco(String logradouro, int numero, String complemento, String bairro, String municipio, String estado, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.municipio = municipio;
        this.estado = estado;
        this.cep = cep;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public int getNumero() {
        return this.numero;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public String getBairro() {
        return this.bairro;
    }

    public String getMunicipio() {
        return this.municipio;
    }

    public String getEstado() {
        return this.estado;
    }

    public String getCep() {
        return this.cep;
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

        if (isEmpty(this.getLogradouro())){
			throw new RuntimeException ("O campo logradouro do endereço é obrigatório");
		}

		if(isEmpty(this.getMunicipio())) {
			throw new RuntimeException("O campo município do endereço é obrigatório");
		}
		
		if(isEmpty(this.getEstado())) {
			throw new RuntimeException("O campo estado do endereço é obrigatório");
		}
    }

    public String dadosEndereco() {

        String _logradouro = this.getLogradouro() + ", ";
		String _numero = (this.getNumero() <= 0)? "s/n" : String.format("%d", this.getNumero());
		String _complemento = isEmpty(this.getComplemento())? "" : " " + this.getComplemento();
		String _bairro = isEmpty(this.getBairro())? "" : this.getBairro() + " - ";
        String _municipio = this.getMunicipio() + " - ";
        String _cep = isEmpty(this.getCep())? "" : "CEP:" + this.getCep();

		
        String retornoEndereco;
        retornoEndereco = _logradouro + _numero + _complemento + Quebra_Linha;
        retornoEndereco += _bairro + _municipio + getEstado() + Quebra_Linha;
        retornoEndereco += _cep;
        return retornoEndereco;

    }
}