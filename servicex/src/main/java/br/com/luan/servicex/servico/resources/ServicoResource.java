package br.com.luan.servicex.servico.resources;



import br.com.luan.servicex.servico.domain.Servico;
import br.com.luan.servicex.servico.services.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = "/servicos")
public class ServicoResource {
    @Autowired
    private ServicoService servicoService;

    @PostMapping
    public ResponseEntity<Servico> criarServico(@RequestBody Servico servico){
        Servico novaServico = servicoService.criarServico(servico);
        return new ResponseEntity<>(novaServico, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Servico>> listarServico(){
        List<Servico> servicos = servicoService.listarServico();
        return new ResponseEntity<>(servicos, HttpStatus.OK);
    }

    @GetMapping("/{idServico}")
    public ResponseEntity<Servico> buscarServico(@PathVariable Integer idServico){
        return servicoService.buscarServico(idServico)
                .map(servico -> new ResponseEntity<>(servico, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{idServico}")
    public ResponseEntity<Void> deletarServico(@PathVariable Integer idServico){
        servicoService.deletarServico(idServico);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{idServico}")
    public ResponseEntity<Servico> atualizarServico(
            @PathVariable Integer idServico,
            @RequestBody Servico servico){
        if(!servicoService.buscarServico(idServico).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        servico.setIdServico(idServico);
        Servico novaServico = servicoService.atualizarServico(servico);
        return new ResponseEntity<>(novaServico, HttpStatus.OK);
    }
}
