package com.InfoShare.demoJJD5.component.controlers;

import com.InfoShare.demoJJD5.component.model.Status;
import com.InfoShare.demoJJD5.component.model.Task;
import com.InfoShare.demoJJD5.component.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        return taskRepository.findById(id)
                .map(task -> ResponseEntity.ok().body(task))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public List<Task> addTask(@RequestBody Task task) {
        taskRepository.save(task);
        return taskRepository.findAll();
    }
    @DeleteMapping("/delete/{id}")
    public List<Task> deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return taskRepository.findAll();
    }

    @GetMapping("/status/{status}")
    public List<Task> findByStatus(@PathVariable Status status) {
        return taskRepository.findAllByStatus(status);
    }

    @PutMapping("/{id}/{status}")
    public ResponseEntity<List<Task>> changeStatusById(@PathVariable Long id,@PathVariable Status status) {
//        Optional<Task> actTask = taskRepository.findById(id);
//        if(actTask.isPresent()){
//            actTask.get().setStatus(status);
//            taskRepository.save(actTask.get());
//        }
//        return taskRepository.findAll();
//    }
    return taskRepository.findById(id)
            .map(task -> {
                task.setStatus(status);
                taskRepository.save(task);
                return ResponseEntity.ok().body(taskRepository.findAll());})
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
