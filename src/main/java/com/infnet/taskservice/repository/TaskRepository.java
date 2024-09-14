package com.infnet.taskservice.repository;

import com.infnet.taskservice.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {}
