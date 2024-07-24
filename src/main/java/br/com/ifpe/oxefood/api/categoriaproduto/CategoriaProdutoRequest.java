package br.com.ifpe.oxefood.api.categoriaproduto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import br.com.ifpe.oxefood.modelo.categoriaproduto.CategoriaProduto;;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaProdutoRequest {
    private String descricao;
   public CategoriaProduto build() {

       return CategoriaProduto.builder()
           .descricao(descricao)
           .build();
   }

}
