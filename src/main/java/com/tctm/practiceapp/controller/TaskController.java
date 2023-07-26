package com.tctm.practiceapp.controller;

import com.tctm.practiceapp.entity.ManagerEntity;
import com.tctm.practiceapp.form.TaskForm;
import com.tctm.practiceapp.repository.ManagerRepository;
import com.tctm.practiceapp.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@AllArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;
    private final ManagerRepository managerRepository;
    @GetMapping("/taskForm")
    public String taskForm(@ModelAttribute("taskForm") TaskForm form, Model model){

        List<ManagerEntity> managers = managerRepository.getAllManagers();
        model.addAttribute("managers", managers);
        return "taskForm";
    }

    @PostMapping("/tasks")
    public String createTask(@Valid TaskForm taskForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasErrors()) { //
            List<String> errorList = new ArrayList<String>();
            for(ObjectError error : bindingResult.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "taskForm";
        }
        try {
            taskRepository.insert(taskForm.getTitle(), taskForm.getContent(), taskForm.getPeriod(), taskForm.getImportance(), taskForm.getManager_id());
            redirectAttributes.addFlashAttribute("message", "タスクを登録しました。");
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("error", "タスクの登録中にエラーが発生しました。");
        }
        return "redirect:/";
    }

    @GetMapping
    public String indexTasks(Model model, @ModelAttribute("message") String message, @ModelAttribute("error") String error){
        var taskList = taskRepository.findAll();
        model.addAttribute("taskList", taskList);
        model.addAttribute("message", message);
        model.addAttribute("error", error);
        return "index";
    }

    @GetMapping("/whereTasks")
    public String whereTask(Model model, String importance){
        var taskList = taskRepository.findWhere(importance);
        model.addAttribute("taskList", taskList);
        return "index";
    }

    @GetMapping("/tasks/{id}")
    public String taskShow(@PathVariable long id, Model model){
        List<ManagerEntity> managers = managerRepository.getAllManagers();
        model.addAttribute("managers", managers);
        var task = taskRepository.findById(id);
        model.addAttribute("task", task);
        return "show";
    }


    @PostMapping("/tasks/{id}/update")
    public String updateTask(@PathVariable long id, TaskForm taskForm, RedirectAttributes redirectAttributes){
        try{
            taskRepository.update(id, taskForm.getTitle(), taskForm.getContent(), taskForm.getPeriod(), taskForm.getImportance(), taskForm.getManager_id());
            redirectAttributes.addFlashAttribute("message", "タスクを更新しました。");
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("error", "タスクの更新中にエラーが発生しました");
        }
        return "redirect:/";
    }

    @PostMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable long id, RedirectAttributes redirectAttributes) {
        try{
            taskRepository.delete(id);
            redirectAttributes.addFlashAttribute("message", "タスクを削除しました。");
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("error", "タスクの削除中にエラーが発生しました。");
        }
        return "redirect:/";
    }



}
