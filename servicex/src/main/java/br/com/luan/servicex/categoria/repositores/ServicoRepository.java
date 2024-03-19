package br.com.luan.servicex.categoria.repositores;

import br.com.luan.servicex.categoria.domain.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository  extends JpaRepository<Servico, Integer> {
}