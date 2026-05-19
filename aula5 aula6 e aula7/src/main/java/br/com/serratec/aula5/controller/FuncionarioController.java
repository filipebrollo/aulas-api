package br.com.serratec.aula5.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.aula5.model.Funcionario;
import br.com.serratec.aula5.repository.FuncionarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario inserir(@Valid @RequestBody Funcionario Funcionario) {
        return repository.save(Funcionario);
    }

    @PutMapping("{id}")
    public ResponseEntity<Funcionario> alterar(@Valid @RequestBody Funcionario Funcionario, @PathVariable Long id) {
        if (repository.existsById(id)) {
            Funcionario.setId(id);
            return ResponseEntity.ok(repository.save(Funcionario));

        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Funcionario> buscar(@PathVariable Long id){
        Optional<Funcionario> Funcionario = repository.findById(id);
        if (Funcionario.isPresent()) {
            ResponseEntity.ok(Funcionario.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Funcionario> listar(){
        return repository.findAll();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.notFound().build();
    }


}
