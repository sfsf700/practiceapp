package com.tctm.practiceapp.controller;

import com.tctm.practiceapp.form.ManagerForm;
import com.tctm.practiceapp.repository.ManagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class ManagerController {

    private final ManagerRepository managerRepository;

    @GetMapping("/managerForm")
    public String managerForm(@ModelAttribute("managerForm")ManagerForm form){
        return "managerForm";
    }

    @PostMapping("/managers")
    public String createManager(@Valid ManagerForm form, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if(result.hasErrors()){
            List<String> errorList = new ArrayList<String>();
            for(ObjectError error : result.getAllErrors()){
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "managerForm";
        }

        try {
            managerRepository.insert(form.getFirst_name(),
                    form.getLast_name(),
                    form.getPost_code(),
                    form.getPrefecture(),
                    form.getCity(),
                    form.getStreet(),
                    form.getOther(),
                    form.getPhone_number(),
                    form.getBirth_day(),
                    form.getGender(),
                    form.getGood_language());
            redirectAttributes.addFlashAttribute("message", "担当者を登録しました");
        } catch (DataAccessException e){
            redirectAttributes.addFlashAttribute("error","登録中にエラーが発生しました。");
        }
        return "redirect:/managerForm";
    }
}
