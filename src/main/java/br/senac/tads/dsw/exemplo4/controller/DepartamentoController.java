package br.senac.tads.dsw.exemplo4.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
  

