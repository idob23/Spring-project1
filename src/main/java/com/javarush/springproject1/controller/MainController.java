package com.javarush.springproject1.controller;

import com.javarush.springproject1.domain.Status;
import com.javarush.springproject1.domain.Task;
import com.javarush.springproject1.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/")
public class MainController {

    private final TaskService taskService;

    public MainController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public String showForm(Model model,
                           @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                           @RequestParam(value = "limit", required = false, defaultValue = "10") int limit
    ) {
        List<Task> tasks = taskService.getAll((page - 1) * limit, limit);
        model.addAttribute("tasks", tasks);
        model.addAttribute("current_page", page);
        int pagesCount = (int) Math.ceil(1.0 * taskService.getPagesCount() / limit);
        List<Integer> pageNumbers = IntStream.rangeClosed(1, pagesCount).boxed().collect(Collectors.toList());
        model.addAttribute("page_number", pageNumbers);

        return "index";
    }

    @RequestMapping(value = "edit/{id}")
    public String edit(Model model, @PathVariable int id) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "edit";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable int id) {
        Task taskById = taskService.getTaskById(id);
        if (taskById != null) {
            taskService.delete(id);
        }
        return showForm(model, 1, 10);
    }


    @RequestMapping("create")
    public String create(Model model,
                         @RequestParam(value = "description") String description,
                         @RequestParam(value = "status") Status status) {
        taskService.create(description, status);
        return showForm(model, 1, 10);
    }

    @RequestMapping("editChange")
    public String editChange(Model model,
                             @RequestParam(value = "description") String description,
                             @RequestParam(value = "status") Status status,
                             @RequestParam(value = "id") int id) {
        System.out.println(description);
        System.out.println(status);
        System.out.println(id);
        taskService.update(id, description, status);
        return showForm(model, 1, 10);
    }

}
