package br.com.luan.servicex.ordemServico.resources;

import br.com.luan.servicex.ordemServico.domain.OrdemServico;
import br.com.luan.servicex.ordemServico.services.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ordemservicos")
public class OrdemServicoResource {
   @Autowired
    private OrdemServicoService ordemServicoService ;

    @PostMapping
    public ResponseEntity<OrdemServico> criarOrdemServico(@RequestBody OrdemServico ordemServico) {
        OrdemServico novaOrdemServico = ordemServicoService.criarOrdemServico(ordemServico);
        return  new ResponseEntity<>(novaOrdemServico,HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<OrdemServico>> listarOrdemServico(){
        List<OrdemServico> ordemServicos = ordemServicoService.listarOrdemServico();
        return new ResponseEntity<>(ordemServicos, HttpStatus.OK);
    }

    @GetMapping("/{idOrdemServico}")
    public ResponseEntity<OrdemServico> buscarOrdemservico(@PathVariable Integer idOrdemServico){
        return ordemServicoService.buscarOrdemServico(idOrdemServico)
                .map(ordemServico -> new ResponseEntity<>(ordemServico, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{idOrdemServico}")
    public ResponseEntity<Void> deletarOrdemServico(@PathVariable Integer idOrdemServico){
        ordemServicoService.deletarOrdemServico(idOrdemServico);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{idOrdemServico}")
    public ResponseEntity<OrdemServico> atualizarOrdemServico(
            @PathVariable Integer idOrdemServico,
            @RequestBody OrdemServico ordemServico){
        if(!ordemServicoService.buscarOrdemServico(idOrdemServico).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ordemServico.setIdOrdemServico(idOrdemServico);
        OrdemServico novaOrdemServico = ordemServicoService.atualizarOrdemServico(ordemServico);
        return new ResponseEntity<>(novaOrdemServico, HttpStatus.OK);
    }
}


