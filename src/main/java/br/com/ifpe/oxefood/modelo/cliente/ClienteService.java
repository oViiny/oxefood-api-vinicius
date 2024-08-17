package br.com.ifpe.oxefood.modelo.cliente;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.modelo.acesso.Usuario;
import br.com.ifpe.oxefood.modelo.acesso.UsuarioService;
import br.com.ifpe.oxefood.modelo.mensagens.EmailService;
import br.com.ifpe.oxefood.util.exception.EntidadeNaoEncontradaException;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {

    public List<Cliente> listarTodos() {
  
        return repository.findAll();
    }

    public Cliente obterPorID(Long id) {
        Optional<Cliente> consulta = repository.findById(id);
  
        if (consulta.isPresent()) {
            return consulta.get();
        } else {
            throw new EntidadeNaoEncontradaException("Cliente", id);
        }
    }

   @Autowired
   private ClienteRepository repository;


    @Autowired
    private EmailService emailService;

    @Autowired
    private UsuarioService usuarioService;
    

   @Transactional
   public Cliente save(Cliente cliente, Usuario usuarioLogado) {

        usuarioService.save(cliente.getUsuario());

       cliente.setHabilitado(Boolean.TRUE);
       cliente.setVersao(1L);
       cliente.setDataCriacao(LocalDate.now());

       Cliente clienteSalvo = repository.save(cliente);

       //emailService.enviarEmailConfirmacaoCadastroCliente(clienteSalvo);

       return clienteSalvo;
   }
   
   public List<Cliente> filtrar(String cpf, String nome) {

    List<Cliente> listaClientes = repository.findAll();

    if ((cpf != null && !"".equals(cpf)) &&
        (nome == null || "".equals(nome))) {
            listaClientes = repository.consultarPorCpf(cpf);
    } else if (
        (cpf == null || "".equals(cpf)) &&
        (nome != null && !"".equals(nome))) {    
            listaClientes = repository.findByNomeContainingIgnoreCaseOrderByNomeAsc(nome);
    } else if (
        (cpf != null || "".equals(cpf)) &&
        (nome != null || "".equals(nome))) {
            listaClientes = repository.consultarPorNomeECpf(nome, cpf); 
    } 
    return listaClientes;
}

@Transactional
public void update(Long id, Cliente clienteAlterado, Usuario usuarioLogado) {
    
    
    Cliente cliente = repository.findById(id).get();
    cliente.setNome(clienteAlterado.getNome());
    cliente.setDataNascimento(clienteAlterado.getDataNascimento());
    cliente.setCpf(clienteAlterado.getCpf());
    cliente.setFoneCelular(clienteAlterado.getFoneCelular());
    cliente.setFoneFixo(clienteAlterado.getFoneFixo());
      
    cliente.setVersao(cliente.getVersao() + 1);
    cliente.setDataUltimaModificacao(LocalDate.now());
    repository.save(cliente);

}
@Transactional
public void delete(Long id) {

    Cliente cliente = repository.findById(id).get();
    cliente.setHabilitado(Boolean.FALSE);
    cliente.setVersao(cliente.getVersao() + 1);

    repository.save(cliente);
}


}
