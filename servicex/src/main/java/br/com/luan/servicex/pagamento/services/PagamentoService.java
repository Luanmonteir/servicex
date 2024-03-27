package br.com.luan.servicex.pagamento.services;

import br.com.luan.servicex.pagamento.domain.Pagamento;
import br.com.luan.servicex.pagamento.repositores.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    public Pagamento criarPagamento(Pagamento pagamento){return pagamentoRepository.save(pagamento);}

    public List<Pagamento> listarPagamento(){return pagamentoRepository.findAll();}

    public Optional<Pagamento> buscarPagamento(Integer idPagameto){ return  pagamentoRepository.findById(idPagameto);}

    public Pagamento atualizarPagamento(Pagamento pagamento){return pagamentoRepository.save(pagamento);}

    public void deletarPagamento (Integer idPagamento){pagamentoRepository.findById(idPagamento);}

}
