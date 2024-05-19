package br.com.luan.servicex.pagamento.resources;

import br.com.luan.servicex.pagamento.domain.Pagamento;
import br.com.luan.servicex.pagamento.services.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pagamentos")
public class PagamentoResource {

    @Autowired
    private PagamentoService pagamentoService;

    @Operation(summary = "Cria um novo pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pagamento criado com sucesso")
    })
    @PostMapping
    public ResponseEntity<Pagamento> criarPagamento(@RequestBody Pagamento pagamento) {
        Pagamento novoPagamento = pagamentoService.criarPagamento(pagamento);
        return new ResponseEntity<>(novoPagamento, HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os pagamentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pagamentos")
    })
    @GetMapping
    public ResponseEntity<List<Pagamento>> listarPagamento() {
        List<Pagamento> pagamentos = pagamentoService.listarPagamento();
        return new ResponseEntity<>(pagamentos, HttpStatus.OK);
    }

    @Operation(summary = "Busca um pagamento pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento encontrado"),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado")
    })
    @GetMapping("/{idPagamento}")
    public ResponseEntity<Pagamento> buscarPagamento(@PathVariable Integer idPagamento) {
        return pagamentoService.buscarPagamento(idPagamento)
                .map(pagamento -> new ResponseEntity<>(pagamento, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Deleta um pagamento pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pagamento deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado")
    })
    @DeleteMapping("/{idPagamento}")
    public ResponseEntity<Void> deletarPagamento(@PathVariable Integer idPagamento) {
        pagamentoService.deletarPagamento(idPagamento);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Atualiza um pagamento existente pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado")
    })
    @PutMapping("/{idPagamento}")
    public ResponseEntity<Pagamento> atualizarPagamento(
            @PathVariable Integer idPagamento,
            @RequestBody Pagamento pagamento) {
        if (!pagamentoService.buscarPagamento(idPagamento).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pagamento.setIdPagamento(idPagamento);
        Pagamento novoPagamento = pagamentoService.atualizarPagamento(pagamento);
        return new ResponseEntity<>(novoPagamento, HttpStatus.OK);
    }
}
