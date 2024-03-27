package br.com.luan.servicex.ordemservico.services;

import br.com.luan.servicex.ordemservico.domain.OrdemServico;
import br.com.luan.servicex.ordemservico.repositores.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrdemServicoService {
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    public OrdemServico criarOrdemServico(OrdemServico ordemServico){return ordemServicoRepository.save(ordemServico);}

    public List<OrdemServico> listarOrdemServico(){return ordemServicoRepository.findAll();}

    public Optional<OrdemServico> buscarOrdemServico(Integer idOrdemServico){ return  ordemServicoRepository.findById(idOrdemServico);}

    public OrdemServico atualizarOrdemServico(OrdemServico ordemServico){return ordemServicoRepository.save(ordemServico);}

    public void deletarOrdemServico (Integer idOrdemServico){ordemServicoRepository.deleteById(idOrdemServico);}
}

