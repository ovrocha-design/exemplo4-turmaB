package br.senac.tads.dsw.exemplo4.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.senac.tads.dsw.exemplo4.model.Departamento;
import br.senac.tads.dsw.exemplo4.repository.DepartamentoRepository;


@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    private final DepartamentoRepository repository;

    public DepartamentoController(DepartamentoRepository repository) {
        this.repository = repository;
    }

    @GetMapping 
    public List<Departamento> listarTodos() {
    return repository.findAll();
} 

    @PostMapping
    public ResponseEntity<Departamento> criar(@RequestBody Departamento departamento) {
    Departamento departamentoSalvo = repository.save(departamento);

            URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(departamentoSalvo.getId())
                .toUri();
        return ResponseEntity.created(location).body(departamentoSalvo);

    }

        @GetMapping("/{id}")
    public ResponseEntity<Departamento> buscarPorId(@PathVariable Long id) {
        Optional<Departamento> departamentoBuscado = repository.findById(id);
                if (departamentoBuscado.isPresent()) {
            return ResponseEntity.ok(departamentoBuscado.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Departamento> atualizar(@PathVariable Long id,
                        @RequestBody Departamento departamentoAtualizado) {
            Optional<Departamento> departamentoBuscado = repository.findById(id);
        if (departamentoBuscado.isPresent()) {
            Departamento departamentoExistente = departamentoBuscado.get();

            departamentoExistente.setNome(departamentoAtualizado.getNome());
            departamentoExistente.setOrcamento(departamentoAtualizado.getOrcamento());
            Departamento departamentoSalvo = repository.save(departamentoExistente);
            return ResponseEntity.ok(departamentoSalvo);

        } else {
            return ResponseEntity.notFound().build();
        }

    }


}
  

