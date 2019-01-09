package com.InfoShare.demoJJD5.component;

import com.InfoShare.demoJJD5.component.model.Status;
import com.InfoShare.demoJJD5.component.model.Task;
import com.InfoShare.demoJJD5.component.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InsertDataComponent {

    private final TaskRepository taskRepository;

    @Autowired
    public InsertDataComponent(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostConstruct
    public void fillData() {
        Task task = new Task("Cos do zrobienia", Status.IN_PROGRESS);
        taskRepository.save(task);
        Task task1 = new Task("Zrobione", Status.FINISHED);
        taskRepository.save(task1);
    }
}
