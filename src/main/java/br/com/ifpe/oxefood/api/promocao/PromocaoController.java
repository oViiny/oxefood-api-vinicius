package br.com.ifpe.oxefood.api.promocao;

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
import org.springframework.web.bind.annotation.RestController;
import br.com.ifpe.oxefood.modelo.promocao.Promocao;
import br.com.ifpe.oxefood.modelo.promocao.PromocaoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/promocao")
@CrossOrigin
public class PromocaoController {

    @Operation(
        summary = "Serviço responsável por atualizar uma promocao(por id) no sistema.",
        description = "Exemplo de descrição de um endpoint responsável por atualizar uma promocao(por id) no sistema."
    )
    @PutMapping("/{id}")
    public ResponseEntity<Promocao> update(@PathVariable("id") Long id, @RequestBody PromocaoRequest request) {

       promocaoService.update(id, request.build());
       return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "Serviço responsável por listar todas as promoções no sistema.",
        description = "Exemplo de descrição de um endpoint responsável por listar todas as promoções no sistema."
    )
    @GetMapping
    public List<Promocao> listarTodos() {
        return promocaoService.listarTodos();
    }


    @Operation(
        summary = "Serviço responsável por obter uma promoção(por id) no sistema.",
        description = "Exemplo de descrição de um endpoint responsável por obter uma promoção(por id) no sistema."
    )
    @GetMapping("/{id}")
    public Promocao obterPorID(@PathVariable Long id) {
        return promocaoService.obterPorID(id);
    }

   @Autowired
   private PromocaoService promocaoService;


   @Operation(
    summary = "Serviço responsável por salvar uma promoção no sistema.",
    description = "Exemplo de descrição de um endpoint responsável por inserir uma promoção no sistema."
    )
   @PostMapping
   public ResponseEntity<Promocao> save(@RequestBody PromocaoRequest request) {

       Promocao promocao = promocaoService.save(request.build());
       return new ResponseEntity<Promocao>(promocao, HttpStatus.CREATED);
   }

    @Operation(
        summary = "Serviço responsável por deletar uma promoção no sistema.",
        description = "Exemplo de descrição de um endpoint responsável por deletar uma promoção no sistema."
    )
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {

       promocaoService.delete(id);
       return ResponseEntity.ok().build();
   }
}
