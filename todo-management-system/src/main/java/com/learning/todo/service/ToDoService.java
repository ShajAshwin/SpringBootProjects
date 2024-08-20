package com.learning.todo.service;

import com.learning.todo.dto.ToDoDto;
import com.learning.todo.entity.ToDo;
import com.learning.todo.repo.ToDoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ToDoDto addToDo(ToDoDto toDoDto){
        ToDo todo = modelMapper.map(toDoDto, ToDo.class);
        todo = toDoRepository.save(todo);

        return modelMapper.map(todo, ToDoDto.class);

    }

    public  ToDoDto getToDo(Long id){
        ToDo toDo = toDoRepository.findById(id)
                                  .orElseThrow(()-> new RuntimeException("List not found"));

        return modelMapper.map(toDo,ToDoDto.class);

    }

    public List<ToDoDto> getAllToDo(){
        List<ToDo> toDos = toDoRepository.findAll();

        return toDos.stream().map((toDo -> modelMapper.map(toDo,ToDoDto.class)))
                .collect(Collectors.toList());
    }

    public String markAsCompleted(long id){
        if(toDoRepository.existsById(id)){
            toDoRepository.deleteById(id);
        }else throw new NoSuchElementException("No Task found");
        return "Task completed and removed from list";
    }

    public ToDoDto updateToDo(ToDoDto toDoDto){
        ToDo toDo = modelMapper.map(toDoDto, ToDo.class);
        if(toDoRepository.existsById(toDo.getId())) {
            toDo = toDoRepository.save(toDo);
        }else throw new NoSuchElementException("ToDo not found");

        return modelMapper.map(toDo,ToDoDto.class);
    }


}

