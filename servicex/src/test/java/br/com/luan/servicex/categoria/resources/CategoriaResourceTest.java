package br.com.luan.servicex.categoria.resources;

import br.com.luan.servicex.categoria.domain.Categoria;
import br.com.luan.servicex.categoria.repositores.CategoriaRepository;
import br.com.luan.servicex.categoria.services.CategoriaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriaResourceTest {

    @InjectMocks
    private CategoriaService categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Test
    public void testCriarCategoria_success() {
        Categoria categoria = new Categoria();
        categoria.setNomeCategoria("New Category");

        when(categoriaRepository.existsByNomeCategoria(categoria.getNomeCategoria())).thenReturn(false);
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);

        Categoria result = categoriaService.criarCategoria(categoria);

        assertNotNull(result);
        assertEquals("New Category", result.getNomeCategoria());
        verify(categoriaRepository).existsByNomeCategoria(categoria.getNomeCategoria());
        verify(categoriaRepository).save(any(Categoria.class));
    }


    @Test
    void testDeletarCategoria_success() {
            Integer categoriaId = 1;

        // Mockando a existência da categoria
        when(categoriaRepository.existsById(categoriaId)).thenReturn(true);

        // Chamando o método de deletar
        categoriaService.deletarCategoria(categoriaId);

        // Verificando se o método existsById e deleteById foram chamados corretamente
        verify(categoriaRepository).existsById(categoriaId);
        verify(categoriaRepository).deleteById(categoriaId);
    }

/*Integer categoriaId = 1;: Define um ID de categoria a ser deletado.

when(categoriaRepository.existsById(categoriaId)).thenReturn(true);: Simula que a categoria com o ID fornecido existe no repositório.
categoriaService.deletarCategoria(categoriaId);: Chama o método de deletar no serviço.
verify(categoriaRepository).existsById(categ    oriaId);: Verifica se o método existsById foi chamado com o ID correto.
verify(categoriaRepository).deleteById(categoriaId);: Verifica se o método deleteById foi chamado com o ID correto.*/

    @Test
    void testAtualizarCategoria_success() {
        Integer categoriaId = 1;

        Categoria categoriaExistente = new Categoria();
        categoriaExistente.setIdCategoria(categoriaId);
        categoriaExistente.setNomeCategoria("Old Category");

        Categoria categoriaAtualizada = new Categoria();
        categoriaAtualizada.setIdCategoria(categoriaId);
        categoriaAtualizada.setNomeCategoria("Updated Category");

        when(categoriaRepository.findById(categoriaId)).thenReturn(Optional.of(categoriaExistente));
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoriaExistente);

        Categoria result = categoriaService.atualizarCategoria(categoriaAtualizada);

        assertNotNull(result);
        assertEquals("Updated Category", result.getNomeCategoria());
        verify(categoriaRepository).findById(categoriaId);
        verify(categoriaRepository).save(categoriaExistente);
    }
}
    /*Integer categoriaId = 1;: Define um ID de categoria a ser atualizado.
Cria uma Categoria existente com nome "Old Category".
Cria uma Categoria atualizada com nome "Updated Category".
when(categoriaRepository.findById(categoriaId)).thenReturn(Optional.of(categoriaExistente));: Simula que a categoria com o ID fornecido existe no repositório.
when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoriaExistente);: Simula a atualização da categoria.
Categoria result = categoriaService.atualizarCategoria(categoriaAtualizada);: Chama o método de atualizar no serviço.
assertNotNull(result);: Verifica se o resultado não é nulo.
assertEquals("Updated Category", result.getNomeCategoria());: Verifica se o nome da categoria foi atualizado corretamente.
verify(categoriaRepository).findById(categoriaId);: Verifica se o método findById foi chamado com o ID correto.
verify(categoriaRepository).save(categoriaExistente);: Verifica se o método save foi chamado com a categoria existente atualizada.*/
