package com.infnet.taskservice.service.impl;

import com.infnet.taskservice.model.Task;
import com.infnet.taskservice.repository.TaskRepository;
import com.infnet.taskservice.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> update(Long id, Task task) throws Exception {
        if (!taskRepository.existsById(id)) {
            throw new Exception("Tarefa não encontrada, tente outro ID.");
        }
        task.setId(id);
        taskRepository.save(task);

        return taskRepository.findById(id);
    }

    @Override
    public Optional<Task> delete(Long id) {
        Optional<Task> deletedTask = taskRepository.findById(id);
        taskRepository.deleteById(id);
        return deletedTask;
    }
}
