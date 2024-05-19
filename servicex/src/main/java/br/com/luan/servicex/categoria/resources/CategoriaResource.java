package br.com.luan.servicex.categoria.resources;

import br.com.luan.servicex.categoria.domain.Categoria;
import br.com.luan.servicex.categoria.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @Operation(summary = "Cria uma nova categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso")
    })
    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria) {
        Categoria novaCategoria = categoriaService.criarCategoria(categoria);
        return new ResponseEntity<>(novaCategoria, HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todas as categorias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de categorias")
    })
    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategoria() {
        List<Categoria> categorias = categoriaService.listarCategoria();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @Operation(summary = "Busca uma categoria pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria encontrada"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @GetMapping("/{idCategoria}")
    public ResponseEntity<Categoria> buscarCategoria(@PathVariable Integer idCategoria) {
        return categoriaService.buscarCategoria(idCategoria)
                .map(categoria -> new ResponseEntity<>(categoria, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Deleta uma categoria pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Integer idCategoria) {
        categoriaService.deletarCategoria(idCategoria);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Atualiza uma categoria existente pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @PutMapping("/{idCategoria}")
    public ResponseEntity<Categoria> atualizarCategoria(
            @PathVariable Integer idCategoria,
            @RequestBody Categoria categoria) {
        if (!categoriaService.buscarCategoria(idCategoria).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoria.setIdCategoria(idCategoria);
        Categoria novaCategoria = categoriaService.atualizarCategoria(categoria);
        return new ResponseEntity<>(novaCategoria, HttpStatus.OK);
    }
}
