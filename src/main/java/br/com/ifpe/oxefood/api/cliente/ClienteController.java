package br.com.ifpe.oxefood.api.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefood.modelo.acesso.UsuarioService;
import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import br.com.ifpe.oxefood.modelo.cliente.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin
public class ClienteController {

    
    @Autowired
    private UsuarioService usuarioService;

    @Operation(
        summary = "Serviço responsável por atualizar um cliente(por id) no sistema.",
        description = "Exemplo de descrição de um endpoint responsável por atualizar um cliente(por id) no sistema."
    )
     @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable("id") Long id, @RequestBody @Valid ClienteRequest clienteRequest, HttpServletRequest request) {

       clienteService.update(id, clienteRequest.build(), usuarioService.obterUsuarioLogado(request));
       return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "Serviço responsável por obter todos os clientes no sistema.",
        description = "Exemplo de descrição de um endpoint responsável por obter todos os cliente no sistema."
    )
    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteService.listarTodos();
    }

    @Operation(
        summary = "Serviço responsável por obter um cliente(por id) no sistema.",
        description = "Exemplo de descrição de um endpoint responsável por obter um cliente(por id) no sistema."
    )
    @GetMapping("/{id}")
    public Cliente obterPorID(@PathVariable Long id) {
        return clienteService.obterPorID(id);
    }

   @Autowired
   private ClienteService clienteService;

   
    @PostMapping("/filtrar")
    public List<Cliente> filtrar(
           @RequestParam(value = "cpf", required = false) String cpf,
           @RequestParam(value = "nome", required = false) String nome) {

        return clienteService.filtrar(cpf, nome);
   }

   @Operation(
       summary = "Serviço responsável por salvar um cliente no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por inserir um cliente no sistema."
   )
   @PostMapping
   public ResponseEntity<Cliente> save(@RequestBody @Valid ClienteRequest clienteRequest, HttpServletRequest request) {

       Cliente cliente = clienteService.save(clienteRequest.build(), usuarioService.obterUsuarioLogado(request));
       return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
   }

   @Operation(
    summary = "Serviço responsável por deletar um cliente no sistema.",
    description = "Exemplo de descrição de um endpoint responsável por deletar um cliente no sistema."
)
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {

       clienteService.delete(id);
       return ResponseEntity.ok().build();
   }

}
