package br.com.luan.servicex.pagamento.domain;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonTypeName("pagamentoPix")
public class PagamentoPix extends Pagamento{
}