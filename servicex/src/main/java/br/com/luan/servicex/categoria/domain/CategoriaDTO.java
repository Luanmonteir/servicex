package br.com.luan.servicex.categoria.domain;


import lombok.*;

@Getter
@Setter
public class CategoriaDTO{
    private Integer idCategoria;
    private String nomeCategoria;
    private String mensagem;

    public CategoriaDTO(){}

    public CategoriaDTO(Categoria categoria) {
        idCategoria = categoria.getIdCategoria();
        nomeCategoria = categoria.getNomeCategoria();
    }

}

