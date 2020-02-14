package com.devsawe.demorestful.demo;

import com.devsawe.demorestful.demo.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByDescription(String description);

    Long countByDescription(String s);
}
