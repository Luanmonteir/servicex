package br.com.luan.servicex.ordemServico.repositores;


import br.com.luan.servicex.ordemServico.domain.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Integer> {

}


