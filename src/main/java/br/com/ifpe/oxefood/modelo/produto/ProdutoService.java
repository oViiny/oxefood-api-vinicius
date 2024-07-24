package br.com.ifpe.oxefood.modelo.produto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.modelo.entregador.Entregador;
import br.com.ifpe.oxefood.util.exception.EntidadeNaoEncontradaException;
import br.com.ifpe.oxefood.util.exception.ProdutoException;
import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

    public List<Produto> listarTodos() {
  
        return repository.findAll();
    }

    public Produto obterPorID(Long id) {

        Optional<Produto> consulta = repository.findById(id);
        if (consulta.isPresent()) {
            return consulta.get();
        } else {
            throw new EntidadeNaoEncontradaException("Produto", id);
        }
    }

   @Autowired
   private ProdutoRepository repository;

   @Transactional
   public Produto save(Produto produto) {

       if (produto.getValorUnitario() < 10) {
	    throw new ProdutoException(ProdutoException.MSG_VALOR_MINIMO_PRODUTO);
	    }

       produto.setHabilitado(Boolean.TRUE);
       produto.setVersao(1L);
       produto.setDataCriacao(LocalDate.now());
       return repository.save(produto);
   }

@Transactional
public void update(Long id, Produto produtoAlterado) {

    Produto produto = repository.findById(id).get();
    produto.setCodigo(produtoAlterado.getCodigo());
    produto.setTitulo(produtoAlterado.getTitulo());
    produto.setDescricao(produtoAlterado.getDescricao());
    produto.setValorUnitario(produtoAlterado.getValorUnitario());
    produto.setTempoEntregaMinimo(produtoAlterado.getTempoEntregaMinimo());
    produto.setTempoEntregaMaximo(produtoAlterado.getTempoEntregaMaximo());


    produto.setVersao(produto.getVersao() + 1);
    repository.save(produto);
    
}

@Transactional
public void delete(Long id) {

    Produto produto = repository.findById(id).get();
    produto.setHabilitado(Boolean.FALSE);
    produto.setVersao(produto.getVersao() + 1);

    repository.save(produto);
}


}
