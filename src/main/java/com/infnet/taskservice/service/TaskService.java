package com.infnet.taskservice.service;

import com.infnet.taskservice.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> findAll();

    Task create(Task task);

    Optional<Task> update(Long id, Task task) throws Exception;

    Optional<Task> delete(Long id);
}
