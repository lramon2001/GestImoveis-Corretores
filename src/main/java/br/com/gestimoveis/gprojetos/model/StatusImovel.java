package br.com.gestimoveis.gprojetos.model;

public enum StatusImovel {

    DISPONIVEL("DISPONIVEL"),
    INDISPONIVEL("INDISPONIVEL"),
    VENDIDO("VENDIDO");

    private String descricao;

    private StatusImovel(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }



    public void setDescricao(String descricao) {
        this.descricao = descricao;
    } 

    
    
}
