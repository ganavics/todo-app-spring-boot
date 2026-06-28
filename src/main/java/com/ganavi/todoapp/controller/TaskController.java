package com.ganavi.todoapp.controller;

import com.ganavi.todoapp.entity.Task;
import com.ganavi.todoapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        model.addAttribute("task", new Task());
        return "index";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task task) {
        taskService.saveTask(task);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }
    @GetMapping("/complete/{id}")
public String completeTask(@PathVariable Long id) {
    Task task = taskService.getTask(id);
    task.setCompleted(true);
    taskService.saveTask(task);
    return "redirect:/";
}
@GetMapping("/edit/{id}")
public String editTask(@PathVariable Long id, Model model) {
    Task task = taskService.getTask(id);
    model.addAttribute("task", task);
    model.addAttribute("tasks", taskService.getAllTasks());
    return "index";
}
}