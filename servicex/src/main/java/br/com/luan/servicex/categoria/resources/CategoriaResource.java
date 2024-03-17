package br.com.luan.servicex.categoria.resources;

import br.com.luan.servicex.categoria.domain.Categoria;
import br.com.luan.servicex.categoria.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria){
        Categoria novaCategoria = categoriaService.criarCategoria(categoria);
        return  new ResponseEntity<>(novaCategoria, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategoria(){
        List<Categoria> categorias = categoriaService.listarCategoria();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<Categoria> buscarCategoria(@PathVariable Integer idCategoria){
        return categoriaService.buscarCategoria(idCategoria)
                .map(categoria -> new ResponseEntity<>(categoria, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Integer idCategoria){
        categoriaService.deletarCategoria(idCategoria);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{idCategoria}")
    public ResponseEntity<Categoria> atualizarCategoria(
            @PathVariable Integer idCategoria,
            @RequestBody Categoria categoria){
        if(!categoriaService.buscarCategoria(idCategoria).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoria.setIdCategoria(idCategoria);
        Categoria novaCategoria = categoriaService.atualizarCategoria(categoria);
        return new ResponseEntity<>(novaCategoria, HttpStatus.OK);
    }
}