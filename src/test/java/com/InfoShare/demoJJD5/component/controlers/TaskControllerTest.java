package com.InfoShare.demoJJD5.component.controlers;

import com.InfoShare.demoJJD5.component.model.Status;
import com.InfoShare.demoJJD5.component.model.Task;
import com.InfoShare.demoJJD5.component.repository.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskControllerTest {

    @Autowired
    private TaskController taskController;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void getAllShouldReturn2Tasks() {
        List<Task> tasks = taskController.getAll();
        Assertions.assertThat(tasks).hasSize(2);
    }

    @Test
    public void getByIdShuldReturnStatusOkWhenIDCorrect() {
        ResponseEntity<Task> task = taskController.getById((long) 1);
        Assertions.assertThat(task.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void getByIdShuldNotReturnStatusOkWhenIDCorrect() {
        ResponseEntity<Task> task = taskController.getById((long) 10);
        Assertions.assertThat(task.getStatusCode()).isNotEqualTo(HttpStatus.OK);
    }

    @Test
    public void getByIdShuldNotReturnCorrectTaskWhenIDCorrect() {
        ResponseEntity<Task> task = taskController.getById((long) 1);
        Assertions.assertThat(task.getBody().getId()).isEqualTo(1);
        Assertions.assertThat(task.getBody().getDescription()).isEqualTo("Cos do zrobienia");
        Assertions.assertThat(task.getBody().getStatus()).isEqualTo(Status.IN_PROGRESS);
    }

}