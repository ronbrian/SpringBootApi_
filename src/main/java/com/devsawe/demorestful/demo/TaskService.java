package com.devsawe.demorestful.demo;

import com.devsawe.demorestful.demo.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Service
public class TaskService {

    @Autowired
    public TaskRepository taskRepository;


    public Map<String, Object> save(Map<String, Object> request) {


        if(null==request.get("task")){
            request.put("status","00");
            request.put("message","Task Cannot Be Blank !");
            return request;

        }else if(null ==request.get("description")) {
            request.put("status","00");
            request.put("message","Description Cannot Be Blank !");
            return request;

        }else {

            String task = (String) request.get("task");
            String description = (String) request.get("description");
            String finishBy = (String) request.get("finishBy");
            boolean finished = (boolean) request.get("finished");
            String location = (String) request.get("location");





             // if phone and email are valid
                //request.put("status", "00"); //status success
                request.put("data", "00");

                Task task1 = new Task();
                task1.setTask(task);
                task1.setDescription(description);
                task1.setFinishBy(finishBy);
                task1.setFinished(finished);
                task1.setLocation(location);

                taskRepository.save(task1);

                return request;



        }


    }

    public Map<String, Object> getTasks() {
        Map<String, Object> map =new HashMap<>();

        List<Task> list = taskRepository.findAll();
        List<Task> list2 = new ArrayList<>();
        for (Task task : list) {

            long id2 = task.getId();
                if (id2>0){                        //return list of users whose id is more than 1
                    list2.add(task);
            }
        }

        map.put("status","ok");
        map.put("error",false);
        map.put("message","Tasks have been found");
        map.put("data" ,list2);
        return map;
    }


    public Map<String, Object> retrieveTask(long id){
        Map<String, Object> map =new HashMap<>();
        Optional<Task> task = taskRepository.findById(id);


        if (!task.isPresent()){

            map.put("status","00");
            map.put("message", "Task with that ID does not exist ! ");

        }else {
            map.put("status", "01");
            map.put("message", "success");

            map.put("data", task);
        }

        return map;
    }


    public ResponseEntity<Object> updateTask(long id, String task1, String description, String finishBy, boolean finished, String location){
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (!taskOptional.isPresent())
            return ResponseEntity.notFound().build();

        Task task = new Task();
        task.setId(id);
        task.setTask(task1);
        task.setDescription(description);
        task.setFinished(finished);
        task.setFinishBy(finishBy);
        task.setLocation(location);

        return ResponseEntity.ok(taskRepository.save(task));

    }




}
