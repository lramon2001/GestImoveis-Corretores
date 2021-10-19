package br.com.gestimoveis.gprojetos.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ManyToAny;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import br.com.gestimoveis.gprojetos.model.Endereço;

@Entity
public class Empreendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min=3,max=80)
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Column(nullable = false)
    private String construtora;

    @NotNull
    @Column(nullable = false)
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    private BigDecimal valorDoImovel;

    @NotNull
    @Column(nullable = false)
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    private BigDecimal comissao;

    @NotNull
    @Column(nullable = false)
    private String tipoDoImovel;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusImovel status;


    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Valid
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id_fk", nullable = false)
    private Endereço endereco;
    
    @ManyToMany
    @JoinTable(
        name = "empreendimento_cliente",
        joinColumns = @JoinColumn(name = "projeto_id_fk"),
        inverseJoinColumns = @JoinColumn(name="cliente_id_fk")
    )
    private List<Cliente> clientes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConstrutora() {
        return construtora;
    }

    public void setConstrutora(String construtora) {
        this.construtora = construtora;
    }

    public BigDecimal getValorDoImovel() {
        return valorDoImovel;
    }

    public void setValorDoImovel(BigDecimal valorDoImovel) {
        this.valorDoImovel = valorDoImovel;
    }

    public BigDecimal getComissao() {
        return comissao;
    }

    public void setComissao(BigDecimal comissao) {
        this.comissao = comissao;
    }

    public String getTipoDoImovel() {
        return tipoDoImovel;
    }

    public void setTipoDoImovel(String tipoDoImovel) {
        this.tipoDoImovel = tipoDoImovel;
    }

    public StatusImovel getStatus() {
        return status;
    }

    public void setStatus(StatusImovel status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Endereço getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereço endereco) {
        this.endereco = endereco;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    
}
