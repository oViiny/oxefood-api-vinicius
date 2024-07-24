package br.com.ifpe.oxefood.modelo.categoriaproduto;
import java.util.Optional;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.ifpe.oxefood.util.exception.EntidadeNaoEncontradaException;

@Service
public class CategoriaProdutoService {

    public List<CategoriaProduto> listarTodos() {
  
        return repository.findAll();
    }

    public CategoriaProduto obterPorID(Long id) {
        Optional<CategoriaProduto> consulta = repository.findById(id);
  
        if (consulta.isPresent()) {
            return consulta.get();
        } else {
            throw new EntidadeNaoEncontradaException("CategoriaProduto", id);
        }
    }

   @Autowired
   private CategoriaProdutoRepository repository;

   @Transactional
   public CategoriaProduto save(CategoriaProduto categoriaProduto) {

       categoriaProduto.setHabilitado(Boolean.TRUE);
       categoriaProduto.setVersao(1L);
       categoriaProduto.setDataCriacao(LocalDate.now());
       return repository.save(categoriaProduto);
   }
   

@Transactional
public void update(Long id, CategoriaProduto categoriaprodutoAlterada) {
    
    
    CategoriaProduto categoriaProduto = repository.findById(id).get();
    categoriaProduto.setDescricao(categoriaprodutoAlterada.getDescricao());
   
      
    categoriaProduto.setVersao(categoriaProduto.getVersao() + 1);
    repository.save(categoriaProduto);

}
@Transactional
public void delete(Long id) {

    CategoriaProduto categoriaProduto = repository.findById(id).get();
    categoriaProduto.setHabilitado(Boolean.FALSE);
    categoriaProduto.setVersao(categoriaProduto.getVersao() + 1);

    repository.save(categoriaProduto);
}

}
