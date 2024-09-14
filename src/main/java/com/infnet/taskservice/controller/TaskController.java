package com.infnet.taskservice.controller;

import com.infnet.taskservice.model.Task;
import com.infnet.taskservice.service.TaskService;
import com.infnet.taskservice.service.feign.ProjectClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
@Slf4j
public class TaskController {
    private final TaskService taskService;

    private final ProjectClient projectClient;

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody Task task) {
        try {
            log.info("Tarefa: {}", task);
            log.info("Test API {}", projectClient.findById(1L));

            String name = projectClient.findById(task.getProjectId()).getName();

            task.setProjectName(name);

            return new ResponseEntity<>(taskService.create(task), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Task task) {
        try {
            return new ResponseEntity<>(taskService.update(id, task), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(taskService.delete(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
