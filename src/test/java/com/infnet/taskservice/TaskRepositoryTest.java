package com.infnet.taskservice;

import com.infnet.taskservice.model.Task;
import com.infnet.taskservice.repository.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    @DisplayName("Deve salvar uma tarefa com sucesso")
    void saveTest() {
        Task task = Task.builder()
                .name("Tarefa Teste")
                .description("Testando")
                .observation("Obs")
                .hourlyRate(100.0f)
                .budget(1000.0f)
                .estimatedHours(10)
                .active(true)
                .build();

        Task savedTask = taskRepository.save(task);

        assertThat(savedTask.getId()).isNotNull();
        assertThat(savedTask.getName()).isEqualTo("Tarefa Teste");
        assertThat(savedTask.getDescription()).isEqualTo("Testando");
        assertThat(savedTask.getObservation()).isEqualTo("Obs");
        assertThat(savedTask.getHourlyRate()).isEqualTo(100.0f);
        assertThat(savedTask.getBudget()).isEqualTo(1000.0f);
        assertThat(savedTask.getEstimatedHours()).isEqualTo(10);
        assertThat(savedTask.getActive()).isEqualTo(true);
    }

    @Test
    @DisplayName("Deve buscar uma tarefa pelo nome")
    void findByNameTest() {
        Task task = Task.builder()
                .name("Tarefa Teste")
                .description("Testando")
                .observation("Obs")
                .hourlyRate(100.0f)
                .budget(1000.0f)
                .estimatedHours(10)
                .active(true)
                .build();

        taskRepository.save(task);

        List<Task> foundTasks = taskRepository.findByName("Tarefa Teste");

        assertThat(foundTasks).isNotNull();
        assertThat(foundTasks.get(0).getName()).isEqualTo("Tarefa Teste");
    }

    @Test
    @DisplayName("Deve atualizar uma tarefa existente")
    void updateTest() {
        Task task = Task.builder()
                .name("Tarefa Teste")
                .description("Testando")
                .observation("Obs")
                .hourlyRate(100.0f)
                .budget(1000.0f)
                .estimatedHours(10)
                .active(true)
                .build();

        Task savedTask = taskRepository.save(task);
        savedTask.setName("Tarefa Atualizada");
        savedTask.setBudget(2500.0f);

        Task updatedTask = taskRepository.save(savedTask);

        assertThat(updatedTask.getName()).isEqualTo("Tarefa Atualizada");
        assertThat(updatedTask.getBudget()).isEqualTo(2500.0f);
    }

    @Test
    @DisplayName("Deve deletar uma tarefa com sucesso")
    void deleteTest() {
        Task task = Task.builder()
                .name("Tarefa Teste")
                .description("Testando")
                .observation("Obs")
                .hourlyRate(100.0f)
                .budget(1000.0f)
                .estimatedHours(10)
                .active(true)
                .build();

        Task savedTask = taskRepository.save(task);

        taskRepository.deleteById(savedTask.getId());

        Optional<Task> deletedTask = taskRepository.findById(savedTask.getId());

        assertThat(deletedTask).isEmpty();
    }
}
