package br.com.ifpe.oxefood.api.cliente;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import br.com.ifpe.oxefood.modelo.produto.Produto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

   @NotNull(message = "O Nome é de preenchimento obrigatório")
   @NotBlank(message = "O Nome é de preenchimento obrigatório")
   @Length(max = 100, message = "O Nome deverá ter no máximo {max} caracteres")
   private String nome;

   @JsonFormat(pattern = "dd/MM/yyyy")
   private LocalDate dataNascimento;

   @NotNull(message = "O CPF é de preenchimento obrigatório")
   @NotBlank(message = "O CPF é de preenchimento obrigatório")
   @CPF
   private String cpf;

   @NotBlank(message = "O Email é de preenchimento obrigatório")
   @Email
   private String email;

   @Length(min = 8, max = 20, message = "O campo Fone tem que ter entre {min} e {max} caracteres")
   private String foneCelular;

   @Length(min = 8, max = 20, message = "O campo Fone tem que ter entre {min} e {max} caracteres")
   private String foneFixo;

   public Cliente build() {

       return Cliente.builder()
           .nome(nome)
           .dataNascimento(dataNascimento)
           .cpf(cpf)
           .foneCelular(foneCelular)
           .foneFixo(foneFixo)
           .email(email)
           .build();
   }

}
