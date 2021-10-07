package br.com.gestimoveis.gprojetos.model;

public enum StatusCliente {
    
    AGUARDANDO_CONTATO("Aguardando Contato"),
    SEM_SIMULACAO("Sem Simulação"),
    EM_SIMULACAO("Em Simulção"),
    SIMULACAO_PRONTA("Simulação pronta"),
    NEGADO("Negado"),
    EM_APROVACAO("Em aprovação"),
    APROVADO("Aprovado"),
    VENDA_CONCLUIDA("Venda Concluída");

    private String descricao; 

    
    private StatusCliente(String descricao) {
        this.descricao = descricao;
    }


    public String getDescricao() {
        return descricao;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    

    

    

    
}
