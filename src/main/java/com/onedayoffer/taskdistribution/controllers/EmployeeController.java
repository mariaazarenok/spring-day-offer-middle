package com.onedayoffer.taskdistribution.controllers;

import com.onedayoffer.taskdistribution.DTO.EmployeeDTO;
import com.onedayoffer.taskdistribution.DTO.TaskDTO;
import com.onedayoffer.taskdistribution.DTO.TaskStatus;
import com.onedayoffer.taskdistribution.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDTO> getEmployees(@RequestParam(required = false) String sort) {
        return employeeService.getEmployees(sort);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDTO getOneEmployee(@PathVariable Integer id) {
        return employeeService.getOneEmployee(id);
    }

    @GetMapping("{id}/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDTO> getTasksByEmployeeId(@PathVariable Integer id) {
        return employeeService.getTasksByEmployeeId(id);
    }

    @PatchMapping("{employeeId}/tasks/{taskId}/status")
    @ResponseStatus(HttpStatus.OK)
    public void changeTaskStatus(@PathVariable Integer employeeId,
                                 @PathVariable Integer taskId,
                                 @RequestParam("newStatus") TaskStatus status) {
        employeeService.changeTaskStatus(taskId, status);
    }


    @PostMapping("{employeeId}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public void postNewTask(@PathVariable Integer employeeId,
                            @RequestBody TaskDTO newTask) {
        employeeService.postNewTask(employeeId, newTask);

    }
}