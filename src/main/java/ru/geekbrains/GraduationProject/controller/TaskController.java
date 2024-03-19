package ru.geekbrains.GraduationProject.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.GraduationProject.model.Task;
import ru.geekbrains.GraduationProject.service.TaskService;

import java.util.List;
import java.util.UUID;

@Controller
@Getter
@Setter

public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    /**
     * метод получения списка всех задач
     */
    @GetMapping("/tasks") // localhost:8080/tasks
    public String viewTasks(Model model){
        List<Task> tasks = taskService.getALLTask();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    /**
     * метод добавления задачи в список
     * @param p
     * @param model
     * @return
     */
    @PostMapping("/tasks")
    public String addTask(Task p, Model model){
        taskService.addTask(p);
        var tasks = taskService.getALLTask();
        model.addAttribute("tasks", tasks);
        return "tasks.html";
    }
    /**
     * метод получения задачи по id
     * @param uuid
     * @param model
     * @return
     */
    @GetMapping("/tasks/{id}")
    public String getById(@PathVariable UUID uuid, Model model){
        Task task = taskService.getTask(uuid);
        if(task == null){
            return "errorPage";
        }
        model.addAttribute("task", task);
        return "task";
    }
    /**
     * метод получения отсортированного списка задач
     * @return отсортированный список задач
     */
    @GetMapping("/tasks/sorted")
    public List<Task> getTasksSortedByStatus(){
        return taskService.getTasksSortByStatus();

    }
    /**
     * метод изменения статуса задачи по id
     * @param uuid
     * @param status
     * @param model
     * @return
     */
    @PostMapping("/tasks/{Id}/status")
    public String updateTaskStatusById(@PathVariable UUID uuid, @RequestParam("status") Task.Status status, Model model) {
        Task existingTask = taskService.getTask(uuid);

        if (existingTask == null) {
            // Обработка случая, когда задача не найдена
            return "errorPage";
        }
        // Устанавливаем новый статус для задачи
        existingTask.setStatus(status);

        taskService.saveTask(existingTask);

        model.addAttribute("existingTask", existingTask);
        return "existingTask";
    }


}
