package br.com.ifpe.oxefood.modelo.promocao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.modelo.promocao.Promocao;
import jakarta.transaction.Transactional;

@Service
public class PromocaoService {

    public List<Promocao> listarTodos() {
  
        return repository.findAll();
    }

    public Promocao obterPorID(Long id) {

        return repository.findById(id).get();
    }

   @Autowired
   private PromocaoRepository repository;

   @Transactional
   public Promocao save(Promocao promocao) {

       promocao.setHabilitado(Boolean.TRUE);
       promocao.setVersao(1L);
       promocao.setDataCriacao(LocalDate.now());
       return repository.save(promocao);
   }

@Transactional
public void update(Long id, Promocao promocaoAlterada) {

    Promocao promocao = repository.findById(id).get();
    promocao.setTitulo(promocaoAlterada.getTitulo());
    promocao.setDataInicio(promocaoAlterada.getDataInicio());
    promocao.setDataFim(promocaoAlterada.getDataFim());
    promocao.setRegra(promocaoAlterada.getRegra());
    promocao.setValorDesconto(promocaoAlterada.getValorDesconto());


    promocao.setVersao(promocao.getVersao() + 1);
    repository.save(promocao);
    
}

@Transactional
public void delete(Long id) {

    Promocao promocao = repository.findById(id).get();
    promocao.setHabilitado(Boolean.FALSE);
    promocao.setVersao(promocao.getVersao() + 1);

    repository.save(promocao);
}


}
