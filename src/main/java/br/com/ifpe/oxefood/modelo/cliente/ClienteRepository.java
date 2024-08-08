package br.com.ifpe.oxefood.modelo.cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    //Exemplo de uma busca exata
   @Query(value = "SELECT c FROM Cliente c WHERE c.cpf = :cpf")
   List<Cliente> consultarPorCpf(String cpf);

   //Exemplo de uma busca aproximada com ordenação:
   List<Cliente> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);

   //Exemplo de uma busca com mais de um atributo
   @Query(value = "SELECT c FROM Cliente c WHERE c.nome like %:nome% AND c.cpf = :cpf")
   List<Cliente> consultarPorNomeECpf(String nome, String cpf);
  
}
