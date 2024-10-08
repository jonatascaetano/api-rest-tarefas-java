package com.jonatas.tarefas.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.tarefas.models.Tarefa;

@RestController
@RequestMapping(value = "/tarefas")
public class TarefaController {

    List<Tarefa> tarefas = new ArrayList<>();
    Map<String, String> error = new HashMap<>();

    public TarefaController() {
        tarefas.add(new Tarefa(1, "Tarefa", "Comprar verduras na promoção de quarta-feira", false));
        tarefas.add(new Tarefa(2, "Fazer a tarefa da faculdade", "terminar a tarefa de java", false));
        error.put("error", "Tarefa não encontrada");

    }

    @GetMapping()
    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getTarefaById(@PathVariable int id) {
        var tarefa = tarefas.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        if (tarefa == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(tarefa);
    }

    @PostMapping()
    public ResponseEntity<Tarefa> createTarefa(@RequestBody Tarefa tarefa) {
        tarefa.setId(generateUniqueID());
        tarefas.add(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefa);
    }

    public int generateUniqueID() {
        int novoId;
        Tarefa tarefa;
        do {
            int id = new Random().nextInt(10000);
            tarefa = tarefas.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
            novoId = id;
        } while (tarefa != null);

        return novoId;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> putTarefa(@PathVariable int id, @RequestBody Tarefa obj) {
        var tarefa = tarefas.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        if (tarefa == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        tarefa.setTitle(obj.getTitle());
        tarefa.setBody(obj.getBody());
        tarefa.setDone(obj.isDone());

        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteTarefa(@PathVariable int id) {
        var tarefa = tarefas.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        if (tarefa != null) {
            tarefas.remove(tarefa);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
