package br.com.gestimoveis.gprojetos.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.com.gestimoveis.gprojetos.model.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=3,max=80)
    @Column(nullable = false, length = 80)
    private String nome;

    @NotNull
    @CPF
    @Column(nullable = false,unique = true)
    private String cpf;

    @NotNull
    @Column(nullable = false)
    private String rg;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    @NotNull
    @Column(nullable = false)
    private Double renda;

    @NotNull
    @Column(nullable = false)
    private String telefone;

    @NotNull
    @Past
    @Column(nullable = false, name="data_nascimento")
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate dataNascimento;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusCliente status;

    @NotNull
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    private Integer dependentes;

    @NotNull
    @Column(nullable = false)
    private Double fgts;

   
    private String observacoes;

    @Valid
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id_fk", nullable = false)
    private Endereço endereco;

    @ManyToMany(mappedBy = "clientes")
    private List<Empreendimento> empreendimentos; 

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Double getRenda() {
        return renda;
    }

    public void setRenda(Double renda) {
        this.renda = renda;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

   
    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDependentes() {
        return dependentes;
    }

    public void setDependentes(Integer dependentes) {
        this.dependentes = dependentes;
    }

    public Double getFgts() {
        return fgts;
    }

    public void setFgts(Double fgts) {
        this.fgts = fgts;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

   
    public StatusCliente getStatus() {
        return status;
    }

    public void setStatus(StatusCliente status) {
        this.status = status;
    }

    public Endereço getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereço endereco) {
        this.endereco = endereco;
    }

    

    public List<Empreendimento> getEmpreendimentos() {
        return empreendimentos;
    }

    public void setEmpreendimentos(List<Empreendimento> empreendimentos) {
        this.empreendimentos = empreendimentos;
    }

    public String getTelefoneSemFormato(){
       String brasil = "55";
        
       String telefone_sem_formato= this.telefone.replace("(","").replace(")", "").replace("-","").replace(" ","");
        return brasil + telefone_sem_formato;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
        result = prime * result + ((dependentes == null) ? 0 : dependentes.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((empreendimentos == null) ? 0 : empreendimentos.hashCode());
        result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
        result = prime * result + ((estadoCivil == null) ? 0 : estadoCivil.hashCode());
        result = prime * result + ((fgts == null) ? 0 : fgts.hashCode());
        result = prime * result + ((genero == null) ? 0 : genero.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((observacoes == null) ? 0 : observacoes.hashCode());
        result = prime * result + ((renda == null) ? 0 : renda.hashCode());
        result = prime * result + ((rg == null) ? 0 : rg.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        if (dataNascimento == null) {
            if (other.dataNascimento != null)
                return false;
        } else if (!dataNascimento.equals(other.dataNascimento))
            return false;
        if (dependentes == null) {
            if (other.dependentes != null)
                return false;
        } else if (!dependentes.equals(other.dependentes))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (empreendimentos == null) {
            if (other.empreendimentos != null)
                return false;
        } else if (!empreendimentos.equals(other.empreendimentos))
            return false;
        if (endereco == null) {
            if (other.endereco != null)
                return false;
        } else if (!endereco.equals(other.endereco))
            return false;
        if (estadoCivil != other.estadoCivil)
            return false;
        if (fgts == null) {
            if (other.fgts != null)
                return false;
        } else if (!fgts.equals(other.fgts))
            return false;
        if (genero != other.genero)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (observacoes == null) {
            if (other.observacoes != null)
                return false;
        } else if (!observacoes.equals(other.observacoes))
            return false;
        if (renda == null) {
            if (other.renda != null)
                return false;
        } else if (!renda.equals(other.renda))
            return false;
        if (rg == null) {
            if (other.rg != null)
                return false;
        } else if (!rg.equals(other.rg))
            return false;
        if (status != other.status)
            return false;
        if (telefone == null) {
            if (other.telefone != null)
                return false;
        } else if (!telefone.equals(other.telefone))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Cliente [cpf=" + cpf + ", dataNascimento=" + dataNascimento + ", dependentes=" + dependentes
                + ", email=" + email + ", empreendimentos=" + empreendimentos + ", endereco=" + endereco
                + ", estadoCivil=" + estadoCivil + ", fgts=" + fgts + ", genero=" + genero + ", id=" + id + ", nome="
                + nome + ", observacoes=" + observacoes + ", renda=" + renda + ", rg=" + rg + ", status=" + status
                + ", telefone=" + telefone + "]";
    }

   

   
    
    
}
