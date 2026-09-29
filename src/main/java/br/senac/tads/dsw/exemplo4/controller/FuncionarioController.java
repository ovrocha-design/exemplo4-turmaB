package br.senac.tads.dsw.exemplo4.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.senac.tads.dsw.exemplo4.model.Funcionario;
import br.senac.tads.dsw.exemplo4.repository.FuncionarioRepository;
import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/api/funcionarios")

public class FuncionarioController {
    private final FuncionarioRepository repository;

        public FuncionarioController(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping 
    public List<Funcionario> listarTodos() {
        return repository.findAll();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario criar(@RequestBody @Valid Funcionario funcionario) {
        return repository.save(funcionario);
    }

}
