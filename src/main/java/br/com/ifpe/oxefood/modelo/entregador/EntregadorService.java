package br.com.ifpe.oxefood.modelo.entregador;

import jakarta.transaction.Transactional;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntregadorService {

   public List<Entregador> listarTodos() {
  
      return repository.findAll();
  }

  public Entregador obterPorID(Long id) {

      return repository.findById(id).get();
  }

   @Autowired
   private EntregadorRepository repository;

   @Transactional
   public Entregador save(Entregador entregador) {

        entregador.setHabilitado(Boolean.TRUE);
        entregador.setVersao(1L);
        entregador.setDataCriacao(LocalDate.now());
        return repository.save(entregador);
   }

}
