package br.com.luan.servicex.ordemservico.domain;

import br.com.luan.servicex.servico.domain.Servico;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "ORDEMSERVICO")
public class OrdemServico {
    @Id
    @GeneratedValue
    @Column(name = "ID_ORDEM_SERVICO")
    private Integer idOrdemServico;
    @Column(name = "VALOR")
    private Double valorOrdemServico;
    @Column(name = "DATA_SOLICITACAO")
    private Date dataSolicitacao;


    @ManyToMany
    @JoinTable(name = "SERVICO_ORDEM_SERVICO",
            joinColumns = @JoinColumn(name = "ID_ORDEM_SERVICO"),
            inverseJoinColumns = @JoinColumn(name = "ID_SERVICO"))
    private Set<Servico> servicos;

    public OrdemServico() {
    }

    public OrdemServico(Integer idOrdemServico, Double valorOrdemServico, Date dataSolicitacao) {
        this.idOrdemServico = idOrdemServico;
        this.valorOrdemServico = valorOrdemServico;
        this.dataSolicitacao = dataSolicitacao;
    }
}
