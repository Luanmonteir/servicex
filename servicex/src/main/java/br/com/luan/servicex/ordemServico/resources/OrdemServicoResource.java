package br.com.luan.servicex.ordemServico.resources;

import br.com.luan.servicex.ordemServico.domain.OrdemServico;
import br.com.luan.servicex.ordemServico.services.OrdemServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ordemservicos")
public class OrdemServicoResource {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @Operation(summary = "Cria uma nova ordem de serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ordem de serviço criada com sucesso")
    })
    @PostMapping
    public ResponseEntity<OrdemServico> criarOrdemServico(@RequestBody OrdemServico ordemServico) {
        OrdemServico novaOrdemServico = ordemServicoService.criarOrdemServico(ordemServico);
        return new ResponseEntity<>(novaOrdemServico, HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todas as ordens de serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de ordens de serviço")
    })
    @GetMapping
    public ResponseEntity<List<OrdemServico>> listarOrdemServico() {
        List<OrdemServico> ordemServicos = ordemServicoService.listarOrdemServico();
        return new ResponseEntity<>(ordemServicos, HttpStatus.OK);
    }

    @Operation(summary = "Busca uma ordem de serviço pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordem de serviço encontrada"),
            @ApiResponse(responseCode = "404", description = "Ordem de serviço não encontrada")
    })
    @GetMapping("/{idOrdemServico}")
    public ResponseEntity<OrdemServico> buscarOrdemservico(@PathVariable Integer idOrdemServico) {
        return ordemServicoService.buscarOrdemServico(idOrdemServico)
                .map(ordemServico -> new ResponseEntity<>(ordemServico, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Deleta uma ordem de serviço pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ordem de serviço deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ordem de serviço não encontrada")
    })
    @DeleteMapping("/{idOrdemServico}")
    public ResponseEntity<Void> deletarOrdemServico(@PathVariable Integer idOrdemServico) {
        ordemServicoService.deletarOrdemServico(idOrdemServico);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Atualiza uma ordem de serviço existente pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordem de serviço atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ordem de serviço não encontrada")
    })
    @PutMapping("/{idOrdemServico}")
    public ResponseEntity<OrdemServico> atualizarOrdemServico(
            @PathVariable Integer idOrdemServico,
            @RequestBody OrdemServico ordemServico) {
        if (!ordemServicoService.buscarOrdemServico(idOrdemServico).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ordemServico.setIdOrdemServico(idOrdemServico);
        OrdemServico novaOrdemServico = ordemServicoService.atualizarOrdemServico(ordemServico);
        return new ResponseEntity<>(novaOrdemServico, HttpStatus.OK);
    }
}
