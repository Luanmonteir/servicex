package br.com.luan.servicex.pagamento.resources;

import br.com.luan.servicex.pagamento.domain.Pagamento;
import br.com.luan.servicex.pagamento.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pagametos")
public class PagamentoResources {
    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<Pagamento> criarPagamento(@RequestBody Pagamento pagamento) {
        Pagamento novaPagamento = pagamentoService.criarPagamento(pagamento);
        return new ResponseEntity<>(novaPagamento, HttpStatus.CREATED);
         }
    @GetMapping
    public ResponseEntity<List<Pagamento>> listarPagamento(){
        List<Pagamento> pagamentos = pagamentoService.listarPagamento();
        return new ResponseEntity<>(pagamentos, HttpStatus.OK);
        }

    @GetMapping("/{idPagameto}")
    public ResponseEntity<Pagamento> buscarPagamento(@PathVariable Integer idPagamento){
        return pagamentoService.buscarPagamento(idPagamento)
                .map(pagamento -> new ResponseEntity<>(pagamento, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{idPagamento}")
    public ResponseEntity<Void> deletarPagamento(@PathVariable Integer idPagamento){
        pagamentoService.deletarPagamento(idPagamento);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{idPagamento}")
    public ResponseEntity<Pagamento> atualizarPagamento(
            @PathVariable Integer idPagamento,
            @RequestBody Pagamento pagamento){
        if(!pagamentoService.buscarPagamento(idPagamento).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pagamento.setIdPagamento(idPagamento);
        Pagamento novaPagamento = pagamentoService.atualizarPagamento(pagamento);
        return new ResponseEntity<>(novaPagamento, HttpStatus.OK);
    }


}
