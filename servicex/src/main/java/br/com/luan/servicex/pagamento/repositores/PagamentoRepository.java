package br.com.luan.servicex.pagamento.repositores;

import br.com.luan.servicex.pagamento.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento,Integer> {
}