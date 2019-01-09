package com.InfoShare.demoJJD5.component.repository;

import com.InfoShare.demoJJD5.component.model.Status;
import com.InfoShare.demoJJD5.component.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByStatus(Status status);

}
