package com.learning.todo.controller;

import com.learning.todo.dto.ToDoDto;
import com.learning.todo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {

    @Autowired
    private ToDoService service;

    @PostMapping("/save")
    public ResponseEntity<ToDoDto> addToDo(@RequestBody ToDoDto toDoDto){
        return new ResponseEntity<>(service.addToDo(toDoDto), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ToDoDto> getToDo(@PathVariable("id") long id){
        return new ResponseEntity<>(service.getToDo(id),HttpStatus.FOUND);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ToDoDto>> getAllToDo(){
        return new ResponseEntity<>(service.getAllToDo(),HttpStatus.FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<ToDoDto> updateToDo(@RequestBody ToDoDto toDoDto){
        return new ResponseEntity<>(service.updateToDo(toDoDto),HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> MarkAsToDo(@PathVariable long id){
        return new ResponseEntity<>(service.markAsCompleted(id),HttpStatus.OK);
    }

}
