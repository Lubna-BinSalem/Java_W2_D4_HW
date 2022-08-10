package com.example.task.Controller;

import com.example.task.Model.ApiMessage;
import com.example.task.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TaskController {

    ArrayList<Task> tasks=new ArrayList<Task>();

    @GetMapping(path="/task")
    public ArrayList<Task> getUsers(){
        return tasks;
    }

    @PostMapping(path="/task")
    public ApiMessage addTask(@RequestBody Task customer){
        tasks.add(customer);
        return new ApiMessage("New task added to the list");
    }

    @PutMapping(path="/task/{index}")
    public ApiMessage updateTask(@RequestBody Task customer, @PathVariable int index){
        tasks.set(index,customer);
        return new ApiMessage("task updated");
    }
    @DeleteMapping(path="/task/{index}")
    public ApiMessage updateTask( @PathVariable int index){
        tasks.remove(index);
        return new ApiMessage("task removed");
    }
    @PostMapping (path="/task/status/{index}")
    public ApiMessage changeTask(@RequestBody String status, @PathVariable int index) {
        if (status.equals("done") || status.equals("not done")) {
            tasks.get(index).setStatus(status);
            return new ApiMessage("status changed");
        }else
            return new ApiMessage("status not changed");

    }
    @PostMapping(path="/task/search")
    public Task search(@RequestBody String title){
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getTitle().equals(title))
                return tasks.get(i);
        }
        return new Task();
    }
}
