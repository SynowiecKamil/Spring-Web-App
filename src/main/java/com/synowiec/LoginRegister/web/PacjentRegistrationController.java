package com.synowiec.LoginRegister.web;

import javax.validation.Valid;

import com.synowiec.LoginRegister.model.Pacjent;
import com.synowiec.LoginRegister.service.PacjentService;
import com.synowiec.LoginRegister.web.dto.PacjentRegistrationDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class PacjentRegistrationController {

    @Autowired
    private PacjentService pacjentService;

    @ModelAttribute("pacjent")
    public PacjentRegistrationDto pacjentRegistrationDto() {
        return new PacjentRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerPacjentAccount(@ModelAttribute("pacjent") @Valid PacjentRegistrationDto pacjentDto,
                                      BindingResult result){

        Pacjent existing = pacjentService.findByEmail(pacjentDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "Isntnieje już użytkownik o tym adresie email");
        }

        if (result.hasErrors()){
            return "registration";
        }

        pacjentService.save(pacjentDto);
        return "redirect:/registration?success";
    }

}