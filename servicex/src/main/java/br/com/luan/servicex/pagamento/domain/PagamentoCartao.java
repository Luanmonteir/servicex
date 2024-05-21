package br.com.luan.servicex.pagamento.domain;

import br.com.luan.servicex.ordemServico.domain.OrdemServico;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonTypeName("pagamentoCartao")
public class PagamentoCartao extends Pagamento{
    private static final long serialVersionUID = 1L;

    @Column(name = "NUMERO_PARCELAS")
    private Integer numeroParcelas;

    public PagamentoCartao(){}

    public PagamentoCartao(Integer idPagamento, StatusPagamento statusPagmento, OrdemServico ordemServico, Integer numeroParcelas) {
        super(idPagamento, statusPagmento, ordemServico);
        this.numeroParcelas = numeroParcelas;
    }

    public PagamentoCartao(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }
}
