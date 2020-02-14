package com.devsawe.demorestful.demo;

import com.devsawe.demorestful.demo.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
public class TaskController {

    //HashMap<String, Object> response = new HashMap<>();

    @Autowired
    public TaskRepository taskRepository;

    @Autowired
    public TaskService taskService;


    //Creating a new Task
    @PostMapping(value = "/tasks")
    public Map<String, Object> addTask(@Valid @RequestBody Map <String, Object> request) {

        return taskService.save(request);
    }

    //retrieving and returning all tasks attributes
    @GetMapping(value = "/tasks")
    public Map<String, Object> getObject1() {
        return taskService.getTasks();
    }



    //retrieving task attribute by id
    @GetMapping(value = "/tasks/{id}")
    public Map<String, Object> retrieveTask(@PathVariable long id){
        return taskService.retrieveTask(id);
    }


    //deleting task by id
    @DeleteMapping(value = "/tasks/{id}")
    public void deleteTask(@PathVariable long id) {
        taskRepository.deleteById(id);
    }


    // Deleting all Tasks
    @DeleteMapping(value = "/tasks")
    public void deleteTasks(){
        taskRepository.deleteAll();
    }

    //Counting number of Tasks
    @GetMapping(value = "/tasks/count")
    public long getCount(){
       return taskRepository.count();
    }


    //Updating Task attributes by id -- passing id as path
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable long id, @RequestBody Map <String, Object> request ) {
        //return taskService.updateTask(id,name,email,phone);

        String task = (String) request.get("task");
        String description = (String) request.get("description");
        String finishBy = (String) request.get("finishBy");
        boolean finished = (boolean) request.get("finished");
        String location = (String) request.get("location");


        Optional<Task> taskOptional = taskRepository.findById(id);
        if (!taskOptional.isPresent())
            return ResponseEntity.notFound().build();

        Task task1 = new Task();
        task1.setTask(task);
        task1.setDescription(description);
        task1.setFinishBy(finishBy);
        task1.setFinished(finished);
        task1.setLocation(location);


        return ResponseEntity.ok(taskRepository.save(task1));

    }






   /* //Updating Task attributes by id -- passing id as path
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable long id, @RequestParam("task") String task,
                                             @RequestParam("description") String description,
                                             @RequestParam("finishBy") String finishBy,
                                             @RequestParam("finished") String finished,
                                             @RequestParam("location") String location ) {
        //return taskService.updateTask(id,name,email,phone);


        Optional<Task> taskOptional = taskRepository.findById(id);
        if (!taskOptional.isPresent())
            return ResponseEntity.notFound().build();

        Task task1 = new Task();
        task1.setTask(task);
        task1.setDescription(description);
        task1.finishBy(finishBy);
        task1.setFinished(finished);
        task1.setLocation(location);


        return ResponseEntity.ok(taskRepository.save(task1));

    }
*/




}
