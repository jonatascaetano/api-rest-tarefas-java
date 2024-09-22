package com.jonatas.tarefas.controllers;

import java.util.ArrayList;
import java.util.List;

import com.jonatas.tarefas.models.Tarefa;

public class TarefaController {

    List<Tarefa> tarefas = new ArrayList<>();

   public TarefaController(){
        tarefas.add(new Tarefa(1, "Tarefa",  "Comprar verduras na promoção de quarta-feira", false));
        tarefas.add(new Tarefa(2, "Fazer a tarefa da faculdade",  "terminar a tarefa de java", false));
    }

    
}
